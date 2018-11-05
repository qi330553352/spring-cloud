package common.toolkit.java.entity.entity.message;

import java.util.Map;

public class CmuMessage {
	private Map<String, String> valueMap = null;
	private String msgType = null;
	private String topic = null;

	public CmuMessage(String topic, String msgType) {
		this.topic = topic;
		this.msgType = msgType;
	}

	public String getTopic() {
		return topic;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public Map<String, String> getValueMap() {
		return valueMap;
	}

	public void setValueMap(Map<String, String> valueMap) {
		this.valueMap = valueMap;
	}

	public String toString() {
		String retString = "CMU Message Type[" + msgType + "] Topic[" + topic
				+ "] Value[" + (valueMap == null ? "" : valueMap.toString())
				+ "]";
		return retString;
	}
}
