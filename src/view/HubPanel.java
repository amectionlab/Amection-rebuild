package view;

import controller.Program;
import controller.utilities.FXUtil;
import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class HubPanel {
    
    //Contenedor: se asigna por referencia
    AnchorPane container;
    
    AnchorPane hub;
    Button loginButton;
    Button administratorButton ;
    Button dermatologistButton;
    Button configurationButton;
    Button exitButton;
    boolean isExtended;
    
    public HubPanel() {
        
        hub = new AnchorPane();
        hub.setId("hub");
        
        loginButton = new Button("Sign in");
        loginButton.setId("loginButton");

        administratorButton = new Button();
        administratorButton.setId("administratorButton");
        
        dermatologistButton = new Button();
        dermatologistButton.setId("dermatologistButton");
        
        configurationButton = new Button();
        configurationButton.setId("configurationButton");
        
        exitButton = new Button();
        exitButton.setId("exitButton");
    }
    
    public void initHub() {
        
        //Inserta botones en hbox
        hub.getChildren().add(0, configurationButton);
        hub.getChildren().add(1, administratorButton);
        hub.getChildren().add(2, loginButton);
        
        loginButton.setLayoutX(100);
        loginButton.setLayoutY(100);
        
        administratorButton.setVisible(false);
        administratorButton.setDisable(true);
        administratorButton.setLayoutX(95);
        administratorButton.setLayoutY(100);
        
        configurationButton.setVisible(false);
        configurationButton.setDisable(true);
        configurationButton.setLayoutX(225);
        configurationButton.setLayoutY(120);
        
        /* *** Controlador botón de inicio de sesión *** */
        loginButton.setOnAction(event -> {
            
            //Realiza transición de botón de acuerdo a estado de sesión.
            if (!this.getIsExtended()) {
                
                //Animación
                hubExtendAction();
                
                //Accion principal: spawnea panel inicio de sesión
            }
            else {
                
                //Animación
                hubshrinkAction();
                
                //Accion principal: cierra panel inicio de sesión
            }
        });
        
        //Boton salir
        exitButton.setOnAction(event -> {
            
            //Cierra hub en caso de estar abierto
            if (this.getIsExtended() ) {
                this.hubshrinkAction();
            }
            
            //Efecto blur
            GaussianBlur gaussianBlur = new GaussianBlur();       
            gaussianBlur.setRadius(10.5);
            
            //Añade efecto
            Program.mainContent.setEffect(gaussianBlur);
            
            //Cuadro de salida customizado
            ExitAlert exitAlert = new ExitAlert();
            exitAlert.insertInto(Program.unblurredPane, Program.width/2-200, Program.height/2-100);
            
            //Configura panel superior de efectos
            Program.background.getChildren().add(1, Program.unblurredPane);
            Program.unblurredPane.setStyle("-fx-background-color: #000b09; -fx-opacity: 0.8");
            
            //Platform.exit(); 
        });
    }
    
    // Animación de hub - extender
    public void hubExtendAction() {
        
        //Marca hub como extendido
        this.setIsExtended(true);
        
        //Boton deshabilitado hasta que termine la transición
        loginButton.setDisable(true);
        
        // Configuración transición botón inicio de sesión
        ScaleTransition loginButtonTransition = new ScaleTransition(Duration.millis(125), loginButton);
        loginButtonTransition.setByX(0.2);
        loginButtonTransition.setByY(0.2);
        loginButtonTransition.setCycleCount(1);
        
        // EVENTO: Aumenta tamaño botón y desencadena siguiente evento.
        loginButtonTransition.setOnFinished(event1 -> {
            
            //Invoca panel inicio de sesión
            Program.loginPanel.hideLoginPanel(false);
            
            //Cambia color de botón login
            loginButton.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(2,58,48,0.5), 10, 0.6, 3, 3);");
            
            //Invoca botón de panel administrador
            administratorButton.setVisible(true);
            
            // Trayecto botón panel administrador
            Line administratorLine = FXUtil.spawnLine(administratorButton.getLayoutX() - 50, 59, administratorButton.getLayoutX() + 80, 59);
            PathTransition administratorButtonTransition = FXUtil.spawnTransition(administratorButton, administratorLine, 0.15, 0);
            
            //Evento desencadenado: desliza botón de configuración
            administratorButtonTransition.setOnFinished(event2 -> {
                
                //Invoca botón de configuración
                configurationButton.setVisible(true);
                
                // Trayecto botón de configuración
                Line configurationLine = FXUtil.spawnLine(administratorButton.getLayoutX() - 50, 39, administratorButton.getLayoutX() + 55, 39);
                PathTransition configurationButtonTransition = FXUtil.spawnTransition(configurationButton, configurationLine, 0.15, 0);
                
                //Ejecuta transición
                configurationButtonTransition.play();
                
                // Habilita botón de inicio de sesión
                loginButton.setDisable(false);
            });
            
            //Ejecuta transición
            administratorButtonTransition.play();
        });
        
        //Ejecuta transición de botón inicio de sesión
        loginButtonTransition.play();
    }
    
    // Animación de hub - contraer
    private void hubshrinkAction() {
        
        //Marca hub como extendido
        this.setIsExtended(false);
        
        //Desaparece panel inicio de sesión
        Program.loginPanel.hideLoginPanel(true);
        
        //Boton deshabilitado hasta que termine la transición
        loginButton.setDisable(true);
        
        // Trayecto botón de configuración (Retroceso)
        Line configurationLine = FXUtil.spawnLine(configurationButton.getTranslateX() + 40, 39, configurationButton.getTranslateX() - 60, 39);
        PathTransition configurationButtonTransition = FXUtil.spawnTransition(configurationButton, configurationLine, 0.15, 0);
        
        // EVENTO: Lo vuelve a su posición inicial, invisible y desencadena evento.
        configurationButtonTransition.setOnFinished(event1 -> {
            
            //Vuelve invisible al botón
            configurationButton.setVisible(false);
            
            // Trayecto botón panel administrador
            Line administratorLine = FXUtil.spawnLine(administratorButton.getLayoutX() + 80, 59, administratorButton.getLayoutX() - 50, 59);
            PathTransition administratorButtonTransition = FXUtil.spawnTransition(administratorButton, administratorLine, 0.15, 0);
            
            administratorButtonTransition.setOnFinished(event2 -> {
                
                //Vuelve invisible al botón
                administratorButton.setVisible(false);
                
                // Configuración transición botón inicio de sesión
                ScaleTransition loginButtonTransition = new ScaleTransition(Duration.millis(125), loginButton);
                loginButtonTransition.setByX(-0.2);
                loginButtonTransition.setByY(-0.2);
                loginButtonTransition.setCycleCount(1);
                loginButtonTransition.setAutoReverse(false);
                
                // Ejecuta transición de botón inicio de sesión
                loginButtonTransition.play();
                
                // Habilita botón de inicio de sesión
                loginButton.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.5), 10, 0.4, 3, 3);");
                loginButton.setDisable(false);
                
        
            });
            
            //Ejecuta transición de botón panel administrador
            administratorButtonTransition.play();
            
        });
        
        //Ejecuta transición de botón inicio de sesión
        configurationButtonTransition.play();
         
    }
    
    // Inserta el hub en el panel ingresado como parametro en coord x, y.
    public void insertInto(AnchorPane anchorPane, double x, double y) {
        
        container = anchorPane;
        anchorPane.getChildren().add(hub);
        
        hub.setLayoutX(x);
        hub.setLayoutY(y);
    }
    
    public boolean getIsExtended() {
        return this.isExtended;
    }
    
    public void setIsExtended(boolean state) {
        this.isExtended = state;
    }

    public AnchorPane getHub() {
        return hub;
    }

    public void setHub(AnchorPane hub) {
        this.hub = hub;
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(Button loginButton) {
        this.loginButton = loginButton;
    }

    public Button getAdministratorButton() {
        return administratorButton;
    }

    public void setAdministratorButton(Button administratorButton) {
        this.administratorButton = administratorButton;
    }

    public Button getDermatologistButton() {
        return dermatologistButton;
    }

    public void setDermatologistButton(Button dermatologistButton) {
        this.dermatologistButton = dermatologistButton;
    }

    public Button getConfigurationButton() {
        return configurationButton;
    }

    public void setConfigurationButton(Button configurationButton) {
        this.configurationButton = configurationButton;
    }

    public Button getExitButton() {
        return exitButton;
    }

    public void setExitButton(Button exitButton) {
        this.exitButton = exitButton;
    }
    
    public void setExitButtonPos(double x, double y) {
        exitButton.setLayoutX(x);
        exitButton.setLayoutY(y);
    }
    
    public double getHubX() {
        return hub.getLayoutX();
    }
    
    public double getHubY() {
        return hub.getLayoutY();
    }

}