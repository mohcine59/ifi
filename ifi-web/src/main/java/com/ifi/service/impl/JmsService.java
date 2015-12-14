package com.ifi.service.impl;

import org.springframework.stereotype.Service;

@Service
public class JmsService {

	// @Autowired
	// private JmsTemplate jmsTemplate;
	//
	// public void send(final Destination dest, final String msg) {
	//
	// this.jmsTemplate.send(dest, new MessageCreator() {
	// @Override
	// public Message createMessage(final Session session) throws JMSException {
	// final Message message = session.createTextMessage(msg);
	// return message;
	// }
	// });
	// }
	//
	// public void createQueue(final String topicName) {
	// final ConnectionFactory connectionFactory =
	// this.jmsTemplate.getConnectionFactory();
	// Connection connection;
	// try {
	// connection = connectionFactory.createConnection();
	// final Session session = connection.createSession(false,
	// Session.AUTO_ACKNOWLEDGE);
	// connection.start();
	// session.createTopic(topicName);
	//
	// session.close();
	// connection.close();
	// } catch (final JMSException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// public void consumeMessage(final String topicName) {
	// final ConnectionFactory connectionFactory =
	// this.jmsTemplate.getConnectionFactory();
	// Connection connection;
	// try {
	// connection = connectionFactory.createConnection();
	// final Session session = connection.createSession(false,
	// Session.AUTO_ACKNOWLEDGE);
	// connection.start();
	// final MessageConsumer consumer = session.createConsumer(new
	// ActiveMQTopic(topicName));
	// // consumer.setMessageListener(this);
	// consumer.close();
	// session.close();
	// connection.close();
	// } catch (final JMSException e) {
	// e.printStackTrace();
	// }
	//
	// }
}