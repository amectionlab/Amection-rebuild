package view;

import controller.utilities.FXUtil;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginPanel {
    
    //Contenedor: se asigna por referencia
    AnchorPane container;
    
    AnchorPane loginContent;
    
    //Nodos de loginContent
    Label titleLabel, errorLabel;
    TextField rutField;
    PasswordField passwordField;
    CheckBox isAdminCheck;
    Button submitButton;
    
    public LoginPanel() {
        
        //Tab Content
        loginContent = new AnchorPane();
        loginContent.setId("loginContent");
        
        // Nodos de loginContent
        titleLabel = new Label("Inicia sesión con tu cuenta Amection");
        titleLabel.setId("titleLabel");
        
        rutField = new TextField();
        rutField.setPromptText("Rut");
        rutField.setId("rutField");
        
        passwordField = new PasswordField();
        passwordField.setPromptText("Contraseña");
        passwordField.setId("passwordField");
        
        isAdminCheck = new CheckBox("Iniciar como administrador");
        isAdminCheck.setId("adminCheckBox");
        
        errorLabel = new Label("Rut o contraseña incorrecto/s");
        errorLabel.setId("errorLabel");
        FXUtil.disableAlert(errorLabel);
        
        submitButton = new Button("Connect");
        submitButton.setId("submitButton");
        
        //Inserta widgets dentro de loginContent (AnchorPane)
        loginContent.getChildren().add(titleLabel);
        loginContent.getChildren().add(rutField);
        loginContent.getChildren().add(passwordField);
        loginContent.getChildren().add(isAdminCheck);
        loginContent.getChildren().add(errorLabel);
        loginContent.getChildren().add(submitButton);
    }
    
    public void initLoginPanel() {
        
        //Ajustes de posición de nodos
        titleLabel.setLayoutX(60); titleLabel.setLayoutY(50);
        rutField.setLayoutX(95); rutField.setLayoutY(150);
        passwordField.setLayoutX(95); passwordField.setLayoutY(220);
        isAdminCheck.setLayoutX(150); isAdminCheck.setLayoutY(270);
        errorLabel.setLayoutX(155); errorLabel.setLayoutY(300);
        submitButton.setLayoutX(195); submitButton.setLayoutY(330);
    }
    
    // Inserta el hub en el panel ingresado como parametro en coord x, y.
    public void insertInto(AnchorPane anchorPane, double x, double y) {
        
        container = anchorPane;
        anchorPane.getChildren().add(loginContent);
        
        loginContent.setLayoutX(x);
        loginContent.setLayoutY(y);
    }
    
    // Oculta panel inicio de sesion
    public void hideLoginPanel(boolean hide) {
        
        if (hide ) {
            loginContent.setVisible(false);
        }
        else{
            loginContent.setVisible(true);
        }
    }
}
