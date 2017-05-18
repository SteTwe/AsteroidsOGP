package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

import java.awt.*;
import java.util.DoubleSummaryStatistics;

/**
 * A class representing an Asteroid entity involving a position, a velocity, and a radius.
 * Created by joachim on 09/04/2017.
 * @author joachim
 */
public abstract class Entity {

    /**
     * Initialize a new entity with x-position, y-position, velocity in x-direction, velocity in y-direction, radius.
     *
     * @param positionX The x-coordinate of this new entity.
     * @param positionY The y-coordinate of this new entity.
     * @param velocityX The velocity in the x-direction of this new entity.
     * @param velocityY The velocity in the y-direction of this new entity.
     * @param radius    The radius of this new entity.
     */
    public Entity(double positionX, double positionY, double velocityX, double velocityY, double radius) throws IllegalArgumentException {
        this.setPositionX(positionX);
        this.setPositionY(positionY);
        this.setVelocityX(velocityX);
        this.setVelocityY(velocityY);
        if (this.isValidRadius(radius)) {
            this.setRadius(radius);
        }
        else throw new IllegalArgumentException("Invalid radius");
    }

    /******************
     * VARIABLES ENTITY
     **************/
    public double radius;
    public double positionX;
    public double positionY;
    public double velocityX;
    public double velocityY;
    private World world;


    /******************
     * POSITION RELATED
     **************/
    /**
     * Returning the x-coordinate of this entity's position.
     * @return  Returns the x-coordinate of this entity.
     *          | result == this.positionX
     */
    @Basic
    public double getPositionX(){
        return this.positionX;
    }

    /**
     * Returning the y-coordinate of this entity's position
     * @return  Returns the y-coordinate of this entity.
     *          | result == this.positionY
     */
    @Basic
    public double getPositionY(){
        return this.positionY;
    }

    /**
     * Return true if the x-coordinate is valid for this entity.
     *
     * @param positionX The given position for this ship.
     * @return true if the position is a number
     *          | result == !Double.isNaN(positionX)
     */
    private boolean isValidPositionX(double positionX){
        return (!Double.isNaN(positionX));
    }

    /**
     * Set the x-coordinate of this entity to the given x-coordiante
     *
     * @param positionX The given x-position of this entity.
     * @pre The given positionX should be a valid positionX for this entity.
     *      | isValidPositionX(positionX)
     * @post The x-coordinate of this entity is equal to the given positionX.
     *      | new.getPositionX() == positionX;
     */
    public void setPositionX(double positionX){
        if (isValidPositionX(positionX))
            this.positionX = positionX;
    }

    /**
     * Return true if the y-coordinate is valid for this entity.
     *
     * @param positionY The given position for this entity.
     * @return true if the position is a number
     *          | result == !Double.isNaN(positionY)
     */
    private boolean isValidPositionY(double positionY){
        return (!Double.isNaN(positionY));
    }

    /**
     * Set the y-coordinate of this entity to the given y-coordiante
     *
     * @param positionY The given y-position of this entity.
     * @pre The given positionY should be a valid positionY for this entity.
     *      | isValidPositionY(positionY)
     * @post The y-coordinate of this entity is equal to the given positionY.
     *      | new.getPositionY() == positionY;
     */
    public void setPositionY(double positionY){
        if (isValidPositionY(positionY))
            this.positionY = positionY;
    }



    /******************
     * VELOCITY RELATED
     **************/
    /**
     * Returning this entity's velocity in the x-direction.
     * @return Returns the velocity of this entity in the x-direction.
     *          | result == this.velocityX
     */
    @Basic
    public double getVelocityX(){
        return this.velocityX;
    }

    /**
     * Returning this entity's velocity in the y-direction.
     * @return Returns the velocity of this entity in the y-direction.
     *          | result == this.velocityY
     */
    @Basic
    public double getVelocityY(){
        return this.velocityY;
    }

    /**
     * Set the velocity in the x-direction of this entity with the given velocityX.
     *
     * @param velocityX The given velocityX for this entity.
     * @pre The given velocityX should be a valid velocityX for this entity.
     *      | isValidVelocityX(velocityX)
     * @post The velocityX of this entity is equal to the given velocityX.
     *      | new.getVelocityX() == velocityX.
     */
    public void setVelocityX(double velocityX){
        if (isValidVelocityX(velocityX))
            this.velocityX = velocityX;
    }

