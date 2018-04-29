package com.hht.myfi.DTO;

public class DTO_Loan {
    private int loanID;
    private String loanName;
    private long loanAmount;
    private double loanInterestRate;
    private String loanDate;
    private String loanExpirationDate;
    private String loanNote;

    public DTO_Loan() {
    }

    public DTO_Loan(int loanID, String loanName, long loanAmount, double loanInterestRate, String loanDate, String loanExpirationDate, String loanNote) {
        this.loanID = loanID;
        this.loanName = loanName;
        this.loanAmount = loanAmount;
        this.loanInterestRate = loanInterestRate;
        this.loanDate = loanDate;
        this.loanExpirationDate = loanExpirationDate;
        this.loanNote = loanNote;
    }

    public DTO_Loan(String loanName, long loanAmount, double loanInterestRate, String loanDate, String loanExpirationDate, String loanNote) {
        this.loanName = loanName;
        this.loanAmount = loanAmount;
        this.loanInterestRate = loanInterestRate;
        this.loanDate = loanDate;
        this.loanExpirationDate = loanExpirationDate;
        this.loanNote = loanNote;
    }

    public int getLoanID() {
        return loanID;
    }

    public void setLoanID(int loanID) {
        this.loanID = loanID;
    }

    public String getLoanName() {
        return loanName;
    }

    public void setLoanName(String loanName) {
        this.loanName = loanName;
    }

    public long getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(long loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getLoanInterestRate() {
        return loanInterestRate;
    }

    public void setLoanInterestRate(double loanInterestRate) {
        this.loanInterestRate = loanInterestRate;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getLoanExpirationDate() {
        return loanExpirationDate;
    }

    public void setLoanExpirationDate(String ngaythu) {
        this.loanExpirationDate = loanExpirationDate;
    }

    public String getLoanNote() { return loanNote; }

    public void setLoanNote(String loanNote) { this.loanNote = loanNote; }
}
