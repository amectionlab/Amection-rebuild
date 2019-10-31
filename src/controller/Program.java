package controller;

import controller.utilities.HubPanel;
import javafx.geometry.Rectangle2D;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.utilities.Database;
import model.utilities.Session;

public class Program extends Application{
    
    //Parametros del programa, base de datos y sesión.
    public static Database db = new Database();
    public static Session session = new Session();
    private double width;
    private double height;
    
    
    public static void main(String[] args) throws IOException {
        
        //Inicializacion de base de datos
        db.initDatabase();
        
        Application.launch(args);
    }
    
    @Override
    public void init() throws Exception{
        db.initDatabase();
        
        //Obtiene dimensiones de la pantalla
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        width  = screenBounds.getWidth();
        height = screenBounds.getHeight();
        
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        //Establece titulo de ventana
        primaryStage.setTitle("Amection");
        
        //Crea contenido de escena
        AnchorPane background = new AnchorPane();
        background.setId("backgroundPane");
        
        //Crea escena incluyendo el contenido en resolucion de pantalla completa
        Scene scene = new Scene(background, width, height);
        scene.setFill(Color.TRANSPARENT);
        
        //Añade escena a la ventana
        primaryStage.setScene(scene);
        
        //Ajusta de ventana
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        
        // ***** HUB ***** //
        HubPanel hub = new HubPanel();
        hub.initHub();
        hub.insertInto(background, 500, 150);
        hub.setExitButtonPos(width-100, height-125);
        Button exitButton = hub.getExitButton();
        background.getChildren().add(exitButton);
        // ***** END HUB ***** //
        
        //Añade estilo de a la escena
        String css = this.getClass().getResource("../resources/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        
        //Muestra ventana principal
        primaryStage.show();
    }
    
    @Override
    public void stop() throws Exception {
        
    }
    
    
}