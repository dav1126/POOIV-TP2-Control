import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Démarre l'application d'optimisation
 * @author 0345162
 *
 */
public class Simulation extends Application
{

	public static void main(String[] args)
	{
		Application.launch();
	}

	@Override
	public void start(Stage pStage) throws Exception
	{
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("TP2.fxml"));
		VBox root = (VBox) loader.load();
		
		Scene scene = new Scene(root);
		pStage.setScene(scene);
		pStage.setTitle("TP2 - Optimisation");
		pStage.show();
	}

}
