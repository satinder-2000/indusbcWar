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
public class ExpenseAccountTransfer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    
    @Column(name = "EXPENSE_ACCOUNT_ID")
    private int expenseAccountId;
    
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

    public int getExpenseAccountId() {
        return expenseAccountId;
    }

    public void setExpenseAccountId(int expenseAccountId) {
        this.expenseAccountId = expenseAccountId;
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
