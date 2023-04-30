package com.TVShows.service;

import com.TVShows.repo.EmailSender;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderService implements EmailSender {

    private final JavaMailSender mailSender;
    private final static  Logger logger = LoggerFactory.getLogger(EmailSenderService.class);

    @Override
    @Async
    public void send(String to, String email) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setFrom("whatepisode@gmail.com");
            logger.info("Sending email to {}", to);
            mailSender.send(mimeMessage);
        } catch (MessagingException exception) {
            logger.error("Failed to send email, {}", exception.getMessage());
            throw new IllegalStateException("Failed to send email");
        }
    }
}
