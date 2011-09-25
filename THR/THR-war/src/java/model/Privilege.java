package model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Didik
 */

public class Privilege {
    public static final String MANAGER = "MANAGER";
    public static final String ADMIN = "ADMIN";
    public static final String OFFICER = "OFFICER";
    private String value;

   public Privilege(String value){
        if(value.equalsIgnoreCase("MANAGER") || value.equalsIgnoreCase("ADMIN") || value.equalsIgnoreCase("OFFICER")){
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
