package com.example.pomockazdymoze;

public class WynikiCukier {

    String cukier;
    String dataWyniku;

    public WynikiCukier(){}

    public WynikiCukier(String cukier, String dataWyniku) {
        this.cukier = cukier;
        this.dataWyniku = dataWyniku;
    }

    public String getCukier() {
        return cukier;
    }

    public void setCukier(String cukier) {
        this.cukier = cukier;
    }

    public String getDataWyniku() {
        return dataWyniku;
    }

    public void setDataWyniku(String dataWyniku) {
        this.dataWyniku = dataWyniku;
    }
}
