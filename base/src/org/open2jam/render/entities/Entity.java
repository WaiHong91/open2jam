package org.open2jam.render.entities;

import org.open2jam.util.Copyable;
import org.open2jam.parser.Event;
import org.open2jam.render.Sprite;
import org.open2jam.render.SpriteList;

public class Entity implements Copyable<Entity>
{
    protected SpriteList frames;
    protected Sprite sprite;

    protected Event.Channel channel = Event.Channel.NONE;

    /** The current speed of this entity horizontally (pixels/millisecs) */
    protected double dx;
    /** The current speed of this entity vertically (pixels/millisecs) */
    protected double dy;

    /** this object stores the position(x,y) and dimensions (width,height) */
    protected double x, y, width, height;

    /** when a entity die the render removes it */
    protected boolean alive = true;

    protected int layer = 0;

    /** allows constructor extensions */
    protected Entity() {}

    public Entity(SpriteList s, Event.Channel ch)
    {
            this(s,ch, 0,0);
    }

    public Entity(SpriteList sp, Event.Channel ch, double x, double y)
    {
        this.frames = sp;
        this.sprite = sp.get(0);
        this.channel = ch;
        this.x = x;
        this.y = y;
        width = sprite.getWidth();
        height = sprite.getHeight();
    }

    protected Entity(Entity org) {
        this.alive = org.alive;
        this.channel = org.channel;
        this.sprite = org.sprite;
        this.frames = org.frames;
        this.dx = org.dx;
        this.dy = org.dy;
        this.x = org.x;
        this.y = org.y;
        this.width = org.width;
        this.height = org.height;
        this.layer = org.layer;
    }

    public boolean isAlive() { return alive; }

    public void setAlive(boolean state){
        alive = state;
    }
	
    /**
     * Request that this entity move itself based on a certain ammount
     * of time passing.
     *
     * @param delta The ammount of time that has passed in milliseconds
     */
    public void move(long delta) {
            // update the location of the entity based on move speeds
            x += delta * dx;
            y += delta * dy;
    }

    /**
     * Sets the moving speed of this entity
     */
    public void setXMove(double dx) { this.dx = dx; }
    public void setYMove(double dy) { this.dy = dy; }

    /**
     * Gets the moving speed of this entity
     */
    public double getXMove() { return dx; }
    public double getYMove() { return dy; }

    /**
     * Draw this entity to the graphics context provided
     */
    public void draw() {
            sprite.draw(x,y, sprite.getScaleX(), sprite.getScaleY());
    }

    /**
     * judgment time.
     * this will be called once, when it hits judgment.
     */
    public void judgment() {}


    public double getX(){ return x;}
    public double getY(){ return y;}
    public void setX(double x){ this.x = x;}
    public void setY(double y){ this.y = y;}

    public double getWidth(){ return width;}
    public double getHeight(){ return height;}

    public void setLayer(int layer){ this.layer = layer; }
    public int getLayer(){ return layer; }

    public SpriteList getFrames(){
        return frames;
    }


    public Event.Channel getChannel() {
        return channel;
    }

    public Entity copy() {
        return new Entity(this);
    }


}
