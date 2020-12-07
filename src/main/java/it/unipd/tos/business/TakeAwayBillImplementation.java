////////////////////////////////////////////////////////////////////
// [STEFANO] [DAL POZ] [1204683]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.ItemType;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.Ordine;

public class TakeAwayBillImplementation implements TakeAwayBill {

    @Override
    public double getOrderPrice(List<MenuItem> itemsOrdered, User user) throws 
        RestaurantBillException {
        double totale = 0, minimo = 0;
        int gelati = 0, totaleGelatiBudini = 0;
        if(itemsOrdered==null) {
            throw new RestaurantBillException("Ordine non valido");
        }
        if(itemsOrdered.isEmpty()) {
            throw new RestaurantBillException("Nessun elemento inserito");
        }
        if(itemsOrdered.size()>30) {
            throw new RestaurantBillException(
                    "Numero di elementi troppo elevato");
        }
        for(int i = 0; i < itemsOrdered.size(); i++) {
            if(itemsOrdered.get(i).getItemType().equals(ItemType.Gelati)) {
                if(gelati == 0) {
                    minimo = itemsOrdered.get(i).getPrezzo();
                }else {
                    if (itemsOrdered.get(i).getPrezzo()<minimo) {
                        minimo = itemsOrdered.get(i).getPrezzo();
                    }
                }
                gelati++;
            }
            if(itemsOrdered.get(i).getItemType() != ItemType.Bevande){
                totaleGelatiBudini +=itemsOrdered.get(i).getPrezzo();
            }
            totale += itemsOrdered.get(i).getPrezzo();
        }
        if(gelati > 5) {
            totale -= minimo/2;
        }
        if(totaleGelatiBudini > 50) {
            totale -= totale*0.1;
        }
        if(totale<10) {
            totale += 0.5;
        }
        return totale;
    }
    
    public List<Ordine> getFreeOrders(List<Ordine> ordini) throws 
    RestaurantBillException {
        List<Ordine> ordiniGratis = new ArrayList<Ordine>();
        for (int i = 0; i < ordini.size(); i++) {
            if(ordini.get(i).getUser().getEta()<18 &&
        ordini.get(i).getOrarioOrdine().isAfter(LocalTime.of(18,00,00,00)) && 
        ordini.get(i).getOrarioOrdine().isBefore(LocalTime.of(19,00,00,00))) { 
                ordiniGratis.add(ordini.get(i));
            }
        }
        if(ordiniGratis.size() > 9){
            for(int i=0; i<10; i++) {
              int indiceRandom = (int)(ordiniGratis.size() * Math.random());
              if(ordiniGratis.get(indiceRandom).getPrezzo() == 0) {
                  i--;
              }else {
                  ordiniGratis.get(indiceRandom).setPrezzo(0);
              }
            }
        }else {
            throw new RestaurantBillException(
                    "Ordini non sufficienti per riceverne gratis");
        }
        return ordiniGratis;
    }

}