package com.waiway.minipos.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.waiway.minipos.Model.ColorModel;

import java.util.ArrayList;

public class ColorDAO {
    public String colId = "ColorId",
            colRed = "ColorRed",
            colGreen = "ColorGreen",
            colBlue = "ColorBlue",
            tbName = "Color";

    DBHelper dbHelper;
    SQLiteDatabase db;
    public ColorDAO(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getReadableDatabase();
    }

    public long saveColor(ColorModel model)
    {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(colRed,model.ColorRed);
        values.put(colGreen,model.ColorGreen);
        values.put(colBlue,model.ColorBlue);
        return  db.insert(tbName,null,values);
    }

    public ArrayList<ColorModel> getAllColor()
    {
        db = dbHelper.getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(tbName);
        ArrayList<ColorModel> colorModels = new ArrayList<>();
        Cursor cursor = qb.query(db,null,null,null,null,null,null);
        while (cursor.moveToNext())
        {
            colorModels.add(
                    new ColorModel(
                    cursor.getInt(cursor.getColumnIndex(colId)),
                            cursor.getInt(cursor.getColumnIndex(colRed)),
                            cursor.getInt(cursor.getColumnIndex(colGreen)),
                            cursor.getInt(cursor.getColumnIndex(colBlue))
                )
            );
        }
       return colorModels;
    }

    ColorModel model;
    public ColorModel getColorById(int id)
    {
        db = dbHelper.getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(tbName);
       String selection = colId+"=?";
       String selectionArgs[] = new String[] {String.valueOf(id)};
        Cursor cursor = qb.query(db,null,selection,selectionArgs,null,null,null);
        while (cursor.moveToNext())
        {
                   model = new ColorModel(
                            cursor.getInt(cursor.getColumnIndex(colId)),
                            cursor.getInt(cursor.getColumnIndex(colRed)),
                            cursor.getInt(cursor.getColumnIndex(colGreen)),
                            cursor.getInt(cursor.getColumnIndex(colBlue))
                    );
        }
        return model;
    }
}
