package Displays;

import Systems.EnvironmentalSystem;

public class PollutionDisplay extends Display {

    float setMaxPollution;
    public PollutionDisplay(float setPollution, EnvironmentalSystem envSys) {
        super(envSys);
        this.setMaxPollution = setPollution;
    }

    @Override
    void setControls() {
        float pollution=envSys.getPollutionFeed().getPollution();
        if (pollution< setMaxPollution) envSys.turnAirPurifierOn(false);
        else envSys.turnAirPurifierOn(true);
    }

    @Override
    void refreshPanel() {
        float pollution=envSys.getPollutionFeed().getPollution();
        boolean purifierOn=envSys.isAirPurifierOn();
        String setPollTxt=String.format("%.1f", pollution);
        setPanelText("Pollution is "+setPollTxt+" percent.\nAir Purifier is "+(purifierOn?"On":"Off"));

        if (pollution< setMaxPollution) setBackgroundWhite();
        else setBackgroundRed();
    }

}