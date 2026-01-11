package com.malef.medcalcbot.telegram;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.malef.medcalcbot.DTO.MedicineDTO;
import com.malef.medcalcbot.config.BotProps;
import com.malef.medcalcbot.data.entity.Medicine;
import com.malef.medcalcbot.data.service.MedicineService;
import com.malef.medcalcbot.telegram.commands.Command;
import com.malef.medcalcbot.telegram.commands.StartCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;


@Component
@RequiredArgsConstructor
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    private final static ObjectMapper mapper = new ObjectMapper();
    public final BotProps botProps;



    @Autowired
    private final StartCommand command;
    @Override
    public String getBotUsername() {
        log.info("Bot Name {}", botProps.getName());
        return botProps.getName();
    }

    @Override
    public String getBotToken() {
        return botProps.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if(update.hasMessage() && update.getMessage().hasText()) {

            SendMessage message = command.handleUpdate(update);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                log.error(e.getMessage());
            }
        }
    }


}
