package Monitors;

import Systems.EnvironmentalSystem;

import javax.swing.*;
import java.awt.*;

public abstract class Monitor extends JPanel {
    // This abstract monitor base class contains code common to all specific monitors

    // The library EnvironmentalSystem object - instantiated once in main, then passed to all monitors' constructors
    protected final EnvironmentalSystem envSys;

    private final JLabel panelLabel; // A JLabel to go in the JPanel

    // The current text for the JPanel. It is stored as plain text as it is needed as such for the Log
    // but it is converted to HTML before being set into the JLabel to allow for <br> line-breaks
    private String panelText = "Starting up";

    protected Monitor(EnvironmentalSystem envSys) {
        this.envSys = envSys;
        this.panelLabel = new JLabel(formatAsHtml(panelText));

        setOpaque(true);
        setBackground(Color.WHITE);
        add(panelLabel);
    }

    // This is called from the Manager every 1 second.
    // Its responsiblities are to check the current sensor values, apply and controls, and
    // accordingly update the text in the JLabel
    public final void updateDisplay() {
        applyControls(); // Abstract - is overridden in the more specific monitor
        updateView();   // Abstract - is overridden in the more specific monitor
        panelLabel.setText(formatAsHtml(panelText));
        revalidate();
        repaint();
    }

    protected void setPanelText(String text) {
        this.panelText = text;
    }
    public String getPanelText() {
        return panelText;
    }

    // Useful method to set the JPanel red or white
    protected void setWarningState(boolean warning) {
        setBackground(warning ? Color.RED : Color.WHITE);
    }

    private String formatAsHtml(String text) {
        return "<html>" + text.replace("\n", "<br>") + "</html>";
    }

    // Template method pieces implemented by subclasses.
    protected abstract void applyControls();
    protected abstract void updateView();
}