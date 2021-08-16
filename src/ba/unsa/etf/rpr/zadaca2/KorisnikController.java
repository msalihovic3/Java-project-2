package ba.unsa.etf.rpr.zadaca2;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.SecureRandom;

public class KorisnikController {
    public Label labela;
    public TextField fldIme;
    public TextField fldPrezime;
    public TextField fldEmail;
    public TextField fldUsername;
    public ListView<Korisnik> listKorisnici;
    public PasswordField fldPassword;
    public PasswordField fldPasswordRepeat;
    public Slider sliderGodinaRodjenja;
    public CheckBox cbAdmin;
    private KorisniciModel model;


    public KorisnikController(KorisniciModel model) {
        this.model = model;
    }

    private boolean kontrolaImePrezime(String a) {

        if (a.length() < 3) return false;
        else {
            for (int i = 0; i < a.length(); i++) {
                if ((a.charAt(i) >= 'A' && a.charAt(i) <= 'Z') || (a.charAt(i) >= 'a' && a.charAt(i) <= 'z') || a.charAt(i) == ' ' || a.charAt(i) == '-') {

                } else return false;
            }
        }
        return true;
    }

    private boolean kontrolaEmaila(String a) {
        if (a.startsWith("@")) return false;
        if (a.endsWith("@")) return false;
        if (!a.contains("@")) return false;
        return true;
    }

    private boolean kontrolaUsername(String a) {
        if (a.length() > 16) return false;
        if (a.charAt(0) >= 'a' && a.charAt(0) <= 'z' || a.charAt(0) >= 'A' && a.charAt(0) <= 'Z'  || a.charAt(0) == '_' || a.charAt(0) == '$') {
            if(a.contains(",") || a.contains("-") || a.contains("@") || a.contains("!") || a.contains(" ") || a.contains("+") || a.contains("/") || a.contains("*") || a.contains("%") || a.contains("=") || a.contains("<") || a.contains(">") || a.contains("&") || a.contains("|"))
            return false;
        }
        return true;
    }

