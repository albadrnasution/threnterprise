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
    public static final String  BUS = "BUS";
    public static final String MOBIL = "MOBIL";
    public static final String KERETA_API = "KERETA API";
    public static final String KAPAL = "KAPAL";
    public static final String PESAWAT = "PESAWAT";
    private String value;

   public Moda(String value){
        if(value == "BUS" || value == "MOBIL" || value == "KERETA API" || value == "KAPAL" || value =="PESAWAT"){
            this.value = value;
        }else{
            this.value = "";
        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
