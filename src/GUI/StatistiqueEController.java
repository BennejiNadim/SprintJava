/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class StatistiqueEController implements Initializable {

    @FXML
    private BarChart<?, ?> BarChart;
    @FXML
    private Button retour;
    @FXML
    private Button affstat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void retour(ActionEvent event) {
    }

    @FXML
    private void affstat(ActionEvent event) {
    }
    
}
