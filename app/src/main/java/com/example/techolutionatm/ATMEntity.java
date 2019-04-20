package com.example.techolutionatm;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "ATM_table")
public class ATMEntity {

    @PrimaryKey
    @ColumnInfo
    public double ATM_Id;

    @ColumnInfo
    public String associated_Bank;

    @ColumnInfo
    public String location;

    @ColumnInfo
    public String mapLocation;

    @ColumnInfo
    public String status;

    @ColumnInfo
    public String facilities;

}
