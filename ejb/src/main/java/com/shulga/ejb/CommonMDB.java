package com.shulga.ejb;


import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.shulga.annotation.WstLogger;


@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "shulga/jms/queue/demo_queue"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")})
public class CommonMDB implements MessageListener {
	
	@Inject
	@WstLogger
	private Logger logger;

	@Override
	public void onMessage(Message rcvMessage) {
		TextMessage msg = null;
		logger.info("Received delevered to MDB");
		try {
			if (rcvMessage instanceof TextMessage) {
				msg = (TextMessage) rcvMessage;
				logger.info("Received Message: " + msg.getText());
			} else {
				logger.warning("Message of wrong type: "
						+ rcvMessage.getClass().getName());
			}
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

}
