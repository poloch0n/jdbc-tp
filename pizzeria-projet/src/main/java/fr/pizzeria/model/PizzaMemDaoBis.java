package fr.pizzeria.model;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class PizzaMemDaoBis implements IPizzaDao{
	
	public Connection connectBdd(){
		
		Connection conn = null;
	    try {
	      Class.forName("com.mysql.jdbc.Driver");
//	      System.out.println("Driver O.K.");

		  InputStream input = new FileInputStream("./jdbc.properties");
		
		// load a properties file
		  final Properties prop = new Properties();
		  prop.load(input);
		  String Environnement = prop.getProperty("ENVIRONNEMENT");
		  String url = prop.getProperty(Environnement+".MYSQL_ADDON_URL");
		  // TODO : export logs in external file
		  String user = prop.getProperty(Environnement+".MYSQL_ADDON_USER");
		  String passwd = prop.getProperty(Environnement+".MYSQL_ADDON_PASSWORD");
	      input.close();

	      conn = (Connection) DriverManager.getConnection(url, user, passwd);
	      System.out.println("Connexion effective !");         
	         
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
		return conn;
	}

	public List<Pizza> getPizzas() {
		List<Pizza> menu = new ArrayList<Pizza>();
		
		try {

			String sql = "select * from pizzas";
			Connection con = connectBdd();
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					
					int id_pizza = rs.getInt("Id_pizza");
					String code = rs.getString("code");
					String libelle = rs.getString("libelle");
					double prix = rs.getDouble("prix");
					String categorie = rs.getString("categorie");
					Pizza newPizza = new Pizza(id_pizza,code,libelle,prix,CategoriePizza.fromString(categorie));

					menu.add(newPizza);
		        }
			}
			rs.close();
			statement.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return menu;
	}
	
	public void addPizza(Pizza pizza) {
		try {
			String sql = "Insert into pizzas (code, libelle, prix, categorie) values (?,?,?,?)";
			Connection con = connectBdd();
			PreparedStatement statement = con.prepareStatement(sql);
			// TODO : to think injection here care
			statement.setString(1,pizza.code);
			statement.setString(2,pizza.libelle);
			statement.setDouble(3,pizza.prix);
			statement.setString(4, pizza.categorie.toString());
			statement.executeUpdate();

			statement.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updatePizzaFromCode(String codePizza,Pizza pizza) {

		try {		
			Pizza pizzaToUpdate = this.getPizzaFromCode(codePizza);

			String sql = "Update pizzas SET code = ?, libelle = ?, prix = ?, categorie = ? where id_pizza = ?";
			Connection con = connectBdd();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, pizza.code);
			statement.setString(2, pizza.libelle);
			statement.setDouble(3, pizza.prix);
			statement.setString(4, pizza.categorie.toString());
			statement.setInt(5, pizzaToUpdate.id);
			statement.executeUpdate();

			statement.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void deletePizzaFromCode(String codePizza) {
		
		try {
			String sql = "DELETE from pizzas Where code = ?";
			Connection con = connectBdd();
			PreparedStatement statement = con.prepareStatement(sql);
			// TODO : to think injection here care
			statement.setString(1,codePizza);
			statement.executeUpdate();

			statement.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Pizza getPizzaFromCode(String codePizza) {
		try {

			String sql = "select * from pizzas where code like ? limit 0,1";
			Connection con = connectBdd();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, codePizza);
			ResultSet rs = statement.executeQuery();

			Pizza pizza = null;
			if (rs != null) {
				while (rs.next()) {
					int id_pizza = rs.getInt("Id_pizza");
					String code = rs.getString("code");
					String libelle = rs.getString("libelle");
					double prix = rs.getDouble("prix");
					String categorie = rs.getString("categorie");
					pizza = new Pizza(id_pizza,code,libelle,prix,CategoriePizza.fromString(categorie));
		        }
			}
			rs.close();
			statement.close();
			con.close();
			return pizza;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean checkPizzaExistFromCode(String codePizza) {
		if(getPizzaFromCode(codePizza) != null) {
			return true;
		}
		return false;
	}
	

	public String checkInformationPizza(Pizza pizza, boolean unicity, String methode) {
		
		String error = "";
		error += checkFormatInformationPizza(pizza);

		if(unicity) {
			error += checkUnicityInformationPizza(pizza,methode);
		}
		
		return error;
	}

	public String checkFormatInformationPizza(Pizza pizza) {
		String error = "";
		if(pizza.code.equals("")) {
			error += "\r\nLe format du code "+ pizza.code +" est invalide";
		}
		if(pizza.libelle.equals("")) {
			error += "\r\nLe format du libelle " + pizza.libelle + " est invalide";
		}
		if(isNegative(pizza.prix)) {
			error += "\r\nLe format du prix est invalide";
		}
		return error;
	}
	
	private String checkUnicityInformationPizza(Pizza pizza,String methode) {
		String error = "";
		for (Pizza pizzaSaved : getPizzas()) {
			if(pizzaSaved.code.equals(pizza.code)) {
				error += "\r\nCe code a déjà été utilisé";
			}
			if(pizzaSaved.libelle.equals(pizza.libelle)) {
				error += "\r\nCe libelle a déjà été utilisé";
			}
			if(methode.equals("add") && pizzaSaved.id == pizza.id) {
					pizza.id ++;
			}
		}
		return error;
	}
	
	private boolean isNegative(double d) {
	     return Double.compare(d, 0.0) < 0;
	}
	
	public void initialiseBddPizza() {
		try {
			String sqlDropTablePizzeria = "DROP TABLE if exists `pizzas` ;";
			String sqlCreateTable = "CREATE TABLE `pizzas` (\r\n" + 
					"  `id_Pizza` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
					"  `code` varchar(3) NOT NULL,\r\n" + 
					"  `libelle` varchar(45) NOT NULL,\r\n" + 
					"  `prix` decimal(5,2) NOT NULL,\r\n" +
					"  `categorie` enum('Viande','Poisson','Sans viande') DEFAULT NULL, \r\n" +
					"  PRIMARY KEY (`id_Pizza`)\r\n" + 
					") ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;";
			String sqlInsertPizzas = "INSERT INTO `pizzas`\r\n" + 
					"( `code`, `libelle`, `prix`,`categorie`) VALUES "+
							"(\"PEP\",\"Pépéoni\", 12.50,\"Viande\"),"+
							"(\"MAR\",\"Margherita\", 14.00,\"Viande\"),"+
							"(\"REI\",\"La Reine\", 11.50,\"Sans viande\"),"+
							"(\"FRO\",\"La 4 fromages\", 12.00,\"Sans viande\"),"+
							"(\"CAN\",\"La cannibale\", 12.50,\"Viande\"),"+
							"(\"SAV\",\"La savoyarde\", 13.00,\"Sans viande\"),"+
							"(\"ORI\",\"L'orientale\", 13.50,\"Sans viande\"),"+
							"(\"IND\",\"L'indienne\", 14.00,\"Sans viande\");";
			
			Connection con = connectBdd();

		    Statement stmt = (Statement) con.createStatement();
		    
		    stmt.executeUpdate(sqlDropTablePizzeria);
		    stmt.executeUpdate(sqlCreateTable);
		    stmt.executeUpdate(sqlInsertPizzas);
		} catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
