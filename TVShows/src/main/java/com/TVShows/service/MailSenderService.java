package com.TVShows.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Service
public class MailSenderService {

    @Value("${mailgun.api-key}")
    private String apiKey;
    @Value("${mailgun.domain}")
    private String domain;
    @Value("${mailgun.from}")
    private String from;
    private final RestTemplate restTemplate;

    @Autowired
    public MailSenderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendEmail(String to, String subject, String htmlContent) {
        // Build the Mailgun API URL
        String apiUrl = "https://api.mailgun.net/v3/" + domain + "/messages";

        // Create the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("api", apiKey);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Create the request body
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("from", from);
        body.add("to", to);
        body.add("subject", subject);
        body.add("html", htmlContent);

        // Create the HTTP entity with headers and body
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        // Send the POST request to the Mailgun API
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiUrl, requestEntity, String.class);

        // Check the response for success or handle errors
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            System.out.println("Email sent successfully!");
        } else {
            System.err.println("Failed to send email. Response: " + responseEntity.getBody());
        }
    }
}

