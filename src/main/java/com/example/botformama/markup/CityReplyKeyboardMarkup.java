package com.example.botformama.markup;

import com.example.botformama.entity.City;
import com.example.botformama.model.CityManager;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@EqualsAndHashCode(callSuper = false)
public class CityReplyKeyboardMarkup  extends ReplyKeyboardMarkup {

    private final int columnCount = 2;

    private CityManager cityManager = new CityManager();

    public CityReplyKeyboardMarkup() {
        List<KeyboardRow> rows = new ArrayList<>();
        ArrayList<City> cities = cityManager.getCities();

        int counter = 0;
        KeyboardRow row = new KeyboardRow();

        for (City c : cities){
            row.add(c.getName());
            counter+=1;

            if (counter == columnCount){
                rows.add(row);
                row = new KeyboardRow();
                counter = 0;
            }
        }
        if (!row.isEmpty()){
            rows.add(row);
        }
        this.setKeyboard(rows);
    }
}
