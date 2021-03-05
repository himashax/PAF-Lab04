package com.codebind;
import java.sql.*;

public class Item {
	public Connection connect()
	{
	 Connection con = null;

	 
	 try
	 {
		 
	 Class.forName("com.mysql.jdbc.Driver");
	 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_lab",
	 "root", "");
	 
	 	System.out.println("Successfully connected");
	 }
	 catch(Exception e)
	 {
	 e.printStackTrace();
	 }

	 return con;
	}
	
	public String insertItem(String code, String name, String price,String desc) {
		String result = "";
		try {
			Connection connection = connect();
			if(connection == null) {
				result = "Error when connecting to the database";
				return result;
			}
			
			String insertQuery = "insert into items values(?,?,?,?,?)";
			
			PreparedStatement ps;
			
			ps = connection.prepareStatement(insertQuery);
			ps.setInt(1, 0);
			ps.setString(2, code);
			ps.setString(3, name);
			ps.setDouble(4, Double.parseDouble(price));
			ps.setString(5, desc);
			ps.execute();
			connection.close();
			result = "Inserted Successfully";
		} catch (SQLException e) {
			result = "Exception Occurred";
			e.printStackTrace();
		}
		return result;
	}
	
	
	public String readItems()
	{
	 String output = "";
	try
	 {
	 Connection connection = connect();
	 if (connection == null)
	 {
	 return "Error while connecting to the database for reading.";
	 }
	
	 output = "<table border=‘1’><tr><th>Item Code</th>"
	 +"<th>Item Name</th><th>Item Price</th>"
	 + "<th>Item Description</th>"
	 + "<th>Update</th><th>Remove</th></tr>";
	 
	 String readQuery = "select * from Items";
	 Statement statement = connection.createStatement();
	 ResultSet rs = statement.executeQuery(readQuery);
	 
	 while (rs.next())
	 {
		 
	 String itemID = Integer.toString(rs.getInt("itemID"));
	 String itemCode = rs.getString("itemCode");
	 String itemName = rs.getString("itemName");
	 String itemPrice = Double.toString(rs.getDouble("itemPrice"));
	 String itemDesc = rs.getString("itemDesc");
	 
	 output += "<tr><td>" + itemCode + "</td>";
	 output += "<td>" + itemName + "</td>";
	 output += "<td>" + itemPrice + "</td>"; 
	 output += "<td>" + itemDesc + "</td>";
	 output += "<td><input type='submit' value='Update' class=\"btn btn-primary\"></input></td>";
	 output += "<td><form method=‘post’ action='items.jsp'><input type='hidden' name='itemID' value='"+itemID+"'></input>"
	 		+ "<input type='submit' value='Remove' class=\"btn btn-danger\"></input></form></td></tr>";	 
	 }
	 connection.close();
	 output += "</table>";
	 }
	catch (Exception e)
	 {
	 output = "Error while reading the items.";
	 System.err.println(e.getMessage());
	 }
	return output;
	}
	
	
	public String deleteItem(String itemID)
	{
		 String output = "";
		try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
		 return "Error while connecting to the database for deleting.";
		 }
		
		 String query = "delete from items where itemID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		
		 preparedStmt.setInt(1, Integer.parseInt(itemID));
	
		 
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		catch (Exception e)
		 {
		 output = "Error while deleting the item.";
		 System.err.println(e.getMessage());
		 }
		return output;
	}
	
}
