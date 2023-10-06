package com.go2geda.Go2GedaApp.utils;

import com.go2geda.Go2GedaApp.exceptions.Go2gedaBaseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.go2geda.Go2GedaApp.configs.AppConfig.ACTIVATE_ACCOUNT_PATH;
import static com.go2geda.Go2GedaApp.utils.JWUtils.generateVerificationToken;

public class AppUtils {
    public static final String APP_NAME = "Go2Geda";
    public static final String APP_EMAIL = "go2geda@mail.com";
    public static final String EMPTY_STRING = "";

    public static final String UPLOAD_SUCCESSFUL = "UPLOAD_SUCCESSFUL";

    public static final String VERIFICATION_SUCCESSFUL = "VERIFICATION_SUCCESSFUL";
    private static final String MAIL_TEMPLATE_LOCATION = "C:\\Users\\USER\\IdeaProjects\\SpringProjects\\newGo2gedaBackend2\\src\\main\\resources\\templates\\emailHtml.html";

    private static final String MAIL_TEMPLATE_LOCATION_COMMUTER = "C:\\Users\\USER\\IdeaProjects\\SpringProjects\\newGo2gedaBackend2\\src\\main\\resources\\templates\\commuterEmail.html";

    public static final Integer STATUS_CODE_OK = 200;
    public static final Integer STATUS_CODE_CREATED = 201;
    public static final String PAYSTACK_INIT = "https://api.paystack.co/plan";
    public static final String PAYSTACK_INITIALIZE_PAY = "https://api.paystack.co/transaction/initialize";
    public static final String PAYSTACK_VERIFY = "https://api.paystack.co/transaction/verify/";

    public static String getMailTemplate() {
        Path templateLocation = Paths.get(MAIL_TEMPLATE_LOCATION);
        try {
            List<String> fileContents = Files.readAllLines(templateLocation);
            String template = String.join(EMPTY_STRING, fileContents);
            return template;
        } catch (IOException exception) {
            throw new Go2gedaBaseException(exception.getMessage());
        }
    }
    public static String getMailTemplateCommuter() {
        Path templateLocation = Paths.get(MAIL_TEMPLATE_LOCATION_COMMUTER);
        try {
            List<String> fileContents = Files.readAllLines(templateLocation);
            String template = String.join(EMPTY_STRING, fileContents);
            return template;
        } catch (IOException exception) {
            throw new Go2gedaBaseException(exception.getMessage());
        }}
    public static String generateActivationLink(String baseUrl, String email){
        String token = generateVerificationToken(email);
        return baseUrl+ACTIVATE_ACCOUNT_PATH+token;
    }

    public static List<String> getPublicPaths(){
        return List.of("/api/v1/user", "/login");
    }


}
