package fr.pizzeria.model;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

public class PizzaMemDaoBis implements IPizzaDao{
	
	public Connection connectBdd(){
		
		Connection conn = null;
	    try {
	      Class.forName("com.mysql.jdbc.Driver");
//	      System.out.println("Driver O.K.");

	      String url = "jdbc:mysql://localhost:3306/pizzeria";
	      // TODO : export logs in external file
	      String user = "root";
	      String passwd = "";
	      
	      conn = (Connection) DriverManager.getConnection(url, user, passwd);
//	      System.out.println("Connexion effective !");         
	         
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
					
					Pizza newPizza = new Pizza(id_pizza,code,libelle,prix);

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
			String sql = "Insert into pizzas (code, libelle, prix) values ( ?, ? , ?)";
			Connection con = connectBdd();
			PreparedStatement statement = con.prepareStatement(sql);
			// TODO : to think injection here care
			statement.setString(1,pizza.code);
			statement.setString(2,pizza.libelle);
			statement.setDouble(3,pizza.prix);
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

			String sql = "Update pizzas SET code = ?, libelle = ?, prix = ? where id_pizza = ?";
			Connection con = connectBdd();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, pizza.code);
			statement.setString(2, pizza.libelle);
			statement.setDouble(3, pizza.prix);
			statement.setInt(4, pizzaToUpdate.id);
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

					pizza = new Pizza(id_pizza,code,libelle,prix);
		        }
			} else {
				return null;
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
				error += "\r\nCe code a d�j� �t� utilis�";
			}
			if(pizzaSaved.libelle.equals(pizza.libelle)) {
				error += "\r\nCe libelle a d�j� �t� utilis�";
			}
			if(methode.equals("add") && pizzaSaved.id == pizza.id) {
					pizza.id ++;
			}
		}
		return error;
	}
	boolean isNegative(double d) {
	     return Double.compare(d, 0.0) < 0;
	}
}