package pl.kuziow.internetshopmockup.service;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

class EmailServiceTest {

    @Test
    void mockSendNotification_ValidEmail_ShouldPrintNotificationAndLogInfo() {
        EmailService emailService = new EmailService();
        String customerEmailAddress = "test@example.com";
        String subject = "Test Subject";
        String message = "Test Message";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        emailService.mockSendNotification(customerEmailAddress, subject, message);

        String consoleOutput = outputStream.toString();
        assertThat(consoleOutput).contains("Mock Email Notification")
                .contains("Recipient: " + customerEmailAddress)
                .contains("Subject: " + subject)
                .contains("Message: " + message);
    }
}