    /**
     * Set the velocity in the y-direction of this entity with the given velocityY.
     *
     * @param velocityY The given velocityY for this entity.
     * @pre The given velocityY should be a valid velocityY for this entity.
     *      | isValidVelocityY(velocityY)
     * @post The velocityY of this entity is equal to the given velocityY.
     *      | new.getVelocity() == velocityY.
     */
    public void setVelocityY(double velocityY){
        if (isValidVelocityY(velocityY))
            this.velocityY = velocityY;
    }

    /**
     * Return true if the velocityX is valid for this entity.
     * @param velocityX The given velocityX for this entity.
     * @return true if the velocityX is a number
     *          | result == !Double.isNaN(velocityX)
     */
    private boolean isValidVelocityX(double velocityX){
        return(!Double.isNaN(velocityX));
    }

    /**
     * Return true if the velocityY is valid for this entity.
     * @param velocityY The given velocityY for this entity.
     * @return true if the velocityY is a number
     *          | result == !Double.isNaN(velocityY)
     */
    private boolean isValidVelocityY(double velocityY){
        return(!Double.isNaN(velocityY));
    }

    /**
     * Method computing the total velocity of this entity following the given formula.
     *
     * @param velocityX The entity's velocity in the x-direction.
     * @param velocityY The entity's velocity in the y-direction.
     * @post Gives the total velocity for this entity.
     * | return (Math.sqrt(Math.pow(velocityX, 2) + Math.pow(velocityY, 2)))
     */
    public double computeVelocity(double velocityX, double velocityY) {
        return (Math.sqrt(Math.pow(velocityX, 2) + Math.pow(velocityY, 2)));
    }

    public double getMaxVelocity(){
         return SPEED_OF_LIGHT;
    }

    private static double SPEED_OF_LIGHT = 300000;

    /******************
     * RADIUS RELATED
     **************/
    /**
     * Return the radius of this entity.
     *
     * @return Return the radius for this entity.
     * | result == radius
     */
    @Basic
    @Raw
    public double getRadius(){
        return this.radius;
    }

    /**
     * Constant holding the minimum radius of a ship.
     */
    public double minRadiusShip = 10;

    /**
     * Constant holding the minimum radius of a bullet.
     */
    public double minRadiusBullet = 1;

    public double minRadiusMinorPlanet = 5;

    /**
     * Return if the given radius is a valid radius for this entity.
     *
     * @param radius The given radius.
     * @return True if the given radius is larger than the minimum radius and must be a number.
     *          | return ((radius > minRadius) && (Double.isNaN(radius)))
     * @pre The radius should be larger than the minimum radius
     */
    public boolean isValidRadius(double radius){
        if (this instanceof Bullet) {
            return ((radius > minRadiusBullet) && (!Double.isNaN(radius)));
        }
        else if (this instanceof Ship){
            return ((radius > minRadiusShip) && (!Double.isNaN(radius)));
        }

        else if (this instanceof MinorPlanet){
            return ((radius > minRadiusMinorPlanet) && (!Double.isNaN(radius)));
        }
        else {
            return false;
        }
    }

    /**
     *
     * @param radius
     */
    public void setRadius(double radius){
        if (isValidRadius(radius)) {
            this.radius = radius;
        }
        else {
            if (this instanceof Bullet) this.radius = minRadiusBullet;
            if (this instanceof Ship) this.radius = minRadiusShip;
            if (this instanceof MinorPlanet) this.radius = minRadiusMinorPlanet;
        }
    }


    /******************
     * MOVEMENT RELATED
     **************/
    /**
     * Move the entity for a certain amount of time (duration).
     *
     * @param duration  The amount of time to move.
     * @throws IllegalArgumentException The duration is les than zero.
     * @post The new position of this entity
     */
    public void move(double duration){
        if (!isValidDuration(duration))
            throw new IllegalArgumentException("duration not valid");
        setPositionX(getPositionX() + duration * getVelocityX());
        setPositionY(getPositionY() + duration * getVelocityY());
    }

