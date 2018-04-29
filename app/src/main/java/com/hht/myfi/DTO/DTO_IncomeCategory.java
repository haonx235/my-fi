package com.hht.myfi.DTO;

public class DTO_IncomeCategory {
    private int incomeCategoryID;
    private String incomeCategoryName;

    public DTO_IncomeCategory() {
    }

    public DTO_IncomeCategory(String incomeCategoryName) {
        this.incomeCategoryName = incomeCategoryName;
    }

    public DTO_IncomeCategory(int incomeCategoryID, String incomeCategoryName) {
        this.incomeCategoryID = incomeCategoryID;
        this.incomeCategoryName = incomeCategoryName;
    }

    public int getIncomeCategoryID() {
        return incomeCategoryID;
    }

    public void setIncomeCategoryID(int incomeCategoryID) {
        this.incomeCategoryID = incomeCategoryID;
    }

    public String getIncomeCategoryName() {
        return incomeCategoryName;
    }

    public void setIncomeCategoryName(String incomeCategoryName) {
        this.incomeCategoryName = incomeCategoryName;
    }
}
