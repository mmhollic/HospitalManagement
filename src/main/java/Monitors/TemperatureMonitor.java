package Monitors;

import Systems.EnvironmentalSystem;

// Inheritance - TemperatureMonitor 'is a' Monitor which 'is a' JPanel
// This class knows how to read the temperature sensors, and how to apply any heater controls accordingly
// It encapsulates that functionality and abstracts - hides - those specifics from any other class
public class TemperatureMonitor extends Monitor {

    private static final float TOLERANCE = 0.5f;

    private final int id;           // The id for the ward - 0,1,2
    private final String location;  // A text description of which ward this object is
    private final float targetTemp;
    private final float minTemp;
    private final float maxTemp;

    public TemperatureMonitor(int id, float targetTemp, String location, EnvironmentalSystem envSys) {
        super(envSys);
        this.id = id;
        this.location = location;
        this.targetTemp = targetTemp;
        this.minTemp = targetTemp - TOLERANCE;
        this.maxTemp = targetTemp + TOLERANCE;
    }

    // Overrides the abstract method in the superclass - Specific controls for heating
    @Override
    protected void applyControls() {
        float temp = envSys.getTempFeed().getTemperature(id);

        if (temp < minTemp) {
            envSys.turnHeatingOn(id, true);
        } else if (temp > maxTemp) {
            envSys.turnHeatingOn(id, false);
        }
    }

    // Overrides the abstract method in the superclass - Specific display formatting for temperature
    @Override
    protected void updateView() {
        float temp = envSys.getTempFeed().getTemperature(id);
        boolean heatingOn = envSys.isHeatingOn(id);

        boolean warning = temp < minTemp || temp > maxTemp;
        setWarningState(warning);

        setPanelText(
                String.format(
                        "Temp in %s is%n%.1f degrees.%nTarget temp is %.1f%nHeating is %s",
                        location,
                        temp,
                        targetTemp,
                        heatingOn ? "On" : "Off"
                )
        );
    }
}