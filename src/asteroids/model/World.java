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


    /******************
     * VARIABLES WORLD
     **************/
    /**
     * Set containing all the entities present in the world.
     */
    private Set<Entity> entitySet = new HashSet<>();

    /**
     * Variable holding the width of the world.
     */
    private double width;

    /**
     * Variable holding the height of the world.
     */
    private double height;


    /******************
     * DIMENSION RELATED
     **************/
    /**
     * Set the width of this world with the given width.
     *
     * @param width The given width for this world.
     * @pre The given width should be a valid width for this world.
     *      | isValidWidth(width)
     * @post The width of this world is equal to the given width.
     *      | new.getWidth() == width
     */
    public void setWidth(double width){
        if (isValidWidth(width))
            this.width = width;
    }

    /**
     * Set the height of this world with the given height.
     *
     * @param height The given height for this world.
     * @pre The given height should be a valid height for this world.
     *      | isValidHeight(height)
     * @post The height of this world is equal to the given height.
     *      | new.getHeight() == height
     */
    public void setHeight(double height){
        if (isValidHeight(height))
            this.height = height;
    }

    /**
     * Return true if the width is valid for this world.
     *
     * @param width The given width for this world.
     * @return True if the width is a number and lies in the range 0 to Double.MAX_VALUE.
     *          | result == ((!Double.isNaN(width)) && width >= 0 && width <= Double.MAX_VALUE)
     */
    private boolean isValidWidth(double width){
        return ((!Double.isNaN(width)) && width >= 0 && width <= Double.MAX_VALUE);
    }

    /**
     * Return true if the height is valid for this world.
     *
     * @param height The given height for this world.
     * @return True if the height is a number and lies in the range 0 to Double.MAX_VALUE.
     *          | result == ((!Double.isNaN(height)) && height >= 0 && height <= Double.MAX_VALUE)
     */
    private boolean isValidHeight(double height){
        return ((!Double.isNaN(height)) && height >= 0 && height <= Double.MAX_VALUE);
    }

    /**
     * Returning this world's width.
     *
     * @return Returns the width of this world.
     *          | result == this.width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returning this world's height.
     *
     * @return Returns the height of this world.
     *          | result == this.height
     */
    public double getHeight() {
        return this.height;
    }


    /******************
     * ENTITY RELATED
     **************/
    /**
     * Add the given ship to the world.
     *
     * @param ship The ship to add.
     * @throws IllegalArgumentException The ship is already in the world.
     * @post The given ship is in the world.
     *       | entitySet.contains(ship)
     */
    public void addShip(Ship ship) throws IllegalArgumentException{
        if (getShipSet().contains(ship)) throw new IllegalArgumentException("Ship is already in the world.");
        else {
            this.entitySet.add(ship);
            ship.setWorld(this);
        }
    }

    /**
     * Remove the given ship from the world.
     *
     * @param ship The ship to remove.
     * @post The given ship is not in the world.
     *       | !entitySet.contains(ship)
     */
    public void removeShip(Ship ship) throws IllegalArgumentException{
        try{
            this.entitySet.remove(ship);
        }
        catch (Exception e){
        }
    }

    /**
     * Add the given bullet to the world.
     *
     * @param bullet The bullet to add.
     * @throws IllegalArgumentException The bullet is already in the world
     * @post The given bullet is in the world.
     *       | entitySet.contains(bullet)
     */
    public void addBullet(Bullet bullet) throws IllegalArgumentException{
        if (getBulletSet().contains(bullet)) throw new IllegalArgumentException("Bullet is already in world.");
        else {
            this.entitySet.add(bullet);
            bullet.setWorld(this);
        }
    }

    /**
     * Remove the given bullet from the world.
     *
     * @param bullet Bullet to remove.
     * @post The given is not in the world.
     *       | !entitySet.contains(bullet)
     */
    public void removeBulletWorld(Bullet bullet){
        try {
            this.entitySet.remove(bullet);
        }
        catch (Exception e){
        }
    }

    public void addMinorPlanet(MinorPlanet planet) throws IllegalArgumentException{
        if (getMinorPlanetSet().contains(planet)) throw new IllegalArgumentException("Planet is already in the world.");
        else {
            this.entitySet.add(planet);
            planet.setWorld(this);
        }
    }

    public void removeMinorPlanet(MinorPlanet planet) throws IllegalArgumentException{
        try{
            this.entitySet.remove(planet);
        }
        catch (Exception e){
        }
    }

    /**
     * Return the set of Bullets of this world.
     *
     * @return Return the set of Bullets of this world.
     */
    public Set<? extends Bullet> getBulletSet(){
        Set<Bullet> bulletSet = new HashSet<>();
        for (Entity entity : getEntitySet()){
            if (entity instanceof Bullet){
                bulletSet.add((Bullet) entity);
            }
        }
        return bulletSet;
    }

    /**
     * Return the set of Ships of this world.
     *
     * @return Return the set of Ships of this world.
     */
    public Set<? extends Ship> getShipSet() {
        Set<Ship> shipSet = new HashSet<>();
        for (Entity entity : getEntitySet()){
            if (entity instanceof Ship){
                shipSet.add((Ship) entity);
            }
        }
        return shipSet;
    }

    public Set<? extends MinorPlanet> getMinorPlanetSet(){
        Set<MinorPlanet> planetSet = new HashSet<>();
        for (Entity entity : getEntitySet()){
            if (entity instanceof MinorPlanet)
                planetSet.add((MinorPlanet) entity);
        }
        return planetSet;
    }

    /**
     * Return the set of Entities of this world.
     *
     * @return Return the set of Entities of this world.
     */
    public Set<? extends Entity> getEntitySet() {
        return this.entitySet;
    }

    /**
     * Terminate the world by removing all of its entities.
     */
    public void terminateWorld(){
        this.getEntitySet().clear();
    }

    /**
     * Return the entity, if any, at the given x- and y-coordinate.
     *
     * @param x Given x-coordinate.
     * @param y Given y-coordinate.
     * @return Returns the entity positioned at the given coordinates.
     *          | givenPosition == entity.position
     */
    public Entity getEntityAtPosition(double x, double y){
        for (Entity entity : this.getEntitySet()){
            if (entity.getPositionX() == x && entity.getPositionY() == y) {
                return entity;
            }
        }
        return null;
    }
}
