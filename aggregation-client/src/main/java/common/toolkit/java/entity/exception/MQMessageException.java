/**
 * Project Name:tailaike-sms
 * File Name:VnidException.java
 * Package Name:com.tailaike.vnid.exception
 * Date:2013年10月25日 下午3:48:39
 * Copyright (c) 2013, www.wisdombud.com All Rights Reserved.
 */

package common.toolkit.java.entity.exception;

/**
 * ClassName: VnidException. <br/>
 * Function: TODO <br/>
 * date: 2013年10月25日 下午3:48:39 <br/>
 * @author ghlin
 * @version
 * @since JDK 1.6
 */
public class MQMessageException extends Exception {
	
	private static final long serialVersionUID = -282872996831069870L;
	public MQMessageException(String msg) {
        super(msg);
    }

    public MQMessageException(String msg, Throwable e) {
        super(msg, e);
    }
}
