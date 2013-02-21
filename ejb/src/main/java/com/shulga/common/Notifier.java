package com.shulga.common;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import com.shulga.ejb.CommonMDB;

public class Notifier {
	private final static Logger LOGGER = Logger.getLogger(CommonMDB.class
			.toString());
	
	@Resource(mappedName="shulga/jms/queue/factory")
	private ConnectionFactory factory;
	@Resource(mappedName="shulga/jms/queue/demo_queue")
	private Queue dest;
	Connection connection;

	
	public void sendMessage(String text){
		Session session;
		try {
			connection = factory.createConnection();
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
			MessageProducer producer = session.createProducer(dest);
			connection.start();
			Message message = session.createTextMessage();
			message.setStringProperty("name", text);
			producer.send(dest, message);
			producer.close();
			LOGGER.info("Jms message sent");
		} catch (JMSException e) {
			e.printStackTrace();
		}finally{
			if(connection!=null)
				try {
					connection.close();
					LOGGER.info("Connection closed");
				} catch (JMSException e) {
					LOGGER.info( "Coonection closing error");
				}
		}
	}
}
