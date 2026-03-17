import Displays.Display;
import Displays.HumidityDisplay;
import Displays.PollutionDisplay;
import Displays.TemperatureDisplay;
import Systems.EnvironmentalSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class EnvironmentalSystemManager {
    private JFrame frame;
    static GraphicsConfiguration gc;	// Contains this computer’s graphics configuration
    EnvironmentalSystem sys;
    ArrayList<Display> displays;

    public EnvironmentalSystemManager(EnvironmentalSystem sys){
        displays=new ArrayList<>();
        this.sys=sys;

        initDisplays();
        initUI();
    }

    private void initUI(){
        // Set up the UI frame
        frame= new JFrame(gc);	// Create a new JFrame
        frame.setSize(800,400);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(2,2));
        // This next line closes the program when the frame is closed
        frame.addWindowListener(new WindowAdapter() {	// Closes the program if close window clicked
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });
        // Add the displays to the frame (uses polymorphism)
        for(Display display:displays){
            frame.add(display);
        }
        frame.setVisible(true);
    }

    private void initDisplays(){
        final int NORTH=0, CENTRAL=1, SOUTH=2;
        displays.add(new TemperatureDisplay(NORTH,23,"North ward",sys));
        displays.add(new TemperatureDisplay(CENTRAL,22,"Central ward",sys));
        displays.add(new TemperatureDisplay(SOUTH,20,"South ward",sys));
        displays.add(new HumidityDisplay(sys));
        displays.add(new PollutionDisplay(5,sys));
    }

    public void update(){
        // uses polymorphism
        for(Display display:displays){
            display.update();
        }
    }

    public void printLogToConsole(){
        // uses polymorphism
        for(Display display:displays){
            System.out.println(display.getPanelText());
        }
        System.out.println(sys.getCTScannerStatus());
        System.out.println(sys.getUltrasoundScannerStatus());
    }
}
