package autohotkey;


import systemstudio.elements.FreelancerObjectElement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class MacroGenerator {

    File createMacroSaveLocation;

    public MacroGenerator(File createMacroSaveLocation, File deleteMacroSaveLocation) {

        this.createMacroSaveLocation = createMacroSaveLocation;
    }

    public void createHotkeyScripts(List<FreelancerObjectElement> availableElements) throws FileNotFoundException {

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
            createBuilder.append(element.getLoadout() + " ");
            createBuilder.append(element.getType() + " ");

            //Position
            float[] position = element.getPos();
            createBuilder.append(position[0] + " ").append(position[1] + " ").append(position[2] + " ");

            //Rotation
            float[] rotation = element.getRot();
            createBuilder.append(rotation[0] + " ").append(rotation[1] + " ").append(rotation[2] + " ");

            createBuilder.append(element.getAffiliation() + " ");
            createBuilder.append(element.getName()).append("\n");
            createBuilder.append("SEND, {ENTER}");
            createBuilder.append("SEND, {ENTER}");

            //Prepare add the entry for the delete macro
            deleteBuilder.append("SEND, .basedestroy " + element.getName() + "\n");
            deleteBuilder.append("SEND, {ENTER}");
            deleteBuilder.append("SEND, {ENTER}");
        }

        //Add whitespace at the bottom of each to make it look nicer
        createBuilder.append("\n\n");
        deleteBuilder.append("\n\n");

        //Write to the files here
        PrintWriter createMacroOut = new PrintWriter(createMacroSaveLocation);
        createMacroOut.println(createBuilder.toString());

        createMacroOut.println(deleteBuilder.toString());

        createMacroOut.close();


    }

}
