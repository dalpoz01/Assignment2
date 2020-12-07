////////////////////////////////////////////////////////////////////
// [STEFANO] [DAL POZ] [1204683]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

public class MenuItem {
    private ItemType itemType;
    private String nome;
    private double prezzo;
    public MenuItem(ItemType itemType, String nome, double prezzo) {
        if(itemType == null) {
            throw new IllegalArgumentException("Elemento nullo");
        }
        if(nome == null) {
            throw new IllegalArgumentException("Nome non valido");
        }
        if(prezzo <= 0) {
            throw new IllegalArgumentException("Prezzo non valido");
        }
        this.itemType = itemType;
        this.nome = nome;
        this.prezzo = prezzo;
    }
    public ItemType getItemType() {
        return itemType;
    }
    public String getNome() {
        return nome;
    }
    public double getPrezzo() {
        return prezzo;
    }
}