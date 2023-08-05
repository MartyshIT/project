package org.yulia_bot.controller;

import lombok.extern.log4j.Log4j;
import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVoice;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;

import org.yulia_bot.Utils.MessageUtils;
import org.yulia_bot.service.UpdateProducer;


import static org.yulia_bot.RabbitQueue.TEXT_MESSAGE_UPDATE;
import static org.yulia_bot.RabbitQueue.PHOTO_MESSAGE_UPDATE;
import static org.yulia_bot.RabbitQueue.DOC_MESSAGE_UPDATE;

@Component
@Log4j
public class UpdateController {
    private TelegramBot telegramBot;
    private MessageUtils messageUtils;
    private UpdateProducer updateProducer;

    public UpdateController(MessageUtils messageUtils, UpdateProducer updateProducer) {
        this.messageUtils = messageUtils;
        this.updateProducer = updateProducer;
    }



    public void registerBot(TelegramBot telegramBot){
        this.telegramBot = telegramBot;
    }
    public void processUpdate(Update update){
        if (update == null){
            log.error("Received update is null");
            return;
        }
        if (update.getMessage() != null){
            distributeMessagesByType(update);
        }
        else if (update.hasCallbackQuery()){
            String call_data = update.getCallbackQuery().getData();
            long chat_id = update.getCallbackQuery().getMessage().getChatId();
            if (call_data.equals("more_info")){
                var sendMessage = new SendMessage();
                sendMessage.setChatId(chat_id);
                var button = messageUtils.mainMenuKeyboard();
                sendMessage.setReplyMarkup(button);
                sendMessage.setText("Оберіть, що Вас цікавить");
                SendVoice audio = new SendVoice();
                audio.setChatId(chat_id);
                audio.setVoice(new InputFile("AwACAgIAAxkBAAIBqWS9UsUH4SMzZZ-b2AhhccvXhqrDAAJBJAACa3dJSqMoUM5e9EEFLwQ"));
                telegramBot.sendAudio(audio);
                telegramBot.sendAnswerMessage(sendMessage);
            }

        }
        else {
            log.error("Received unsupported message type" + update);
        }
    }

    private String[] typeProfile = new String[2];

    private int usingTimes;

