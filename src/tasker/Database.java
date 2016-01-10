/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tasker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Marquis
 */
public class Database 
{
  private Connection con;
  PreparedStatement ps;
  private ResultSet rs;
  private int key;
  
  private final String DATABASE = "tasker";
  private final String USERNAME = "root";
  private final String PASSWORD = "";
  
  //default constructor
  public Database()
  {
    try{
      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE , USERNAME, PASSWORD);
      ps = null;
      rs = null;
      key = 0;
    }catch(SQLException sql)
    {
      System.out.println("Having trouble connection to database with the giben Username and Password");
    }//end fo try and catch
    
    
  }//end default constructor
  
  //set the users session key
  public void setKey(String email)
  {
    String query1 = "SELECT loginID FROM login WHERE email = '" + email + "'";
    
    try
    {
      ps = con.prepareStatement(query1);
      rs = ps.executeQuery();
      
      if(rs.next())
        key = rs.getInt("loginID");
      
      System.out.println(key);
    }catch(SQLException sql)
    {
      System.out.println("Cannot get user key.");
    }
  }//end method setKey
  
  //set key to default upon exit
  public void resetKey()
  {
    key = 0;
  }//end method resetKey
  
  //checks the database to see if the email trying to be used already exists
  public Boolean emailExists(String email)
  {
    String query = "SELECT EMAIL FROM LOGIN WHERE EMAIL = '" + email + "'";
    String dbEmail = null;
    try{
      //prepare the statement with the bones of the query
      ps = con.prepareStatement(query);
      rs = ps.executeQuery();
      
      while(rs.next())
         dbEmail = rs.getString("email");
      
    }catch(SQLException sql){
      sql.printStackTrace();
      System.out.println("Cannot compare emails.");
    }
    
    return dbEmail != null;
  }//end method emailExists
  
  public Boolean checkPassword(String email, String password)
  {
    String query = "SELECT PASSWORD FROM LOGIN WHERE EMAIL = '" + email + "'";
    String passInDB = "";
    try
    {
      //prepare the statement with the bones of the query
      ps = con.prepareStatement(query);
      rs = ps.executeQuery();
      
      while (rs.next())
        passInDB = rs.getString("password");
      
    }catch(SQLException sql)
    {
      sql.printStackTrace();
      System.out.println("Could not compare user input with password in database. Please Try again.");
    }
    
    return passInDB.equals(password);
  }
  
  //add the user's information to database
  public void addUserToDB(String firstName, String lastName, String email, String password, int positionID)
  {
    //Database Queries
    String query1 = "INSERT INTO LOGIN (email, password) VALUES (?,?)";
    String query2 = "INSERT INTO EMPLOYEES (firstName, lastName, positionID, loginID) VALUES (?,?,?,?)";
    
    //try to insert email and password into the database
    try
    {
      //prepare the statement with the bones of the query
      ps = con.prepareStatement(query1);
      //add in the values to the query
      ps.setString(1, email);
      ps.setString(2, password);
      //execute the statement with the given query and values
      ps.executeUpdate();
    }//end of try
    catch(SQLException sql)
    {
      sql.printStackTrace();
      //if there is a problem, let the user know there was an issue with the function
      System.out.println("System could not execute Insert Query.Check the stack trace.");
    }//end of catch    
    
    setKey(email);
    
    //try to insert first/last name and position into the database
    try
    {
      //prepare the statement with the bones of the query
      ps = con.prepareStatement(query2);
      //add in the values to the query
      ps.setString(1, firstName);
      ps.setString(2, lastName);
      ps.setInt(3, positionID);
      ps.setInt(4, key);
      //execute the statement with the given query and values
      ps.executeUpdate();
    }//end of try
    catch(SQLException sql)
    {
      sql.printStackTrace();
      //if there is a problem, let the user know there was an issue with the function
      System.out.println("System could not execute Insert Query.Check the stack trace.");
    }//end of catch    
  }//end of method addUserToDB
}//end of class Database
