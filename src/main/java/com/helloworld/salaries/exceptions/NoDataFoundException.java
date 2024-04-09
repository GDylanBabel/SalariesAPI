package com.helloworld.salaries.exceptions;

public class NoDataFoundException extends Exception {

    public NoDataFoundException(String query) {
        super("No data found for " + query);
    }
}