    private void distributeMessagesByType(Update update) {
        var message = update.getMessage();
//        if (GenericValidator.isDate(message.getText(), "dd.MM.yyyy", true)) {
//            birthDate = message.getText();
//            giveYourBirthTime(update);
//        }
//        else if (GenericValidator.isInt(message.getText()) && message.getText().length()<3){
//            birthTime = message.getText();
//            giveYourCity(update);
//
//        }
//        else if (message.getText().contains("істо")){
//            city = message.getText().substring(5);
//            processingMessage(update);
//        }

        if(message.getText() != null) {
            processTextMessage(update);

            String yourProfile;
            String yourType;
            switch (message.getText()) {
                case "/start": {
                    greeting(update);
                    break;
                }
                case "Приємно познайомитись":{
                    greetingMessage(update);
                    break;
                }
                case "Так знаю!", "Так":{
                    chooseYourType(update);
                    break;
                }
                case "А що це?", "Ні":{
                    info(update);
                    chooseYourOwnedType(update);
                    break;
                }
                case "Обрати тип та профіль":{
                    chooseYourOwnedType(update);
                    break;
                }
                case"Маніфестор":{
                    yourType="Маніфестор";
                    chooseYourProfile(update);
                    typeProfile[0]=yourType;
                    break;
                }
                case "Генератор":{
                    yourType="Генератор";
                    chooseYourProfile(update);
                    typeProfile[0]=yourType;
                    break;
                }
                case "Проектор":{
                    yourType="Проектор";
                    chooseYourProfile(update);
                    typeProfile[0]=yourType;
                    break;
                }
                case "Маніфестуючий генератор":{
                    yourType="Маніфестуючий генератор";
                    chooseYourProfile(update);
                    typeProfile[0]=yourType;
                    break;
                }
                case "Рефлектор":{
                    yourType="Рефлектор";
                    chooseYourProfile(update);
                    typeProfile[0]=yourType;
                    break;
                } //тип
                case "1/3": {
                    yourProfile = "1/3";
                    typeProfile[1]=yourProfile;
                    break;
                }
                case "1/4": {
                    yourProfile = "1/4";
                    typeProfile[1]=yourProfile;
                    break;
                }
                case "2/4": {
                    yourProfile = "2/4";
                    typeProfile[1]=yourProfile;
                    break;
                }
                case "2/5": {
                    yourProfile = "2/5";
                    typeProfile[1]=yourProfile;
                    break;
                }
                case "3/5": {
                    yourProfile = "3/5";
                    typeProfile[1]=yourProfile;
                    break;
                }
                case "3/6": {
                    yourProfile = "3/6";
                    typeProfile[1]=yourProfile;
                    break;
                }
                case "4/6": {
                    yourProfile = "4/6";
                    typeProfile[1]=yourProfile;
                    break;
                }
                case "4/1": {
                    yourProfile = "4/1";
                    typeProfile[1]=yourProfile;
                    break;
                }
                case "5/1": {
                    yourProfile = "5/1";
                    typeProfile[1]=yourProfile;
                    break;
                }
                case "5/2": {
                    yourProfile = "5/2";
                    typeProfile[1]=yourProfile;
                    break;
                }
                case "6/2": {
                    yourProfile = "6/2";
                    typeProfile[1]=yourProfile;
                    break;
                }
                case "6/3": {
                    yourProfile = "6/3";
                    typeProfile[1]=yourProfile;
                    break;
                }
                case "Безкоштовний бонус": {
                    freeBonus(update);
                    break;
                }
                case "Повний розбір": {
                    fullCase(update);
                    break;
                }
                case "Дитячий Дизайн": {
                    kidDesign(update);
                    break;
                }
                case "Карта сумісності": {
                    consistenceCard(update);
                    break;
                }
                case "Головне меню", "/main": {
                    callMainMenu(update);
                    break;
                }
                case "Послуги та ціни": {
                    servicesAndPrices(update);
                    break;
                }
                case "Детальніше про мене":{
                    abutMe(update);
                    break;
                }
                case "Задати питання чи залишити відгук": {
                    rateQuestion(update);
                    break;
                }
                case "Що ще ви можете про себе дізнатись?": {
                    makeBoardDefault(update);
                    break;
                }
                default:{
                    unknownCommand(update);
                    break;
                }

            }





        } else if (message.getDocument() != null) {
            processDocMessage(update);
            log.error(message.getDocument().getFileId());
        } else if (message.getPhoto() != null) {
            log.error(message);
        }

        else if (message.hasVoice()){
            log.error(update.getMessage().getVoice().getFileId());
        }

         else {
            setUnsupportedMessageTypeView(update);
        }
        if (typeProfile[0]!=null && typeProfile[1]!=null && usingTimes<=1){
            sendYourDesign(update);
            typeProfile[0]=null;
            typeProfile[1]=null;
            usingTimes +=1;
            moreInfo(update);
        }
        if (typeProfile[0]!=null && typeProfile[1]!=null && usingTimes>1) {
            sendCaution(update);
        }


    }

    private void rateQuestion(Update update) {
        var sendMessage = messageUtils.generateSendMessageWithText(update, "Зробити це Ви можете написавши мені в особисті за кнопкою нижче");
        var keyboardMarkup = messageUtils.rateQuestionKeyboard(update);
        sendMessage.setReplyMarkup(keyboardMarkup);
        setView(sendMessage);
    }

    private void abutMe(Update update) {
        var sendMessage = messageUtils.generateSendMessageWithText(update, "Привіт!\n" +
                "Я Юлія Ніквас - коуч, психолог, експерт системи Дизайн Людини. Окрім цього я засновниця трансформаційного клубу «Жіночий Океан» та організатор одноіменного фестивалю, ігропрактик та спеціаліст по створенню трансформаційних ігор. \n" +
                "\n" +
                "У світі Дизайну я майже 4 роки і за цей час мені вдалося поєднати свої глибокі пізнання в психології, особистий досвід та особливості системи Human Design. Я Маніфестуючий Генератор 2/4 з каналом 13-33 та з 23 воротами у Меркурію, то ж моя унікальність полягає в тому, щоб передати світові досвід та складні речі дуже простими і зрозумілими словами, роблячи це легко і з задоволенням. \n" +
                "\n" +
                "У моєму інстаграмі ти знайдеш купу цікавої інформації, яку можна дивитися у своєму Дизайні та дізнаватися щось нове про себе. Окрім Дизайну Людини я веду ще купу крутих проектів (як справжній МГ), з ними ти можеш ознайомитися на моєму сайті.");
        var keyboardMarkup = messageUtils.aboutMeKeyboard(update);
        sendMessage.setReplyMarkup(keyboardMarkup);
        sendPhoto(update, "AgACAgIAAxkBAAIC_2S-3XqBWQpJNR9Uz526Lr7UpmTaAAJhyzEb6JX4SfhHW_fT0OuXAQADAgADcwADLwQ");
        setView(sendMessage);
    }

