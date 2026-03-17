package Displays;
import Systems.EnvironmentalSystem;

public class HumidityDisplay extends Display {
    public HumidityDisplay(EnvironmentalSystem envSys) {
        super(envSys);
    }


    @Override
    void setControls(){
        int in=envSys.getHumidityFeed().getInsideHumidity();
        int out=envSys.getHumidityFeed().getOutsideHumidity();
        if (in<(out-10)) envSys.turnDehumudifierOn(false);
        else envSys.turnDehumudifierOn(true);
    }

    @Override
    void refreshPanel(){
        boolean dehumudifierOn=envSys.isDehumudifierOn();
        int in=envSys.getHumidityFeed().getInsideHumidity();
        int out=envSys.getHumidityFeed().getOutsideHumidity();
        if (in<(out-10)) setBackgroundWhite();
        else setBackgroundRed();
        setPanelText("Inside humidity is "+in+"%.\nOutside Humidity is "+(out)+"%\nDifference is "+(out-in)+"%\nDehumidifier is "+(dehumudifierOn?"On":"Off"));
    }

}