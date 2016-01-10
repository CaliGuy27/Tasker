/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;

import javafx.scene.control.Button;

import javafx.scene.layout.VBox;
import tasker.Database;
import View.TaskerController;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import tasker.Employee;

/**
 * FXML Controller class
 *
 * @author Marquis
 */
public class SignInController implements Initializable 
{
  @FXML VBox parent;
  @FXML VBox signUp;
  @FXML VBox signIn;
  @FXML Button signInButton;
  @FXML TextField emailTF;
  @FXML PasswordField passwordTF;
  
  FXMLLoader loader;
  TaskerController tasker;
  SignUpController signUpController;
  
  Employee employee;
  Database database;
  
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    
    try {
      //initialize the tasker object
      tasker = TaskerController.class.newInstance();
    } catch (InstantiationException ex) {
      Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    try{
      //set path to fxml file
      URL location = getClass().getResource("/Login/SignUpView.fxml");
      loader = new FXMLLoader();
      //set the location of the loaders view file
      loader.setLocation(location);
      loader.setBuilderFactory(new JavaFXBuilderFactory());
    
      //open a stream without storing the layout into a variable
      //(no need to catch that instance because it is set inside of its class)
      loader.load(location.openStream());
      //get a reference to the signUpController
      signUpController = loader.getController();
      }catch(IOException io){
        System.out.println("Couldn't open stream to location");
      }
    
    employee = new Employee();
    database = new Database();
  }
  
  //create a reference to the main application
  public void setTaskerReference(TaskerController controller) throws IOException
  {
    tasker = controller;
  }//end method setTaskerReference
  
  @FXML
  public void signIn()
  {
    try{
      //if email does exist in the database, move on to the next step
      if(database.emailExists(getEmail()))
        //if password matches password in the database, grant user access to the application
        if(database.checkPassword(getEmail(), getPassword()))
        {
          tasker.signInToApp();
          clearAllFields();
        }
    }catch(IOException io)
    {
      
    }
  }//end method signIn
  
  //clear fields of any text
  private void clearAllFields()
  {
    emailTF.setText("");
    passwordTF.setText("");
  }//end method clearAllFields
  
  public String getEmail()
  {
    return emailTF.getText();
  }//end method getEmail
  
  public String getPassword()
  {
    return passwordTF.getText();
  }//end method getPassword()
  
  @FXML
  //grant access to the Dashboard
  private void showDashboard()
  {
    try{
      tasker.signInToApp();
    }catch(IOException io)
    {
      
    }
  }//end method showDashboard
  
  @FXML
  //show signUpView dialog
  private void showSignUp() throws IOException
  {
    signUpController.showModal();
  }//end method showSignUp
}//end class SignInController
