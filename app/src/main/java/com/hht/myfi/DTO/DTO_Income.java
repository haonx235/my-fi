package com.hht.myfi.DTO;

public class DTO_Income {
    private int incomeID;
    private String incomeName;
    private long incomeAmount;
    //private int incomeCategoryID;
    private String incomeDate;
    private String incomeNote;

    public DTO_Income() {
    }

    public DTO_Income(int incomeID, String incomeName, long incomeAmount, /*int incomeCategoryID,*/ String incomeDate, String incomeNote) {
        this.incomeID = incomeID;
        this.incomeName = incomeName;
        this.incomeAmount = incomeAmount;
        //this.incomeCategoryID = incomeCategoryID;
        this.incomeDate = incomeDate;
        this.incomeNote = incomeNote;
    }

    public DTO_Income(String incomeName, long incomeAmount, String incomeDate, String incomeNote) {
        this.incomeName = incomeName;
        this.incomeAmount = incomeAmount;
        this.incomeDate = incomeDate;
        this.incomeNote = incomeNote;
    }

    public int getIncomeID() {
        return incomeID;
    }

    public void setIncomeID(int incomeID) {
        this.incomeID = incomeID;
    }

    public String getIncomeName() {
        return incomeName;
    }

    public void setIncomeName(String incomeName) {
        this.incomeName = incomeName;
    }

    public long getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(long incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

//    public int getIncomeCategoryID() {
//        return incomeCategoryID;
//    }
//
//    public void setIncomeCategoryID(int incomeCategoryID) {
//        this.incomeCategoryID = incomeCategoryID;
//    }

    public String getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(String incomeDate) {
        this.incomeDate = incomeDate;
    }

    public String getIncomeNote() {
        return incomeNote;
    }

    public void setIncomeNote(String incomeNote) {
        this.incomeNote = incomeNote;
    }
}
