package ee.kool.demokool.email.controller;

import ee.kool.demokool.email.Email;
import ee.kool.demokool.email.service.EmailSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class EmailController {

  @Autowired
  EmailSender emailSender;

  @PostMapping(path = "/v1/emails",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
  public void create(@RequestBody Email newUser) {
    emailSender.sendEmail(newUser);

  }

}
