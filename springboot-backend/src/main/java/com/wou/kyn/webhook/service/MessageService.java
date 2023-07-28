package com.wou.kyn.webhook.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.wou.kyn.payload.response.ApiResponse;
import com.wou.kyn.webhook.pojo.SlackMessage;
import com.wou.kyn.webhook.request.MessageRequest;

@Service
public class MessageService {
	@Value("${app.webhook.slack}")
	private String SLACK_WEBHOOK_URL;
	
	/**
	 * Sends a message to Slack using the provided MessageRequest object.
	 *
	 * @param messageRequest The MessageRequest object containing the message details.
	 * @return 
	 * @throws JsonProcessingException If there is an issue while processing the JSON data.
	 */
	public ApiResponse sendMessageToSlack(MessageRequest messageRequest) {
		try {
			// Extract name, email and message from the MessageRequest
			String name = messageRequest.getName();
			String email = messageRequest.getEmail();
			String text = messageRequest.getText();
			String fullText = text + " - sent from " + name + " (" + email + ")";
			
		    // Create a SlackMessage object and set the modified text
			SlackMessage message = new SlackMessage();
			message.setText(fullText);

		    // Prepare headers with content type as JSON
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

		    // Convert the SlackMessage object to JSON
			ObjectMapper objectMapper = new ObjectMapper();
			String messageJson = objectMapper.writeValueAsString(message);

			// Create an HttpEntity with the JSON payload and headers
			HttpEntity<String> entity = new HttpEntity<>(messageJson, headers);

		    // Use RestTemplate to send the HTTP POST request to the Slack webhook URL
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> response = restTemplate.exchange(SLACK_WEBHOOK_URL, HttpMethod.POST, entity, String.class);

			if (response.getStatusCode().is2xxSuccessful()) {
                // Success response
                return new ApiResponse(true, "Message successfully sent");
            } else {
                // Error response
                return new ApiResponse(false, "Failed to send message to Slack channel");
            }
		} catch (JsonProcessingException e) {
			// Handle JSON processing exception
            return new ApiResponse(false, "Error processing JSON");
		}
	}
}