    private boolean isValidDuration(double duration) {
        return (duration >= 0);
    }

    /******************
     * MASS RELATED
     **************/
    private double densityPlanetoids = 0.917 * Math.pow(10, 12);
    private double densityAsteroids = 2.65 * Math.pow(10, 12);
    private double densityBullet = 7.8 * Math.pow(10, 12);
    private double densityShip = 1.42 * Math.pow(10, 12);

    public double calcMass(){
        if (this instanceof Planetoid){
            double massPlanetoid = ((4/3) * Math.PI * Math.pow(this.getRadius(), 3) * densityPlanetoids);
            return massPlanetoid;
        }
        if (this instanceof Asteroid){
            double massAsteroid = ((4/3) * Math.PI * Math.pow(this.getRadius(), 3) * densityAsteroids);
            return massAsteroid;
        }
        if (this instanceof Bullet){
            double massBullet = ((4/3) * Math.PI * Math.pow(this.getRadius(), 3) * densityBullet);
            return massBullet;
        }
        if (this instanceof Ship){
            double massShip = ((4/3) * Math.PI * Math.pow(this.getRadius(), 3) * densityShip);
            return massShip;
        }
        return 0;
    }

    public double getBulletMass(){
        return this.calcMass();
    }

    public double getAsteroidMass(){
        return this.calcMass();
    }

    public double getPlanetoidMass(){
        return this.calcMass();
    }

    public double getShipMass(){
        Ship ship = (Ship) this;
        double totalmass = this.calcMass();
        for (Bullet bullet : ship.getBullets()){
            totalmass += bullet.getBulletMass();
        }
        return totalmass;
    }

    /******************
     * WORLD RELATED
     **************/
    public World getWorld(){
        return this.world;
    }

    /**
     * Remove the world this entity is part of.
     * world is equal to null
     */
    public void removeWorld(){
        this.world = null;
    }

    /**
     * Terminate this entity.
     */
    public void terminate(){
        status = true;
    }

    /**
     * Return true if this entity's status (if it's already terminated or not)
     * @return
     */
    public boolean isTerminated(){
        return status;
    }

    /**
     * Boolean holding the standard value of this entity's status (not terminated => false)
     */
    private boolean status = false;

    /******************
     * COLLISION RELATED
     **************/

    //TODO
    /**
     * Method returning the time until THE FIRST collision that happens between this entity and a boundary.
     * @return
     * shall return Double.POSITIVE_INFINITY if the entity and boundary never collide. => default is Double.POSITIVE_INFINITY
     */
    public double getTimeToCollisionWithBoundary(){
        double[] velocity = {getVelocityX(), getVelocityY()};
        double[] position = {getPositionX(), getPositionY()};
        double[] worldSize = {getWorld().getWidth(), getWorld().getHeight()};
        double time = Double.POSITIVE_INFINITY;
        //in direction X:
        //double x = (afstand tussen rand radius en  horizontale boundary) / Xsnelheid
        double timeInDirectionX = (worldSize[0] - position[0] - getRadius()) / velocity[0];
        //in direction Y:
        //double y = (afstand tussen rand radius en verticale boundary) / Ysnelheid
        double timeInDirectionY = (worldSize[1] - position[1] - getRadius()) / velocity[1];

        // choose smallest of the two
        return time;
    }

    public double[] getMovementPrediction(double t){
        double[] velocity = {getVelocityX(), getVelocityY()};
        double[] position = {getPositionX(), getPositionY()};
        double[] newPosition = {position[0] + velocity[0] * t, position[1] + velocity[1] * t};
        return newPosition;
    }

    //TODO
    /**
     * Method returning a pair of coordinates that represent the location of collision between an entity and a boundary.
     * @return
     */
    public double[] getCollisionPositionWithBoundary() {
        double[] collisionPosition = this.getMovementPrediction(getTimeToCollisionWithBoundary());
        return collisionPosition;
    }

