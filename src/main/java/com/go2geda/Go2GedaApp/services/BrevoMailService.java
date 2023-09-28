package com.go2geda.Go2GedaApp.services;

import com.go2geda.Go2GedaApp.configs.AppConfig;
import com.go2geda.Go2GedaApp.dtos.request.EmailSenderRequest;
import com.go2geda.Go2GedaApp.dtos.response.OkResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@AllArgsConstructor
@Service
public class BrevoMailService implements MailService{
    private final AppConfig appConfig;

    @Override
    public OkResponse send(EmailSenderRequest emailSenderRequest) {
//        String brevoMailAddress = "https://api.brevo.com/v3/smtp/email";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("api-key", appConfig.getMailApiKey());
        headers.set("Content-Type", "application/json");
        HttpEntity<EmailSenderRequest> request =
                new HttpEntity<>(emailSenderRequest, headers);

        ResponseEntity<OkResponse> response =
                restTemplate.postForEntity(appConfig.getBrevoMailAddress(), request, OkResponse.class);
        OkResponse emailNotificationResponse = response.getBody();
        return emailNotificationResponse;
    }
}
