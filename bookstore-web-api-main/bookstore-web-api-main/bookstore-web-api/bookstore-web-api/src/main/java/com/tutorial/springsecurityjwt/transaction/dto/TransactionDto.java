package com.tutorial.springsecurityjwt.transaction.dto;


import java.util.Date;

public class TransactionDto {
    public record TransactionRequest(String transactionId, float cost, String userId, Date date){

    }


    public record Response(String message, TransactionDto.TransactionRequest request){

    }
}
