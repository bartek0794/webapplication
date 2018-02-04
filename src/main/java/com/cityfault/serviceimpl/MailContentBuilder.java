package com.cityfault.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailContentBuilder {

    private TemplateEngine templateEngine;
    @Autowired
    public MailContentBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String build(String header, String department, String status, String priority, String description, String title) {
        Context context = new Context();
        context.setVariable("header", header);
        context.setVariable("department", department);
        context.setVariable("status", status);
        context.setVariable("priority", priority);
        context.setVariable("description", description);
        context.setVariable("title", title);
        return templateEngine.process("mailTemplate", context);
    }
}