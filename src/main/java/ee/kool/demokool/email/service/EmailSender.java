package ee.kool.demokool.email.service;

import ee.kool.demokool.email.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

  @Autowired
  JmsTemplate jmsTemplate;

  public void sendEmail(Email email){
    jmsTemplate.convertAndSend("mailbox", email);
  }


}
