package com.wou.kyn.webhook.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.wou.kyn.payload.response.ApiResponse;
import com.wou.kyn.webhook.request.MessageRequest;
import com.wou.kyn.webhook.service.MessageService;

@RestController
@RequestMapping("/api/message")
public class MessageInterceptorController {
	@Autowired
	private MessageService messageService;
	
	/**
	 * Sends a message using the provided MessageRequest object.
	 *
	 * @param messageRequest the MessageRequest object containing the message details.
	 * @throws JsonProcessingException if there is an issue while processing the JSON data.
	 */
	@PostMapping(path = "/send", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> sendMessage(@Valid @RequestBody MessageRequest messageRequest) {
		ApiResponse response = messageService.sendMessageToSlack(messageRequest);

		if (response.getSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
}
