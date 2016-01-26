import java.util.Observable;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class used to manage the stock of the program.
 * Observable to notify the controller when new results are generated.
 */
public class Reserve extends Observable
{
	/**
	 * Object permettant de gérer la sauvegarde dans la BD
	 */
	gestionBD gestionBD = new gestionBD();
	
	/**
	 * Observable list qui conserve l'historique des transaction dans des 
	 * objet de type CellFactory
	 */
	ObservableList<CellFactory> transactionList;

	/**
	 * Compteur de transaction
	 */
	private int num;
	
	/**
	 * Reserve current stock
	 */
	private int stock;
	
	/**
	 * Nombre de rupture de stock
	 */
	int nbreRuptureDeStock;
	
	/**
	 * Quantité de rupture de stock
	 */
	int qteRuptureDeStock;
	
	/**
	 * Pénalité cumulée de rupture de stock, en dollars
	 */
	int penaltyCumulee;
	
	/**
	 * Quantite moyenne de stock
	 */
	int qteMoyenne;
	
	/**
	 * Somme des stock. Utilisé pour calculer la moyenne
	 */
	int somme;
	
	/**
	 * Initial stock when a reserve is created
	 */
	static final int STOCK_INITIAL = 500;
	
	/**
	 * Coût d'une rupture de stock par unité manquante
	 */
	static final int PENALTY_COST = 20;
	
	/**
	 * Reserve constructor that sets the stock to STOCK_INITAL
	 */
	public Reserve()
	{
		transactionList = FXCollections.observableArrayList();
		this.num = 0;
		this.stock = STOCK_INITIAL;	
		this.nbreRuptureDeStock = 0;
		this.qteRuptureDeStock = 0;
		this.penaltyCumulee = 0;
		this.qteMoyenne = 0;
		this.somme = 0;
	}
	
	/**
	 * Get the reserve stock
	 * @return stock
	 */
	public int getStock()
	{
		return stock;
	}
	
	/**
	 * Subtract a consumed quantity from the stock, manages the rupture de stock
	 * and saves this transaction info into a CellFactory
	 * Synchronized to prevent 
	 * @param consumed quantity
	 */
	protected synchronized void consume(int qty)
	{
		num++;
		this.stock -= qty;
		
		if(this.stock < 0)
		{
			nbreRuptureDeStock++;
			qteRuptureDeStock = -this.stock;
			penaltyCumulee += PENALTY_COST*this.qteRuptureDeStock;
		}
		//On calcule la moyenne
		somme += stock;	
		qteMoyenne = somme/num;
		
		//On conserve les infos de cette transaction dans un CellFactory
		CellFactory cf = new CellFactory(new SimpleIntegerProperty(this.num),
										 new SimpleStringProperty("T1"),
										 new SimpleIntegerProperty(-qty),
										 new SimpleIntegerProperty(this.nbreRuptureDeStock),
										 new SimpleIntegerProperty(this.qteRuptureDeStock),
										 new SimpleIntegerProperty(this.penaltyCumulee),
										 new SimpleIntegerProperty(qteMoyenne),
										 new SimpleIntegerProperty(this.stock));
		
		//Ajouter le cellFactory a la liste observable
		transactionList.add(0, cf);
		setChanged();
		notifyObservers();
		
		//Sauvegarder dans la BD
		gestionBD.sauvegardeDansLaBd(cf);
	}
	
	/**
	 * Add a produced quantity to the stock, and saves this transaction info 
	 * into a CellFactory.  
	 * @param produced quantity
	 */
	protected synchronized void produce(int qty)
	{
		num++;
		this.stock += qty;	
		
		//On calcule la moyenne
		somme += stock;	
		qteMoyenne = somme/num;
		
		//On conserve les infos de cette transaction dans un CellFactory
		CellFactory cf = new CellFactory(new SimpleIntegerProperty(this.num),
										 new SimpleStringProperty("T2"),
										 new SimpleIntegerProperty(qty),
										 new SimpleIntegerProperty(this.nbreRuptureDeStock),
										 new SimpleIntegerProperty(this.qteRuptureDeStock),
										 new SimpleIntegerProperty(this.penaltyCumulee),
										 new SimpleIntegerProperty(qteMoyenne),
										 new SimpleIntegerProperty(this.stock));
		
		//Ajouter le cellFactory a la liste observable
		transactionList.add(0, cf);
		setChanged();
		notifyObservers();
		
		//Sauvegarder dans la BD
		gestionBD.sauvegardeDansLaBd(cf);
	}
	
	/**
	 * Retourne l'observableList de CellFactory
	 * @return
	 */
	public ObservableList<CellFactory> getTransactionList()
	{
		return transactionList;
	}
}
