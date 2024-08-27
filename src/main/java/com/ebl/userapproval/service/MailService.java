package com.ebl.userapproval.service;

import com.ebl.userapproval.model.Mail;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailService {
    @Qualifier("templateEngine")
    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String username;

    private final JavaMailSenderImpl javaMailSenderImpl;

    @Autowired
    public MailService(JavaMailSenderImpl javaMailSenderImpl) {
        this.javaMailSenderImpl = javaMailSenderImpl;
    }

    @Async
    public void sendMail(Mail mail, String mailTemplate) {
        try {
            MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setFrom(username);
            mimeMessageHelper.setTo(mail.getTo());
            if (mail.getCc() != null) {
                mimeMessageHelper.setCc(mail.getCc());
            }
            mimeMessageHelper.setSubject(mail.getSubject());
            mimeMessageHelper.setText(build(mail, mailTemplate), true);
            javaMailSenderImpl.send(mimeMessage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String build(Mail mail, String mailTemplate) {
        Context context = new Context();
        context.setVariable("mail", mail);
        return templateEngine.process(mailTemplate, context);
    }
}
