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
public class Employee 
{
  private String firstName;
  private String lastName;
  private String position;
  private String fullName;
  private String email;
  private String password;
  private int key;
  
  public Employee()
  {
    firstName = "";
    lastName = "";
    fullName = "";
    position = "";
    email = "";
    password = "";
    key = 0;
  }
  
  public Employee(String fName, String lName, String pos)
  {
    firstName = fName;
    lastName = lName;
    fullName = fName + " " + lName;
    position = pos;
  }
  
  public Employee(String wholeName, String pos)
  {
    fullName = wholeName;
    position = pos;
  }

  /**
   * @return the firstName
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * @param firstName the firstName to set
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * @return the lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * @param lastName the lastName to set
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  /**
   * @return the fullName
   */
  public String getFullName() {
    return firstName + " " + lastName;
  }
  
  /**
   * @param fullName the position to set 
   */
  public void setFullName(String fullName)
  {
    this.fullName = fullName;
  }

  /**
   * @return the position
   */
  public String getPosition() {
    return position;
  }

  /**
   * @param position the position to set
   */
  public void setPosition(String position) {
    this.position = position;
  }
  
  public String getEmail()
  {
    return email;
  }//end method getEmail
  
  public void setEmail(String e)
  {
    email = e;
  }//end method setEmail
  
  public String getPassword()
  {
    return password;
  }//end method getPassword
  
  public void setPassword(String pass)
  {
    password = pass;
  }//end method setPassword;
  
  @Override
  public String toString()
  {
    return fullName;
  }
}
