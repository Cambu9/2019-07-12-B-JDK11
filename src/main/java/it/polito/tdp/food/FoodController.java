/**
 * Sample Skeleton for 'Food.fxml' Controller Class
 */

package it.polito.tdp.food;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.food.model.Differenza;
import it.polito.tdp.food.model.Food;
import it.polito.tdp.food.model.Model;
import it.polito.tdp.food.model.Simulator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FoodController {
	
	private Model model;
	private Simulator sim;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtPorzioni"
    private TextField txtPorzioni; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="btnGrassi"
    private Button btnGrassi; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="boxFood"
    private ComboBox<Food> boxFood; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	boxFood.getItems().clear();
    	txtResult.clear();
    	txtResult.appendText("Creazione grafo...\n");
    	int porzioni;
    	try {
    		porzioni = (int) Integer.parseInt(txtPorzioni.getText());
    	}catch(NumberFormatException e) {
    		txtResult.appendText("Inserisci un numero intero");
    		return;
    	}
    	txtResult.appendText(model.creaGrafo(porzioni));
    	boxFood.getItems().addAll(model.getVertici());
    }

    @FXML
    void doGrassi(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Analisi grassi...\n");
    	Food f = boxFood.getValue();
    	if(f == null) {
    		txtResult.appendText("Devi selezionare un cibo");
    		return;
    	}
    	List<Differenza> differenze;
    	differenze = model.differenzaMinima(f);
    	int i = 0;
    	for(Differenza d: differenze) {
    		if(i<5) {
    			txtResult.appendText(d +"\n");
    			i++;
    		}
    	}
    	}

    @FXML
    void doSimula(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Simulazione...");
    	int k;
    	Food f;
    	f = boxFood.getValue();
    	if(f == null) {
    		txtResult.appendText("\nDevi selezionare un cibo");
    		return;
    	}
    	try {
    		k = Integer.parseInt(txtK.getText());
    	}catch (NumberFormatException e) {
    		txtResult.appendText("\nDevi inserire un numero intero di stazioni di preparazione");
    		return;
    	}
    	sim = model.Simulatore(k);
    	
    	sim.init(model.differenzaMinima(f));
    	sim.run();
    	txtResult.appendText("\nSono stati preparati " + sim.cibiPreparati.size() + " cibi in " + sim.minuti + " minuti");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtPorzioni != null : "fx:id=\"txtPorzioni\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnGrassi != null : "fx:id=\"btnGrassi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Food.fxml'.";
        assert boxFood != null : "fx:id=\"boxFood\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Food.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
