package model;

public interface MoveCommand {
    boolean isUp();
    boolean isDown();
    boolean isLeft();
    boolean isRight();
}