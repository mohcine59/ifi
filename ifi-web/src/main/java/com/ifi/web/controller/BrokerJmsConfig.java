package com.ifi.web.controller;

import org.springframework.context.annotation.Configuration;

@Configuration
public class BrokerJmsConfig {
	// @Bean(initMethod = "start", destroyMethod = "stop")
	// public BrokerService broker() throws Exception {
	// final BrokerService broker = new BrokerService();
	// broker.addConnector("ws://localhost:61614");
	// broker.setPersistent(false);
	// broker.setShutdownHooks(Collections.<Runnable> singletonList(new
	// SpringContextHook()));
	//
	// // final ActiveMQTopic topic = new ActiveMQTopic("jms.topic.tweet");
	// // broker.setDestinations(new ActiveMQDestination[] { topic });
	//
	// final ManagementContext managementContext = new ManagementContext();
	// managementContext.setCreateConnector(true);
	// broker.setManagementContext(managementContext);
	//
	// return broker;
	// }
}
