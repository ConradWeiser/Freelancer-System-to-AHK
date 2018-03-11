package autohotkey;


import systemstudio.SystemFile;
import systemstudio.elements.FreelancerObjectElement;

import java.io.*;
import java.util.List;

public class MacroGenerator {

    File createMacroSaveLocation;
    SystemFile systemFile;

    public MacroGenerator(File createMacroSaveLocation, SystemFile systemFile) {

        this.createMacroSaveLocation = createMacroSaveLocation;
        this.systemFile = systemFile;
    }

    public void createHotkeyScripts(String prefix) throws IOException {

        systemFile.retainObjectsUsingNicknamePrefix(prefix);
        List<FreelancerObjectElement> availableElements = systemFile.getSystemObjects();

        //Generate the contents for both deletion, and creation
        StringBuilder createBuilder = new StringBuilder();
        StringBuilder deleteBuilder = new StringBuilder();

        //Append the header for the create macro (CTRL+SHIFT+V)
        createBuilder.append("^+v::\n");
        createBuilder.append("SEND, {ENTER}\n");

        //Append the header for the delete macro (CTRL+SHIFT+Z)
        deleteBuilder.append("^+z::\n");
        deleteBuilder.append("SEND, {ENTER}\n");

        for(FreelancerObjectElement element : availableElements) {

            createBuilder.append("SEND, .custombasecreate ");
            createBuilder.append(element.getArchtype() + " ");

            //If there is no loadout, give it the null loadout
            if(element.getLoadout() == null) {

                createBuilder.append("null_loadout ");
            }

            else {

                createBuilder.append(element.getLoadout() + " ");
            }



            createBuilder.append(element.getType() + " ");

            //Position
            float[] position = element.getPos();
            createBuilder.append(position[0] + " ").append(position[1] + " ").append(position[2] + " ");

            //If the rotation doesn't exist, merely give it 0, 0, 0
            if(element.getRot() == null) {
                createBuilder.append("0 0 0 ");
            }

            else {

                //Rotation
                float[] rotation = element.getRot();
                createBuilder.append(rotation[0] + " ").append(rotation[1] + " ").append(rotation[2] + " ");

            }

            //If there is no faction specified, give it freelancer reputation
            if(element.getAffiliation() == null) {

                createBuilder.append("fc_neutral ");
            }

            else {

                createBuilder.append(element.getAffiliation() + " ");

            }

            createBuilder.append(element.getName()).append("\n");
            createBuilder.append("SEND, {ENTER}\n");

            //Prepare add the entry for the delete macro
            deleteBuilder.append("SEND, .basedestroy " + element.getName() + "\n");
            deleteBuilder.append("SEND, {ENTER}\n");
        }

        //Add whitespace at the bottom of each to make it look nicer
        createBuilder.append("\n\n");
        deleteBuilder.append("\n\n");

        //Write to the files here
        PrintWriter createMacroOut = new PrintWriter(new FileWriter(createMacroSaveLocation));
        createMacroOut.print(createBuilder.toString());

        createMacroOut.print(deleteBuilder.toString());

        createMacroOut.close();


    }

}
