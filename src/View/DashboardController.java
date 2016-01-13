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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tasker.Project;

/**
 * FXML Controller class
 *
 * @author Marquis
 */
public class DashboardController implements Initializable {

  //GUI
  @FXML private ListView projectList;
  @FXML private Button addProjectBtn;
  @FXML private HBox projectSection;
  @FXML private VBox progressSection;
  @FXML private Label projectNameLabel;
  @FXML private Button jobNumButton;

  @FXML private TitledPane designTP;
  @FXML private HBox designRow;
  @FXML private ProgressIndicator schedInd;
  @FXML private Button scheduleBtn;
  @FXML private ProgressIndicator dwgInd;
  @FXML private Button drawingBtn;
  @FXML private ProgressIndicator manualInd;
  @FXML private Button manualBtn;
  @FXML private ProgressIndicator dbInd;
  @FXML private TitledPane softwareTP;
  @FXML private HBox softwareRow;
  @FXML private Button databaseBtn;
  @FXML private ProgressIndicator gfxInd;
  @FXML private Button graphicsBtn;
  @FXML private ProgressIndicator programInd;
  @FXML private Button programBtn;
  @FXML private TextArea projectNotes;
  

  private Project project;
  //Loaders
  FXMLLoader projectLoader;

  //Controllers
  NewProjectController projectController;
  TaskerController tasker;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    try {
      //set path to fxml file
      URL location = getClass().getResource("NewProjectView.fxml");
      projectLoader = new FXMLLoader();
      //set location of the NewProjectView
      projectLoader.setLocation(location);
      projectLoader.setBuilderFactory(new JavaFXBuilderFactory());
      //open a stream to the NewProjectView class and assign it to a layout
      projectLoader.load(location.openStream());
      //set reference to the NewProjectController Class
      projectController = projectLoader.getController();
      projectController.dashboardReference(this);

      projectList.setOnMouseClicked(e ->updateProjectProgress());
      designTP.setOnMouseClicked(e->designTP.setContent(designRow));
      softwareTP.setOnMouseClicked(e->softwareTP.setContent(softwareRow));
    } catch (IOException io) {}
  }

  public void setTaskerReference(TaskerController controller) throws IOException {
    tasker = controller;
  }//end method setTaskerReference

  @FXML
  public void showAddProjectModal() {
    projectController.showModal();
  }//end method showAddProjectModal

  @FXML
  public void updateProjectProgress() 
  {
    if (!projectList.getItems().isEmpty()) 
    {
      //get project information from the item selected in the list
      project = (Project) projectList.getSelectionModel().getSelectedItem();

      //update the project name, job number, and task progress
      projectNameLabel.setText(project.getProjectName());
      jobNumButton.setText(project.getProjectNumber());
      schedInd.setProgress((project.getScheduleStatus()));
      dwgInd.setProgress(project.getDrawingStatus());
      manualInd.setProgress(project.getManualStatus());
      dbInd.setProgress(project.getDatabaseStatus());
      gfxInd.setProgress(project.getGraphicsStatus());
      programInd.setProgress(project.getProgramStatus());
    }
  }//end method updateProjectProgress
  
  @FXML
  public void setProgress(){
    double currentProgress;
    Label item = new Label("");
    //item.translateYProperty().setValue(50);
    
    //set GUI elements
    ProgressIndicator progress = new ProgressIndicator();
    //progress.translateYProperty().setValue(50);
    
    Button updateBtn = new Button("Update");
    Button cancelBtn = new Button("Cancel");
    HBox btnRow = new HBox(cancelBtn, updateBtn);
    btnRow.setAlignment(Pos.CENTER);
    
    Slider slider = new Slider();
    slider.setMin(0);
    slider.setMax(100);
    slider.setMajorTickUnit(10);
    slider.setMinorTickCount(2);
    slider.showTickMarksProperty().set(true);
    slider.showTickLabelsProperty().set(true);
    //Give slider a minimum height or issues occur when mouse is released
    //in this case, without the height, when the slider is released above or below itself 
    //it will swap back to the designRow. 
    //slider.setMinHeight(150);
    slider.setOnMouseReleased(e->progress.setProgress(slider.getValue()/100));
    //VBox.setMargin(slider, new Insets(30,0,30,0));
    
    
    //Container for temp GUI elements
    VBox updater = new VBox(item, progress, slider, btnRow);
    updater.setMaxHeight(250);
    updater.setAlignment(Pos.CENTER);
    
    
    if(scheduleBtn.isArmed())
    {
      //switch to edit mode
      designTP.setContent(updater);
      
      //save the current progress of the schedule
      currentProgress = schedInd.getProgress();
      
      //set GUI properties
      item.setText("Schedule Progress");
      progress.setProgress(schedInd.getProgress());
      slider.setValue(schedInd.getProgress()*100);
      
      //Action events for buttons
      updateBtn.setOnAction(e->{
        //set the new value of the schedule
        schedInd.setProgress(slider.getValue()/100);
        //switch back to titled pane
        designTP.setContent(designRow);
      });
      /*cancelBtn.setOnAction(e->{
        //keep the current progress of the schedule
        schedInd.setProgress(currentProgress);
        //switch back to titled pane
        designTP.setContent(designRow);
      });*/
    }
    else if(drawingBtn.isArmed())
    {
      //switch to edit mode
      designTP.setContent(updater);
      
      //save the current progress of the drawing
      currentProgress = dwgInd.getProgress();
      
      //set GUI properties
      item.setText("Drawing Progress");
      progress.setProgress(dwgInd.getProgress());
      slider.setValue(dwgInd.getProgress()*100);
      
      //Action events for buttons
      updateBtn.setOnAction(e->{
        //set the new value of the schedule
        dwgInd.setProgress(slider.getValue()/100);
        //switch back to titled pane
        designTP.setContent(designRow);
      });
      /*cancelBtn.setOnAction(e->{
        //keep the current progress of the drawing
        dwgInd.setProgress(currentProgress);
        //switch back to titled pane
        designTP.setContent(designRow);
      });*/
    }
    else if(manualBtn.isArmed())
    {
      //switch to edit mode
      designTP.setContent(updater);
      
      //save the current progress of the manual
      currentProgress = manualInd.getProgress();
      
      //set GUI properties
      item.setText("Manual Progress");
      progress.setProgress(manualInd.getProgress());
      slider.setValue(manualInd.getProgress()*100);
      
      //Action events for buttons
      updateBtn.setOnAction(e->{
        //set the new value of the manual
        manualInd.setProgress(slider.getValue()/100);
        //switch back to titled pane
        designTP.setContent(designRow);
      });
      /*cancelBtn.setOnAction(e->{
        //keep the current progress of the manual
        manualInd.setProgress(currentProgress);
        //switch back to titled pane
        designTP.setContent(designRow);
      });*/
    }
    else if(databaseBtn.isArmed())
    {
      //switch to edit mode
      softwareTP.setContent(updater);
      
      //save the current progress of the database
      currentProgress = dbInd.getProgress();
      
      //set GUI properties
      item.setText("Database Progress");
      progress.setProgress(dbInd.getProgress());
      slider.setValue(dbInd.getProgress()*100);
      
      //Action events for buttons
      updateBtn.setOnAction(e->{
        //set the new value of the database
        dbInd.setProgress(slider.getValue()/100);
        //switch back to titled pane
        softwareTP.setContent(softwareRow);
      });
      cancelBtn.setOnAction(e->{
        //keep the current progress of the database
        dbInd.setProgress(currentProgress);
        //switch back to titled pane
        softwareTP.setContent(softwareRow);
      });
    }
    else if(graphicsBtn.isArmed())
    {
      //switch to edit mode
      softwareTP.setContent(updater);
      
      //save the current progress of the graphics
      currentProgress = gfxInd.getProgress();
      
      //set GUI properties
      item.setText("Graphics Progress");
      progress.setProgress(gfxInd.getProgress());
      slider.setValue(gfxInd.getProgress()*100);
      
      //Action events for buttons
      updateBtn.setOnAction(e->{
        //set the new value of the graphics
        gfxInd.setProgress(slider.getValue()/100);
        //switch back to titled pane
        softwareTP.setContent(softwareRow);
      });
      cancelBtn.setOnAction(e->{
        //keep the current progress of the graphics
        gfxInd.setProgress(currentProgress);
        //switch back to titled pane
        softwareTP.setContent(softwareRow);
      });
    }
    else if(programBtn.isArmed())
    {
      //switch to edit mode
      softwareTP.setContent(updater);
      
      //save the current progress of the programming
      currentProgress = programInd.getProgress();
      
      //set GUI properties
      item.setText("Programming Progress");
      progress.setProgress(programInd.getProgress());
      slider.setValue(programInd.getProgress()*100);
      
      //Action events for buttons
      updateBtn.setOnAction(e->{
        //set the new value of the programs
        programInd.setProgress(slider.getValue()/100);
        //switch back to titled pane
        softwareTP.setContent(softwareRow);
      });
      cancelBtn.setOnAction(e->{
        //keep the current progress of the programs
        programInd.setProgress(currentProgress);
        //switch back to titled pane
        softwareTP.setContent(softwareRow);
      });
    }
  }//end method setProgress

  public void addProjectToList(Project p) 
  {
      project = p;
      projectList.getItems().add(p);
  }//end method addProjectToList
}//end class DashboardController
