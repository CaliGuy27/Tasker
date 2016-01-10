/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;

/**
 * FXML Controller class
 *
 * @author Marquis
 */
public class TweetController implements Initializable 
{
  //graphical objects
  @FXML ToggleButton timelineBtn, profileBtn, settingsBtn;
  @FXML ScrollPane scrollPane;
  private VBox timeLine;
  private VBox profile;
  private VBox settings;
  @FXML Button tweetBtn;
  @FXML TextArea commentBox;
  
  //twitter objects
  private Twitter twitter;
  private User user;
  private final String CONSUMER_KEY = "bMfmFVAEQPsZzW5kBqvZgaIUH";
  private final String CONSUMER_SECRET = "WFNOXxyBteREtbxhZhkiohk1Mnpon2O5x4vBSOoQEc3TSJ54gL";
  
  //reference to the main application
  TaskerController tasker;
  
  //sets the reference to the main application
  public void setTaskerReference(TaskerController controller) throws IOException        
  {
    tasker = controller;
  }
  
  //load the users timeline
  public void loadTimeline() throws TwitterException, MalformedURLException
  {
    //get a list of statuses from users timeline
    List<Status> statuses = twitter.getHomeTimeline();
    
    //post status with name each time we loop through the statuses
    for(Status status : statuses)
    {
      TextArea post = new TextArea();
      
      user = status.getUser();
      URL url = new URL(user.getProfileImageURL());
      Image profImage = new Image(url.toString());
      ImageView viewer = new ImageView(profImage);
      
      post.setMinWidth(150);
      post.setMaxWidth(300);
      post.setMaxHeight(70);
      post.setEditable(false);
      post.setWrapText(true);
      VBox.setMargin(post, new Insets(5,0,0,0));
      post.setText(status.getUser().getName() +": "+ status.getText());
      
      HBox row = new HBox();
      row.getChildren().addAll(viewer, post);
      
      timeLine.getChildren().add(row);
    }
  }//end method loadTimeline

  //shows the users timeline
  @FXML private void showTimeline()
  {
    scrollPane.setContent(timeLine);
  }//end method showTimeline
 
  //shows the users profile
  @FXML private void showProfile()
  {
    scrollPane.setContent(profile);
  }//end method showProfile
  
  //shows the users settings
  @FXML private void showSettings()
  {
    scrollPane.setContent(settings);
  }//end method showSettings
  
  //get typed text and send to this apps "implemented" timeline as well as twitters timeline
  @FXML
  private void postTweet() throws TwitterException, MalformedURLException
  {
    //tweet to twitter
    twitter.updateStatus(commentBox.getText());
    
    //create (uneditable) visual twitter post for this application
    TextArea post = new TextArea();
    post.setText(commentBox.getText());
    
    //get the users information
    user = twitter.showUser(twitter.getId());
    //store the url of the users profile image
    URL url = new URL(user.getProfileImageURL());
    //place url in image file and show it in a viewer
    Image profImage = new Image(url.toString());
    ImageView viewer = new ImageView(profImage);
    //twitter post properties
    post.setMinWidth(150);
    post.setMaxWidth(300);
    post.setMaxHeight(70);
    post.setEditable(false);
    post.setWrapText(true);
    VBox.setMargin(post, new Insets(5,0,0,0));
    //add the text to the post
    post.setText(commentBox.getText() + "'JTasker App'");
    
    HBox row = new HBox();
    //add user image and post combination
    row.getChildren().addAll(viewer, post);
    //place on this applications "implemented" timeline
    timeLine.getChildren().add(row);
  }
  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb)
  {
    //create twitter object
    twitter = new TwitterFactory().getInstance();
    //used specifically for the application (this verifies the application with twitter)
    twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
    //my (the users) specific access information which will persist in a database once production ready
    twitter.setOAuthAccessToken(new AccessToken("192799309-khPEsofCf90vu5ejWG3zAKOlhcuyszg8l0MC4YEg", "1kMLko3zzQMyDwpZ8hC5oqRZQ5KqwQhCgYUy5cZSZROCM"));
    
    
    //the users timeline
    timeLine = new VBox();
    timelineBtn.setSelected(true);
    //give the timeline a scrollpane
    scrollPane.setContent(timeLine);
    //users profile
    profile = new VBox();
    //users settings
    settings = new VBox();
    
    //set the action for the tweet button
    tweetBtn.setOnAction(e->
    {
      try{postTweet();
      } 
      catch (TwitterException ex) {} catch (MalformedURLException ex) {
        Logger.getLogger(TweetController.class.getName()).log(Level.SEVERE, null, ex);
      }
    });
    
    //any text that is too long for the line is sent to next line
    commentBox.setWrapText(true);
  }  
  
}
