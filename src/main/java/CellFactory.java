import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


/**
 * Cette classe conserve l'information pour chaque transaction de manière a 
 * permettre l'affichage dans un tableview
 */
public class CellFactory
{
	/**
	 * Numero de la transaction
	 */
	SimpleIntegerProperty numProperty;
	
	/**
	 * Identifiant de thread (T1 pour consommation, T2 pour production)
	 */
	SimpleStringProperty threadLabelProperty;
	
	/**
	 * Variation de stock
	 */
	SimpleIntegerProperty deltaProperty;
	
	/**
	 * Nombre de rupture de stock
	 */
	SimpleIntegerProperty nbreRuptureDeStockProperty;
	
	/**
	 * Quantité de rupture de stock
	 */
	SimpleIntegerProperty qteRuptureDeStockProperty;
	
	/**
	 * Pénalité cumulée de rupture de stock, en dollars
	 */
	SimpleIntegerProperty penaltyProperty;
	
	/**
	 * Quantite moyenne de stock
	 */
	SimpleIntegerProperty qteMoyenneProperty;

	/**
	 * Reserve current stock
	 */
	private SimpleIntegerProperty stockProperty;

	/**
	 * Constructeur de CellFactory
	 * @param numProperty
	 * @param threadLabelProperty
	 * @param deltaProperty
	 * @param nbreRuptureDeStockProperty
	 * @param qteRuptureDeStockProperty
	 * @param penaltyProperty
	 * @param qteMoyenneProperty
	 * @param stockProperty
	 */
	public CellFactory(SimpleIntegerProperty numProperty,
			SimpleStringProperty threadLabelProperty,
			SimpleIntegerProperty deltaProperty,
			SimpleIntegerProperty nbreRuptureDeStockProperty,
			SimpleIntegerProperty qteRuptureDeStockProperty,
			SimpleIntegerProperty penaltyProperty,
			SimpleIntegerProperty qteMoyenneProperty,
			SimpleIntegerProperty stockProperty)
	{
		this.numProperty = numProperty;
		this.threadLabelProperty = threadLabelProperty;
		this.deltaProperty = deltaProperty;
		this.nbreRuptureDeStockProperty = nbreRuptureDeStockProperty;
		this.qteRuptureDeStockProperty = qteRuptureDeStockProperty;
		this.penaltyProperty = penaltyProperty;
		this.qteMoyenneProperty = qteMoyenneProperty;
		this.stockProperty = stockProperty;
	}

	/**
	 * Returns getNumProperty()
	 * @return
	 */
	public Integer getNumProperty()
	{
		return numProperty.get();
	}

	/**
	 * Returns threadLabelProperty
	 * @return
	 */
	public String getThreadLabelProperty()
	{
		return threadLabelProperty.get();
	}

	/**
	 * Returns deltaProperty
	 * @return
	 */
	public Integer getDeltaProperty()
	{
		return deltaProperty.get();
	}

	/**
	 * Returns nbreRuptureDeStockProperty
	 * @return
	 */
	public Integer getNbreRuptureDeStockProperty()
	{
		return nbreRuptureDeStockProperty.get();
	}
	
	/**
	 * Returns qteRuptureDeStockProperty
	 * @return
	 */
	public Integer getQteRuptureDeStockProperty()
	{
		return qteRuptureDeStockProperty.get();
	}

	/**
	 * Returns penaltyProperty
	 * @return
	 */
	public Integer getPenaltyProperty()
	{
		return penaltyProperty.get();
	}
	
	/**
	 * Returns qteMoyenneProperty
	 * @return
	 */
	public Integer getQteMoyenneProperty()
	{
		return qteMoyenneProperty.get();
	}
	
	/**
	 * Returns stockProperty
	 * @return
	 */
	public Integer getStockProperty()
	{
		return stockProperty.get();
	}	
}
