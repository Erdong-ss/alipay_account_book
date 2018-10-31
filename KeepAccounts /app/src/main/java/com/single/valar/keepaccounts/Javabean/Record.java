package com.single.valar.keepaccounts.Javabean;

import org.litepal.crud.LitePalSupport;

public class Record extends LitePalSupport{
    //消费类型
    private int type;
    private int year;
    private int month;
    private int day;
    private boolean isIncome;
    private String Note;
    private float Amount;

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void setAmount(float amount) {
        Amount = amount;
    }

    public float getAmount() {
        return Amount;
    }

    public void setIncome(boolean income) {
        isIncome = income;
    }
    public boolean getIncome() {
        return isIncome;
    }
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }
}
