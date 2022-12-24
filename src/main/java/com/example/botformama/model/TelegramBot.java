package com.example.botformama.model;

import com.example.botformama.model.EmojiContainer.*;
import com.example.botformama.config.BotConfig;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.botformama.model.TimeManager.*;

@Data
@Component
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

        sendMessage.setReplyMarkup(generateReplyKeyboardMarkup());

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private ReplyKeyboardMarkup generateReplyKeyboardMarkup() {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rows = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        row.add(kaiserslauternAndPrague);
        row.add(canadaClient);
        rows.add(row);

        row = new KeyboardRow();
        row.add(moscow);
        row.add(kiev);
        rows.add(row);

        row = new KeyboardRow();
        row.add(eugene);
        row.add(northBey);
        rows.add(row);

        markup.setKeyboard(rows);
        return markup;
    }

}
