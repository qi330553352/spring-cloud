package common.toolkit.java.entity.entity.message;

import javax.jms.MapMessage;
import java.util.Map;

public interface MsgHandler {
	// parse the MapMessage to CmuMessage
	public Map<String, String> parse(MapMessage mapMessage);

	// handle the message
	public boolean handle(CmuMessage cmuMessage);
}