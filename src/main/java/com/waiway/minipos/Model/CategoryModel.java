package com.waiway.minipos.Model;

public class CategoryModel {
    public String Name;
    public  int Id,ColorId,Disable;

    public CategoryModel(String name, int id, int colorId, int disable) {
        Name = name;
        Id = id;
        ColorId = colorId;
        Disable = disable;
    }

    public CategoryModel() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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
}
