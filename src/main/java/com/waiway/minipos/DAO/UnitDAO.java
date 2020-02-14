package com.waiway.minipos.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.waiway.minipos.Model.BrandModel;
import com.waiway.minipos.Model.UnitModel;

import java.util.ArrayList;

public class UnitDAO {
    public String colId = "Id",
            colName = "Name",
            colColorId = "ColorId",
            colDisable = "Disable",
            tbName = "Unit";

    DBHelper dbHelper;
    SQLiteDatabase db;

    public UnitDAO(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getReadableDatabase();
    }

    public  int saveModel(UnitModel model)
    {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(colName,model.Name);
        values.put(colColorId,model.ColorId);
        values.put(colDisable,model.Disable);
        int id = (int) db.insert(tbName,null,values);
        return id;
    }

    public ArrayList<UnitModel> getModels()
    {
        db = dbHelper.getReadableDatabase();

        ArrayList<UnitModel> unitModels = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from "+tbName,null);
        while (cursor.moveToNext())
        {
            UnitModel model = new UnitModel();
            model.Id = cursor.getInt(cursor.getColumnIndex(colId));
            model.Name = cursor.getString(cursor.getColumnIndex(colName));
            model.ColorId = cursor.getInt(cursor.getColumnIndex(colColorId));
            model.Disable = cursor.getInt(cursor.getColumnIndex(colDisable));
            unitModels.add(model);
        }
        return unitModels;
    }
}
