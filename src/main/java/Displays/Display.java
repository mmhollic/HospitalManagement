package Displays;

import Systems.EnvironmentalSystem;

import javax.swing.*;
import java.awt.*;

public abstract class Display extends JPanel {
    protected EnvironmentalSystem envSys;
    private JLabel panelJLabel;
    private String panelText ="Starting up";
    public Display(EnvironmentalSystem envSys) {
        this.envSys = envSys;
        panelJLabel = new JLabel(panelText);
        add(panelJLabel);
    }
    public void setBackgroundRed(){
        setBackground(Color.RED);
    }
    public void setBackgroundWhite(){
        setBackground(Color.WHITE);
    }

    public void setPanelText(String text){panelText =text;}
    public String getPanelText(){return panelText;}

    // Update a Display
    public void update(){
        setControls();
        refreshPanel();
        // Convert to HTML just for JLabel
        String labelText="<html>"+panelText.replaceAll("\n", "<br>")+"</html>";
        panelJLabel.setText(labelText);
    }

    // abstract to make sure specific classes process the update
    // Separation of responsibilites when updating a Display into controls, and display
    abstract void setControls(); // Set the controls to the correct settings
    abstract void refreshPanel(); // Update the JPanel text
}
