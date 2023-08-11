package org.yulia_bot.Utils;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVoice;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.yulia_bot.controller.TelegramBot;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
@Log4j
public class MessageUtils {
    private TelegramBot telegramBot;
    public SendMessage generateSendMessageWithText(Update update, String text){
        var message = update.getMessage();
        var sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        return sendMessage;
    }

    public SendPhoto generateSendMessageWithPhoto(Update update, String photoID){
        var message = update.getMessage();
        var sendMessage = new SendPhoto();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setPhoto(new InputFile(photoID));
        return sendMessage;
    }
    public SendVoice generateSendMessageWithAudio(Update update, String audioId){
        var message = update.getMessage();
        SendVoice audio = new SendVoice();
        audio.setChatId(message.getChatId().toString());
        audio.setVoice(new InputFile(audioId));
        return audio;
    }
//    public ReplyKeyboardMarkup defaultKeyboard(Update update){
//        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
//
//        List<KeyboardRow> keyboardRows = new ArrayList<>();
//        KeyboardRow row = new KeyboardRow();
//        row.add("Безкоштовний бонус");
//        keyboardRows.add(row);
//
//        row = new KeyboardRow();
//        row.add("Головне меню");
//        keyboardRows.add(row);
//
//        row = new KeyboardRow();
//        row.add("Повний розбір");
//        keyboardRows.add(row);
//
//        row = new KeyboardRow();
//        row.add("Дитячий Дизайн");
//        keyboardRows.add(row);
//
//
//        row = new KeyboardRow();
//        row.add("Карта сумісності");
//
//        keyboardRows.add(row);
//        row = new KeyboardRow();
//        row.add("Задати питання чи залишити відгук");
//        keyboardRows.add(row);
//
//        keyboardMarkup.setKeyboard(keyboardRows);
//        return keyboardMarkup;
//    }
    public ReplyKeyboardMarkup yesNoKeyboard(Update update){
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        row.add("\uD83D\uDE0EТак, знаю");
        row.add("\uD83D\uDE33Ні, не знаю");

        keyboardRows.add(row);
        keyboardMarkup.setKeyboard(keyboardRows);
        keyboardMarkup.setResizeKeyboard(true);
        return keyboardMarkup;
    }
    public ReplyKeyboardMarkup knowMore(Update update){
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        row.add("Дізнатись більше");
        row.add("Подивитись інші послуги");

        keyboardRows.add(row);
        keyboardMarkup.setKeyboard(keyboardRows);
        keyboardMarkup.setResizeKeyboard(true);
        return keyboardMarkup;
    }
    public ReplyKeyboardMarkup other(Update update){
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        row.add("Подивитись інші послуги");

        keyboardRows.add(row);
        keyboardMarkup.setKeyboard(keyboardRows);
        keyboardMarkup.setResizeKeyboard(true);
        return keyboardMarkup;
    }
    public ReplyKeyboardMarkup typeKeyboard(Update update) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("Маніфестор");
        keyboardRows.add(row);

        row = new KeyboardRow();
        row.add("Генератор");
        keyboardRows.add(row);

        row = new KeyboardRow();
        row.add("Проектор");
        keyboardRows.add(row);

        row = new KeyboardRow();
        row.add("Маніфестуючий генератор");
        keyboardRows.add(row);

        row = new KeyboardRow();
        row.add("Рефлектор");
        keyboardRows.add(row);

        keyboardMarkup.setKeyboard(keyboardRows);

