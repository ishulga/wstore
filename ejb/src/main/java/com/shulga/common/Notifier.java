package com.shulga.common;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import com.shulga.annotation.WstLogger;



public class Notifier {
	
	@Resource(mappedName="shulga/jms/queue/factory")
	private ConnectionFactory factory;
	@Resource(mappedName="shulga/jms/queue/demo_queue")
	private Destination dest;
//	@Inject
//	@WstLogger
//	private Logger logger;
	private Connection connection;

	
	public void sendMessage(String text){
		Session session;
		try {
			connection = factory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer producer = session.createProducer(dest);
			connection.start();
			Message message = session.createTextMessage();
			message.setStringProperty("name", text);
			producer.send(dest, message);
//			logger.info( "JMS message sent");
			producer.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}finally{
			if(connection!=null)
				try {
					connection.close();
				} catch (JMSException e) {
//					logger.info( "Could not close connection");
				}
		}
	}
}
