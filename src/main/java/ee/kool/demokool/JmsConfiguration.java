package ee.kool.demokool;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;



import javax.jms.ConnectionFactory;

@Configuration
@EnableJms
public class JmsConfiguration {


  @Value( "${spring.activemq.broker-url}" )
  private String brokerUrl;

  @Value( "${spring.activemq.user}" )
  private String user;

  @Value( "${spring.activemq.password}" )
  private String password;

  @Bean
  public ActiveMQConnectionFactory connectionFactory() {
    if ("".equals(user)) {
      return new ActiveMQConnectionFactory(brokerUrl);
    }
    return new ActiveMQConnectionFactory(user, password, brokerUrl);
  }



  @Bean
  public JmsListenerContainerFactory<?> jmsTopicFactory(ConnectionFactory connectionFactory,
                                                     DefaultJmsListenerContainerFactoryConfigurer configurer) {
    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    configurer.configure(factory, connectionFactory);
    factory.setPubSubDomain(true);
    factory.setSubscriptionDurable(true);
    factory.setClientId("MovieReceiver");
    return factory;
  }

  @Bean
  public JmsListenerContainerFactory<?> jmsQueueFactory(ConnectionFactory connectionFactory,
                                                  DefaultJmsListenerContainerFactoryConfigurer configurer) {
    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    // This provides all boot's default to this factory, including the message converter
    configurer.configure(factory, connectionFactory);
    // You could still override some of Boot's default if necessary.
    return factory;
  }

  @Bean
  @Primary
  public JmsTemplate jmsTemplateQueue() {
    JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
    jmsTemplate.setMessageConverter(jacksonJmsMessageConverter());
    jmsTemplate.setPubSubDomain(false);
    return jmsTemplate;
  }


  @Bean
  public JmsTemplate jmsTemplateTopic() {
    JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
    jmsTemplate.setMessageConverter(jacksonJmsMessageConverter());
    jmsTemplate.setPubSubDomain(true);
    return jmsTemplate;
  }

  @Bean // Serialize message content to json using TextMessage
  public MessageConverter jacksonJmsMessageConverter() {
    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
    converter.setTargetType(MessageType.TEXT);
    converter.setTypeIdPropertyName("_type");
    return converter;
  }

}