    //TODO
    /**
     * Method executing the change in velocity in order to resolve a collision with a boundary.
     *
     */
    public void collideWithBoundary(){
        //if boundary is horizontal => negate y-velocity
        //if boundary is vertical => negate x-velocity

        // if ((getCollisionPositionWithBoundary(positionX) == 0) || (getCollisionPositionWithBoundary(positionX) == getWorld().getHeight())
        // negate y-velocity
        if (getCollisionPositionWithBoundary()[0] == 0 || getCollisionPositionWithBoundary()[0] == getWorld().getHeight())
            this.velocityY = - velocityY;
        // if ((getCollisionPositionWithBoundary(positionY) == 0) || (getCollisionPositionWithBoundary(positionY) == getWorld().getWidth())
        // negate x-velocity
        if (getCollisionPositionWithBoundary()[1] == 0 || getCollisionPositionWithBoundary()[1] == getWorld().getWidth())
            this.velocityX = - velocityX;

    }

    //TODO
    public double getTimeCollisionWithEntity(Entity entity){
        if (entity == null)
            throw new IllegalArgumentException("ship2 does not exist");
        if (this.overlap(entity))
            throw new IllegalArgumentException("the ships overlap");
        double[] deltaR = {entity.getPositionX() - this.getPositionX(), entity.getPositionY() - this.getPositionY()};
        double[] deltaV = {entity.getVelocityX() - this.getVelocityX(), entity.getVelocityY() - this.getVelocityY()};

        //difference in x-coordinate
        double diffPosX = (entity.getPositionX() - this.getPositionX());

        //difference in y-coordinate
        double diffPosY = (entity.getPositionY() - this.getPositionY());

        //total position difference
        double[] differencePosition = new double[]{diffPosX, diffPosY};

        //difference in velocity in the x-direction
        double diffVelX = (entity.getVelocityX() - this.getVelocityX());

        //difference in velocity in the y-direction
        double diffVelY = (entity.getVelocityY() - this.getVelocityY());

        //total velocity difference
        double[] differenceVelocity = new double[]{diffVelX, diffVelY};

        //position difference multiplication
        double diffPosMult = (Math.pow(differencePosition[0], 2) + (Math.pow(differencePosition[1], 2)));

        //velocity difference multiplication
        double diffVelMult = (Math.pow(differenceVelocity[0], 2) + (Math.pow(differenceVelocity[1], 2)));

        //velocity position multiplication
        double diffVelPosMult = ((differenceVelocity[0] * differencePosition[0]) + differenceVelocity[1] * differencePosition[1]);

        //sigma as defined by the assignment (just the sum of the radii of the ships involved)
        double sigma = (this.getRadius() + entity.getRadius());
        //d as defined by the assignment
        double d = (Math.pow((diffVelPosMult), 2)) - (diffVelMult) * (diffPosMult - Math.pow(sigma, 2));

        double time = -((diffVelPosMult + Math.sqrt(d)) / diffVelMult);

        //given by the assignment
        if (diffVelPosMult >= 0)
            return Double.POSITIVE_INFINITY;
        else if (d < 0)
            return Double.POSITIVE_INFINITY;
        else
            return time;
    }

    /**
     * Check if two entities overlap.
     *
     * @param       entity2
     *              | Second entity
     * @return      | True if the entities overlap
     */
    public boolean overlap(Entity entity2) {
        if (this == entity2) {
            return true;
        } else {
            double distance = getDistanceBetween(entity2);
            if (this.radius > distance) {
                return true;
            } else if (entity2.radius > distance) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Compute the distance between two entities. If the two compared entities are the same, distance is 0.
     * @param        entity2
     *              | Second entity
     * @return The distance between this entity and the given other entity.
     *              | return (Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2)));
     */
    public double getDistanceBetween(Entity entity2) {

    }

    /**
     * Compute the distance between the centers of two entities.
     * @param entity2
     * @return
     */
    public double getDistanceBetweenCenter(Entity entity2){

    }

    public double[] getPositionCollisionEntity(Entity ship2){
        double time = this.getTimeCollisionWithEntity(ship2);
        if (time == Double.POSITIVE_INFINITY) {
            return null;
        } else {
            double collisionPositionX = getTimeCollisionWithEntity(ship2) * velocityX;
            double collisionPositionY = getTimeCollisionWithEntity(ship2) * velocityY;
            return new double[]{collisionPositionX, collisionPositionY};
        }
    }

    /**
     * method is implemented in Ship and Bullet
     */
    public abstract void collide(Entity entity);

}

