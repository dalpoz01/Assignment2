////////////////////////////////////////////////////////////////////
// [STEFANO] [DAL POZ] [1204683]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business.exception;

public class RestaurantBillException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public RestaurantBillException(String messaggio) {
        super(messaggio);
    }
} 