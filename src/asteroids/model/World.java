package asteroids.model;

import be.kuleuven.cs.som.annotate.*;


import java.util.HashSet;
import java.util.Set;


/**
* A class representing an Asteroid World involving the dimensions, witdh and height.
 *
 * @author Stef & Joachim
 * @version pre-alpha
 * @invar The width must be valid for any world.
 * | isValidWidth(width)
 * @invar The height must be valid for any world.
 * | isValidHeight(height)
 */
public class World {

    /**
     * Initaliaze a world with width and height.
     *
     * @param width     The width of the world.
     * @param height    The height of the world.
     *
     */
    public World(double width, double height){
        this.setWidth(width);
        this.setHeight(height);

    }

    /**
     * Set containing all the entities present in the world.
     */
    private Set<Entity> entitySet = new HashSet<>();

    /**
     * Set containing all the ships present in the world.
     */
    private Set<Ship> shipSet = new HashSet<>();

    /**
     * Set containing all the bullets present in the world.
     */
    private Set<Bullet> bulletSet = new HashSet<>();

    /**
     * Variable holding the width of the world.
     */
    private double width;

    /**
     * Variable holding the height of the world.
     */
    private double height;

    /**
     * 
     * @param width
     */
    private void setWidth(double width){
        if (isValidWidth(width))
            this.width = width;
    }

    private void setHeight(double height){
        if (isValidHeight(height))
            this.height = height;
    }

    private boolean isValidWidth(double width){
        return ((!Double.isNaN(width)) && width >= 0 && width <= Double.MAX_VALUE);
    }

    private boolean isValidHeight(double height){
        return ((!Double.isNaN(height)) && height >= 0 && height <= Double.MAX_VALUE);
    }

    public double getHeight() {
        return this.height;
    }

    public double getWidth() {
        return this.width;
    }

    public void addShip(Ship ship){
        if (getShipSet().contains(ship));
        else
            this.shipSet.add(ship);
            this.entitySet.add(ship);
    }

    public void removeShip(Ship ship){
        this.shipSet.remove(ship);
    }

    public void addBullet(Bullet bullet){
        if (getBulletSet().contains(bullet));
        else
            this.bulletSet.add(bullet);
            this.entitySet.add(bullet);
    }

    public void removeBulletWorld(Bullet bullet){
        this.bulletSet.remove(bullet);
    }

    public Set<? extends Bullet> getBulletSet(){
        return this.bulletSet;
    }

    public Set<? extends Ship> getShipSet() {
        return this.shipSet;
    }

    public Set<? extends Entity> getEntitySet() {
        return this.entitySet;
    }

    public void terminateWorld(){
        this.getBulletSet().clear();
        this.getShipSet().clear();
    }

    public Entity getEntityAtPosition(double x, double y){
        for (Entity entity : this.getEntitySet()){
            if (entity.getPositionX() == x && entity.getPositionY() == y) {
                return entity;
            }
        }
        return null;
    }
}
