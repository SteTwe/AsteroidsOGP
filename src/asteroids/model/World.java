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
    private Set<Entity> entitySet = new HashSet<Entity>();

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
     * Add the given entity to the world.
     *
     * @param entity The entity to add.
     * @throws IllegalArgumentException The entity is already in the world.
     * @post The given entity is in the world.
     *       | entitySet.contains(entity)
     */
    public void addEntity(Entity entity) throws IllegalArgumentException {
        if (!isValidEntity(entity)) throw new IllegalArgumentException("Entity is not valid.");
        else {
            this.entitySet.add(entity);
            entity.setWorld(this);
        }
    }

    /**
     * Remove the given entity from the world.
     *
     * @param entity Entity to remove.
     * @post The given entity is not in the world.
     *       | !entitySet.contains(entity)
     */
    public void removeEntity(Entity entity) throws IllegalArgumentException{
        if ((entity == null) || entity.getWorld() != this) throw new IllegalArgumentException("Entity is not valid.");
            this.entitySet.remove(entity);
            entity.removeWorld();
    }

    /**
     * Return the set of Entities of this world.
     *
     * @return Return the set of Entities of this world.
     */
    public Set<Entity> getEntitySet() {
        return this.entitySet;
    }

    /**
     * Terminate the world by removing all of its entities.
     */
    public void terminateWorld(){
        System.out.println(getEntitySet());
        for (Entity entity : getEntitySet()){
            entity.removeWorld();
        }
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


    public boolean isValidEntity(Entity entity){
        if (getEntitySet().contains(entity)) return false;
        if (entity == null) return false;
        if (entity.getWorld() != null) return false;
        if (entity.isTerminated()) return false;
        for (Entity other : getEntitySet()){
            if (entity.overlap(other)){
                return false;
            }
        }
        return true;
    }

    //TODO
    public void evolve(double duration){
        //Predict next collision
        double timeNextCollision =0; //TODO Time next collision

        // If tC > duration: advance entities duration sec
        if (timeNextCollision > duration){
            for (Entity entity: getEntitySet()){
                entity.move(duration);
            }
        }
        // Else advance entities tC sec, resolve collision, substract tC from duration, start again.
        else {
            for (Entity entity : getEntitySet()){
                entity.move(duration);
            }
            // TODO Resolve collsion

            //Substract and start over
            double newDuration = duration - timeNextCollision;
            evolve(newDuration);
        }

    }
}
