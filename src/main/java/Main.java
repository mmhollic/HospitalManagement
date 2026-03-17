import Systems.EnvironmentalSystem;

import javax.swing.*;

public class Main {
    // Main instantiates the high level classes needed to run the program, and
    // has responsibility for program execution by running a timer loop every 1 sec
    private static final int UPDATE_INTERVAL_MS = 1000;
    private static final int LOG_INTERVAL_SECONDS = 30; // Log every 30 secs
    private static int secondsCounter=0;

    public static void main(String[] args) {

        EnvironmentalSystem system = new EnvironmentalSystem(); // External library supplied
        EnvironmentalSystemManager manager = new EnvironmentalSystemManager(system);

        Timer timer = new Timer(UPDATE_INTERVAL_MS, e -> {
            manager.update();   // Updates the system sensors and actions every second
            secondsCounter++;
            // Logs to the console very 30 seconds
            if (secondsCounter >= LOG_INTERVAL_SECONDS) {
                manager.printLogToConsole();
                secondsCounter = 0;
            }
        });

        timer.start();
    }
}