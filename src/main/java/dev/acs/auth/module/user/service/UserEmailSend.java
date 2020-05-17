package dev.acs.auth.module.user.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import dev.acs.auth.module.user.persistence.User;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserEmailSend {

	@Async("EmailSendExecutor")
	public void sendRegistryConfirmationEmail(User user) {
	    try {Thread.sleep(5000);} catch (InterruptedException e) {}
		log.debug("%s Sending confirmation email: %s", Thread.currentThread().getName(), user.getEmail());
	}
	
}
