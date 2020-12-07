////////////////////////////////////////////////////////////////////
// [STEFANO] [DAL POZ] [1204683]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.ItemType;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.Ordine;

public class TakeAwayBillImplementationTest {
    private List<MenuItem> list;
    private TakeAwayBillImplementation test; 
    private User user;
    private LocalTime ora;

    @Before
    public void setup() {
        list = new ArrayList<MenuItem>();
        test = new TakeAwayBillImplementation();
        user = new User("Giacomo","Mazzo", 11123, LocalDate.of(2000, 1, 1));
        ora = LocalTime.of(18,10,00,00);
    }

    @Test
    public void testCalcoloTotale(){
        list.add(new MenuItem( ItemType.Budini, "Pinguino",6));
        list.add(new MenuItem( ItemType.Bevande, "Spritz", 3));
        list.add(new MenuItem( ItemType.Gelati, "Latte", 5));
        assertEquals(14, test.getOrderPrice(list,user), 0);
    }

    @Test(expected=RestaurantBillException.class)
    public void testListaVuota() {
        test.getOrderPrice(list, user);
    }

    @Test(expected=RestaurantBillException.class)
    public void testListaNulla() {
        list=null;
        test.getOrderPrice(list, user);
    }
    @Test
    public void testSconto50() {
        list.add(new MenuItem(ItemType.Gelati, "Cioccolato", 3));
        list.add(new MenuItem(ItemType.Budini, "Biancaneve", 7));
        list.add(new MenuItem(ItemType.Bevande, "Cocacola", 10));
        list.add(new MenuItem(ItemType.Gelati, "Puffy", 2));
        list.add(new MenuItem(ItemType.Gelati, "Latte", 5));
        list.add(new MenuItem(ItemType.Gelati, "Panna", 5));
        list.add(new MenuItem(ItemType.Gelati, "Stracciatella", 4));
        list.add(new MenuItem(ItemType.Gelati, "Pistacchio", 1));
        list.add(new MenuItem(ItemType.Gelati, "Limone", 10));
        list.add(new MenuItem(ItemType.Gelati, "Cocco", 7));
        assertEquals(53.5, test.getOrderPrice(list, user),0);
    }
    @Test
    public void testSconto10() {
        list.add(new MenuItem(ItemType.Gelati, "Cioccolato", 23));
        list.add(new MenuItem(ItemType.Budini, "Biancaneve", 17));
        list.add(new MenuItem(ItemType.Gelati, "Cocacola", 10));
        list.add(new MenuItem(ItemType.Budini, "Puffy", 12));
        list.add(new MenuItem(ItemType.Bevande, "Fanta", 5));
        list.add(new MenuItem(ItemType.Bevande, "Sprite", 5));
        list.add(new MenuItem(ItemType.Bevande, "Acqua", 4));
        list.add(new MenuItem(ItemType.Bevande, "Frizzante", 1));
        list.add(new MenuItem(ItemType.Budini, "Limone", 10));
        assertEquals(78.3, test.getOrderPrice(list, user),0);

    }
    @Test
    public void testSconto10_50() {
        list.add(new MenuItem(ItemType.Gelati, "Cioccolato", 3));
        list.add(new MenuItem(ItemType.Budini, "Biancaneve", 7));
        list.add(new MenuItem(ItemType.Bevande, "Cocacola", 10));
        list.add(new MenuItem(ItemType.Gelati, "Puffy", 2));
        list.add(new MenuItem(ItemType.Gelati, "Latte", 5));
        list.add(new MenuItem(ItemType.Gelati, "Panna", 5));
        list.add(new MenuItem(ItemType.Gelati, "Stracciatella", 4));
        list.add(new MenuItem(ItemType.Gelati, "Pistacchio", 1));
        list.add(new MenuItem(ItemType.Gelati, "Limone", 10));
        list.add(new MenuItem(ItemType.Gelati, "Cocco", 7));
        list.add(new MenuItem(ItemType.Budini, "Noce", 20));
        assertEquals(66.15, test.getOrderPrice(list, user),0);
    }
    @Test(expected = RestaurantBillException.class)
    public void testOrdineMaggiore30() throws RestaurantBillException{
        for(int i = 0; i<40; i++) {
            list.add(new MenuItem(ItemType.Bevande, "Spritz", 5));
        }
        test.getOrderPrice(list,user);
    }
    @Test
    public void testTotaleMinore10() {
        list.add(new MenuItem(ItemType.Gelati, "Cioccolato", 2));
        list.add(new MenuItem(ItemType.Budini, "Biancaneve", 4));
        assertEquals(6.5, test.getOrderPrice(list, user),0);
    }
    @Test
    public void testOrdiniRegalatiMinorenni() {
        List<Ordine> ordini = new ArrayList<Ordine>();
        list.add(new MenuItem( ItemType.Budini, "Pinguino",6));
        list.add(new MenuItem( ItemType.Bevande, "Spritz",2));
        String[] nomi =new String[]{"Luca", "Marco", "Matteo", "Giovanni",
                        "Valerio", "Giacinta", "Alessandra", "Luisa", "Elisabetta",
                        "Paolo", "Thomas", "Riccardo"};
        String cognome = "Pozzebon";
        int id = 12345;
        user = null;
        for(int i = 0; i < nomi.length; i++) {
            user = new User(nomi[i],cognome,id+i,LocalDate.of(2012, 3, 20));;
            ordini.add(new Ordine(list, user,  ora, test.getOrderPrice(list, user)));
        }

        List<Ordine> ordiniGratis = test.getFreeOrders(ordini);
        int ordiniRegalati = 0;
        for(Ordine ordine : ordiniGratis) {
            if(ordine.getPrezzo() == 0) {
                ordiniRegalati++;
            }
        }
        assertEquals(10,ordiniRegalati); 
    }

    @Test(expected=RestaurantBillException.class)
    public void testOrdiniRegalatiMinorenniMenoDi10() { 
        List<Ordine> ordini = new ArrayList<Ordine>();
        list.add(new MenuItem( ItemType.Bevande, "Spritz",3));
        String[] nomi =new String[]{"Luca", "Marco", "Giovanni", "Sara", "Micol"};
        String cognome = "Pozzebon";
        user = null;
        int id = 12345;
        for (int i = 0; i < nomi.length; i++) {
            user = new User(nomi[i],cognome,id+i,LocalDate.of(2013, 1, 13));;
            ordini.add(new Ordine(list, user,  ora, test.getOrderPrice(list, user)));
        }
        test.getFreeOrders(ordini);
    }
}