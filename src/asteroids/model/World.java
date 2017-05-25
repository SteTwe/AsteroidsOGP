package asteroids.model;
import asteroids.part2.CollisionListener;
//import be.kuleuven.cs.som.annotate.*;


import java.util.HashSet;
import java.util.Set;


/**
* A class representing an Asteroid World involving the dimensions, witdh and height.
 *
 * @author Stef & Joachim
 * @version 1.0
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

    /**
     * Variable holding the condition of the world.
     */
    private boolean isTerminated = false;

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
        if (entity instanceof Bullet){
            Bullet bullet = (Bullet) entity;
            if (!isValidEntity(bullet)) throw new IllegalArgumentException("Entity is not valid.");

            double[] position = {bullet.getPositionX(), bullet.getPositionY()};
            double radius = bullet.getRadius();
            double distance = position[0]-radius;
            distance = Math.min(distance, this.width - position[0]-radius);
            distance = Math.min(distance, position[1]-radius);
            distance = Math.min(distance, this.height-position[1]-radius);
            if(distance < 0) bullet.terminate();
            else{
                this.entitySet.add(bullet);
                bullet.setWorld(this);
            }

        }
        else {
            if (!isValidEntity(entity)) throw new IllegalArgumentException("Entity is not valid.");
            else {
                this.entitySet.add(entity);
                entity.setWorld(this);

            }
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
     * Return true if the entity is out of bounds.
     *
     * @param entity The given entity to evaluate.
     * @see implementation
     */
    public boolean entityOutOfBounds(Entity entity){
        if ((entity.getPositionX() + entity.getRadius() > getWidth()) || (entity.getPositionX() - entity.getRadius() < 0)) return true;
        if ((entity.getPositionY() + entity.getRadius() > getHeight()) || (entity.getPositionY() - entity.getRadius() < 0)) return true;
        return false;
    }

    /**
     * Return the set of Entities of this world.
     *
     * @return Return the set of Entities of this world.
     */
    public Set<Entity> getEntitySet() {
        return new HashSet<Entity>(entitySet);
    }

    /**
     * Terminate the world by removing all of its entities.
     * @post All entities are removed from this world.
     * @post isTerminated is true.
     */
    public void terminateWorld(){
        for (Entity entity : getEntitySet()){
            this.removeEntity(entity);
        }
        isTerminated = true;
    }

    /**
     * Return the status of this world.
     *
     * @return Returns true if the world is terminated.
     *         | result == isTerminated
     */
    public boolean isTerminated() {
        return isTerminated;
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

    /**
     * Return true if the entity is valid for this world.
     *
     * @param entity The given entity to evaluate.
     * @see implementation
     */
    public boolean isValidEntity(Entity entity){
        if (entity == null) return false;
        if (entity instanceof Bullet && ((Bullet) entity).getShip() != null) return false;
        if ((entity.getWorld() != null ) && (entity.getWorld() != this)) return false;
        if (entity.isTerminated() || this.isTerminated()) return false;
        if(entityOutOfBounds(entity)) return false;
        for (Entity other : getEntitySet()){
            if (entity.overlap(other)){
                return false;
            }
        }
        return true;
    }


    /******************
     * COLLISION RELATED
     **************/

    /**
     * Method to evolve the world during a given duration.
     */
    public void evolve(double duration, CollisionListener collisionListener) throws IllegalArgumentException{
        if ((duration < 0) || (Double.isNaN(duration)))
            throw new IllegalArgumentException("Duration is not valid.");
        //Predict next collision
        double timeNextCollision = getTimeNextCollision();
        double[] collisionPosition = getNextCollisionPosition();
        Entity[] collidingEntities = getNextCollidingEntities();


        while (timeNextCollision <= duration){
            for (Entity entity : getEntitySet()){
            //Useless cuz programs not on point
            /*  if (entity instanceof Ship){
                ((Ship) entity).executeProgram(duration);
            }*/
                entity.move(timeNextCollision);
            }
            if (collidingEntities[1] == null) {
                if (collisionListener != null) collisionListener.boundaryCollision(collidingEntities[0], collisionPosition[0], collisionPosition[1]);
                collidingEntities[0].collideWithBoundary();

            }
            else {
                if (collisionListener != null) collisionListener.objectCollision(collidingEntities[0], collidingEntities[1],collisionPosition[0], collisionPosition[1]);
                collidingEntities[0].collide(collidingEntities[1]);
            }

            duration = duration - timeNextCollision;
            timeNextCollision = getTimeNextCollision();
            collisionPosition = getNextCollisionPosition();
            collidingEntities = getNextCollidingEntities();
            if (timeNextCollision ==0) break;
        }
        for (Entity entity: getEntitySet()){
            //Useless cuz programs not on point
            /*  if (entity instanceof Ship){
                ((Ship) entity).executeProgram(duration);
            }*/
            entity.move(duration);
        }

    }

    /**
     * Return the time until the next collision occurs in this world, if 2 entities overlap, return 0.
     * If no collisions in return Double.POSITIVE_INFINITY.
     * @see implementation
     *
     */
    public double getTimeNextCollision(){
        double time = Double.POSITIVE_INFINITY;
        for (Entity entity1 : getEntitySet()){
            for (Entity entity2 : getEntitySet()){
                //If timeToCollisionWithBoundary is smaller than current time, time = timeToCollisionWithBoundary
                time = Math.min(time, entity1.getTimeToCollisionWithBoundary());
                if (entity1 != entity2){
                    // If 2 entities overlap return 0
                    if (entity1.overlap(entity2)) return 0;
                    double newTime = entity1.getTimeCollisionWithEntity(entity2);
                    //If timeToCollisionWithEntity is smaller than current time, time = timeToCollisionWithEntity
                    time = Math.min(time, newTime);
                }
            }
        }
        return time;
    }

    /**
     * Method that returns the position of the next collision.
     * @return The position of the next collision.
     *          | if there is no collision,  return Double.POSITIVE_INFINITY.
     *          | if (collidingEntities == null) return Double.POSITIVE_INFINITY
     *          | if there is a collision with a boundary, return the collision position
     *          | see implementation
     *          | if two entities collide, return the collision position.
     *          | see implementation
     */
    public double[] getNextCollisionPosition(){
        Entity[] collidingEntities = getNextCollidingEntities();
        if (collidingEntities[0] == null && collidingEntities[1] == null) return new double[]{Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY};
        if (collidingEntities[1] == null) return collidingEntities[0].getCollisionPositionWithBoundary();
        else return collidingEntities[0].getPositionCollisionWithEntity(collidingEntities[1]);
    }

    /**
     * Method that returns the entities involved in the next collision.
     *
     * @see implementation
     */
    public Entity[] getNextCollidingEntities(){
        Entity[] collidingEntities = new Entity[]{null,null};
        double timeNextCollision = Double.POSITIVE_INFINITY;
        //Loop over entities and compare time with timeNextCollision
        for (Entity entity1 : getEntitySet()){
            for (Entity entity2 : getEntitySet()){
                // if time is smaller than timeNextCollision --> colliding entities are these + set new time
                if(entity1.getTimeCollisionWithEntity(entity2) < timeNextCollision){
                    timeNextCollision = entity1.getTimeCollisionWithEntity(entity2);
                    collidingEntities = new Entity[]{entity1, entity2};
                }
            }
            // if time is smaller than timeNextCollision --> colliding entity is this + set new time
            if (entity1.getTimeToCollisionWithBoundary() < timeNextCollision){
                timeNextCollision = entity1.getTimeToCollisionWithBoundary();
                collidingEntities = new Entity[]{entity1, null};
            }
        }
        return collidingEntities;
    }
}
