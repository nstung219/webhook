package com.smac.webhook.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.smac.webhook.req.ServiceReceiverReq;

@RestController
@RequestMapping(value = "/api/v1/service/")
public class ServiceController {

	@RequestMapping(value = "receiver", method = RequestMethod.POST)
	public ResponseEntity<Object> test(@RequestBody JsonNode node) {
		System.out.println(node);
		return new ResponseEntity<Object>("called", HttpStatus.OK);
	}
}
