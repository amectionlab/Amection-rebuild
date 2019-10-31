package controller.utilities;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class LoginPanel {
    
    //Contenedor: se asigna por referencia
    AnchorPane container;
    
    //Nodos del panel
    TabPane tabPane;
    Tab loginTab;
    AnchorPane loginContent;
    
    public LoginPanel() {
        
        tabPane = new TabPane();
        loginTab = new Tab();
        
        loginContent = new AnchorPane();
        loginContent.setId("loginContent");
        
        tabPane.getTabs().add(loginTab);
        loginTab.setContent(loginContent);
    }
    
    public void initLoginPanel() {
        
        //Permite mover panel
        loginContent.setOnMouseDragged(event -> {
            tabPane.setLayoutX(event.getSceneX());
            tabPane.setLayoutY(event.getSceneY());
        });
        
    }    
    
    // Inserta el hub en el panel ingresado como parametro en coord x, y.
    public void insertInto(AnchorPane anchorPane, double x, double y) {
        
        container = anchorPane;
        anchorPane.getChildren().add(tabPane);
        
        tabPane.setLayoutX(x);
        tabPane.setLayoutY(y);
    }
    
}
