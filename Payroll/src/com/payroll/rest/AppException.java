package com.payroll.rest;

import java.util.Date;

public class AppException extends RuntimeException
{
    private static final long serialVersionUID = 1L;
     
    private Date date;
    private String message;
     
    public AppException(Date date, String message) {
        super();
        this.date = date;
        this.message = message;
    }
 
    public Date getDate() {
        return date;
    }
 
    public String getMessage() {
        return message;
    }
 
    @Override
    public String toString() {
        return "AuthException [date=" + date + ", message=" + message + "]";
    }
}
