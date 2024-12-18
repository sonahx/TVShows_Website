package com.TVShows.mail;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.transactional.SendContact;
import com.mailjet.client.transactional.SendEmailsRequest;
import com.mailjet.client.transactional.TrackOpens;
import com.mailjet.client.transactional.TransactionalEmail;
import com.mailjet.client.transactional.response.SendEmailsResponse;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailSender {

    @Value("${mj.apiKey}")
    private String apiKey;
    @Value("${mj.secretKey}")
    private String secretKey;
    @Value("${mj.sender}")
    private String sender;
    private MailjetClient client;

    @PostConstruct
    private void initializeMailClient() {
        ClientOptions options = ClientOptions.builder()
                .apiKey(apiKey)
                .apiSecretKey(secretKey)
                .build();

        client = new MailjetClient(options);
    }

    public void sendEmail(String to, String subject, String htmlContent) {
        TransactionalEmail message1 = TransactionalEmail
                .builder()
                .to(new SendContact(to))
                .from(new SendContact(sender))
                .htmlPart(htmlContent)
                .subject(subject)
                .trackOpens(TrackOpens.ENABLED)
                .build();

        SendEmailsRequest request = SendEmailsRequest
                .builder()
                .message(message1)
                .build();

        try {
            SendEmailsResponse response = request.sendWith(client);
        } catch (MailjetException exception) {
            log.error("There was an error sending email: {}", exception.getMessage());
        }
    }
}
