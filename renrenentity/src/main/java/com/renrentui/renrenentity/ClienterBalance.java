package com.renrentui.renrenentity;



public class ClienterBalance {
    private Long id;

    private Long clienterId;

    private Double balance;

    private Double withdraw;

    private Double hadWithdraw;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(Double withdraw) {
        this.withdraw = withdraw;
    }

	public Long getClienterId() {
		return clienterId;
	}

	public void setClienterId(Long clienterId) {
		this.clienterId = clienterId;
	}

	public Double getHadWithdraw() {
		return hadWithdraw;
	}

	public void setHadWithdraw(Double hadWithdraw) {
		this.hadWithdraw = hadWithdraw;
	}

   
}