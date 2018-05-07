package com.example.rciot.web;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;

@RestController
public class SimpleController {
	@Autowired
	private LineMessagingClient lineMessagingClient;
    @GetMapping("/push")
    public String pushSakata() {

    	TextMessage message = new TextMessage("");
    	PushMessage pm = new PushMessage("",message);

    	try {
			BotApiResponse apiResponse = lineMessagingClient
					.pushMessage(pm).get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
    	return "";
    }
}