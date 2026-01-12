package com.malef.medcalcbot.telegram.commands;

import com.malef.medcalcbot.DTO.MedicineDTO;
import com.malef.medcalcbot.data.entity.Medicine;
import com.malef.medcalcbot.data.service.MedicineService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class StartCommand implements Command{


    private final MedicineService service;

    @Autowired
    public StartCommand(MedicineService service) {
        this.service = service;
    }

    @Override
    public SendMessage handleUpdate(Update update) {
        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();
        List<Medicine> meds = service.findAll();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Выбери препарат:");
        message.setReplyMarkup(createKeyboard(meds));
        return message;
    }

    public  InlineKeyboardMarkup createKeyboard(List<Medicine> meds) {

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        for (Medicine m : meds) {
            List<InlineKeyboardButton> list = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(m.getTitle());
            button.setCallbackData(m.getTitle());
            list.add(button);
            keyboard.add(list);
        }
        markup.setKeyboard(keyboard);
        return markup;
    }


}
