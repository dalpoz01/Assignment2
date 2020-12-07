////////////////////////////////////////////////////////////////////
// [STEFANO] [DAL POZ] [1204683]
////////////////////////////////////////////////////////////////////
package it.unpd.tos.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.unipd.tos.model.ItemType;
import it.unipd.tos.model.MenuItem;

public class MenuItemTest {
    private MenuItem Budino;
    private MenuItem Bevanda;
    private MenuItem Gelato;

    @Before
    public void setup() {
        Budino = new MenuItem( ItemType.Budini, "Pinguino", 10);
        Bevanda = new MenuItem(ItemType.Bevande, "Spritz", 2);
        Gelato = new MenuItem( ItemType.Gelati, "Coppa Nafta", 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTipologiaNull() {
        new MenuItem(null, "Spritz", 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNomeNullo() {
        new MenuItem(ItemType.Budini, null, 4D);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPrezzoNegativo() {
        new MenuItem(ItemType.Bevande, "Spritz", -2);
    }

    @Test
    public void testGetNome() {
        assertEquals("Pinguino", Budino.getNome());
        assertEquals("Spritz", Bevanda.getNome());
        assertEquals("Coppa Nafta", Gelato.getNome());
    }

    @Test
    public void testGetPrezzo() {
        assertEquals(10, Budino.getPrezzo(), 0.0);
        assertEquals(2, Bevanda.getPrezzo(), 0.0);
        assertEquals(5, Gelato.getPrezzo(), 0.0);
    }

    @Test
    public void testGetType() {
        assertEquals(ItemType.Budini, Budino.getItemType());
        assertEquals(ItemType.Bevande, Bevanda.getItemType());
        assertEquals(ItemType.Gelati, Gelato.getItemType());
    }
}