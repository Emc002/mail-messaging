package com.lawencon.mailservice.service;

import java.util.Map;

public interface ThymeleafService {

    String createContent(String template, Map<String, Object> variables);
    String initContent(String template, Map<String, Object> variables);
}
