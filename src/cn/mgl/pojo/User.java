package cn.mgl.pojo;

import java.math.BigDecimal;

public class User {
    @Override
    public String toString() {
        return "User{" +
                "balance=" + balance +
                ", id=" + id +
                ", username='" + username + '\'' +
                '}';
    }

    BigDecimal balance;
    Integer id;
    String username;

    public User(BigDecimal balance, Integer id, String username) {
        this.balance = balance;
        this.id = id;
        this.username = username;
    }

    public User() {
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
