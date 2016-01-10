/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Login;

import javafx.scene.paint.Color;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tasker.Database;
import View.TaskerController;

/**
 *
 * @author Marquis
 */
public class SignUpController implements Initializable
{
  @FXML private VBox signUpContainer;
  @FXML private TextField firstNameTF;
  @FXML private TextField lastNameTF;
  @FXML private TextField emailTF;
  @FXML private PasswordField passwordTF;
  @FXML private PasswordField confirmPasswordTF;
  @FXML private RadioButton deRadioBtn;
  @FXML private VBox passCautionArea;
  @FXML private VBox emailCautionArea;
  
  private Database database;
  private TaskerController tasker;
  private Stage modal;
  

  @Override
  public void initialize(URL location, ResourceBundle resources) 
  {
    database = new Database();
    
    modal = new Stage();
    //need to set CSS up to style the scene the way we want
    //modal.initStyle(StageStyle.UNDECORATED);
    modal.setResizable(false);
    modal.setScene(new Scene(signUpContainer));
    //clear all of the fields when the window is closed by pressing the X
    modal.setOnCloseRequest(e->clearAllFields());
    Platform.runLater(()->signUpContainer.requestFocus());
    
    deRadioBtn.setSelected(true);
    
    //when enter button is clicked add user info to database
    //firstNameTF.setOnAction(e->addAccount());
    //lastNameTF.setOnAction(e->addAccount());
    //emailTF.setOnAction(e->addAccount());
    //passwordTF.setOnAction(e->addAccount());
    //confirmPasswordTF.setOnAction(e->addAccount());
    
    //if warning is up, when the user clicks into the field to fix error it dissapears
    passwordTF.setOnMouseClicked(e->passCautionArea.getChildren().clear());
    confirmPasswordTF.setOnMouseClicked(e->passCautionArea.getChildren().clear());
    emailTF.setOnMouseClicked(e->emailCautionArea.getChildren().clear());
  }
  
  //clear fields of any text
  private void clearAllFields()
  {
    firstNameTF.setText("");
    lastNameTF.setText("");
    emailTF.setText("");
    passwordTF.setText("");
    confirmPasswordTF.setText("");
  }//end method clearAllFields
  
  //add employee to system
  @FXML
  public void addAccount()
  {
    Label alert = new Label("");
    alert.setTextFill(Color.RED);
    
    //clean up user's input
    String firstName = parseTFValue(getFirstName());
    String lastName = parseTFValue(getLastName());
    
    //if email doesnt exist in the database, move on to the next step
    if(!database.emailExists(emailTF.getText()))
    {
      //if both passwords the user typed match, move on to the next step
      if(comparePasswords(getPassword(), getConfirmPassword()))
      {
        //if none of the text fields are empty add the user information to the database
        if(!tfIsEmpty())
        {
          database.addUserToDB(firstName, lastName, getEmail(), getPassword(), getPosition());
          hideModal();
        }
      }
      else
      {
        //if there is no alert showing - show an alert
        if(passCautionArea.getChildren().isEmpty())
        {
          alert.setText("Caution: Passwords do not match. Please fix.");
          passCautionArea.getChildren().add(alert);
          passCautionArea.setAlignment(Pos.CENTER);
        }
      }
    }//end if
    else
    {
      //if there is no alert showing = show alert
      if(emailCautionArea.getChildren().isEmpty())
      {
        alert.setText("Email already taken. Please try again.");
        emailCautionArea.getChildren().add(alert);
        emailCautionArea.setAlignment(Pos.CENTER);
      }
    }//end else
    
  }//end method addAccount
  
  //validate user password
  private Boolean comparePasswords(String pass, String verifyPass)
  {
    //if the passwords match return true
    if(pass.equals(verifyPass))
      return true;
    else
      return false;
  }//end method passowrdIsValid
  
  //show error if any text field is empty
  private Boolean tfIsEmpty()
  {
    return firstNameTF.getText().isEmpty() || lastNameTF.getText().isEmpty() ||
            emailTF.getText().isEmpty() || passwordTF.getText().isEmpty() || confirmPasswordTF.getText().isEmpty();
  }//end method tfISEmpty
  
  //create a reference to the main application
  public void setTaskerReference(TaskerController t)
  {
    tasker = t;
  }//end method setTaskerReference
  
  @FXML
  //make dialog visible
  public void showModal(){
    modal.show();
  }//end method showModal
  
  @FXML
  //make dialog invisible
  public void hideModal(){
    clearAllFields();
    deRadioBtn.setSelected(true);
    modal.hide();
  }//end method hideModal
  
  /**
   * @return the firstNameTF value
   */
  public String getFirstName() {
    return firstNameTF.getText();
  }//end method getFirstName

  /**
   * @return the lastNameTF value 
   */
  public String getLastName() {
    return lastNameTF.getText();
  }//end method getLastName

  /**
   * @return the emailTF value
   */
  public String getEmail() {
    return emailTF.getText();
  }//end method getEmail

  /**
   * @return the passwordTF value
   */
  public String getPassword() {
    return passwordTF.getText();
  }//end method getPassword
  
  /**
   * @return the confirmPasswordTF value
   */
  public String getConfirmPassword(){
  return confirmPasswordTF.getText();
}//end method getPassword
  
  /**
   * @return the position
   */
  public int getPosition()
  {
    int position;
    
    if(deRadioBtn.isSelected())
      position = 1;
    else
      position = 2;
    
    return position;
  }//end method getPosition
  
  //clean up user input
  private String parseTFValue(String string)
  {
    //take away and spaces at the beginning and end of the name
    //make all letters lowercase
    //the functions create a copy of the string and then apply themselves to it.
    String name = string.trim().toLowerCase();
    
    return name;
  }//end method  
}//end class SignUpController
