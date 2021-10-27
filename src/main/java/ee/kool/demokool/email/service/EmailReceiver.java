package ee.kool.demokool.email.service;

import ee.kool.demokool.email.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailReceiver {

  @JmsListener(destination = "mailbox", containerFactory = "jmsQueueFactory")
  public void receiveMessage(Email email) {
    log.info("Received <" + email + ">");
  }

}
