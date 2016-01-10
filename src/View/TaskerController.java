/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Login.SignInController;
import View.DashboardController;
import View.TweetController;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import twitter4j.TwitterException;

/**
 *
 * @author Marquis
 */
public class TaskerController implements Initializable {

  //GUI properties
  @FXML BorderPane container;
  @FXML Menu socialMenu;
  @FXML VBox signUp;
  @FXML VBox signIn;
  @FXML Label fullNameLabel; 
  @FXML Label positionLabel;
  @FXML ListView projectList;

  //Loaders
  FXMLLoader loader;
  FXMLLoader dashboardLoader;
  FXMLLoader twitterLoader;

  //Controllers
  SignInController signInController;
  DashboardController dashController;
  TweetController tweetController;

  BorderPane twitterPane;
  BorderPane dashBoard;
  VBox login;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    try {
      //set path to TweetView
      URL tweetLocation = getClass().getResource("/View/TweetView.fxml");
      twitterLoader = new FXMLLoader();
      //set the loacation of the TweetView
      twitterLoader.setLocation(tweetLocation);
      twitterLoader.setBuilderFactory(new JavaFXBuilderFactory());

      //open a stream to the TweetView class and assign it to a layout
      twitterPane = twitterLoader.load(tweetLocation.openStream());
      VBox.setMargin(twitterPane, new Insets(0, 5, 0, 0));
      //reference to TweetController class
      tweetController = twitterLoader.getController();

      //set path to DashBoardView
      URL dashLocation = getClass().getResource("/View/DashboardView.fxml");
      dashboardLoader = new FXMLLoader();
      //set the tweetLocation of the DashBoardView
      dashboardLoader.setLocation(dashLocation);
      dashboardLoader.setBuilderFactory(new JavaFXBuilderFactory());

      //open a stream to the DashBoardView class and assign it to a layout
      dashBoard = dashboardLoader.load(dashLocation.openStream());
      //reference to DashBoardController Class
      dashController = dashboardLoader.getController();

      //set path to fxml file
      URL location = getClass().getResource("/Login/SignInView.fxml");
      loader = new FXMLLoader();
      //set location of the SignInView
      loader.setLocation(location);
      loader.setBuilderFactory(new JavaFXBuilderFactory());

      //open a stream to the SignInView class and assign it to a layout
      login = loader.load(location.openStream());
      //reference to SignInController Class
      signInController = loader.getController();
      //send a reference of this class to the signInController
      signInController.setTaskerReference(this);
    } catch (MalformedURLException ex) {
      Logger.getLogger(TaskerController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(TaskerController.class.getName()).log(Level.SEVERE, null, ex);
    }
    container.setLeft(null);
    socialMenu.setDisable(true);
    try {
      showLogin();
    } catch (IOException ex) {
      Logger.getLogger(TaskerController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  //grant access to application (dashboard first)
  public void signInToApp() throws IOException {
    showDashboard();
    container.setLeft(projectList);
    //enable the social menu 
    socialMenu.setDisable(false);
  }//end method signInToApp

  //Show the login page
  public void showLogin() throws IOException {
    //disable socialMenu - should not be available if user isn't logged in
    socialMenu.setDisable(true);

    container.setLeft(null);
    //place layout in the center of the application
    container.setCenter(login);
    //make it seem as though there is no focus given 
    Platform.runLater(() -> container.requestFocus());
  }//end method showLogin

  //show the dashboard
  public void showDashboard() throws IOException {
    //send a reference of this class to the DashboardController
    dashController.setTaskerReference(this);

    //place layout in the center of the application
    container.setCenter(dashBoard);
  }//end method showDashboard

  @FXML
  //sign into twitter
  public void signInToTwitter() throws IOException, TwitterException {
    //get the timeline of the user from twitter
    tweetController.loadTimeline();
    //place all of the tweets on the right side of the application
    container.setRight(twitterPane);
  }//end method signInToTwitter

  @FXML
  //make tweet tab visible
  public void openTweetTab() {
    container.setRight(twitterPane);
  }//end method openTweetTab

  @FXML
  //make tweet tab invisible
  public void minimizeTweetTab() {
    container.setRight(null);
  }//end method minimizeTweetTab

  //Methods for main application properties------------------------>
  //set the value for the Full Name label
  public void setFullNameLabel(String fullName) {
    fullNameLabel.setText(fullName);
  }//end method setFullName

  //return the Full Name value
  public String getFullName() {
    return fullNameLabel.getText();
  }//end method getFullNameLabel

  //set the value for the Position label
  public void setPostionLabel(String position) {
    positionLabel.setText(position);
  }//end method setPosition

  //return the value for the 
  public String getPosition() {
    return positionLabel.getText();
  }//end method getPosition
}//end class TaskerController