    @FXML
    public void initialize() {
        sliderGodinaRodjenja.setShowTickLabels(true);
        listKorisnici.setItems(model.getKorisnici());
        listKorisnici.getSelectionModel().selectedItemProperty().addListener((obs, oldKorisnik, newKorisnik) -> {
            if(newKorisnik instanceof Administrator){
                Administrator k=(Administrator) newKorisnik;
                model.setTrenutniKorisnik(k);
            }else{
            model.setTrenutniKorisnik(newKorisnik);}
            listKorisnici.refresh();
         });

        model.trenutniKorisnikProperty().addListener((obs, oldKorisnik, newKorisnik) -> {
            if (oldKorisnik != null) {
                fldIme.textProperty().unbindBidirectional(oldKorisnik.imeProperty() );
                fldPrezime.textProperty().unbindBidirectional(oldKorisnik.prezimeProperty() );
                fldEmail.textProperty().unbindBidirectional(oldKorisnik.emailProperty() );
                fldUsername.textProperty().unbindBidirectional(oldKorisnik.usernameProperty() );
                fldPassword.textProperty().unbindBidirectional(oldKorisnik.passwordProperty() );

                fldPasswordRepeat.textProperty().unbindBidirectional(oldKorisnik.passwordRepeatProperty() );
                if(oldKorisnik instanceof  Administrator)
                cbAdmin.selectedProperty().unbindBidirectional(((Administrator)oldKorisnik).adminProperty());
                else cbAdmin.setSelected(false);

            }
            if (newKorisnik == null) {
                fldIme.setText("");
                fldPrezime.setText("");
                fldEmail.setText("");
                fldUsername.setText("");
                fldPassword.setText("");
                fldPasswordRepeat.setText("");
                sliderGodinaRodjenja.setValue(2000);
                cbAdmin.setSelected(false);
            }
            else {
                fldIme.textProperty().bindBidirectional( newKorisnik.imeProperty() );
                fldPrezime.textProperty().bindBidirectional( newKorisnik.prezimeProperty() );
                fldEmail.textProperty().bindBidirectional( newKorisnik.emailProperty() );
                fldUsername.textProperty().bindBidirectional( newKorisnik.usernameProperty() );
                fldPassword.textProperty().bindBidirectional( newKorisnik.passwordProperty() );

                fldPasswordRepeat.textProperty().bindBidirectional( newKorisnik.passwordRepeatProperty() );
                sliderGodinaRodjenja.valueProperty().bindBidirectional(newKorisnik.godinaRodjenjaProperty());
                if(newKorisnik instanceof Administrator) {
                    cbAdmin.selectedProperty().bindBidirectional(((Administrator) oldKorisnik).adminProperty());
                    if(cbAdmin.isSelected()) {

                        Administrator k = (Administrator) model.getTrenutniKorisnik();
                        k.setAdmin(true);
                        model.setTrenutniKorisnik(k);
                    }
                }else cbAdmin.setSelected(false);
               // cbAdmin.selectedProperty.bin( ((Administrator) newKorisnik).isAdmin());
            }
        });




        fldIme.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty() && kontrolaImePrezime(newIme)) {
                fldIme.getStyleClass().removeAll("poljeNijeIspravno");
                fldIme.getStyleClass().add("poljeIspravno");
            } else {
                fldIme.getStyleClass().removeAll("poljeIspravno");
                fldIme.getStyleClass().add("poljeNijeIspravno");
            }
        });


        fldPrezime.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty() && kontrolaImePrezime(newIme)) {

                fldPrezime.getStyleClass().removeAll("poljeNijeIspravno");
                fldPrezime.getStyleClass().add("poljeIspravno");
            } else {
                fldPrezime.getStyleClass().removeAll("poljeIspravno");
                fldPrezime.getStyleClass().add("poljeNijeIspravno");
            }
        });

        fldEmail.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty() && kontrolaEmaila(newIme)) {
                fldEmail.getStyleClass().removeAll("poljeNijeIspravno");
                fldEmail.getStyleClass().add("poljeIspravno");
            } else {
                fldEmail.getStyleClass().removeAll("poljeIspravno");
                fldEmail.getStyleClass().add("poljeNijeIspravno");
            }
        });

        fldUsername.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty() && kontrolaUsername(newIme)) {
                fldUsername.getStyleClass().removeAll("poljeNijeIspravno");
                fldUsername.getStyleClass().add("poljeIspravno");
            } else {
                fldUsername.getStyleClass().removeAll("poljeIspravno");
                fldUsername.getStyleClass().add("poljeNijeIspravno");
            }
        });

        fldPassword.textProperty().addListener((obs, oldIme, newIme) -> {
            Korisnik k= new Korisnik("Neko", "nekic", "vljubovic@etf.unsa.ba", "vedranlj", newIme);

            k.setPassword(newIme);
            if (!newIme.isEmpty() && fldPasswordRepeat.textProperty().getValue().equals(newIme) && k.checkPassword() ) {

                fldPassword.getStyleClass().removeAll("poljeNijeIspravno");
                fldPassword.getStyleClass().add("poljeIspravno");
                fldPasswordRepeat.getStyleClass().removeAll("poljeNijeIspravno");
                fldPasswordRepeat.getStyleClass().add("poljeIspravno");
            } else {
                fldPassword.getStyleClass().removeAll("poljeIspravno");
                fldPassword.getStyleClass().add("poljeNijeIspravno");

                fldPasswordRepeat.getStyleClass().removeAll("poljeIspravno");
                fldPasswordRepeat.getStyleClass().add("poljeNijeIspravno");
            }

        }
        );

        fldPasswordRepeat.textProperty().addListener((obs, oldIme, newIme) -> {
            Korisnik k= new Korisnik("Neko", "nekic", "vljubovic@etf.unsa.ba", "vedranlj", newIme);

            k.setPassword(newIme);
                    if (!newIme.isEmpty() && fldPassword.textProperty().getValue().equals(newIme) && k.checkPassword()) {
                        fldPasswordRepeat.getStyleClass().removeAll("poljeNijeIspravno");
                        fldPasswordRepeat.getStyleClass().add("poljeIspravno");
                        fldPassword.getStyleClass().removeAll("poljeNijeIspravno");
                        fldPassword.getStyleClass().add("poljeIspravno");
                    } else {
                        fldPasswordRepeat.getStyleClass().removeAll("poljeIspravno");
                        fldPasswordRepeat.getStyleClass().add("poljeNijeIspravno");
                        fldPassword.getStyleClass().removeAll("poljeIspravno");
                        fldPassword.getStyleClass().add("poljeNijeIspravno");
                    }


                }
        );
    }

    public void dodajAction(ActionEvent actionEvent) {
        model.getKorisnici().add(new Korisnik("", "", "", "", ""));
        listKorisnici.getSelectionModel().selectLast();
    }

    public void krajAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void deleteOption(ActionEvent actionEvent) {
        Korisnik k=model.getTrenutniKorisnik();
        model.getKorisnici().remove(k);
        listKorisnici.getSelectionModel().selectLast();
    }
    public void dugmeGenerisi(ActionEvent actionEvent) throws IOException {
        String pom = "";
        if(model.getTrenutniKorisnik().getIme()!=""){
        pom = String.valueOf(model.getTrenutniKorisnik().getIme().charAt(0));
        }
        pom += String.valueOf(model.getTrenutniKorisnik().getPrezime());
        if (!pom.isEmpty()) {
            pom = pom.toLowerCase();
            for (int i = 0; i < pom.length(); i++) {

                if (pom.charAt(i) == 'č')
                    pom = pom.replace('č', 'c');
                else if (pom.charAt(i) == 'ć')
                    pom = pom.replace('ć', 'c');
                else if (pom.charAt(i) == 'đ')
                    pom = pom.replace('đ', 'd');
                else if (pom.charAt(i) == 'š')
                    pom = pom.replace('š', 's');
                else if (pom.charAt(i) == 'ž')
                    pom = pom.replace('ž', 'z');
            }
            model.getTrenutniKorisnik().setUsername(pom);

        }else {
            model.getTrenutniKorisnik().setUsername("");
        }
       //generisanje passworda
        SecureRandom random=new SecureRandom();
        StringBuilder sb=new StringBuilder(8);
        String FORMAT=new String("abcdefghijklmnopqrstuvwyzABCDEFGHIJKLMNOPQRSTUVWZY0123456789");
        for(int i=0; i<8; i++){
            int ch=random.nextInt(FORMAT.length());
            char znak=FORMAT.charAt(ch);
            sb.append(znak);
        }
        model.getTrenutniKorisnik().setPassword(sb.toString());
        if( model.getTrenutniKorisnik().checkPassword()){
            fldPassword.getStyleClass().removeAll("poljeNijeIspravno");
            fldPassword.getStyleClass().add("poljeIspravno");
        }else
        {
            fldPassword.getStyleClass().removeAll("poljeIspravno");
            fldPassword.getStyleClass().add("poljeNijeIspravno");
        }
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Vaša lozinka glasi");
        alert.setContentText(model.getTrenutniKorisnik().getPassword());
        alert.show();
    }



}
