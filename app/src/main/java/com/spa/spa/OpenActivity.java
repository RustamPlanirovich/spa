package com.spa.spa;

import android.content.Context;
import android.content.Intent;

import com.spa.spa.book.Book;
import com.spa.spa.costs.Costs;
import com.spa.spa.currencyconverter.currencyConverter;
import com.spa.spa.date.Date;
import com.spa.spa.income.Income;
import com.spa.spa.links.Links;
import com.spa.spa.notes.Notes;
import com.spa.spa.plans.Plans;
import com.spa.spa.shedule.Shedule;
import com.spa.spa.todolist.todoList;
import com.spa.spa.voiceassistant.VoiceAssistant;

public interface OpenActivity {

  /**
   * Запуск активити Заметки.
   * @param mcontext mcontext.
   */
  static void notess(Context mcontext) {
    Intent intent;
    intent = new Intent(mcontext, Notes.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    mcontext.startActivity(intent);
  }

  /**
   * Запуск активити Книги.
   * @param mcontext mcontext.
   */
  static void bookk(Context mcontext) {
    Intent intent;
    intent = new Intent(mcontext, Book.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    mcontext.startActivity(intent);
  }

  /**
   * Запуск активити Даты.
   * @param mcontext mcontext.
   */
  static void datee(Context mcontext) {
    Intent intent;
    intent = new Intent(mcontext, Date.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    mcontext.startActivity(intent);
  }

  /**
   * Запуск активити Планы.
   * @param mcontext mcontext.
   */
  static void planss(Context mcontext) {
    Intent intent;
    intent = new Intent(mcontext, Plans.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    mcontext.startActivity(intent);
  }

  /**
   * Запуск активити Голосовой помощник.
   * @param mcontext mcontext.
   */
  static void voicee(Context mcontext) {
    Intent intent;
    intent = new Intent(mcontext, VoiceAssistant.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    mcontext.startActivity(intent);
  }

  /**
   * Запуск активити Настройки.
   * @param mcontext mcontext.
   */
  static void settingg(Context mcontext) {
    Intent intent;
    intent = new Intent(mcontext, MainActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    mcontext.startActivity(intent);
  }

  /**
   * Запуск активити Конвертер валют.
   * @param mcontext mcontext.
   */
  static void currencyy(Context mcontext) {
    Intent intent;
    intent = new Intent(mcontext, currencyConverter.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    mcontext.startActivity(intent);
  }

  /**
   * Запуск активити Ссылки.
   * @param mcontext mcontext.
   */
  static void linkss(Context mcontext) {
    Intent intent;
    intent = new Intent(mcontext, Links.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    mcontext.startActivity(intent);
  }

  /**
   * Запуск активити Дела.
   * @param mcontext mcontext.
   */
  static void todoo(Context mcontext) {
    Intent intent;
    intent = new Intent(mcontext, todoList.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    mcontext.startActivity(intent);
  }

  /**
   * Запуск активити График.
   * @param mcontext mcontext.
   */
  static void shedulee(Context mcontext) {
    Intent intent;
    intent = new Intent(mcontext, Shedule.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    mcontext.startActivity(intent);
  }

  /**
   * Запуск активити Расходы.
   * @param mcontext mcontext.
   */
  static void costss(Context mcontext) {
    Intent intent;
    intent = new Intent(mcontext, Costs.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    mcontext.startActivity(intent);
  }

  /**
   * Запуск активити Доходы.
   * @param mcontext mcontext.
   */
  static void incomm(Context mcontext) {
    Intent intent;
    intent = new Intent(mcontext, Income.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    mcontext.startActivity(intent);
  }
}