    private void greeting(Update update) {
        var sendMessage = messageUtils.generateSendMessageWithText(update, "Привіт! Давайте знайомитись");
        var keyboardMarkup = messageUtils.greetingKeyboard(update);
        sendMessage.setReplyMarkup(keyboardMarkup);
        setView(sendMessage);
        sendAudio(update,"AwACAgIAAxkBAAICxmS-2BFCG7i298FaZHh6HeJBz8asAALbIwACLQ8pSj370WUt0vLmLwQ");
    }

    private void unknownCommand(Update update) {
        var sendMessage = messageUtils.generateSendMessageWithText(update, "На жаль, я не знаю такої команди, будь ласка оберіть з існуючих");
        var keyboardMarkup = messageUtils.mainMenuKeyboard();
        sendMessage.setReplyMarkup(keyboardMarkup);
        setView(sendMessage);
    }

    private void sendCaution(Update update) {
        var sendMessage = messageUtils.generateSendMessageWithText(update, "На жаль, Ви можете зробити це лише двічі, спробуйте інші послуги");
        var keyboardMarkup = messageUtils.mainMenuKeyboard();
        sendMessage.setReplyMarkup(keyboardMarkup);
        setView(sendMessage);
        typeProfile[0]=null;
        typeProfile[1]=null;
    }

    private void servicesAndPrices(Update update){
        var sendMessage = messageUtils.generateSendMessageWithText(update, "Оберіть послугу нижче \uD83D\uDC47");
        var keyboardMarkup = messageUtils.servicesKeyboard(update);
        sendMessage.setReplyMarkup(keyboardMarkup);
        setView(sendMessage);
    }

    private void kidDesign(Update update) {
        var sendMessage = messageUtils.generateSendMessageWithText(update, "Дитячий Дизайн - це унікальна можливість виховати впевнену та гармонійну дитину, розкрити та пітримувати розвиток її талантів, розуміти її і легко комунікувати.  Дитячий розбір дає відповіді на такі питання: \n" +
                "як впливати та доносити прості речі? \n" +
                "як і куди направляти енергію? \n" +
                "які таланти закладені від природи? \n" +
                "на що звернути увагу, щоб не заблокувати природні здібності? \n" +
                "як не зламати те, що закладено природою?\n" +
                "яка генетична травма активована від природи?\n" +
                "який тип харчування? \n" +
                "який сон та графік дня необхідний, щоб правильно звільняти і наповнювати енергію? \n" +
                "Після Дитячої Розшифровки найголовніше, що отримують батьки - це розуміння своєї дитини і впевненність у своєму батьківстві\n" +
                "Розбір у вигляді аудіозаписів тривалістю приблизно 1 година.\n" +
                "Вартість 2400 грн");
        var button = messageUtils.bookButton();
        sendMessage.setReplyMarkup(button);
        setView(sendMessage);
    }

    private void consistenceCard(Update update) {
        var sendMessage = messageUtils.generateSendMessageWithText(update, "Розшифровка Карти Сумісності дозволяє партнерам розуміти один одного, мінімізувати конфлікти, покращити сексуальні стосунки і найголовніше - прийняти себе та свого партнера таким, який він є. Це ідеальна можливість вийти на новий рівень відносин безболісно та гармонійно.\n" +
                "Якщо навіть лише один з партнерів знає Дизайн пари, то цього достатньо, щоб покращити стосунки, оскільки наші партнери - це завжди наше відзеркалення. І коли ми йдемо у розвиток та усвідомлену роботу над собою, то наші партнери підтягуються автоматично на наш рівень. \n" +
                "Розбір складається з таких блоків:\n" +
                " 1. сумісність типів та профілей\n" +
                " 2. вплив один на одного, огляд сильних та слабких сторін\n" +
                " 3. сексуальні стратегії та теми притяжіння\n" +
                " 4. генетична травма та цінності (причини конфліктів та як їх уникнути)\n" +
                " 5. формула сумісності та розбір електромагнітів (іскри у стосунках)\n" +
                "Дізнайтеся про формулу вашої пари та нові здібності, які зʼявляються у поєднанні ваших бодіграфів за допомогою розрахунку Карти Сумісності.\n" +
                "Розбір у вигляді аудіозаписів тривалістю приблизно 1 година.\n" +
                "Вартість 2400 грн");
        var button = messageUtils.bookButton();
        sendMessage.setReplyMarkup(button);
        setView(sendMessage);
    }

