package com.philippabather.properproperties.db;

import android.content.Context;

import androidx.room.Room;

/**
 * DBHelperMethods - contiene el método estático para crear una conexión con el base de datos local
 * de Rooms.
 *
 * @author Philippa Bather
 */
public class DBHelperMethods {

    public static AppLocalDB getConnection(Context context) {
        return Room.databaseBuilder(context, AppLocalDB.class, DBConstants.DATABASE_NAME).allowMainThreadQueries().build();
    }
}
