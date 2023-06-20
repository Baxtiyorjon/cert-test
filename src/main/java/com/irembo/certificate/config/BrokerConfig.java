package com.irembo.certificate.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.irembo.certificate.config.properties.AppProperties;
import com.irembo.certificate.listener.MessagingListener;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class BrokerConfig {
  private final AppProperties appProperties;

  @Bean
  public Queue queue() {
    return new Queue(appProperties.getQueueName(), false);
  }

//  @Bean
//  public TopicExchange exchange() {
//    return new TopicExchange(appProperties.getTopicExchangeName());
//  }
//
//  @Bean
//  public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
//      MessageListenerAdapter listenerAdapter) {
//    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//    container.setConnectionFactory(connectionFactory);
//    container.setQueueNames(appProperties.getQueueName());
//    container.setMessageListener(listenerAdapter);
//    return container;
//  }
//
//  @Bean
//  public MessageListenerAdapter listenerAdapter(MessagingListener receiver) {
//    return new MessageListenerAdapter(receiver, "receiveMessage");
//  }

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }
}
