package systemstudio.elements;

public class FreelancerObjectElement {

    private String archtype;
    private String loadout;
    private String type;
    private float[] pos;
    private float[] rot;
    private String affiliation;
    private String name;

    public String getArchtype() {
        return archtype;
    }

    public void setArchtype(String archtype) {
        this.archtype = archtype;
    }

    public String getLoadout() {
        return loadout;
    }

    public void setLoadout(String loadout) {
        this.loadout = loadout;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float[] getPos() {
        return pos;
    }

    public void setPos(float[] pos) {
        this.pos = pos;
    }

    public float[] getRot() {
        return rot;
    }

    public void setRot(float[] rot) {
        this.rot = rot;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
