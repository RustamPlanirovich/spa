package com.spa.spa;

import android.content.Context;
import android.content.Intent;

import com.spa.spa.Book.Book;
import com.spa.spa.Costs.Costs;
import com.spa.spa.Currency_converter.Currency_converter;
import com.spa.spa.Date.Date;
import com.spa.spa.Income.Income;
import com.spa.spa.Links.Links;
import com.spa.spa.Notes.Notes;
import com.spa.spa.Plans.Plans;
import com.spa.spa.Settings.Settings;
import com.spa.spa.Shedule.Shedule;
import com.spa.spa.Todo_list.Todo_list;
import com.spa.spa.Voice_assistant.Voice_assistant;

public interface OpenActivity {

    //Запуск активити Заметки
    public static void Notess(Context mcontext) {
        Intent intent;
        intent = new Intent(mcontext, Notes.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mcontext.startActivity(intent);
    }

    //Запуск активити Книги
    public static void Bookk(Context mcontext) {
        Intent intent;
        intent = new Intent(mcontext, Book.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mcontext.startActivity(intent);
    }

    //Запуск активити Даты
    public static void Datee(Context mcontext) {
        Intent intent;
        intent = new Intent(mcontext, Date.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mcontext.startActivity(intent);
    }

    //Запуск активити Планы
    public static void Planss(Context mcontext) {
        Intent intent;
        intent = new Intent(mcontext, Plans.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mcontext.startActivity(intent);
    }

    //Запуск активити Голосовой помощник
    public static void Voicee(Context mcontext) {
        Intent intent;
        intent = new Intent(mcontext, Voice_assistant.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mcontext.startActivity(intent);
    }

    //Запуск активити Настройки
    public static void Settingg(Context mcontext) {
        Intent intent;
        intent = new Intent(mcontext, Settings.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mcontext.startActivity(intent);
    }

    //Запуск активити Конвертер валют
    public static void Currencyy(Context mcontext) {
        Intent intent;
        intent = new Intent(mcontext, Currency_converter.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mcontext.startActivity(intent);
    }

    //Запуск активити Ссылки
    public static void Linkss(Context mcontext) {
        Intent intent;
        intent = new Intent(mcontext, Links.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mcontext.startActivity(intent);
    }

    //Запуск активити Дела
    public static void Todoo(Context mcontext) {
        Intent intent;
        intent = new Intent(mcontext, Todo_list.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mcontext.startActivity(intent);
    }

    //Запуск активити График
    public static void Shedulee(Context mcontext) {
        Intent intent;
        intent = new Intent(mcontext, Shedule.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mcontext.startActivity(intent);
    }

    //Запуск активити Расходы
    public static void Costss(Context mcontext) {
        Intent intent;
        intent = new Intent(mcontext, Costs.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mcontext.startActivity(intent);
    }

    //Запуск активити Доходы
    public static void Incomm(Context mcontext) {
        Intent intent;
        intent = new Intent(mcontext, Income.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mcontext.startActivity(intent);
    }
}
