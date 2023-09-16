package org.indusbc.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CENTRAL_ACCOUNT")
public class CentralAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "ACCOUNT_NAME")
    private String accountName;
    @Column(name = "YEAR")
    private int year;
    @Column(name = "REVENUE_ACCOUNT_HASH")
    private String revenueAccountHash;
    @Column(name = "AMOUNT")
    private String amount;
    @Column(name = "EXPENSE_ACCOUNT_HASH")
    private String expenseAccountHash;
    @Column(name = "TRANSACTION_DATE")
    private LocalDateTime transactionDate;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRevenueAccountHash() {
        return revenueAccountHash;
    }

    public void setRevenueAccountHash(String revenueAccountHash) {
        this.revenueAccountHash = revenueAccountHash;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    

    public String getExpenseAccountHash() {
        return expenseAccountHash;
    }

    public void setExpenseAccountHash(String expenseAccountHash) {
        this.expenseAccountHash = expenseAccountHash;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

}