    private void freeBonus(Update update) {
        var sendMessage = messageUtils.generateSendMessageWithText(update, "Вау, тепер ти на крок ближче до себе! А хочеш дізнатися ще більше? " +
                "Пропоную тобі додаткову інформацію про те, як для тебе краще приймати рішення. Для цього опублікуй в своєму інстаграмі " +
                "цю сторіс з відміткою nikvas.julia  - щойно побачу відмітку, напишу тобі. Я вже готую твій бонус!");
        setView(sendMessage);
        sendPhoto(update, "AgACAgIAAxkBAAICsWS-05aJMnIELJgaQspTXRHOHJ-KAAJZ0jEboSLwSVsLd5i7Jl4aAQADAgADcwADLwQ");
    }
    private void fullCase(Update update){
        var sendMessage = messageUtils.generateSendMessageWithText(update, "Повний розбір - це інформація у вигляді аудіозаписів тривалістю 4 години та розбита на 2 етапи: першу частину надсилаю у заплановану дату, а другу частину через тиждень. Згідно  досвіду надання розшифровок - так краще засвоюється розбір  та більш зручно для всіх.\n" +
                "Розшифровка складається з чотирьох основних блоків:\n" +
                "1) тип та унікальні риси особистості, головна стратегія по життю, як краще приймати рішення, соціальна роль для реалізації у світі\n" +
                "2) розбір слабких і сильних сторін (центрів), теми, які чіпляють та куди сплавляється вся енергія (зачіпки), суперздібності та інструменти для реалізації призначення (канали)\n" +
                "3) дрібні налаштування (харчування, здоровʼя, мотивація, фокус уваги, місце сили та ін.)\n" +
                "4) призначення та глобальна місія, розбір проживання в плюсах та в мінусах\n" +
                "Кожний блок має рекомендації для втілення інформації у життя та покращення слабких місць.\n" +
                "Після отримання розбору ви маєте унікальну можливість задавати необмежену кількість питань.\n" +
                "Вартість повного розбору 4000 грн (можна сплатити частинами).\n" +
                "Текстовий формат (20 стр у пдф форматі) сплачується додатково 1000 грн.");
        var button = messageUtils.bookButton();
        sendMessage.setReplyMarkup(button);
        setView(sendMessage);
    }

    private void makeBoardDefault(Update update) {
        sendAudio(update,"AwACAgIAAxkBAAIBqWS9UsUH4SMzZZ-b2AhhccvXhqrDAAJBJAACa3dJSqMoUM5e9EEFLwQ");
        var sendMessage = messageUtils.generateSendMessageWithText(update, "В головному меню Ви можете обрати що ще бажаєте дізнатись");
        var keyboardMarkup = messageUtils.mainMenuKeyboard();
        sendMessage.setReplyMarkup(keyboardMarkup);
        setView(sendMessage);

    }

    private void moreInfo(Update update) {
        var sendMessage = messageUtils.generateSendMessageWithText(update, "Що ще можна дізнатись про свій Дизайн");
        var keyboardMarkup = messageUtils.moreInfoButton();
        sendMessage.setReplyMarkup(keyboardMarkup);
        setView(sendMessage);

    }

    private void callMainMenu(Update update){
        var sendMessage = messageUtils.generateSendMessageWithText(update, "Оберіть, що Вас цікавить");
        var keyboardMarkup = messageUtils.mainMenuKeyboard();
        sendMessage.setReplyMarkup(keyboardMarkup);
        setView(sendMessage);
    }

    private void processingMessage(Update update) {
        var sendMessage = messageUtils.generateSendMessageWithText(update,
                "Дані прийнято, йде обробка, зачекай");
        setView(sendMessage);
    }

    private void giveYourCity(Update update) {
        var sendMessage = messageUtils.generateSendMessageWithText(update,
                "Вкажи місто народження у форматі 'місто Львів'(якщо це село або смт - найближче велике місто)");
        setView(sendMessage);
    }

