package com.example.aysenur.ymtproje;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.jar.Attributes;

/**
 * Created by AYSENUR on 06/04/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static  final String Database_Name = "Student.db";
    public static final String Table_Name ="student_tbl";
    public static final String Col_1 = "Id";
    public static final String Col_2 = "Name";
    public static final String Col_3 = "SurName";
    public static final String Col_4 = "Marks";

    public DatabaseHelper(Context context){
        super(context,Database_Name,null,1);
    }

    public void onCreate(SQLiteDatabase database){
        database.execSQL("create table "+Table_Name+" (Id INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT" +
                ", SurName TEXT, Marks INTEGER)");
    }


    public void onUpgrade(SQLiteDatabase database,int oldVersion,int newVersion){
          database.execSQL("DROP TABLE IF EXISTS "+Table_Name);
          onCreate(database);

      }
      public boolean insertData(String name , String surname ,String marks){
          SQLiteDatabase database = this.getWritableDatabase();
          ContentValues contentValues = new ContentValues();
          contentValues.put(Col_2, name);
          contentValues.put(Col_3,surname);
          contentValues.put(Col_4,marks);

          long result = database.insert(Table_Name,null,contentValues);
          if (result == -1)
              return false;
          else
              return true;
      }

      public Cursor getAllData(){
          SQLiteDatabase database = this.getWritableDatabase();
          Cursor result = database.rawQuery("select * from "+Table_Name,null);
          return result;
      }
      public boolean updateData(String id,String name ,String surname ,String marks){
          SQLiteDatabase database = this.getWritableDatabase();
          ContentValues contentValues = new ContentValues();
          contentValues.put(Col_1,id);
          contentValues.put(Col_2,name);
          contentValues.put(Col_3,surname);
          contentValues.put(Col_4,marks);

          database.update(Table_Name,contentValues,"Id = ?",new String[]{id});
          return true;
      }
      public Integer deleteData(String id){
          SQLiteDatabase database = this.getWritableDatabase();
          return database.delete(Table_Name,"Id = ?",new String[]{id});
      }
}
