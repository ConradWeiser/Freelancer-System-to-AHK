import gui.InterfaceControlView;

import javax.swing.*;
import java.awt.*;

public class Core {

    public static void main(String[] args) {

        JFrame frame = new JFrame("InterfaceControlView");
        frame.setContentPane(new InterfaceControlView().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
