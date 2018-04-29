package com.hht.myfi.DTO;

public class DTO_ExpenseCategory {
    private int expenseCategoryID;
    private String expenseCategoryName;

    public DTO_ExpenseCategory() {
    }

    public DTO_ExpenseCategory(String expenseCategoryName) {
        this.expenseCategoryName = expenseCategoryName;
    }

    public DTO_ExpenseCategory(int expenseCategoryID, String expenseCategoryName) {
        this.expenseCategoryID = expenseCategoryID;
        this.expenseCategoryName = expenseCategoryName;
    }

    public int getExpenseCategoryID() {
        return expenseCategoryID;
    }

    public void setExpenseCategoryID(int expenseCategoryID) {
        this.expenseCategoryID = expenseCategoryID;
    }

    public String getExpenseCategoryName() {
        return expenseCategoryName;
    }

    public void setExpenseCategoryName(String expenseCategoryName) {
        this.expenseCategoryName = expenseCategoryName;
    }
}
