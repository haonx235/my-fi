package com.hht.myfi.DTO;

public class DTO_Expense {
    private int expenseID;
    private String expenseName;
    private long expenseAmount;
    //private int expenseCategoryID;
    private String expenseDate;
    private String expenseNote;

    public DTO_Expense() {
    }

    public DTO_Expense(int expenseID, String expenseName, long expenseAmount, /*int expenseCategoryID,*/ String expenseDate, String expenseNote) {
        this.expenseID = expenseID;
        this.expenseName = expenseName;
        this.expenseAmount = expenseAmount;
        //this.expenseCategoryID = expenseCategoryID;
        this.expenseDate = expenseDate;
        this.expenseNote = expenseNote;
    }

    public DTO_Expense(String expenseName, long expenseAmount, String expenseDate, String expenseNote) {
        this.expenseName = expenseName;
        this.expenseAmount = expenseAmount;
        this.expenseDate = expenseDate;
        this.expenseNote = expenseNote;
    }

    public int getExpenseID() {
        return expenseID;
    }

    public void setExpenseID(int expenseID) {
        this.expenseID = expenseID;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public long getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(long expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

//    public int getExpenseCategoryID() {
//        return expenseCategoryID;
//    }
//
//    public void setExpenseCategoryID(int expenseCategoryID) {
//        this.expenseCategoryID = expenseCategoryID;
//    }

    public String getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(String expenseDate) {
        this.expenseDate = expenseDate;
    }

    public String getExpenseNote() {
        return expenseNote;
    }

    public void setExpenseNote(String expenseNote) {
        this.expenseNote = expenseNote;
    }
}
