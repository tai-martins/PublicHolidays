package br.inatel.idp.PublicHolidays.exception;

public class ConnectionException extends RuntimeException{
    public ConnectionException(String url){
        super(String.format("Was not possible to communicate with Holiday at location ", url));
    }

}
