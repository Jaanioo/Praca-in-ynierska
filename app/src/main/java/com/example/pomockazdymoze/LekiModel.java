package com.example.pomockazdymoze;

public class LekiModel {

     String nazwaLeku;
     String dawkowanieLeku;
     String czestotliwosc;

    protected LekiModel() {}

    protected LekiModel(String nazwaLeku, String dawkowanieLeku, String czestotliwosc) {

        this.nazwaLeku = nazwaLeku;
        this.dawkowanieLeku = dawkowanieLeku;
        this.czestotliwosc = czestotliwosc;
    }

    public String getNazwaLeku() {
        return nazwaLeku;
    }

    public void setNazwaLeku(String nazwaLeku) {
        this.nazwaLeku = nazwaLeku;
    }

    public String getDawkowanieLeku() {
        return dawkowanieLeku;
    }

    public void setDawkowanieLeku(String dawkowanieLeku) {
        this.dawkowanieLeku = dawkowanieLeku;
    }

    public String getCzestotliwosc() {
        return czestotliwosc;
    }

    public void setCzestotliwosc(String czestotliwosc) {
        this.czestotliwosc = czestotliwosc;
    }
}
