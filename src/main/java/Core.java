import gui.InterfaceControlView;

import javax.swing.*;

public class Core {

    public static void main(String[] args) {

        JFrame frame = new JFrame("InterfaceControlView");
        frame.setTitle("AHK Gen tool");
        frame.setContentPane(new InterfaceControlView().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
