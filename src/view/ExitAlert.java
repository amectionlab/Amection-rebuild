package view;

import controller.Program;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ExitAlert {
    
    AnchorPane container;
    Label alertLabel;
    Button staytButton, leaveButton;
    
    public ExitAlert() {
        
        container = new AnchorPane();
        container.setId("exitBox");
        
        alertLabel = new Label("Are you sure you want to exit the application?");
        alertLabel.setId("titleLabel");
        
        Button stayButton = new Button("Stay");
        stayButton.setId("stayButton");
        
        Button leaveButton = new Button("Leave");
        leaveButton.setId("leaveButton");
        
        //Inserta en contenedor
        container.getChildren().add(alertLabel);
        container.getChildren().add(stayButton);
        container.getChildren().add(leaveButton);
        
        //Configuracion de nodos
        stayButton.setLayoutX(100); stayButton.setLayoutY(50);
        leaveButton.setLayoutX(300); leaveButton.setLayoutY(50);
        
        //Acciones de botones
        stayButton.setOnAction(event -> {
            
            //Añade efecto
            Program.mainContent.setEffect(null);
            
            //Cuadro de salida customizado
            
            //Configura panel superior de efectos
            Program.background.getChildren().remove(1);
        });
        
        leaveButton.setOnAction(event -> {
            Platform.exit();
        });
    }
    
    public void insertInto(AnchorPane anchorPane, double x ,double y) {
    
        anchorPane.getChildren().add(container);
        
        container.setLayoutX(x);
        container.setLayoutY(y);
        
    }
    
}
