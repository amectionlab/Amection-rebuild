package controller.utilities;

import javafx.animation.PathTransition;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public final class FXUtil {
    
    public FXUtil() {}
    
    //Cambia texto de label y lo hace visible
    public static void activateAlert(Label label, String alertText) {
        
        label.setText(alertText);
        
        if (!label.isVisible()) {
            label.setVisible(true);
        }
    }
    
    //Desactiva alerta si es visible
    public static void disableAlert(Label label) {
        if (label.isVisible()) {
            label.setVisible(false);
        }
        
    }
    
    //Designa limite a entradas de texto
    public static void addTextLimiter(final TextField textField, final int maxLength) {
        
        textField.textProperty().addListener((final ObservableValue<? extends String> ov, final String oldValue, final String newValue) -> {
            
            if (textField.getText().length() > maxLength) {
                String s = textField.getText().substring(0, maxLength);
                textField.setText(s);
            }
        });
    }
    
     //Crea linea con coord. de punto inicial y punto de llegada.
    public static Line spawnLine(double startX, double startY, double endX, double endY) {
        
        Line newLine = new Line();
        newLine.setStartX(startX);
        newLine.setStartY(startY);     
        newLine.setEndX(endX);
        newLine.setEndY(endY);
        
        return newLine;
    }
    
    //Crea transición para el botón ingresado como parametro y siguiendo el path, con duración y delay.
    public static PathTransition spawnTransition(Button node, Line path, double duration, double delay) {
        
        PathTransition newTransition = new PathTransition();
        newTransition.setNode(node);
        newTransition.setDelay(Duration.seconds(delay));
        newTransition.setDuration(Duration.seconds(duration));
        newTransition.setPath(path);
        
        return newTransition;
    }
}
