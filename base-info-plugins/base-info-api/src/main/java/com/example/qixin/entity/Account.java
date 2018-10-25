package com.example.qixin.entity;

import lombok.Data;

/**
 * 创  建   时  间： 2018/10/22 23:22
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Data
public class Account {

    private String id;
    private String customerId;
    private String number;
    private String amount;

    public Account() {
    }

    public Account(String customerId, String number, String amount) {
        this.customerId = customerId;
        this.number = number;
        this.amount = amount;
    }

    public Account(String id, String customerId, String number, String amount) {
        this.id = id;
        this.customerId = customerId;
        this.number = number;
        this.amount = amount;
    }
}
