package com.vm.kidslearning.kids.kids123.dbhandler;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.vm.kidslearning.kids.kids123.model.CategoryModel;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteAssetHelper {
    public static final String db_name = "kids123.db";
    public static final int db_ver = 1;

    public DatabaseHandler(Context context) {
        super(context, db_name, null, db_ver);
    }

    public ArrayList<CategoryModel> getCategory() {

        ArrayList<CategoryModel> category = new ArrayList<>();
        String SQL = "SELECT * FROM CategoryDetails";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();

        for(int i = 0; i < cursor.getCount(); i++){
            CategoryModel c = new CategoryModel();
            c.setCategoryID(cursor.getString(cursor.getColumnIndex("CategoryID")));
            c.setCategoryName(cursor.getString(cursor.getColumnIndex("CategoryName")));
            c.setImg(cursor.getBlob(cursor.getColumnIndex("CategoryImg")));

            category.add(c);
            cursor.moveToNext();
        }
        db.close();
        cursor.close();
        return category;
    }

    public String [] getOption(String qnum, String cat, String tnum){

        String [] op = new String[5];
        String SQL = "SELECT * FROM QuizDetails WHERE QuestionNo= "+qnum+" AND CategoryID="+cat+" AND TestNo="+tnum;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();

        op[0] = cursor.getString(cursor.getColumnIndex("Option1"));
        op[1] = cursor.getString(cursor.getColumnIndex("Option2"));
        op[2] = cursor.getString(cursor.getColumnIndex("Option3"));
        op[3] = cursor.getString(cursor.getColumnIndex("Option4"));
        op[4] = cursor.getString(cursor.getColumnIndex("Answer"));

        return op;
    }

    public byte [] getOpimage(String qnum, String cat, String tnum){
        byte [] img;
        String SQL = "SELECT * FROM QuizDetails WHERE QuestionNo= "+qnum+" AND CategoryID="+cat+" AND TestNo="+tnum;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();

        img = cursor.getBlob(cursor.getColumnIndex("Image"));

        return img;

    }

}
