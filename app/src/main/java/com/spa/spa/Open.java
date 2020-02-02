package com.spa.spa;

import android.content.Context;
import android.content.Intent;

import com.spa.spa.Book.Book;
import com.spa.spa.Date.Date;
import com.spa.spa.Notes.Notes;
import com.spa.spa.Plans.Plans;
import com.spa.spa.Voice_assistant.Voice_assistant;

public class Open  {

    public static void Notess(Context mcontext) {
        Intent intent;
        intent = new Intent(mcontext, Notes.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mcontext.startActivity(intent);
    }

    public static void Bookk(Context mcontext) {
        Intent intent;
        intent = new Intent(mcontext, Book.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mcontext.startActivity(intent);
    }
    public static void Datee(Context mcontext) {
        Intent intent;
        intent = new Intent(mcontext, Date.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mcontext.startActivity(intent);
    }
    public static void Planss(Context mcontext) {
        Intent intent;
        intent = new Intent(mcontext, Plans.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mcontext.startActivity(intent);
    }
    public static void Voicee(Context mcontext) {
        Intent intent;
        intent = new Intent(mcontext, Voice_assistant.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mcontext.startActivity(intent);
    }

}
