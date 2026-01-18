package model.entity;

public abstract class Entity {
    public int x, y;
    public int speed;
    public String direction = "right";
    public boolean collisionOn = false;
    public java.awt.Rectangle solidArea = new java.awt.Rectangle(6, 6, 8, 8);
    public int spriteCounter = 0;
    public int spriteNum = 1;
}