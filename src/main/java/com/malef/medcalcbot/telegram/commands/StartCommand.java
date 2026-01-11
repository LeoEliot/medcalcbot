package com.malef.medcalcbot.telegram.commands;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class StartCommand implements Command{
    private final static Marker CHAT_ID = MarkerFactory.getMarker("CHAT_ID");
    private enum UserState {
        IDLE,
        PENDING
    }

    private class UserData {
        String title;
        UserState state = UserState.IDLE;
    }

    private Map<Long,UserData> userStates = new HashMap<>();
    @Override
    public SendMessage handleUpdate(Update update) {
        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();

        if(!userStates.containsKey(chatId)) {
            UserData data = new UserData();
            data.title = text;
            data.state = UserState.PENDING;
            userStates.put(chatId,data);

            return new SendMessage(chatId.toString(),data.title);
        }

        UserData data = userStates.get(chatId);
        log.info(CHAT_ID,"Chat ID is {}",chatId.toString());
        if (data.state == UserState.PENDING) {
            userStates.remove(chatId);
            return new SendMessage(chatId.toString(),data.title + " Registered");
        }
        return null;
    }
}
