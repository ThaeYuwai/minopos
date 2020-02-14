package com.waiway.minipos.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.waiway.minipos.Model.BrandModel;
import com.waiway.minipos.Model.CategoryModel;
import com.waiway.minipos.Model.ItemModel;

import java.util.ArrayList;

public class ItemDAO {
    public String colId = "Id",
            colName = "Name",
            colCategoryId = "CategoryId",
            colBrandId = "BrandId",
            colUnitId = "UnitId",
            colOPrice = "OPrice",
            colSPrice = "SPrice",
            colColorId = "ColorId",
            colPicturePath = "PicturePath",
            colDisable = "Disable",
            tbName = "Item";

    DBHelper dbHelper;
    SQLiteDatabase db;

    public ItemDAO(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getReadableDatabase();
    }

    public  int saveModel(ItemModel model)
    {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(colName,model.Name);
        values.put(colCategoryId,model.CategoryId);
        values.put(colBrandId,model.BrandId);
        values.put(colUnitId,model.UnitId);
        values.put(colOPrice,model.OPrice);
        values.put(colSPrice,model.SPrice);
        values.put(colColorId,model.ColorId);
        values.put(colPicturePath,model.PicturePath);
        values.put(colDisable,model.Disable);
        int id = (int) db.insert(tbName,null,values);
        return id;
    }

    public ArrayList<ItemModel> getModels()
    {
        db = dbHelper.getReadableDatabase();

        ArrayList<ItemModel> itemModels = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from "+tbName,null);
        while (cursor.moveToNext())
        {
            ItemModel model = new ItemModel();
            model.Id = cursor.getInt(cursor.getColumnIndex(colId));
            model.Name = cursor.getString(cursor.getColumnIndex(colName));
            model.CategoryId = cursor.getInt(cursor.getColumnIndex(colCategoryId));
            model.BrandId = cursor.getInt(cursor.getColumnIndex(colBrandId));
            model.UnitId = cursor.getInt(cursor.getColumnIndex(colUnitId));
            model.OPrice = cursor.getInt(cursor.getColumnIndex(colOPrice));
            model.SPrice = cursor.getInt(cursor.getColumnIndex(colSPrice));
            model.ColorId = cursor.getInt(cursor.getColumnIndex(colColorId));
            model.PicturePath = cursor.getString(cursor.getColumnIndex(colPicturePath));
            model.Disable = cursor.getInt(cursor.getColumnIndex(colDisable));
            itemModels.add(model);
        }
        return itemModels;
    }
}
