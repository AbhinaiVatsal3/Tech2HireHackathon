package com.example.techolutionatm;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
@Database(entities = {ATMEntity.class}, version = 1)
public abstract class ATMDatabase extends RoomDatabase {

    private static volatile ATMDatabase atmDatabase;

    public abstract ATMDao atmDao();

    static ATMDatabase getAtmDatabase(Context context){
        if(atmDatabase == null){
            synchronized (ATMDatabase.class){
                if(atmDatabase == null){
                    atmDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            ATMDatabase.class, "ATM_Database").build();
                }
            }
        }
        return atmDatabase;
    }

}
