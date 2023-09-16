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
@Table(name = "REVENUE_ACCOUNT")
public class RevenueAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "REVENUE_PARTY_ID")
    private int revenuePartyId;
    @Column(name = "REVENUE_ACCOUNT_HASH")
    private String revenueAccountHash;
    @Column(name = "REVENUE_CATEGORY_ID")
    private int revenueCategoryId;
    @Column(name = "NAME")
    private String name;
    @Column(name="CREATED_ON")
    private Timestamp createdOn;
    @Column(name = "YEAR")
    private int year;
    @Column(name="YTD_BALANCE")
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

    public int getRevenuePartyId() {
        return revenuePartyId;
    }

    public void setRevenuePartyId(int revenuePartyId) {
        this.revenuePartyId = revenuePartyId;
    }

    

    public String getRevenueAccountHash() {
        return revenueAccountHash;
    }

    public void setRevenueAccountHash(String revenueAccountHash) {
        this.revenueAccountHash = revenueAccountHash;
    }

    public int getRevenueCategoryId() {
        return revenueCategoryId;
    }

    public void setRevenueCategoryId(int revenueCategoryId) {
        this.revenueCategoryId = revenueCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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