package model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Didik
 */

public class Moda {
    public static final String BUS = "bus";
    public static final String MOBIL = "mobil";
    public static final String KERETA_API = "kereta api";
    public static final String KAPAL = "kapal";
    public static final String PESAWAT = "pesawat";
    private String value;

   public Moda(String value){
        if(value.equals("bus") || value.equals("mobil") || value.equals("kereta api") || value.equals("kapal") || value.equals("pesawat")){
            this.value = value;
        }else{
            this.value = "";
        }
    }

    Moda() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
