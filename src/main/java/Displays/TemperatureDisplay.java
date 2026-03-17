package Displays;

import Systems.EnvironmentalSystem;

public class TemperatureDisplay extends Display {
    int id;
    String location;
    float minTemp, maxTemp,setTemp;
    public TemperatureDisplay(int id,float setTemp,String location, EnvironmentalSystem envSys) {
        super(envSys);
        this.id = id;
        this.location=location;
        this.setTemp=setTemp;
        minTemp=setTemp-0.5f;
        maxTemp=setTemp+0.5f;
    }

    @Override
    void setControls() {
        float temp=envSys.getTempFeed().getTemperature(id);
        if (temp<minTemp) envSys.turnHeatingOn(id,true);
        else if (temp>maxTemp) envSys.turnHeatingOn(id,false);
    }

    @Override
    void refreshPanel() {
        boolean heatOn=envSys.isHeatingOn(id);
        float temp=envSys.getTempFeed().getTemperature(id);
        String tmp=String.format("%.1f", temp);
        String setTempTxt=String.format("%.1f", setTemp);

        setPanelText("Temp in "+location+" is \n"+tmp+" degrees.\nTarget temp is "+setTempTxt+"\nHeating is "+(heatOn?"On":"Off"));
        if ((temp<minTemp)||(temp>maxTemp)) setBackgroundRed();
        else setBackgroundWhite();
    }
}
