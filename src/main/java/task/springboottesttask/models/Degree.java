package task.springboottesttask.models;

public enum Degree {
    ASSISTANT("assistant"),
    ASSOCIATE_PROFESSOR("associate professor"),
    PROFESSOR("professor");

    private final String degreeName;

    Degree(String degreeName) {
        this.degreeName = degreeName;
    }

    public String getDegreeName() {
        return this.degreeName;
    }
}
