package common.toolkit.java.entity.entity.message;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import javax.jms.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class MsgClient implements MessageListener {

    private MQClient                mqClient      = null;
    private static MsgClient        _instance     = null;

    Logger                          LOGGER        = Logger.getLogger(MsgClient.class);

    // message handler
    private Map<String, MsgHandler> mapMsgHandler = null;
    static int                      index         = 0;
    private ExecutorService         es            = Executors.newCachedThreadPool(new ThreadFactory() {
                                                      public Thread newThread(Runnable r) {
                                                          return new Thread(r, "mq-message-handler-thread" + ++index);
                                                      }
                                                  });

    private MsgClient() {
        mqClient = null;
        mapMsgHandler = Collections.synchronizedMap(new HashMap<String, MsgHandler>());
    }

    private MsgClient(String uriBroker, String srcTopic) {
        mqClient = new MQClient(uriBroker, srcTopic, this);
        mapMsgHandler = Collections.synchronizedMap(new HashMap<String, MsgHandler>());
    }

    public static synchronized MsgClient createInstance() {
        if (_instance == null) {
            _instance = new MsgClient();
        }
        return _instance;
    }

    public static synchronized MsgClient createInstance(String uriBroker, String srcTopic) {
        if (_instance == null) {
            _instance = new MsgClient(uriBroker, srcTopic);
        }
        return _instance;
    }

    public void registerMsgHandler(String msgType, MsgHandler msgHandler) {
        mapMsgHandler.put(msgType, msgHandler);
    }

    public void createConnection(String uriBroker, String srcTopic) {
        if (mqClient == null) {
            mqClient = new MQClient(uriBroker, srcTopic, this);
        }
    }

    public void closeConnection() {
        if (mqClient != null)
            mqClient.destroy();
        if (null != es) {
            es.shutdownNow();
        }
    }

    private class MQClient {

        private ConnectionFactory connectionFactory = null;
        private Connection        mqConnection      = null;
        private Session           sessionListener   = null;
        private Session           sessionSender     = null;
        private MessageListener   messageListener   = null;
        private MessageConsumer   messageConsumer   = null;

        public MQClient(String uriBroker, String srcTopic, MessageListener listener) {
            this.messageListener = listener;
            createConnection(uriBroker, srcTopic);
        }

        private void createConnection(String uriBroker, String srcTopic) {
            try {
                connectionFactory = new ActiveMQConnectionFactory(uriBroker);
                mqConnection = connectionFactory.createConnection();
                mqConnection.start();
                sessionListener = mqConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                sessionSender = mqConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                messageConsumer = sessionListener.createConsumer(sessionListener.createTopic(srcTopic));
                messageConsumer.setMessageListener(messageListener);
            } catch (JMSException e) {
                LOGGER.error("", e);
            }
        }

        public void destroy() {
            if (sessionListener != null) {
                try {
                    sessionListener.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if (sessionSender != null) {
                try {
                    sessionSender.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if (mqConnection != null) {
                try {
                    mqConnection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }

        public void sendMessage(String dstTopic, String msgType, Map<String, String> valueMap) {
            if (sessionSender == null)
                return;
            try {
                MessageProducer messageProducer = sessionSender.createProducer(sessionListener.createTopic(dstTopic));
                messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
                MapMessage mapMessage = sessionSender.createMapMessage();
                mapMessage.setJMSType(msgType);
                if (valueMap != null) {
                    for (Map.Entry<String, String> entry : valueMap.entrySet()) {
                        mapMessage.setString(entry.getKey(), entry.getValue());
                    }
                }
                messageProducer.send(mapMessage);
            } catch (JMSException e) {
                LOGGER.error(e.getLocalizedMessage());
            }
        }
    }

    public void onMessage(Message message) {
        if (null == message || !(message instanceof MapMessage))
            return;
        es.submit(new MessageHandler((MapMessage) message));
    }

    class MessageHandler implements Runnable {

        private MapMessage message;

        public MessageHandler(MapMessage message) {
            this.message = message;
        }

        public void run() {
            if (null == message)
                return;

            try {
                Destination topic = message.getJMSDestination();
                CmuMessage cmuMessage = new CmuMessage(topic.toString(), message.getJMSType());
                LOGGER.info("receive message:" + cmuMessage);
                if (mapMsgHandler != null && (!mapMsgHandler.isEmpty())) {
                    MsgHandler parser = mapMsgHandler.get(cmuMessage.getMsgType());
                    if (parser != null) {
                        cmuMessage.setValueMap(parser.parse(message));
                        LOGGER.info("parse message:" + cmuMessage.getValueMap());
                        parser.handle(cmuMessage);
                    }
                }
            } catch (Exception e) {
                LOGGER.error("", e);
            }
        }

    }

    public void sendMessage(CmuMessage msg) {
        if (msg == null || mqClient == null)
            return;
        LOGGER.info("Send CmuMessage {" + msg.toString() + "} ");
        mqClient.sendMessage(msg.getTopic(), msg.getMsgType(), msg.getValueMap());
    }
}