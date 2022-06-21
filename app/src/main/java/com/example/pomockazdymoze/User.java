package com.example.pomockazdymoze;

public class User {

    String zdjecieProfilowe;

    public String getZdjecieProfilowe() {
        return zdjecieProfilowe;
    }

    public void setZdjecieProfilowe(String zdjecieProfilowe) {
        this.zdjecieProfilowe = zdjecieProfilowe;
    }

    String rULogin;
    String rUEmail;

    public User() {

    }

    public User(String rULogin, String rUEmail) {
        this.rULogin = rULogin;
        this.rUEmail = rUEmail;
    }

    public String getrULogin() {
        return rULogin;
    }

    public void setrULogin(String rULogin) {
        this.rULogin = rULogin;
    }

    public String getrUEmail() {
        return rUEmail;
    }

    public void setrUEmail(String rUEmail) {
        this.rUEmail = rUEmail;
    }
}
