import Systems.EnvironmentalSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static int secondsCounter=0;

    public static void main(String[] args) {
        EnvironmentalSystem sys = new EnvironmentalSystem();
        EnvironmentalSystemManager manager = new EnvironmentalSystemManager(sys);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.update();
                secondsCounter++;
                if (secondsCounter == 30) {
                    manager.printLogToConsole();
                    secondsCounter=0;
                }
            }
        });
        timer.start();
    }
}
