package com.waiway.minipos.Model;

public class UnitModel {
    public int Id,ColorId,Disable;
    public String Name;

    public UnitModel(int id, int colorId, int disable, String name) {
        Id = id;
        ColorId = colorId;
        Disable = disable;
        Name = name;
    }

    public UnitModel() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getColorId() {
        return ColorId;
    }

    public void setColorId(int colorId) {
        ColorId = colorId;
    }

    public int getDisable() {
        return Disable;
    }

    public void setDisable(int disable) {
        Disable = disable;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
