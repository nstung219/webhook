package com.smac.webhook.business;

import java.util.List;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface WebhookBusiness {

    public boolean add(SseEmitter sseEmitter);

    public boolean remove(SseEmitter sseEmitter);

    public List<SseEmitter> getNotiList();
    
    public String publishMessage(String message);
}
