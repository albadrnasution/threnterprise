package model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Didik
 */
public class ItemJalan {
    private int idi;
    private String name;
    private String description;
    private Moda moda;
    private int origin;
    private int dest;
    private int bprice_child;
    private int idmoedik;

    public int getBprice_child() {
        return bprice_child;
    }

    public void setBprice_child(int bprice_child) {
        this.bprice_child = bprice_child;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDest() {
        return dest;
    }

    public void setDest(int dest) {
        this.dest = dest;
    }

    public int getIdi() {
        return idi;
    }

    public void setIdi(int idi) {
        this.idi = idi;
    }

    public int getIdmoedik() {
        return idmoedik;
    }

    public void setIdmoedik(int idmoedik) {
        this.idmoedik = idmoedik;
    }

    public Moda getModa() {
        return moda;
    }

    public void setModa(Moda moda) {
        this.moda = moda;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrigin() {
        return origin;
    }

    public void setOrigin(int origin) {
        this.origin = origin;
    }
    
    
    
}
