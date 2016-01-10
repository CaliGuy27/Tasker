/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Marquis
 */
public class Tasker extends Application {
  
  @Override
  public void start(Stage stage) throws Exception {
    
    Parent root= FXMLLoader.load(getClass().getResource("/View/TaskerView.fxml"));   
    
    
    Scene scene = new Scene(root);
    scene.getStylesheets().add("/Login/splash.css");
    
    stage.setScene(scene);
    stage.setMaximized(true);
    stage.show();
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
  
}
