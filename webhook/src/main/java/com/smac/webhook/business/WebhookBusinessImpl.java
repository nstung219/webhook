package com.smac.webhook.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smac.webhook.repo.ReceiverDataRepo;
import com.smac.webhook.repo.ReceiverLinkRepo;
import com.smac.webhook.repo.entity.ReceiverData;
import com.smac.webhook.repo.entity.ReceiverLink;

@Service
public class WebhookBusinessImpl implements WebhookBusiness{
	
    private final List<SseEmitter> notiList = new ArrayList<SseEmitter>();
    
    @Autowired
    private ReceiverLinkRepo receiverLinkRepo;
    
    @Autowired
    private ReceiverDataRepo receiverDataRepo;

    @Override
    public boolean add(SseEmitter sseEmitter) {
        return notiList.add(sseEmitter);
    }

    @Override
    public boolean remove(SseEmitter sseEmitter) {
        return notiList.remove(sseEmitter);
    }

    @Override
    public List<SseEmitter> getNotiList() {
        return notiList;
    }

	@Override
	public String publishMessage(String message) {
		
		RestTemplate restTemplate = new RestTemplate();
		List<ReceiverLink> lst = receiverLinkRepo.findAll();
		
		lst.forEach(l -> {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			try {
				JsonNode justifiedMessage = justifyDataForm(l.getCustomerId(), message);

				HttpEntity<String> entity = new HttpEntity<String>(justifiedMessage.toString(),headers);
				restTemplate.postForObject(l.getWaiter(), entity, String.class);
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} 
		});
		
		return "Published";
	}
	
	@SuppressWarnings("unchecked")
	public JsonNode justifyDataForm(String customerId, String message) throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(message);
		
		List<ReceiverData> lst = receiverDataRepo.getListByCusId(customerId);
		
		Map<String, String> mapResponse = new HashMap<>();
		
		for (ReceiverData d: lst) {
			mapResponse.put(d.getTargetName(), d.getAlterName());
		}
		
		Map<String, Object> preConvert = new HashMap<>();
		Map<String, Object> result = objectMapper.convertValue(jsonNode, Map.class);
		
		for(Map.Entry<String,Object> entry : result.entrySet()) {
			if (mapResponse.containsKey(entry.getKey())) {
				if (mapResponse.get(entry.getKey()) != null && !mapResponse.get(entry.getKey()).isEmpty()) {
					preConvert.put(mapResponse.get(entry.getKey()), entry.getValue());
				} 
			} else {
				preConvert.put(entry.getKey(), entry.getValue());
			}
		}
		
		String newStringRes = objectMapper.writeValueAsString(preConvert);
		JsonNode newNode = objectMapper.readTree(newStringRes);
		
		return newNode;
	}

}