    private void giveYourBirthTime(Update update) {
        var sendMessage = messageUtils.generateSendMessageWithText(update,
                "Тепер введи годину народження");
        setView(sendMessage);
    }


    private void giveYourBirthDate(Update update) {
        var sendMessage = messageUtils.generateSendMessageWithText(update,
                "Для цього введи дату свого народження у форматі дд.мм.рр(наприклад 25.12.1901)");
        setView(sendMessage);
    }

    private void info(Update update) {
        var sendMessage = messageUtils.generateSendMessageWithText(update,
                "Для того, щоб дізнатися свій тип і профіль, необхідно зробити простий розрахунок. Для цього" +
                        " натисни на кнопку меню, що знаходиться ліворуч від поля вводу тексту. "+
                "Повертайся та обирай свій тип і профіль, щоб отримати дизайн. Обовʼязково зроби скрін свого " +
                        "бодіграфу та інших даних як показано нижче - це тобі знадобиться, коли ти будеш спостегрігати " +
                        "у моєму інстаграмі за різними фішками по Дизайну Людини і дізнаватися про себе нове. УВАГА. Зробити цей розбір " +
                        "можна лише двічі, тож будьте уважні");
        var inlineKeyboard = messageUtils.countButton();
        sendMessage.setReplyMarkup(inlineKeyboard);
        setView(sendMessage);
    }

    private void sendYourDesign(Update update) {
        switch (typeProfile[0])     {
            case "Маніфестор":{
                sendAudio(update,
                        "AwACAgIAAxkBAAIBimS9S3_VJv3c9wL576QPFlevlbfdAAK-JAACLQ8pSnpQhCiEAAFiWy8E");
                break;
            }
            case "Генератор":{
                sendAudio(update, "AwACAgIAAxkBAAIFGmTAEPfJCvrLMGtCa4k_9ccEQ4pPAAIGJAACa3dBSlBZHShU6yDKLwQ");
                break;
            }
            case "Проектор":{
                sendAudio(update, "AwACAgIAAxkBAAIBjGS9S74DECVNR-0PAzu-7oshB8TGAAJjJAACa3dBSg_nrR77i8XoLwQ");
                break;
            }
            case "Маніфестуючий генератор":{
                sendAudio(update, "AwACAgIAAxkBAAIBi2S9S6Q9dglCFELlCosGYFPqgftnAAI1JAACa3dBSnM8BAAByhXYai8E");
                break;
            }
            case "Рефлектор":{
                sendAudio(update, "AwACAgIAAxkBAAIBjWS9S9Rwz6PmImNTrJktjQqAjWKxAAKNJAACa3dBSmR4387k7MQaLwQ");
                break;
            }
        } //тип
        switch (typeProfile[1]) {
            case "1/3":{
                sendAudio(update, "AwACAgIAAxkBAAIBjmS9TGI9btUIg7ecbqq2XZnzTXxeAALQJQACa3dBSkL22F7xJ_bJLwQ");
                break;
            }
            case "1/4": {
                sendAudio(update, "AwACAgIAAxkBAAIBj2S9TJNuMECoGuvJULmX9WxUpxBAAAIUJAACa3dJSr1MPjMbIzJxLwQ");
                break;
            }
            case "2/4": {
                sendAudio(update,"AwACAgIAAxkBAAIBkGS9TOTf-43WZVO3fHjapQ6zNfgqAAK5JQACa3dBSqW0AAEqTXKc6C8E");
                break;
            }
            case "2/5": {
                sendAudio(update,"AwACAgIAAxkBAAIBkWS9TQJgsD92URL4YQ9LcXaUr-1UAAIsJAACa3dJSqsrAZpjWjQxLwQ");
                break;
            }
            case "3/5": {
                sendAudio(update,"AwACAgIAAxkBAAIBkmS9TSjTcVD3g1Ii7Iir2TYnO7KvAALHJQACa3dBSkfC7zsSTUBrLwQ");
                break;
            }
            case "3/6": {
                sendAudio(update,"AwACAgIAAxkBAAIBk2S9TTufVFEruW6Px6Ze1MoEEhcGAAIgJAACa3dJSrQmmFm38DSCLwQ");
                break;
            }
            case "4/6": {
                sendAudio(update,"AwACAgIAAxkBAAIBlGS9TXsGFdlo6XS_g0Rt7F-kgBsNAAJRJAACQTOhSt0qIZwQWntNLwQ");
                break;
            }
            case "4/1": {
                sendAudio(update, "AwACAgIAAxkBAAIBj2S9TJNuMECoGuvJULmX9WxUpxBAAAIUJAACa3dJSr1MPjMbIzJxLwQ");
                break;
            }
            case "5/1": {
                sendAudio(update,"AwACAgIAAxkBAAIBlWS9TZLhrYzQiU4WWr_XI0jKJtJlAAMkAAJrd0lK2OFHrikfPu0vBA");
                break;
            }
            case "5/2": {
                sendAudio(update,"AwACAgIAAxkBAAIBkWS9TQJgsD92URL4YQ9LcXaUr-1UAAIsJAACa3dJSqsrAZpjWjQxLwQ");
                break;
            }
            case "6/2": {
                sendAudio(update,"AwACAgIAAxkBAAIBlmS9Ta_CXRtNn5hbu1FZ_RqYqvzFAAIOJAACa3dJStDA8tnVq4uxLwQ");
                break;
            }
            case "6/3": {
                sendAudio(update,"AwACAgIAAxkBAAIBk2S9TTufVFEruW6Px6Ze1MoEEhcGAAIgJAACa3dJSrQmmFm38DSCLwQ");
                break;
            }
        }
//профіль
    }

