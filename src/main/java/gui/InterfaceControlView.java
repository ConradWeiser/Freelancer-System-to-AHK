package gui;

import systemstudio.SystemFile;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class InterfaceControlView extends JFrame{
    public JPanel mainPanel;
    private JLabel fileLoadedLabel;
    private JButton loadIniButton;
    private JTextField iniPrefixTextField;
    private JButton saveMacroButton;
    private JLabel valuesLoadedDisplayLabel;

    private boolean hasIniFileLoaded = false;

    private SystemFile systemFile;


    public InterfaceControlView() {

        this.initForm();
        this.attachListeners();

    }


    private void attachListeners() {

        //Add the listener which deals with keys being typed in the box
        iniPrefixTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

                //Only do actions if hasIniFileLoaded is set to true
                if(hasIniFileLoaded) {

                    //If there is an INI file loaded, set the available objects to be generated with the current prefix
                    int availableObjects =  systemFile.getWorkableObjectAmount(iniPrefixTextField.getText());

                    valuesLoadedDisplayLabel.setText(String.valueOf(availableObjects) + " objects with given prefix");
                }

            }
        });

        //Add the listener which runs on INI LOAD button press
        loadIniButton.addActionListener(e -> {

            if (e.getSource() == loadIniButton) {

                JFileChooser fc = new JFileChooser();

                //Only allow INI files to be chosen
                FileNameExtensionFilter filter = new FileNameExtensionFilter(".ini files", "ini");
                fc.setFileFilter(filter);

                int resultVal = fc.showOpenDialog(mainPanel);

                if(resultVal == JFileChooser.APPROVE_OPTION) {
                    try {

                        File file = fc.getSelectedFile();

                        //Verify it can be opened.
                        FileReader reader = new FileReader(file);
                        reader.close();

                        fileLoadedLabel.setForeground(new Color(0, 153, 51));
                        fileLoadedLabel.setText(".ini file loaded!");

                        systemFile = new SystemFile(file);
                        hasIniFileLoaded = true;

                    } catch(IOException ex) {

                        fileLoadedLabel.setForeground(Color.RED);
                        fileLoadedLabel.setText("Error loading .ini file");
                        hasIniFileLoaded = false;
                    }
                }


            }

        });

    }

    private void initForm() {

        this.fileLoadedLabel.setText("No .ini file loaded..");

    }
}
