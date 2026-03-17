import Monitors.*;
import Monitors.HumidityMonitor;
import Monitors.Monitor;
import Systems.EnvironmentalSystem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EnvironmentalSystemManager {
    // This class is the layer at the top that holds the knowledge about how to set up specific monitors
    // and how to set up the UI. It instantiates, initializes and co-ordinates all monitors
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 400;

    private final JFrame frame;
    private final EnvironmentalSystem envSys;
    private final List<Monitor> monitors;

    public EnvironmentalSystemManager(EnvironmentalSystem envSys) {
        this.envSys = envSys;
        this.monitors = new ArrayList<>();
        this.frame = new JFrame("Environmental System Manager");

        initMonitors();
        initUI();
    }

    private void initUI() {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        frame.setLayout(new GridLayout(2, 3));

        // Polymorphism in action
        // we don't care what the specific objects are when adding them to the frame,
        // only that they are Monitors, which we know inherit from JPanel
        for (Monitor monitor : monitors) {
            frame.add(monitor);
        }

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initMonitors() {
        // This is the only method that knows the specifics of the different monitor objects in the system
        // If we added a new class of monitor, or further monitor objects, this is the only
        // place that we would have to make changes to the code, other than writing the new
        // specific monitor class
        // The library EnvironmentalSystem is only instantiated once in the contructor above, and passed into the monitors
        final int NORTH = 0;
        final int CENTRAL = 1;
        final int SOUTH = 2;
        monitors.add(new TemperatureMonitor(NORTH, 23.0f, "North ward", envSys));
        monitors.add(new TemperatureMonitor(CENTRAL, 22.0f, "Central ward", envSys));
        monitors.add(new TemperatureMonitor(SOUTH, 20.0f, "South ward", envSys));
        monitors.add(new HumidityMonitor(envSys));
        monitors.add(new PollutionMonitor(5.0f, envSys));
    }

    public void update() {
        // Polymorphism in action
        // We don't care what each monitor is because we know from the shared superclass Monitor that they
        // all implement the updateDisplay method
        for (Monitor monitor : monitors) {
            monitor.updateDisplay();
        }
    }

    public void printLogToConsole() {
        // Polymorphism in action. All specific monitors can be treated as their superclass, which implements getPanelText
        for (Monitor monitor : monitors) {
            System.out.println(monitor.getPanelText());
        }
        System.out.println(envSys.getCTScannerStatus());
        System.out.println(envSys.getUltrasoundScannerStatus());
    }
}