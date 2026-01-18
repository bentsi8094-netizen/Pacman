package model;

public class MoveCommand {
    private String direction;

    public MoveCommand(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }
}