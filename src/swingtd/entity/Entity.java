package swingtd.entity;

import java.awt.Color;
import java.awt.Graphics;
import swingtd.math.Vector2f;

/**
 *
 * @author peixoto
 */
public abstract class Entity {
    protected Vector2f position;
    protected Color color;
    
    public abstract void update();
    public abstract void render(Graphics graphics);
}
