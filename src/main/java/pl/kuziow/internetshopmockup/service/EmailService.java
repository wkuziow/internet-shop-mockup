package pl.kuziow.internetshopmockup.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.lang.System.*;

@Service
@Slf4j
public class EmailService {

    public void mockSendNotification(String customerEmailAddress, String subject, String message) {
        out.println("Mock Email Notification");
        out.println("Recipient: " + customerEmailAddress);
        out.println("Subject: " + subject);
        out.println("Message: " + message);
        log.info("Email notification sent successfully. Customer Email Address: [{}], Subject: [{}], Message: [{}]",
                customerEmailAddress, subject, message);
    }
}