    private void chooseYourProfile(Update update) {
        var sendMessage = messageUtils.generateSendMessageWithText(update,
                "Обери свій профіль");
        var keyboardMarkup = messageUtils.profileKeyboard(update);
        sendMessage.setReplyMarkup(keyboardMarkup);
        setView(sendMessage);

    }

    private void setUnsupportedMessageTypeView(Update update) {
        var sendMessage = messageUtils.generateSendMessageWithText(update,
                "Відправлене повідомлення не підтримується");
        setView(sendMessage);
    }
    private void setFileIsReceived(Update update) {
        var sendMessage = messageUtils.generateSendMessageWithText(update, "Файл отримно, йде обробка");
        setView(sendMessage);
    }

    private void sendAudio(Update update, String audioId){
        var sendAudio = messageUtils.generateSendMessageWithAudio(update, audioId);
        setAudio(sendAudio);
    }
    private void sendPhoto(Update update, String photoId) {
        var sendPhoto = messageUtils.generateSendMessageWithPhoto(update, photoId);
        setPhoto(sendPhoto);
    }
    private void setView(SendMessage sendMessage) {
        telegramBot.sendAnswerMessage(sendMessage);
    }
    private void setAudio(SendVoice sendAudio){
        telegramBot.sendAudio(sendAudio);
    }
    private void setPhoto(SendPhoto sendPhoto){
        telegramBot.sendPhoto(sendPhoto);
    }

    private void processPhotoMessage(Update update) {
        updateProducer.produce(PHOTO_MESSAGE_UPDATE, update);
        setFileIsReceived(update);
    }



    private void processDocMessage(Update update) {
        updateProducer.produce(DOC_MESSAGE_UPDATE, update);
        setFileIsReceived(update);
    }

    private void processTextMessage(Update update) {
        updateProducer.produce(TEXT_MESSAGE_UPDATE, update);
    }

    private void greetingMessage(Update update){
        var sendMessage = messageUtils.generateSendMessageWithText(update, "Ви однозначно унікальна людина!\n" +
                "І зараз я хочу підтвердити свої слова і розповісти про вашу унікальність!\n" +
                "Але для початку давайте вияснимо важливу річ.\n" +
                "Чи знаєте ви свій тип та профіль?");
        var keyboardMarkup = messageUtils.yesNoKeyboard(update);
        sendMessage.setReplyMarkup(keyboardMarkup);
        setView(sendMessage);

    }

    private void chooseYourType(Update update){
        var sendMessage = messageUtils.generateSendMessageWithText(update,
                "Чудово! Обери свій тип але робіть це обережно адже є всього дві спроби");
        var keyboardMarkup = messageUtils.typeKeyboard(update);
        sendMessage.setReplyMarkup(keyboardMarkup);
        setView(sendMessage);
    }

    private void chooseYourOwnedType(Update update){
        var sendMessage = messageUtils.generateSendMessageWithText(update,
                "Обери свій тип");
        var keyboardMarkup = messageUtils.typeKeyboard(update);

        sendMessage.setReplyMarkup(keyboardMarkup);

        setView(sendMessage);
    }

}
