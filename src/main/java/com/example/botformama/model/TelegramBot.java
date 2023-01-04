package com.example.botformama.model;

import com.example.botformama.config.BotConfig;
import com.example.botformama.container.EmojiContainer;
import com.example.botformama.markup.CityReplyKeyboardMarkup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.botformama.model.TimeManager.*;

@Data
@Component
@EqualsAndHashCode(callSuper = false)
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig config;
    private final String moscow = "Moscow " + EmojiContainer.RU_FLAG + EmojiContainer.NESTING_DOLL;
    private final String kiev = "Kiev " + EmojiContainer.UA_FLAG;
    private final String kaiserslauternAndPrague =
            "Kaiserslautern " + EmojiContainer.DE_FLAG + EmojiContainer.PRETZEL + "/Prague" + EmojiContainer.CZ_FLAG;
    private final String canadaClient = "Oksana " + EmojiContainer.CA_FLAG;
    private final String northBey = "North Bay " + EmojiContainer.CA_FLAG;
    private final String eugene = "Eugene " + EmojiContainer.CA_FLAG;


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

            switch (messageText) {
                case "/start" -> sendMessage(chatId, "AVE технологии, маманя!");
                case moscow -> sendMessage(chatId, getMoscowTime());
                case kiev -> sendMessage(chatId, getKievTime());
                case kaiserslauternAndPrague -> sendMessage(chatId, getKaizerslauternAndPragueTime());
                case canadaClient -> sendMessage(chatId, getCanadaClientTime());
                case eugene -> sendMessage(chatId, getEugeneTime());
                case northBey -> sendMessage(chatId,getNorthBeyTime());
                default -> sendMessage(chatId, "Command not recognised");
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
