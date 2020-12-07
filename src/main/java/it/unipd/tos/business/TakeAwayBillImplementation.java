////////////////////////////////////////////////////////////////////
// [STEFANO] [DAL POZ] [1204683]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import java.util.List;

import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.MenuItem;

public class TakeAwayBillImplementation implements TakeAwayBill {

    @Override
    public double getOrderPrice(List<MenuItem> itemsOrdered, User user) throws 
        RestaurantBillException {
        double totale = 0;
        if(itemsOrdered==null) {
            throw new RestaurantBillException("Ordine non valido");
        }
        if(itemsOrdered.isEmpty()) {
            throw new RestaurantBillException("Nessun elemento inserito");
        }
        for(int i = 0; i < itemsOrdered.size(); i++) {
            totale += itemsOrdered.get(i).getPrezzo();
        }
        return totale;
    }

}