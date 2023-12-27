package com.philippabather.properproperties.db;

import android.content.Context;

import androidx.room.Room;

public class DBHelperMethods {

    public static AppLocalDB getConnection(Context context) {
        return Room.databaseBuilder(context, AppLocalDB.class, DBConstants.DATABASE_NAME).allowMainThreadQueries().build();
    }
}
