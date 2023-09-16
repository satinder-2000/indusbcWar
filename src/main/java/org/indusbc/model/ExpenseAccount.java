package org.indusbc.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "EXPENSE_ACCOUNT")
public class ExpenseAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "EXPENSE_ACCOUNT_HASH")
    private String expenseAccountHash;
    @Column(name = "EXPENSE_CATEGORY_ID")
    private int expenseCategoryId;
    @Column(name = "EXPENSE_PARTY_ID")
    private int expensePartyId;
    @Column(name = "CREATED_ON")
    private Timestamp createdOn;
    @Column(name = "YEAR")
    private int year;
    @Column(name = "YTD_BALANCE")
    // It is important to initialse ytdBalance to 0 String to avoid null pointer exception 
    //  as the field participates in BigDecimal arithmatic operations.
    private String ytdBalance="0";
    @Transient
    private String moneyIn;
    @Transient
    private String moneyOut;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpenseAccountHash() {
        return expenseAccountHash;
    }

    public void setExpenseAccountHash(String expenseAccountHash) {
        this.expenseAccountHash = expenseAccountHash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExpenseCategoryId() {
        return expenseCategoryId;
    }

    public void setExpenseCategoryId(int expenseCategoryId) {
        this.expenseCategoryId = expenseCategoryId;
    }

    public int getExpensePartyId() {
        return expensePartyId;
    }

    public void setExpensePartyId(int expensePartyId) {
        this.expensePartyId = expensePartyId;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public String getYtdBalance() {
        return ytdBalance;
    }

    public void setYtdBalance(String ytdBalance) {
        this.ytdBalance = ytdBalance;
    }

    public String getMoneyIn() {
        return moneyIn;
    }

    public void setMoneyIn(String moneyIn) {
        this.moneyIn = moneyIn;
    }

    public String getMoneyOut() {
        return moneyOut;
    }

    public void setMoneyOut(String moneyOut) {
        this.moneyOut = moneyOut;
    }

    

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
 }
