package com.hht.myfi.DTO;

public class DTO_Debt {
    private int debtID;
    private String debtName;
    private long debtAmount;
    private double debtInterestRate;
    private String debtDate;
    private String debtExpirationDate;
    private String debtNote;

    public DTO_Debt() {
    }

    public DTO_Debt(int debtID, String debtName, long debtAmount, double debtInterestRate, String debtDate, String debtExpirationDate, String debtNote) {
        this.debtID = debtID;
        this.debtName = debtName;
        this.debtAmount = debtAmount;
        this.debtInterestRate = debtInterestRate;
        this.debtDate = debtDate;
        this.debtExpirationDate = debtExpirationDate;
        this.debtNote = debtNote;
    }

    public DTO_Debt(String debtName, long debtAmount, double debtInterestRate, String debtDate, String debtExpirationDate, String debtNote) {
        this.debtName = debtName;
        this.debtAmount = debtAmount;
        this.debtInterestRate = debtInterestRate;
        this.debtDate = debtDate;
        this.debtExpirationDate = debtExpirationDate;
        this.debtNote = debtNote;
    }

    public int getDebtID() {
        return debtID;
    }

    public void setDebtID(int debtID) {
        this.debtID = debtID;
    }

    public String getDebtName() {
        return debtName;
    }

    public void setDebtName(String debtName) {
        this.debtName = debtName;
    }

    public long getDebtAmount() {
        return debtAmount;
    }

    public void setDebtAmount(long debtAmount) {
        this.debtAmount = debtAmount;
    }

    public double getDebtInterestRate() {
        return debtInterestRate;
    }

    public void setDebtInterestRate(double debtInterestRate) {
        this.debtInterestRate = debtInterestRate;
    }

    public String getDebtDate() {
        return debtDate;
    }

    public void setDebtDate(String debtDate) {
        this.debtDate = debtDate;
    }

    public String getDebtExpirationDate() {
        return debtExpirationDate;
    }

    public void setDebtExpirationDate(String debtExpirationDate) {
        this.debtExpirationDate = debtExpirationDate;
    }

    public String getDebtNote() { return debtNote; }

    public void setDebtNote(String debtNote) { this.debtNote = debtNote; }
}
