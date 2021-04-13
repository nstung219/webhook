package com.smac.webhook.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.smac.webhook.business.WebhookBusiness;
import com.smac.webhook.req.WebhookRegisterReq;

@RestController
@RequestMapping("/api/v1/webhook/")
public class WebhooksController {
	
    @Autowired
    private WebhookBusiness webhookBusiness;

    @GetMapping("subscription")
    public SseEmitter subscribe() throws IOException {

        SseEmitter emitter = new SseEmitter();

        webhookBusiness.add(emitter);
        emitter.onCompletion(() -> webhookBusiness.remove(emitter));

        return emitter;        
    }

    @GetMapping("test/{data}")
    public String notify(@PathVariable String data) {

    	webhookBusiness.getNotiList().forEach((SseEmitter emitter) -> {
            try {
                emitter.send(data, MediaType.APPLICATION_JSON);
            } catch (IOException e) {
                emitter.complete();
                webhookBusiness.remove(emitter);
                e.printStackTrace();
            }
        });
    	
        return data;
    }
    
    @PostMapping("register")
    public void register(@RequestBody WebhookRegisterReq req) {
    	
    }
    
    @PostMapping("/publish")
    public String publishMessage(@RequestBody String message) {
    	return webhookBusiness.publishMessage(message);
    }
    
}
