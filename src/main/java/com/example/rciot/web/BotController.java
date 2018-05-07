package com.example.rciot.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@LineMessageHandler
public class BotController {
	@Autowired
	private LineMessagingClient lineMessagingClient;


	/**
	 * @param event テキストが送られたイベント
	 * @return
	 */
    @EventMapping
    public void handleTextMessageEvent(MessageEvent<TextMessageContent> event) throws Exception {
        TextMessageContent message = event.getMessage();
        handleTextContent(event.getReplyToken(), event, message);
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }

    /* ここで返すリプライの整形をする*/
    private void handleTextContent(String replyToken, Event event, TextMessageContent content)
    	throws Exception {
    	this.replyText(replyToken, event.getSource().getUserId());
    }

    /* 渡ってきたメッセージをStringからTextMessageに変換させる */
    private void replyText(@NonNull String replyToken, @NonNull String message) {
        if (replyToken.isEmpty()) {
            throw new IllegalArgumentException("replyToken must not be empty");
        }
        /* 二個送るテスト */
        List<Message> li = new ArrayList<>();
        li.add(new TextMessage("坂田氏が勉強中です。"));
        li.add(new TextMessage("貴方のUserIdをpushします。"));
        li.add(new TextMessage(message));
        this.reply(replyToken, li);
    }

    /* リプライを送る 送るメッセージが一つの場合*/
    private void reply(@NonNull String replyToken, @NonNull Message message) {
        reply(replyToken, Collections.singletonList(message));
    }

    /* リプライを送る */
    private void reply(@NonNull String replyToken, @NonNull List<Message> messages) {
        try {
            BotApiResponse apiResponse = lineMessagingClient
                    .replyMessage(new ReplyMessage(replyToken, messages))
                    .get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}