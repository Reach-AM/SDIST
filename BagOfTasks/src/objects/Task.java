package objects;

import java.io.Serializable;

public class Task implements Serializable {
    private final String taskID;
    private final String requirementID;
    private final int length;

    public Task(String taskID, String requirementID, int length) {
        this.taskID = taskID;
        this.requirementID = requirementID;
        this.length = length;
    }

    public String getRequirementID() { return requirementID; }
    public int getLength() { return length; }

    @Override
    public String toString() {
        return "[" + taskID + "," + requirementID + "," + length + "]";
    }
}
