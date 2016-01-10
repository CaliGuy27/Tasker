/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tasker;

/**
 *
 * @author Marquis
 */
public class Project 
{
  private String projectName;
  private String projectNumber;
  
  private Employee designEngineer;
  private Employee softwareEngineer;
  
  private double scheduleStatus;
  private double drawingStatus;
  private double manualStatus;
  private double databaseStatus;
  private double graphicsStatus;
  private double programStatus;
  private String notes;
  
  public Project()
  {
    projectName = "";
    projectNumber = "";
    
    designEngineer = new Employee();
    softwareEngineer = new Employee();
    
    scheduleStatus = 0;
    drawingStatus = 0;
    manualStatus = 0;
    databaseStatus = 0;
    graphicsStatus = 0;
    programStatus = 0;
    
    notes="";
  }
  
  public Project(String name, String number)
  {
    projectName = name;
    projectNumber = number;
    
    designEngineer = new Employee();
    softwareEngineer = new Employee();
    
    scheduleStatus = 0;
    drawingStatus = 0;
    manualStatus = 0;
    databaseStatus = 0;
    graphicsStatus = 0;
    programStatus = 0;
  }

  /**
   * @return the projectName
   */
  public String getProjectName() {
    return projectName;
  }

  /**
   * @param projectName the projectName to set
   */
  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  /**
   * @return the projectNumber
   */
  public String getProjectNumber() {
    return projectNumber;
  }

  /**
   * @param projectNumber the projectNumber to set
   */
  public void setProjectNumber(String projectNumber) {
    this.projectNumber = projectNumber;
  }

  /**
   * @return the designEngineer
   */
  public Employee getDesignEngineer() {
    return designEngineer;
  }

  /**
   * @param designEngineer the designEngineer to set
   */
  public void setDesignEngineer(Employee designEngineer) {
    this.designEngineer = designEngineer;
  }

  /**
   * @return the softwareEngineer
   */
  public Employee getSoftwareEngineer() {
    return softwareEngineer;
  }

  /**
   * @param softwareEngineer the softwareEngineer to set
   */
  public void setSoftwareEngineer(Employee softwareEngineer) {
    this.softwareEngineer = softwareEngineer;
  }

  /**
   * @return the scheduleStatus
   */
  public double getScheduleStatus() {
    return scheduleStatus;
  }

  /**
   * @param scheduleStatus the scheduleStatus to set
   */
  public void setScheduleStatus(double scheduleStatus) {
    this.scheduleStatus = scheduleStatus/100;
  }

  /**
   * @return the drawingStatus
   */
  public double getDrawingStatus() {
    return drawingStatus;
  }

  /**
   * @param drawingStatus the drawingStatus to set
   */
  public void setDrawingStatus(double drawingStatus) {
    this.drawingStatus = drawingStatus/100;
  }

  /**
   * @return the manualStatus
   */
  public double getManualStatus() {
    return manualStatus;
  }

  /**
   * @param manualStatus the manualStatus to set
   */
  public void setManualStatus(double manualStatus) {
    this.manualStatus = manualStatus/100;
  }

  /**
   * @return the databaseStatus
   */
  public double getDatabaseStatus() {
    return databaseStatus;
  }

  /**
   * @param databaseStatus the databaseStatus to set
   */
  public void setDatabaseStatus(double databaseStatus) {
    this.databaseStatus = databaseStatus/100;
  }

  /**
   * @return the graphicsStatus
   */
  public double getGraphicsStatus() {
    return graphicsStatus;
  }

  /**
   * @param graphicsStatus the graphicsStatus to set
   */
  public void setGraphicsStatus(double graphicsStatus) {
    this.graphicsStatus = graphicsStatus/100;
  }

  /**
   * @return the programStatus
   */
  public double getProgramStatus() {
    return programStatus;
  }

  /**
   * @param programStatus the programStatus to set
   */
  public void setProgramStatus(double programStatus) {
    this.programStatus = programStatus/100;
  }
  
  /**
   * @return the notes
   */
  public String getNotes()
  {
    return notes;
  }
  
  /**
   * 
   * @param notes the notes to set 
   */
  public void setNotes(String notes){
    this.notes = notes;
  }
  
  @Override
  public String toString()
  {
    return projectName;
  }
  
}
