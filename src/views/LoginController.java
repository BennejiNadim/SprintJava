/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entite.Employe;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.CurrentSession;
import service.GestionEmploye;

/**
 * FXML Controller class
 *
 * @author Djemaiel
 */
public class LoginController implements Initializable {

    @FXML
    private TextField loginField;
    @FXML
    private Label output;
    @FXML
    private PasswordField pwdField;
    @FXML
    private Label loginErr;
    @FXML
    private AnchorPane loginAp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginAp.setPrefSize(CurrentSession.wx, CurrentSession.wy);
        // TODO
    }

    @FXML
    private void log_in_btn() throws IOException {
        CurrentSession.wx = (int) loginAp.getBoundsInParent().getWidth();
        CurrentSession.wy = (int) loginAp.getBoundsInParent().getHeight();
        GestionEmploye ge = new GestionEmploye();
        Boolean pwdbool = false;
        Employe e = ge.getFOS(loginField.getText());

        System.out.println("eee" + e);
        if (e.getMdp().contains("{")) {
            pwdbool = e.getMdp().substring(0, e.getMdp().indexOf("{")).equals(pwdField.getText());
        } else {
            pwdbool = e.getMdp().equals(pwdField.getText());
        }

        if (pwdbool && !e.getPoste().toLowerCase().equals("client") && (e.getPoste() != null)) {
            CurrentSession.isAdmin = true;
            CurrentSession.currentLogin = e.getLogin();
            Stage appStage = (Stage) loginField.getScene().getWindow();

            Parent root = FXMLLoader.load(getClass().getResource("vente.fxml"));
            Scene scene = new Scene(root);
            appStage.setScene(scene);
            appStage.show();
        } else {
            loginErr.setText("Login ou mot de passe incorrecte!!");
            //("Login ou Mot de passe incorrect!!");
            loginField.setText("");
            pwdField.setText("");

        }
    }

}
