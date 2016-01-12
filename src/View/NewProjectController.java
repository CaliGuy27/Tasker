/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tasker.Employee;
import tasker.Project;

/**
 * FXML Controller class
 *
 * @author Marquis
 */
public class NewProjectController implements Initializable {

  //GUI
  private Stage modal;
  private @FXML VBox container;
  private @FXML TextField pNameTF;
  private @FXML TextField pNumberTF;
  private @FXML ComboBox dEngineerCB;
  private @FXML ComboBox sEngineerCB;
  private @FXML TextField scheduleStatusTF;
  private @FXML TextField drawingStatusTF;
  private @FXML TextField manualStatusTF;
  private @FXML TextField databaseStatusTF;
  private @FXML TextField graphicsStatusTF;
  private @FXML TextField programStatusTF;

  //Controllers
  DashboardController dash;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    modal = new Stage();
    modal.setScene(new Scene(container));
    modal.initModality(Modality.APPLICATION_MODAL);
    
    dEngineerCB.getItems().addAll("Christian Omphroy");
    sEngineerCB.getItems().addAll("Marquis Butler");
  }//end method initialize
  
  public void dashboardReference(DashboardController controller) throws IOException {
    dash = controller;
  }//end method dashboardReference
  
  @FXML  
  public void showModal() {
    modal.show();
  }//end method showModal
  
  @FXML
  public void hideModal() {
    modal.hide();
  }//end method hideModal
  
  @FXML
  public void createProject() {
    Project project = new Project(pNameTF.getText(), pNumberTF.getText());
    
    String dEngineer = dEngineerCB.getValue().toString();
    String deName[] = dEngineer.split(" ");
    project.setDesignEngineer(new Employee(deName[0], deName[1], "Design Engineer"));
    
    String sEngineer = sEngineerCB.getValue().toString();
    String seName[] = sEngineer.split(" ");
    project.setSoftwareEngineer(new Employee(seName[0], seName[1], "Software Engineer"));
    
    project.setScheduleStatus(Double.parseDouble(scheduleStatusTF.getText()));
    project.setDrawingStatus(Double.parseDouble(drawingStatusTF.getText()));
    project.setManualStatus(Double.parseDouble(manualStatusTF.getText()));
    project.setDatabaseStatus(Double.parseDouble(databaseStatusTF.getText()));
    project.setGraphicsStatus(Double.parseDouble(graphicsStatusTF.getText()));
    project.setProgramStatus(Double.parseDouble(programStatusTF.getText()));
    //add project to the list
    dash.addProjectToList(project);
    //reset the values in each field
    clearAllFields();
    //exit modal when done
    hideModal();
  }//end method createProject
  
  public void clearAllFields() {
    pNameTF.setText("");
    pNumberTF.setText("");
    dEngineerCB.getSelectionModel().clearSelection();
    sEngineerCB.getSelectionModel().clearSelection();
    scheduleStatusTF.setText("");
    drawingStatusTF.setText("");
    manualStatusTF.setText("");
    databaseStatusTF.setText("");
    graphicsStatusTF.setText("");
    programStatusTF.setText("");
  }//end method clearAllFields
}//end class NewProjectController
