////////////////////////////////////////////////////////////////////
// [STEFANO] [DAL POZ] [1204683]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

public class UserTest {
    private User prova = new User("Luca","Cavo",12345,LocalDate.of(2001, 3, 10));

    @Test(expected=IllegalArgumentException.class)
    public void testIDNullo() {
        new User("Luca","Cavo",-1,LocalDate.of(2001, 3, 10));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNomeNullo() {
        new User(null,"Cavo",12345,LocalDate.of(2001, 3, 10));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testCognomeNullo() {
        new User("Luca",null,12345,LocalDate.of(2001, 3, 10));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testDataNulla() {
        new User("Luca","Cavo",12345,null);
    }

    @Test
    public void testGetNome() {
        assertEquals(prova.getNome(), "Luca");
    }

    @Test
    public void testGetID(){
        assertEquals(prova.getID(), 12345);
    }

    @Test
    public void testGetCognome() {
        assertEquals(prova.getCognome(), "Cavo");
    }

    @Test
    public void testGetEta() {
        assertEquals(prova.getEta(), 19);  
    }
}