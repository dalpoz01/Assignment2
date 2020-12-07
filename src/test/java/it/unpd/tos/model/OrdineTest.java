////////////////////////////////////////////////////////////////////
// [STEFANO] [DAL POZ] [1204683]
////////////////////////////////////////////////////////////////////
package it.unpd.tos.model;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.unipd.tos.business.User;
import it.unipd.tos.model.ItemType;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.Ordine;

public class OrdineTest {
    private MenuItem item;
    private List<MenuItem> list;
    private User user;
    private LocalTime ora;
    private Ordine ordine;

    @Before
    public void setup() {
        list = new ArrayList<MenuItem>();
        user = new User("Luca","Toni",12367,LocalDate.of(1980, 6, 9));
        ora = LocalTime.of(13,00);
        item = new MenuItem(ItemType.Bevande, "Spritz", 3);
        list.add(item);
        ordine = new Ordine(list, user, ora, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListaVuota() {
        list.clear();
        new Ordine(list, user, ora, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUserNullo() {
        new Ordine(list, null, ora, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOraNulla() {
        new Ordine(list, user, null, 0);
    }

    @Test
    public void testSetPrezzo() {
        ordine.setPrezzo(14);
        assertEquals(14,ordine.getPrezzo(),0.0);
    }

    @Test
    public void testGetUser(){
        assertEquals(ordine.getUser(), user);
    }

    @Test
    public void testGetOrarioOrdine(){
        assertEquals(ordine.getOrarioOrdine(), ora);
    }

    @Test
    public void testGetOrdine(){      
        assertEquals(ordine.getOrdine(), list);
    }
} 