package org.indusbc.model;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author user
 */
@Entity
@Table(name = "EXPENSE_ACCOUNT_TRANSFER")
public class RevenueAccountTransfer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    
    @Column(name = "REVENUE_ACCOUNT_ID")
    private int revenueAccountId;
    
    @Column(name = "CENTRAL_ACCOUNT_ID")
    private int centralAccountId;
    
    @Column(name = "MONEY_IN")
    private String moneyIn;
    
    @Column(name = "MONEY_OUT")
    private String moneyOut;
    
    @Column(name = "TRANSACTION_TIME")
    private LocalDateTime transactionTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRevenueAccountId() {
        return revenueAccountId;
    }

    public void setRevenueAccountId(int revenueAccountId) {
        this.revenueAccountId = revenueAccountId;
    }

    public int getCentralAccountId() {
        return centralAccountId;
    }

    public void setCentralAccountId(int centralAccountId) {
        this.centralAccountId = centralAccountId;
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

    

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }
    
}