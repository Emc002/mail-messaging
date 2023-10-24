package com.lawencon.mailservice.service.impl;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import com.lawencon.mailservice.service.ThymeleafService;

import java.util.Map;

@Service
public class ThymeleafServiceImpl implements ThymeleafService {
	
    private static final String MAIL_TEMPLATE_BASE_NAME = "mail/MailMessages";

    private static final String MAIL_TEMPLATE_PREFIX = "/templates/";

    private static final String MAIL_TEMPLATE_SUFFIX = "";

    private static final String UTF_8 = "UTF-8";

    private static TemplateEngine templateEngine;

    private static TemplateEngine emailHtmlTemplateEngine() {
        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(htmlTemplateResolver());
        templateEngine.setTemplateEngineMessageSource(emailMessageSource());

        return templateEngine;
    }
    
    private static TemplateEngine emailStringTemplateEngine() {
        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(htmlStringResolver());
        templateEngine.setTemplateEngineMessageSource(emailMessageSource());

        return templateEngine;
    }

    private static ITemplateResolver htmlTemplateResolver() {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix(MAIL_TEMPLATE_PREFIX);
        templateResolver.setSuffix(MAIL_TEMPLATE_SUFFIX);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding(UTF_8);
        templateResolver.setCacheable(false);

        return templateResolver;
    }
    
    private static ITemplateResolver htmlStringResolver() {
  	  final StringTemplateResolver templateResolver = new StringTemplateResolver();
       templateResolver.setTemplateMode(TemplateMode.HTML);
       templateResolver.setCacheable(false);

      return templateResolver;
  }

    private static ResourceBundleMessageSource emailMessageSource() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(MAIL_TEMPLATE_BASE_NAME);
        return messageSource;
    }

    @Override
    public String createContent(String template, Map<String, Object> variables) {
    	templateEngine = emailHtmlTemplateEngine();
        final Context context = new Context();
        context.setVariables(variables);
        return templateEngine.process(template, context);
    }

	@Override
	public String initContent(String template, Map<String, Object> variables) {
    	templateEngine = emailStringTemplateEngine();
        final Context context = new Context();
        context.setVariables(variables);
        String htmlParse = convertToThymeleafSyntax(template);
        return templateEngine.process(htmlParse, context);
	}
	
	public String convertToThymeleafSyntax(String html) {
		html = html.replaceAll("\\{\\{", "<span th:text=\"\\$\\{");
		html = html.replaceAll("\\}\\}", "\\}\\\"></span>");
		return html;
	}
}
