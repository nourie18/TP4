package com.example.databasesql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="base.db";
    public static final String DATABASE_TABLE="Employes";
    public static final String col_1="ID";
    public static final String col_2="NOM";
    public static final String col_3="EMAIL";
    public static final String col_4="PHONE";
    public DataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+DATABASE_TABLE+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NOM TEXT,EMAIL TEXT,PHONE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
        onCreate(sqLiteDatabase);
    }
    public boolean insertData(String nom,String mail,String phone){
        SQLiteDatabase dB=this.getWritableDatabase();
        ContentValues cn=new ContentValues();
        cn.put(col_2,nom);
        cn.put(col_3,mail);
        cn.put(col_4,phone);
        long resultat= dB.insert(DATABASE_TABLE,null,cn);
        if (resultat==-1)
            return false;
        else
            return true;
    }
    public Cursor getALLData()
    {
        SQLiteDatabase dB=this.getWritableDatabase();
        Cursor resultat=dB.rawQuery("SELECT * FROM "+DATABASE_TABLE,null);
        return resultat;
    }
    public void delete(int id){
        SQLiteDatabase dB=this.getWritableDatabase();
        dB.delete(DATABASE_TABLE,col_1+" = ?",new String[]{String.valueOf(id)});
        dB.close();
    }
    public void update(String nom,String mail,String phone,int id){
        SQLiteDatabase dB=this.getWritableDatabase();
        ContentValues cVals=new ContentValues();
        cVals.put(col_2,nom);
        cVals.put(col_3,mail);
        cVals.put(col_4,phone);
        int count =dB.update(DATABASE_TABLE,cVals,col_1+" = ?",new String[]{String.valueOf(id)});
        dB.close();
    }

}
