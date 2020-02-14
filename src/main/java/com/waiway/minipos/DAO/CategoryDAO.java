package com.waiway.minipos.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.waiway.minipos.Model.CategoryModel;

import java.util.ArrayList;

public class CategoryDAO {
    public String colId = "Id",
            colName = "Name",
            colColorId = "ColorId",
            colDisable = "Disable",
            tbName = "Category";

    DBHelper dbHelper;
    SQLiteDatabase db;

    public CategoryDAO(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getReadableDatabase();
    }
    public  int saveModel(CategoryModel model)
    {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(colName,model.Name);
        values.put(colColorId,model.ColorId);
        values.put(colDisable,model.Disable);
        int id = (int) db.insert(tbName,null,values);
        return id;
    }

    public ArrayList<CategoryModel> getModels()
    {
        db = dbHelper.getReadableDatabase();

        ArrayList<CategoryModel> categoryModels = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from "+tbName,null);
        while (cursor.moveToNext())
        {
            CategoryModel model = new CategoryModel();
            model.Id = cursor.getInt(cursor.getColumnIndex(colId));
            model.Name = cursor.getString(cursor.getColumnIndex(colName));
            model.ColorId = cursor.getInt(cursor.getColumnIndex(colColorId));
            model.Disable = cursor.getInt(cursor.getColumnIndex(colDisable));
            categoryModels.add(model);
        }
        return categoryModels;
    }


}
