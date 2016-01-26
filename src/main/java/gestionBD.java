import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

/**
 * Classe permettant l'interactiona avec la base de données
 * @author 0345162
 *
 */
public class gestionBD
{
	String url = "jdbc:mysql://localhost/tp2_simulation_stock";
	String utilisateur = "root";
	String motDePasse = "";
	static Connection connexion;
	Statement statement;
	static int idSimulation = 0;
	
	/**
	 * Établi la connexion à la BD
	 */
	public void etablirConnection()
	{
		try
		{
			connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
			statement = connexion.createStatement();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Sauvegarde le cellfactory passé en paramètre dans la BD
	 * @param cf
	 */
	public void sauvegardeDansLaBd(CellFactory cf)
	{
		if (connexion == null)
			etablirConnection();
		if (idSimulation == 0)
			lireIdSimulation();
		if (idSimulation == 0)
			idSimulation = 1;
			
		try
		{
			
			String threadLabel = cf.getThreadLabelProperty();
			int delta = cf.getDeltaProperty();
			int nbreRuptureDeStock = cf.getNbreRuptureDeStockProperty();
			int qteRuptureDeStock = cf.getQteRuptureDeStockProperty();
			double penalty = cf.getPenaltyProperty();
			int qteMoyenne = cf.getQteMoyenneProperty();
			int stock = cf.getStockProperty();
			int statut = statement.executeUpdate
					("INSERT INTO simulation values (null,"+ idSimulation + ",\""+ threadLabel + "\", " + delta + ", " + stock + ", " + nbreRuptureDeStock + ", " + qteRuptureDeStock + ", " + penalty + ", " + qteMoyenne + ", " + "2016);");
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	/**
	 * Lit dans la BD pour mettre le idSimulation suivant
	 */
	public void lireIdSimulation()
	{
		try
		{
			ResultSet resultat = statement.executeQuery("Select MAX(idSimulation) AS maxID FROM simulation;");
			resultat.next();
			idSimulation = resultat.getInt(1);
			idSimulation++;
		} catch (SQLException e)
		{
			e.printStackTrace();
			System.out.println("incapable de lire le idSimulation max");
		}
	}
	
	/**
	 * Ferme la connexion à la BD
	 */
	public static void closeBDConnection()
	{
		try
		{
			idSimulation =0;
			connexion.close();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
