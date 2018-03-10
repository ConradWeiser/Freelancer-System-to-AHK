package systemstudio;

import org.ini4j.Profile;
import org.ini4j.Wini;
import systemstudio.elements.FreelancerObjectElement;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;


public class SystemFile {

    List<FreelancerObjectElement> systemObjects;

    public SystemFile(File iniFile) throws IOException {

        this.systemObjects = new Vector<>();

        Wini ini = new Wini();
        ini.getConfig().setMultiSection(true);
        ini.getConfig().setMultiOption(true);
        ini.load(iniFile);

        List<Profile.Section> sections = ini.getAll("Object");

        //For each of the objects, create a FreelancerObjectElement
        for(Profile.Section section : sections) {

            //We need only certain bits of information. Pull only what is needed
            FreelancerObjectElement element = new FreelancerObjectElement();

            element.setName(section.get("nickname"));
            element.setLoadout(section.get("loadout"));
            element.setAffiliation(section.get("reputation"));
            element.setArchtype(section.get("archetype"));

            //Get the position
            String[] positions = section.get("pos").replaceAll(" ","").split(",");
            float[] positionNumeric = new float[positions.length];
            for(int i = 0; i < positions.length; i++) {
                positionNumeric[i] = Float.valueOf(positions[i]);
            }

            float[] rotateNumeric = null;
            //Get the rotation. This may not exist.
            if(section.containsKey("rotate")) {
                String[] rotations = section.get("rotate").replaceAll(" ", "").split(",");
                rotateNumeric = new float[rotations.length];
                for (int i = 0; i < rotations.length; i++) {
                    rotateNumeric[i] = Float.valueOf(rotations[i]);
                }
            }

            element.setPos(positionNumeric);
            element.setRot(rotateNumeric);

            //TODO: Support other kinds of types
            element.setType("Solar");

            //Add the element to the list
            systemObjects.add(element);
        }

    }

    public void retainObjectsUsingNicknamePrefix(String prefix) {

        prefix = prefix.toLowerCase();

        Iterator<FreelancerObjectElement> iter = systemObjects.iterator();

        while(iter.hasNext()) {
            FreelancerObjectElement element = iter.next();

            if(!element.getName().toLowerCase().startsWith(prefix)) {
                iter.remove();
            }

        }
    }

    /**
     * Method which returns the number of elements which has a prefix matching the function input
     * @param prefix the prefix which should be checked against
     * @return the number of objects
     */
    public int getWorkableObjectAmount(String prefix) {

        int number = 0;

        for(FreelancerObjectElement element : systemObjects) {

            if(element.getName().startsWith(prefix))
                number++;
        }

        return number;

    }

    public int getWorkableObjectAmount() {
        return systemObjects.size();
    }


}
