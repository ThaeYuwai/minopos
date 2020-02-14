package com.waiway.minipos.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.waiway.minipos.Model.BrandModel;
import com.waiway.minipos.Model.CategoryModel;

import java.util.ArrayList;

public class BrandDAO {
    public String colId = "Id",
            colName = "Name",
            colColorId = "ColorId",
            colDisable = "Disable",
            tbName = "Brand";

    DBHelper dbHelper;
    SQLiteDatabase db;

    public BrandDAO(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getReadableDatabase();
    }

    public  int saveModel(BrandModel model)
    {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(colName,model.Name);
        values.put(colColorId,model.ColorId);
        values.put(colDisable,model.Disable);
        int id = (int) db.insert(tbName,null,values);
        return id;
    }

    public ArrayList<BrandModel> getModels()
    {
        db = dbHelper.getReadableDatabase();

        ArrayList<BrandModel> brandModels = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from "+tbName,null);
        while (cursor.moveToNext())
        {
            BrandModel model = new BrandModel();
            model.Id = cursor.getInt(cursor.getColumnIndex(colId));
            model.Name = cursor.getString(cursor.getColumnIndex(colName));
            model.ColorId = cursor.getInt(cursor.getColumnIndex(colColorId));
            model.Disable = cursor.getInt(cursor.getColumnIndex(colDisable));
            brandModels.add(model);
        }
        return brandModels;
    }


    public BrandModel getModelById(int id)
    {
        BrandModel model = new BrandModel();
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " +tbName+ " where " +colId+" = "+id,null);
        while (cursor.moveToNext())
        {
            model.Id = cursor.getInt(cursor.getColumnIndex(colId));
            model.Name = cursor.getString(cursor.getColumnIndex(colName));
            model.ColorId = cursor.getInt(cursor.getColumnIndex(colColorId));
            model.Disable = cursor.getInt(cursor.getColumnIndex(colDisable));
        }
        return model;
    }

}
