package com.single.valar.keepaccounts.Javabean;

public class CostTypeData {
    private String typeName;
    private int imgID;
    public CostTypeData(String name,int imgID){
        this.typeName=name;
        this.imgID=imgID;
    }

    public int getImgID() {
        return imgID;
    }

    public String getTypeName() {
        return typeName;
    }
}
