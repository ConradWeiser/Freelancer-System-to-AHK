package gui;

import autohotkey.MacroGenerator;
import systemstudio.SystemFile;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
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
        mainPanel.setPreferredSize(new Dimension(250, 150));

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

        //Add the listener which runs on the MACRO SAVE button press
        saveMacroButton.addActionListener(e -> {

            //Don't run this if there is no system file loaded

            if(hasIniFileLoaded) {

                if (e.getSource() == saveMacroButton) {

                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File("."));
                    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    fileChooser.setAcceptAllFileFilterUsed(false);

                    int resultVal = fileChooser.showSaveDialog(mainPanel);

                    if (resultVal == JFileChooser.APPROVE_OPTION) {

                        File folder = new File(fileChooser.getSelectedFile().toString() + "\\buildMacro.ahk");

                        //Generate, and print the macro to the directory
                        try {

                            MacroGenerator macro = new MacroGenerator(folder, systemFile);
                            macro.createHotkeyScripts(iniPrefixTextField.getText());

                        } catch (FileNotFoundException ex) {

                            ex.printStackTrace();
                        }

                        catch (IOException ex) {

                            ex.printStackTrace();
                        }


                    }
                }
            }

        });

        //Add the listener which runs on INI LOAD button press
        loadIniButton.addActionListener(e -> {

            if (e.getSource() == loadIniButton) {

                JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(new File("."));
                fc.setDialogTitle("Open an ini file..");

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

                        //Set the number of available objets label
                        valuesLoadedDisplayLabel.setText(systemFile.getWorkableObjectAmount() + " objects with given prefix");


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
