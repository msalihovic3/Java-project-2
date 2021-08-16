package ba.unsa.etf.rpr.zadaca2;

import javafx.beans.property.SimpleBooleanProperty;

public class Administrator extends Korisnik {
    private SimpleBooleanProperty admin=new SimpleBooleanProperty(false);
    public Administrator(String ime, String prezime, String email, String username, String password) {
        super(ime, prezime, email, username, password);
    }

    public boolean isAdmin() {
        return admin.get();
    }

    public SimpleBooleanProperty adminProperty() {
        return admin;
    }


    public void setAdmin(boolean admin) {
        this.admin.set(admin);
    }
    public boolean checkPassword(){
        String pomocni=this.getPassword();
        boolean a1=false,a2=false,a3=false,a4=false;
        for(int i=0; i<pomocni.length(); i++){
            if(pomocni.charAt(i)>='a' && pomocni.charAt(i)<='z' && a1==false){
                a1=true;
            }else if(pomocni.charAt(i)>='A' && pomocni.charAt(i)<='Z' && a2==false){
                a2=true;

            }else if(pomocni.charAt(i)>='0' && pomocni.charAt(i)<='9' && a3==false){
                a3=true;
            }else if(!(pomocni.charAt(i)>='a' && pomocni.charAt(i)<='z'|| pomocni.charAt(i)>='A' && pomocni.charAt(i)<='Z' || pomocni.charAt(i)>='0' && pomocni.charAt(i)<='9') && a4==false){
                a4=true;
            }
        }
        if(a1 && a2 && a3 && a4) return true;
        else return false;
    }
}
