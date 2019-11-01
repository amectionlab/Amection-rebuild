package controller;

import java.io.FileInputStream;
import javafx.geometry.Rectangle2D;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.utilities.Database;
import model.utilities.Session;
import view.HubPanel;
import view.LoginPanel;

public class Program extends Application{
    
    //Declaración
    public static Database db = new Database();
    public static Session session = new Session();
    public static double width;
    public static double height;
    public static HubPanel hub;
    public static LoginPanel loginPanel;
    public static AnchorPane background;
    public static AnchorPane mainContent; 
    public static AnchorPane unblurredPane;
    
    public static void main(String[] args) throws IOException {
        
        //Inicializacion de base de datos
        db.initDatabase();
        
        Application.launch(args);
    }
    
    @Override
    public void init() throws Exception{
        
        //Obtiene dimensiones de la pantalla
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        width  = screenBounds.getWidth();
        height = screenBounds.getHeight();

        //Panel de fondo
        background = new AnchorPane();
        background.setId("backgroundPane");
        
        //Panel de contenido
        mainContent = new AnchorPane();
        mainContent.setId("mainContentPane");
        
        //Panel superpuesto a panel de contenido
        unblurredPane = new AnchorPane();
        unblurredPane.setId("unblurredPane");
        
        //Agregar contenido principal sobre el background
        background.getChildren().add(0, mainContent);
        
        //Realiza ajustes de tamaño
        mainContent.setPrefSize(width,height);
        unblurredPane.setPrefSize(width,height);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        //Establece titulo de ventana
        primaryStage.setTitle("Amection");
        
        // ***** SCENE ***** //
        Scene scene = new Scene(background, width, height);
        scene.setFill(Color.TRANSPARENT);
        
        //Añade escena a la ventana
        primaryStage.setScene(scene);
        
        //Ajusta de ventana
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        
        // ***** ICON ***** //
        //FileInputStream input = new FileInputStream("src/sprites/waifu.png");
        //Image image = new Image(input);
        //ImageView logo = new ImageView(image);
        //mainContent.getChildren().add(logo);
        //logo.setLayoutX(-100); logo.setLayoutY(300);
        
        // ***** HUB ***** //
        hub = new HubPanel();
        hub.initHub();
        hub.insertInto(mainContent, 0, 0);
        hub.setExitButtonPos(width-100, height-125);
        //exit button
        Button exitButton = hub.getExitButton();
        mainContent.getChildren().add(exitButton);
        
        // ***** LOGIN PANEL ***** //
        // Nota: es invocado mediante acción de botón en HUB //
        loginPanel = new LoginPanel();
        loginPanel.initLoginPanel();
        loginPanel.insertInto(mainContent, hub.getHubX() + 90, hub.getHubY() + 210);
        loginPanel.hideLoginPanel(true);
        
        //Añade estilo a la escena
        String css = this.getClass().getResource("../resources/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        
        //Muestra ventana principal
        primaryStage.show();
    }
    
    @Override
    public void stop() throws Exception {
        
    }
    
    
}