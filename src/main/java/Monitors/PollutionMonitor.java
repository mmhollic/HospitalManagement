package Monitors;

import Systems.EnvironmentalSystem;
// Inheritance - PollutionMonitor 'is a' Monitor which 'is a' JPanel
// This class knows how to read the pollution sensors, and how to apply any air purifier controls accordingly
// It encapsulates that functionality and abstracts - hides - those specifics from any other class
public class PollutionMonitor extends Monitor {

    private final float maxPollution;

    public PollutionMonitor(float maxPollution, EnvironmentalSystem envSys) {
        super(envSys);
        this.maxPollution = maxPollution;
    }

    // Overrides the abstract method in the superclass - Specific controls for the air purifier
    @Override
    protected void applyControls() {
        float pollution = envSys.getPollutionFeed().getPollution();
        envSys.turnAirPurifierOn(pollution >= maxPollution);
    }

    // Overrides the abstract method in the superclass - Specific display formatting for pollution
    @Override
    protected void updateView() {
        float pollution = envSys.getPollutionFeed().getPollution();
        boolean purifierOn = envSys.isAirPurifierOn();

        setWarningState(pollution >= maxPollution);

        setPanelText(
                String.format(
                        "Pollution is %.1f percent.%nAir purifier is %s",
                        pollution,
                        purifierOn ? "On" : "Off"
                )
        );
    }
}