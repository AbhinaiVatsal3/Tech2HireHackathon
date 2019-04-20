package com.example.techolutionatm;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public  interface ATMDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ATMEntity atm);

    @Query("SELECT * FROM ATM_table")
    LiveData<List<ATMEntity>> selectAll();

    @Query("DELETE FROM ATM_table WHERE ATM_id = :id")
    void delete(int id);

    @Query("SELECT * FROM ATM_table WHERE ATM_Id = :id")
    LiveData<ATMEntity> selectId(double id);

}
