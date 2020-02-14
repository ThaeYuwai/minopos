package com.waiway.minipos.Model;

public class ItemModel {

    public int Id,CategoryId,BrandId,UnitId,OPrice,SPrice,ColorId,Disable;
    public String Name,PicturePath;

    public ItemModel(int id, int categoryId, int brandId, int unitId, int OPrice, int SPrice, int colorId, int disable, String name, String picturePath) {
        Id = id;
        CategoryId = categoryId;
        BrandId = brandId;
        UnitId = unitId;
        this.OPrice = OPrice;
        this.SPrice = SPrice;
        ColorId = colorId;
        Disable = disable;
        Name = name;
        PicturePath = picturePath;
    }

    public ItemModel() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public int getBrandId() {
        return BrandId;
    }

    public void setBrandId(int brandId) {
        BrandId = brandId;
    }

    public int getUnitId() {
        return UnitId;
    }

    public void setUnitId(int unitId) {
        UnitId = unitId;
    }

    public int getOPrice() {
        return OPrice;
    }

    public void setOPrice(int OPrice) {
        this.OPrice = OPrice;
    }

    public int getSPrice() {
        return SPrice;
    }

    public void setSPrice(int SPrice) {
        this.SPrice = SPrice;
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

    public String getPicturePath() {
        return PicturePath;
    }

    public void setPicturePath(String picturePath) {
        PicturePath = picturePath;
    }
}
