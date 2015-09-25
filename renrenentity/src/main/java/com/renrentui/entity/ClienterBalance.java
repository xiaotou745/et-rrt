package com.renrentui.entity;

import java.math.BigDecimal;

public class ClienterBalance {
    private Long id;

    private Long clienterid;

    private BigDecimal balance;

    private BigDecimal withdraw;

    private BigDecimal hadwithdraw;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienterid() {
        return clienterid;
    }

    public void setClienterid(Long clienterid) {
        this.clienterid = clienterid;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(BigDecimal withdraw) {
        this.withdraw = withdraw;
    }

    public BigDecimal getHadwithdraw() {
        return hadwithdraw;
    }

    public void setHadwithdraw(BigDecimal hadwithdraw) {
        this.hadwithdraw = hadwithdraw;
    }
}