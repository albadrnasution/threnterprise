package model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Didik
 */
public class ItemBingkisan {
    private int idi;
    private String name;
    private String description;
    private int basic_price;
    private int id_tempe;

    public int getBasic_price() {
        return basic_price;
    }

    public void setBasic_price(int basic_price) {
        this.basic_price = basic_price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_tempe() {
        return id_tempe;
    }

    public void setId_tempe(int id_tempe) {
        this.id_tempe = id_tempe;
    }

    public int getIdi() {
        return idi;
    }

    public void setIdi(int idi) {
        this.idi = idi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
