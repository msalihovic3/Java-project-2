package ba.unsa.etf.rpr.zadaca2;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Korisnik {
    private SimpleStringProperty ime, prezime, email, username, password,passwordRepeat;
    private SimpleIntegerProperty godinaRodjenja;


    public Korisnik(String ime, String prezime, String email, String username, String password) {
        this.ime = new SimpleStringProperty(ime);
        this.prezime = new SimpleStringProperty(prezime);
        this.email = new SimpleStringProperty(email);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.passwordRepeat=new SimpleStringProperty("");
        this.godinaRodjenja=new SimpleIntegerProperty(2000);

    }
    public boolean checkPassword(){
        String pomocni=this.getPassword();
        boolean a1=false,a2=false,a3=false;
        for(int i=0; i<pomocni.length(); i++){
            if(pomocni.charAt(i)>='a' && pomocni.charAt(i)<='z' && a1==false){
                a1=true;
            }else if(pomocni.charAt(i)>='A' && pomocni.charAt(i)<='Z' && a2==false){
                a2=true;

            }else if(pomocni.charAt(i)>='0' && pomocni.charAt(i)<='9' && a3==false){
                a3=true;
            }
        }
        if(a1 && a2 && a3) return true;
        else return false;
    }

    public String getPasswordRepeat() {
        return passwordRepeat.get();
    }

    public SimpleStringProperty passwordRepeatProperty() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat.set(passwordRepeat);
    }

    @Override
    public String toString() {
        return prezime.get() + " " + ime.get();
    }

    public String getIme() {
        return ime.get();
    }

    public SimpleStringProperty imeProperty() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime.set(ime);
    }

    public String getPrezime() {
        return prezime.get();
    }

    public SimpleStringProperty prezimeProperty() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime.set(prezime);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }


    public int getGodinaRodjenja() {
        return  godinaRodjenja.get();
    }

    public void setGodinaRodjenja(int i) {
        godinaRodjenja.set(i);
    }

    public SimpleIntegerProperty godinaRodjenjaProperty() {
        return godinaRodjenja;
    }
}
