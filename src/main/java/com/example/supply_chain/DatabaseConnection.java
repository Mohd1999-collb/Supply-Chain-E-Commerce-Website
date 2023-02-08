package com.example.supply_chain;
import java.sql.*;

public class DatabaseConnection{
   /* Make the connection from database */
   String sqlUrl = "jdbc:mysql://localhost:3306/Supply_Chain?useSSL=false";
   String userName = "root";
   String password = "MohdTalib@7232";
   Connection con = null;

   DatabaseConnection(){
      try{
         con = DriverManager.getConnection(sqlUrl, userName, password);
         if(con != null){
            System.out.println("OUR DATABASE CONNECTION IS SUCCESSFUL!");
         }
      }catch(Exception e){
         e.printStackTrace();
      }
   }

   /* Here we execute the database CRUD operation */

   /* 1. Read the data from the database*/
   public ResultSet executeQuery(String query) throws SQLException {
      ResultSet result = null;
      Statement statement = con.createStatement();
      /* Execute the query and return the given query result and stored it in ResultSet */
      result = statement.executeQuery(query);
      return result;
   }

   /* 2. Insert the new data into tha database and return the number of row affected*/
   public int executeInsert(String query) throws SQLException {
      int result = 0;
      Statement statement = con.createStatement();
      result = statement.executeUpdate(query);
      return result;
   }
}
