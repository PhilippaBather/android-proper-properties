package com.philippabather.properproperties.db;

import static com.philippabather.properproperties.db.DBConstants.DATABASE_NAME;

import android.content.Context;

import androidx.room.Room;

public class DBHelperMethods {

    public static AppLocalDB getConnection(Context context) {
        return Room.databaseBuilder(context, AppLocalDB.class, DATABASE_NAME).allowMainThreadQueries().build();
    }
}
