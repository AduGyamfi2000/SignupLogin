package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class MainController implements Initializable{
	
	ObservableList<String> typeList = FXCollections
			.observableArrayList("Admin", "Client", "Guest");
	
    @FXML
    private TextField email_signup;

    @FXML
    private Button login_btn;

    @FXML
    private AnchorPane login_panel;

    @FXML
    private AnchorPane panel_signup;

    @FXML
    private PasswordField password_login;

    @FXML
    private TextField password_signup;

    @FXML
    private ComboBox<String> type_login;

    @FXML
    private ComboBox<String> type_signup;

    @FXML
    private TextField username_login;

    @FXML
    private TextField username_signup;
    
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    @FXML
    void login(ActionEvent event) throws ClassNotFoundException, SQLException {
    	
    	con =mysqlConnect.ConnectDb();
    	String sql = "Select * from users where username = ? and pass_word = ?";
    	try {
    		pst = con.prepareStatement(sql);
    		pst.setString(1, username_login.getText());
    		pst.setString(2, password_login.getText());
    		rs = pst.executeQuery();
    		if (rs.next()) {
    			JOptionPane.showMessageDialog(null, "Correct username and password");
    		} else {
    			JOptionPane.showMessageDialog(null, "Incorrect username and password");
    		}
    		
    	} catch (Exception e) {
    		JOptionPane.showMessageDialog(null, e);
    	}
    }
    	
    	@FXML
    	public void addUsers(ActionEvent event) {
    		con = mysqlConnect.ConnectDb();
        	String sql = "Insert into users (username, pass_word, email) values (?, ?, ?, )";
        			
        	try {
        		pst = con.prepareStatement(sql);
        		pst.setString(1, username_signup.getText());
        		pst.setString(2, password_signup.getText());
        		pst.setString(2, email_signup.getText());
        		
        		rs = pst.executeQuery();
        		
        			JOptionPane.showMessageDialog(null, "Saved");
        		
        	} catch (Exception e) {
        		JOptionPane.showMessageDialog(null, e);
        	}
    	
    	
    }
    
    @FXML
    public void showSignupPanel() {
    	login_panel.setVisible(false);
    	panel_signup.setVisible(true);
    }
    
    @FXML
    public void showLoginPanel() {
    	login_panel.setVisible(true);
    	panel_signup.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	type_login.getItems().addAll(typeList);
    	type_signup.getItems().addAll(typeList);
    }

}
