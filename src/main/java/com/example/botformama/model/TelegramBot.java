package com.example.botformama.model;

import com.example.botformama.config.BotConfig;
import com.example.botformama.markup.CityReplyKeyboardMarkup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.botformama.model.TimeManager.getCityTime;

@Data
@Component
@EqualsAndHashCode(callSuper = false)
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig config;

    @Autowired
    CityManager cityManager;


    public TelegramBot(BotConfig config) {
        this.config = config;
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            var messageText = update.getMessage().getText();
            var chatId = update.getMessage().getChatId();

            if (cityManager.getAllCityNames().contains(messageText)) {
                sendMessage(chatId, getCityTime(cityManager.getCityByName(messageText)));
            } else {
                switch (messageText) {
                    case "/start" ->
                            sendMessage(chatId, "Click on the city button and I will show you time!" + System.lineSeparator() + "Author -> @Nikita_Mor1");
                    default -> sendMessage(chatId, "Command not recognised");
                }
            }
            System.out.println("Time: " + new SimpleDateFormat("HH:mm").format(new Date()));
        }
    }


    private void sendMessage(long chatId, String text) {
        var sendMessage = new SendMessage();
        sendMessage.setChatId(chatId + "");
        sendMessage.setText(text);

        sendMessage.setReplyMarkup(new CityReplyKeyboardMarkup());

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}
