package com.cityfault.serviceimpl;

import com.cityfault.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    public MailContentBuilder mailContentBuilder;

    @Autowired
    private MessageSource messageSource;

    public void prepareAndSend(String recipient, String department, String status, String priority, String description) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(recipient);
            messageHelper.setSubject(messageSource.getMessage("email.subject",null, LocaleContextHolder.getLocale()));
            String content = mailContentBuilder.build(messageSource.getMessage("email.content",null, LocaleContextHolder.getLocale()), department, status, priority, description);
            messageHelper.setText(content, true);
        };
        try {
            emailSender.send(messagePreparator);
        } catch (MailException e) {
            // runtime exception; compiler will not force you to handle it
        }
    }
}