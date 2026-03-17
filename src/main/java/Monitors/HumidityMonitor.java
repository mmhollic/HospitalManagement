package Monitors;

import Systems.EnvironmentalSystem;

// Inheritance - HumidityMonitor 'is a' Monitor which 'is a' JPanel
// This class knows how to read the humidity sensors, and how to apply any humidity controls accordingly
// It encapsulates that functionality and abstracts - hides - those specifics from any other class
public class HumidityMonitor extends Monitor {

    private static final int HUMIDITY_DIFFERENCE_THRESHOLD = 10;

    public HumidityMonitor(EnvironmentalSystem envSys) {
        super(envSys);
    }

    // Overrides the abstract method in the superclass - Specific controls for humidity
    @Override
    protected void applyControls() {
        int inside = envSys.getHumidityFeed().getInsideHumidity();
        int outside = envSys.getHumidityFeed().getOutsideHumidity();

        boolean dehumidifierOn = inside >= (outside - HUMIDITY_DIFFERENCE_THRESHOLD);
        envSys.turnDehumidifierOn(dehumidifierOn);
    }

    // Overrides the abstract method in the superclass - Specific display formatting for humidity
    @Override
    protected void updateView() {
        int inside = envSys.getHumidityFeed().getInsideHumidity();
        int outside = envSys.getHumidityFeed().getOutsideHumidity();
        int difference = outside - inside;
        boolean dehumidifierOn = envSys.isDehumidifierOn();

        boolean warning = inside >= (outside - HUMIDITY_DIFFERENCE_THRESHOLD);
        setWarningState(warning);

        setPanelText(
                "Inside humidity is " + inside + "%.\n" +
                        "Outside humidity is " + outside + "%.\n" +
                        "Difference is " + difference + "%.\n" +
                        "Dehumidifier is " + (dehumidifierOn ? "On" : "Off")
        );
    }
}