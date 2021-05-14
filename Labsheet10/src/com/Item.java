package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Item {
	
	private Connection connect()  
	{   
		Connection con = null;
		
		try   
		{    
			Class.forName("com.mysql.jdbc.Driver");    
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/paf", "root", "");   
			
		}   
		catch (Exception e)   
		{    
			e.printStackTrace();   
		}  
	  return con;  
	}
	
	public String readItems()  
	{   
		String output = "";  
	
		try   
		{    
			Connection con = connect();  
		
			if (con == null)    
			{     
				return "Error while connecting to the database for reading.";   
			}  
			
		
		// Prepare the html table to be displayed    
		output = "<table border='1'><tr><th>seller name</th>       "
				+ "<th>order nameame</th><th>orderdate</th>"      
				+ "<th>order Description</th>        "
				+ "<th>Update</th><th>Remove</th></tr>";
		
		String query = "select * from order";    
		Statement stmt = con.createStatement();    
		ResultSet rs = stmt.executeQuery(query);
		
	   // iterate through the rows in the result set    
		while (rs.next())    
		{     
			String sellerID = Integer.toString(rs.getInt("sellerID"));     
			String sellername = rs.getString("sellername");
			String ordername = rs.getString("ordername");     
			String orderdate = rs.getString("orderdate");     
			String orderDesc = rs.getString("orderDesc")
					;  
		    // Add into the html table     
			output += "<tr><td><input id='hidsellerIDUpdate' "
					+ "name='hidsellerIDUpdate'          "
					+ "type='hidden' value='" + sellerID       
					+ "'>" + sellername + "</td>";     
			output += "<td>" + ordername + "</td>";     
			output += "<td>" + orderdate + "</td>";     
			output += "<td>" + orderDesc + "</td>";
			
		    // buttons     
			output += "<td><input name='btnUpdate'          "
					+ "type='button' value='Update'         "
					+ "class='btnUpdate btn btn-secondary'></td>"       
					+ "<td><input name='btnRemove'         "
					+ "type='button' value='Remove'         "
					+ "class='btnRemove btn btn-danger'        "
					+ "data-sellerid='"       
					+ sellerID + "'>" + "</td></tr>";    
		}  
		con.close();
		
		// Complete the html table    
		output += "</table>";   
	}  
	catch (Exception e)   
	{    
		output = "Error while reading the orders.";    
		System.err.println(e.getMessage());   
	}
	
	return output;  
	}
	
	public String insertItem(String sellername, String ordername, String date, String desc)  
	{   
		String output = "";  
	
		try   
		{    
			Connection con = connect();  
		
			if (con == null)    
			{     
				return "Error while connecting to the database for inserting.";    
			}  
			
			// create a prepared statement   
			String query = " insert into order(`sellerID`,`sellername`,`ordername`,`orderdate`,`orderDesc`)"+ " values (?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values    
			preparedStmt.setInt(1, 0);    
			preparedStmt.setString(2, sellername);    
			preparedStmt.setString(3, ordername);    
			preparedStmt.setString(4, date);    
			preparedStmt.setString(5, desc);
			
			// execute the statement    
			preparedStmt.execute();    
			con.close();
			
			String newItems = readItems();    
			output = "{\"status\":\"success\", \"data\": \"" +        
						newItems + "\"}";   
		}   
		catch (Exception e)   
		{    
			output = "{\"status\":\"error\", \"data\":         "
					+ "\"Error while inserting the item.\"}";    
			System.err.println(e.getMessage());   
		}
		
		return output;  
	}
	
	
	public String updateItem(String ID, String sellername, String ordername, String date, String desc)  
	{   
		String output = "";  
	
		try   
		{    
			Connection con = connect();  
		
			if (con == null)    
			{     
				return "Error while connecting to the database for updating.";    }  
			
	   // create a prepared statement    
			String query = "UPDATE order SET sellername=?,ordername=?,orderdate=?,orderDesc=?WHERE sellerID=?";
	   
	   PreparedStatement preparedStmt = con.prepareStatement(query);
	   
	   // binding values    
	   preparedStmt.setString(1, sellername);    
	   preparedStmt.setString(2, ordername);    
	   preparedStmt.setString(3, date);    
	   preparedStmt.setString(4, desc);    
	   preparedStmt.setInt(5, Integer.parseInt(ID));
	   
	   // execute the statement    
	   preparedStmt.execute();    
	   con.close();
	   
	   String newItems = readItems();    
	   output = "{\"status\":\"success\", \"data\": \"" +    
	   newItems + "\"}";   
	   
	}   
		catch (Exception e)   
   {   
			output = "{\"status\":\"error\", \"data\":         "
					+ "\"Error while updating the Order.\"}";    
			System.err.println(e.getMessage());   
   	}  
	return output;  
}
	
	
	public String deleteItem(String sellerID)  
	{   
		String output = "";
		
		try   
		{    
			Connection con = connect();
			
			if (con == null)    
			{     
				return "Error while connecting to the database for deleting.";    
			}
			
			// create a prepared statement    
			String query = "delete from item where sellerID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(sellerID));
			
			// execute the statement    
			preparedStmt.execute();    
			con.close();
			
			String newItems = readItems();    
			output = "{\"status\":\"success\", \"data\": \"" +        
					newItems + "\"}";   
		}   
		catch (Exception e)   
		{    
			output = "{\"status\":\"error\", \"data\":         "
					+ "\"Error while deleting the item.\"}";    
			System.err.println(e.getMessage());   
		}
		
		return output;  
	} 
					
		

}