        return keyboardMarkup;
    }

    public ReplyKeyboardMarkup profileKeyboard(Update update) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        row.add("1/3");
        row.add("1/4");
        row.add("2/4");

        keyboardRows.add(row);

        row = new KeyboardRow();
        row.add("2/5");
        row.add("3/5");
        row.add("3/6");

        keyboardRows.add(row);

        row = new KeyboardRow();
        row.add("4/6");
        row.add("4/1");
        row.add("5/1");

        row = new KeyboardRow();
        row.add("5/2");
        row.add("6/2");
        row.add("6/3");

        keyboardRows.add(row);

        keyboardMarkup.setKeyboard(keyboardRows);

        return keyboardMarkup;
    }
    public InlineKeyboardMarkup bookButton(){
        var inlineKeyboardMarkup =new InlineKeyboardMarkup();
        List < List < InlineKeyboardButton >> rowsInline = new ArrayList < > ();
        List < InlineKeyboardButton > rowInline = new ArrayList < > ();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Запитати про вільні дати та забронювати");
        button.setUrl("https://t.me/nikvas_julia");
        rowInline.add(button);
        rowsInline.add(rowInline);
        inlineKeyboardMarkup.setKeyboard(rowsInline);
        return inlineKeyboardMarkup;
    }
    public InlineKeyboardMarkup moreInfoButton(){
        var inlineKeyboardMarkup =new InlineKeyboardMarkup();
        List < List < InlineKeyboardButton >> rowsInline = new ArrayList < > ();
        List < InlineKeyboardButton > rowInline = new ArrayList < > ();
        rowInline.add(InlineKeyboardButton.builder().text("Дізнатись більше?").callbackData("more_info").build());
        rowsInline.add(rowInline);
        inlineKeyboardMarkup.setKeyboard(rowsInline);
        return inlineKeyboardMarkup;
    }



    public ReplyKeyboardMarkup mainMenuKeyboard(){
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("Обрати тип та профіль");
        keyboardRows.add(row);
        row = new KeyboardRow();
        row.add("Безкоштовний бонус");
        keyboardRows.add(row);
        row = new KeyboardRow();
        row.add("Послуги та ціни");
        keyboardRows.add(row);
        row = new KeyboardRow();
        row.add("Детальніше про мене");
        keyboardRows.add(row);
        row = new KeyboardRow();
        row.add("Задати питання чи залишити відгук");
        keyboardRows.add(row);
        keyboardMarkup.setKeyboard(keyboardRows);

        return keyboardMarkup;
    }

    public ReplyKeyboardMarkup servicesKeyboard(Update update) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("Повний розбір");
        keyboardRows.add(row);
        row = new KeyboardRow();
        row.add("Дитячий дизайн");
        keyboardRows.add(row);
        row = new KeyboardRow();
        row.add("Карта сумісності");
        keyboardRows.add(row);
        row = new KeyboardRow();
        row.add("Головне меню");
        keyboardRows.add(row);

        keyboardMarkup.setKeyboard(keyboardRows);

        return keyboardMarkup;
    }

    public ReplyKeyboardMarkup greetingKeyboard(Update update) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        row.add("Приємно познайомитись");
        keyboardRows.add(row);
        keyboardMarkup.setKeyboard(keyboardRows);
        keyboardMarkup.setResizeKeyboard(true);
        return keyboardMarkup;
    }

    public InlineKeyboardMarkup aboutMeKeyboard(Update update) {
        var inlineKeyboardMarkup =new InlineKeyboardMarkup();
        List < List < InlineKeyboardButton >> rowsInline = new ArrayList < > ();
        List < InlineKeyboardButton > rowInline = new ArrayList < > ();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Перейти в інстаграм");
        button.setUrl("https://instagram.com/nikvas.julia/");
        rowInline.add(button);
        List < InlineKeyboardButton > rowInline1 = new ArrayList < > ();
        button = new InlineKeyboardButton();
        button.setText("Перейти на сайт");
        button.setUrl("https://nikvas.pro/");
        rowInline1.add(button);


        rowsInline.add(rowInline);
        rowsInline.add(rowInline1);
        inlineKeyboardMarkup.setKeyboard(rowsInline);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup rateQuestionKeyboard(Update update) {
        var inlineKeyboardMarkup =new InlineKeyboardMarkup();
        List < List < InlineKeyboardButton >> rowsInline = new ArrayList < > ();
        List < InlineKeyboardButton > rowInline = new ArrayList < > ();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Залишити відгук/задати питання");
        button.setUrl("https://t.me/nikvas_julia");
        rowInline.add(button);
        rowsInline.add(rowInline);
        inlineKeyboardMarkup.setKeyboard(rowsInline);
        return inlineKeyboardMarkup;
    }
    public InlineKeyboardMarkup countButton(){
        var inlineKeyboardMarkup =new InlineKeyboardMarkup();
        List < List < InlineKeyboardButton >> rowsInline = new ArrayList < > ();
        List < InlineKeyboardButton > rowInline = new ArrayList < > ();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Зробити розрахунок");
        button.setUrl("http://humdes.com/");
        rowInline.add(button);
        rowsInline.add(rowInline);
        inlineKeyboardMarkup.setKeyboard(rowsInline);
        return inlineKeyboardMarkup;
    }

    public ReplyKeyboardMarkup knowPrice(Update update) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        row.add("Дізнатись вартість");
        keyboardRows.add(row);
        keyboardMarkup.setKeyboard(keyboardRows);
        keyboardMarkup.setResizeKeyboard(true);
        return keyboardMarkup;
    }
}

