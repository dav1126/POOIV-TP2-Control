import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLController implements Initializable{

    @FXML
    private TextField t1TextField;

    @FXML
    private TextField t2TextField;

    @FXML
    private TextField deltaT1TextField;

    @FXML
    private TextField deltaT2TextField;

    @FXML
    private Button demarrerButton;

    @FXML
    private Button arreterButton;

    @FXML
    private Button resetButton;

    @FXML
    private TextField quantiteTextField;

    @FXML
    private TextField rupturesTextField;

    @FXML
    private TextField penalitesTextField;

    @FXML
    private TextField stockMoyenTextField;

    @FXML
    private TextField coutStockMoyenTextField;

    @FXML
    private TableView<CellFactory> tableView;
    
    @FXML
    private TableColumn<CellFactory, Integer> numColumn;

    @FXML
    private TableColumn<CellFactory, String> threadColumn;

    @FXML
    private TableColumn<CellFactory, Integer> deltaColumn;

    @FXML
    private TableColumn<CellFactory, Integer> stockColumn;

    @FXML
    private TableColumn<CellFactory, Integer> rsColumn;

    @FXML
    private TableColumn<CellFactory, Integer> qrsColumn;

    @FXML
    private TableColumn<CellFactory, Integer> penaltyColumn;

    @FXML
    private TableColumn<CellFactory, Integer> qteMoyColumn;

    private Reserve reserve;
    
    private boolean sessionEnCours = false;
    
    private int t1, t2, deltaT1, deltaT2;
    
    ConsumeThreadPool consPool;
    ProdThreadPool prodPool;

    @FXML
    void arreter(ActionEvent event) 
    {
    	consPool.getScheduledThreadPool().shutdown();
    	prodPool.getScheduledThreadPool().shutdown();
    	sessionEnCours = false;
    }

    @FXML
    void demarrer(ActionEvent event)
    {
	    if(sessionEnCours)
	    {
	    	Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur");
			alert.setContentText("Arrêter la simulation avant d'en démarrer une autre...");
			alert.setHeaderText(null);
			alert.showAndWait();
		}
		else{
			sessionEnCours = true;
			//Si les text field sont dument rempli
			if((!t1TextField.getText().isEmpty()) &&
			   (!t2TextField.getText().isEmpty()) &&
			   (!deltaT1TextField.getText().isEmpty() &&
			   (!deltaT2TextField.getText().isEmpty()))){
				
				//Essayer si les textfield sont bien numeric
				try{
					
				t1 = Integer.parseInt(t1TextField.getText());
				t2 = Integer.parseInt(t2TextField.getText());
				deltaT1 = Integer.parseInt(deltaT1TextField.getText());
				deltaT2 = Integer.parseInt(deltaT2TextField.getText());
				
				//Si les t1 et t2 sont plus grand que 0
				if(t1 > 0 && t2 > 0){
					
					
					//Création de la réserve et des pools de thread
	    		
	    			prodPool = new ProdThreadPool(deltaT1, reserve, t1);
	    			consPool = new ConsumeThreadPool(deltaT2, reserve, t2);
	    			
	    			//Disable les input pendant l'execution
	    			t1TextField.setDisable(true);
	    			t2TextField.setDisable(true);
	    			deltaT1TextField.setDisable(true);
	    			deltaT2TextField.setDisable(true);
	    			
	    			
	    			
	    			//Session en cours
	    			sessionEnCours = true;
		    	}
				else{
					
					Alert alert = new Alert(AlertType.ERROR);
	    			alert.setTitle("Entrée invalide");
	    			alert.setContentText("T1 et T2 doivent être plus grand que 0.");
	    			alert.setHeaderText(null);
	    			alert.showAndWait();
				}
				//Si les textfield ne sont pas tous numeric
				}catch(NumberFormatException ex){
					
					Alert alert = new Alert(AlertType.ERROR);
	    			alert.setTitle("Entrée invalide");
	    			alert.setContentText("Champs numérique seulement.");
	    			alert.setHeaderText(null);
	    			alert.showAndWait();
				}
		
			}
			else
			{		
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Entrée invalide");
				alert.setContentText("Des champs sont manquants.");
				alert.setHeaderText(null);
				alert.showAndWait();
			}
		}
	}

    @FXML
    void reset(ActionEvent event) 
    {
    	 if(sessionEnCours)
 	    {
 	    	Alert alert = new Alert(AlertType.ERROR);
 			alert.setTitle("Erreur");
 			alert.setContentText("Arrêter la simulation avant d'en démarrer une autre...");
 			alert.setHeaderText(null);
 			alert.showAndWait();
 		}
 		else{
    	reserve.getTransactionList().clear();
 		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{	
		reserve = new Reserve();

		//Set the columns
			numColumn.setCellValueFactory(new PropertyValueFactory<CellFactory, Integer>("numProperty"));
			threadColumn.setCellValueFactory(new PropertyValueFactory<CellFactory, String>("threadLabelProperty"));
			deltaColumn.setCellValueFactory(new PropertyValueFactory<CellFactory, Integer>("deltaProperty"));
			stockColumn.setCellValueFactory(new PropertyValueFactory<CellFactory, Integer>("stockProperty"));
			rsColumn.setCellValueFactory(new PropertyValueFactory<CellFactory, Integer>("nbreRuptureDeStockProperty"));
			qrsColumn.setCellValueFactory(new PropertyValueFactory<CellFactory, Integer>("qteRuptureDeStockProperty"));
			penaltyColumn.setCellValueFactory(new PropertyValueFactory<CellFactory, Integer>("penaltyProperty"));
			qteMoyColumn.setCellValueFactory(new PropertyValueFactory<CellFactory, Integer>("qteMoyenneProperty"));    
		
		//Set the table view
			tableView.setItems(reserve.getTransactionList());
			
	}

}

