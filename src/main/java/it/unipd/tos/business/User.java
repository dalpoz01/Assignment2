////////////////////////////////////////////////////////////////////
// [STEFANO] [DAL POZ] [1204683]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import java.time.LocalDate;

public class User {
    private String Nome;
    private String Cognome;
    private int ID;
    private LocalDate DatadiNascita;
    public User(String nome, String cognome, int id, LocalDate datadiNascita) {
        if(id <= 0) {
            throw new IllegalArgumentException("ID nullo");
        }
        if(nome == null) {
            throw new IllegalArgumentException("Nome non valido");
        }
        if(cognome == null) {
            throw new IllegalArgumentException("Cognome non valido");
        }
        if(datadiNascita == null) {
            throw new IllegalArgumentException("Data non valida");
        }
        Nome = nome;
        Cognome = cognome;
        this.ID = id;
        DatadiNascita = datadiNascita;
    }
    public String getNome() {
        return Nome;
    }
    public String getCognome() {
        return Cognome;
    }
    public int getID() {
        return ID;
    }
    public int getEta() {
        return LocalDate.now().getYear()-DatadiNascita.getYear();
    }
}
