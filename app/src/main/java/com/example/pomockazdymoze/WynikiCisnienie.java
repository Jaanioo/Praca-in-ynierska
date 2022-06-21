package com.example.pomockazdymoze;

public class WynikiCisnienie {

    String skurczowe;
    String rozkurczowe;
    String puls;
    String dataWyniku;

    public WynikiCisnienie() {}

    public WynikiCisnienie(String skurczowe, String rozkurczowe, String puls, String dataWyniku) {

        this.skurczowe = skurczowe;
        this.rozkurczowe = rozkurczowe;
        this.puls = puls;
        this.dataWyniku = dataWyniku;
    }

    public String getSkurczowe() {

        return skurczowe;
    }

    public String getRozkurczowe() {

        return rozkurczowe;
    }

    public String getPuls() {
        return puls;
    }

    public String getDataWyniku() {
        return dataWyniku;
    }

}
