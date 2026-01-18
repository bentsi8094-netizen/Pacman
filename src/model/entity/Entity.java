package model.entity;

import java.awt.Rectangle;

public abstract class Entity {
    public int x, y;
    public int speed;
    public String direction = "right";
    public boolean collisionOn = false;
    
    public Rectangle solidArea = new Rectangle(8, 8, 8, 8); 
    
    public int spriteCounter = 0;
    public int spriteNum = 1;
}