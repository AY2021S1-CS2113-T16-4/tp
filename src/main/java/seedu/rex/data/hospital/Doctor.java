package seedu.rex.data.hospital;

/**
 * Doctor class.
 */
public class Doctor {
    private String name;

    public Doctor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }
}
