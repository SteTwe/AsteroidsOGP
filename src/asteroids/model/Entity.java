package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;


/**
 * A class representing an Asteroid entity involving a position, a velocity, and a radius.
 * Created by joachim on 09/04/2017.
 * @author joachim
 */
public abstract class Entity implements Collideable {

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

    /**
     * Boolean holding the condition of this entity (not terminated => false).
     */
    private boolean status = false;


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
    public double getTotalVelocity() {
        //TODO Velocity cannot exceed Speed of light!!
        return (Math.sqrt(Math.pow(getVelocityX(), 2) + Math.pow(getVelocityY(), 2)));
    }

    /**
     * Constant holding the speed of light as defined by the assignment.
     */
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
        if (this instanceof Planetoid){
            Planetoid planetoid = (Planetoid) this;
            return planetoid.getRadius() - planetoid.getTotalTraveledDistance() * 0.000001;
        }
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

    /**
     * Constant holding the minimum radius of a MinorPlanet.
     */
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

    public double getMinRadius(){
        if (this instanceof Bullet) return minRadiusBullet;
        if (this instanceof Ship) return minRadiusShip;
        else return minRadiusMinorPlanet;
    }


    /******************
     * MOVEMENT RELATED
     **************/
    /**
     * Move the entity for a certain amount of time (duration).
     * If the entity is a ship and the thruster of that ship is enabled,
     * adjust the velocity of the ship after moving.
     *
     * @param duration  The amount of time to move.
     * @throws IllegalArgumentException The duration is less than zero.
     * @post The new position of this entity
     */
    public void move(double duration){
        if (!isValidDuration(duration))
            throw new IllegalArgumentException("duration not valid");
        setPositionX(getPositionX() + duration * getVelocityX());
        setPositionY(getPositionY() + duration * getVelocityY());
        if (this instanceof Ship){
            Ship ship = (Ship) this;
            if (ship.getActiveThruster()){
            double newVelocityX = getVelocityX() + ship.getAcceleration() * Math.cos(ship.getAngle()) * duration;
            double newVelocityY = getVelocityY() + ship.getAcceleration() * Math.sin(ship.getAngle()) * duration;
            setVelocityX(newVelocityX);
            setVelocityY(newVelocityY);
        }
        }
        if (this instanceof Planetoid){
            Planetoid planetoid = (Planetoid) this;
            planetoid.setTotalTraveledDistance(planetoid.getTotalTraveledDistance() + getTotalVelocity() * duration);
            if (planetoid.getRadius() < planetoid.getMinRadius()) planetoid.terminate();
        }
    }

    /**
     * Return true if the duration is valid.
     *
     * @param duration The given duration.
     * @return  True if duration is higher or equal to 0.
     *          |return (duration >= 0)
     */
    private boolean isValidDuration(double duration) {
        return (duration >= 0);
    }


    /******************
     * MASS RELATED
     **************/
    /**
     * Constant holding the massdensity of Planetoids.
     */
    private double densityPlanetoids = 0.917 * Math.pow(10, 12);

    /**
     * Constant holding the massdensity of Asteroids.
     */
    private double densityAsteroids = 2.65 * Math.pow(10, 12);

    /**
     * Constant holding the massdensity of Bullets.
     */
    private double densityBullet = 7.8 * Math.pow(10, 12);

    /**
     * Constant holding the massdensity of Ships.
     */
    private double densityShip = 1.42 * Math.pow(10, 12);

    /**
     * Calculate and return the mass of the entity.
     *
     * @return  Returns the calculated mass of the entity.
     *          | result == (4/3) * Math.PI * Math.pow(radius,3) * massDensityEntity
     */
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

    /**
     * Return the mass of the entity. If the entity is a ship, return mass + mass of the loaded bullets.
     *
     * @return  Returns the total mass of the entity.
     *          | if (entity instanceof Ship)
     *          |       return (massShip + massBulletsOfShip)
     *          | else
     *          |       return massEntity
     */
    public double getMass(){
        if (this instanceof Ship){
            Ship ship = (Ship) this;
            double totalmass = this.calcMass();
            for (Bullet bullet : ship.getBullets()){
                totalmass += bullet.getMass();
            }
            return totalmass;
        }
        else return this.calcMass();
    }


    /******************
     * WORLD RELATED
     **************/
    /**
     * Set the world of this entity to the given world.
     *
     * @param world The given world.
     * @post The world of this entity equals the given world.
     *       | entity.getWorld == world
     */
    public void setWorld(World world){
        if (getWorld() == null){
            this.world = world;
        }
    }

    /**
     * Return this entities world. Returns null if the entity isn't linked to a world.
     *
     * @return World linked with this entity
     */
    public World getWorld(){
        return this.world;
    }

    /**
     * Remove the world this entity is part of.
     * @post    The world of this entity is null.
     *          | entity.getWorld == null
     */
    public void removeWorld(){
        this.world = null;
    }

    /**
     * Terminate this entity.
     */
    public void terminate(){
        status = true;
        if (getWorld() != null){
            getWorld().removeEntity(this);
        }
    }

    /**
     * Return true if this entity's status (if it's already terminated or not)
     * @return
     */
    public boolean isTerminated(){
        return this.status;
    }


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
        /**
         *needs shortening with function
         */
        if (timeInDirectionX < timeInDirectionY)
                time = timeInDirectionX;
        else
            time = timeInDirectionY;
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
            setVelocityX(-getVelocityX());
        // if ((getCollisionPositionWithBoundary(positionY) == 0) || (getCollisionPositionWithBoundary(positionY) == getWorld().getWidth())
        // negate x-velocity
        if (getCollisionPositionWithBoundary()[1] == 0 || getCollisionPositionWithBoundary()[1] == getWorld().getWidth())
            setVelocityY(-getVelocityY());
    }

    public double getTimeCollisionWithEntity(Entity entity){
        double time = Double.POSITIVE_INFINITY;
        if (entity == null)
            throw new IllegalArgumentException("ship2 does not exist");
        if (this.overlap(entity))
            throw new IllegalArgumentException("the ships overlap");
        double[] positionDifference = {entity.getPositionX() - this.getPositionX(), entity.getPositionY() - this.getPositionY()};
        double[] velocityDifference = {entity.getVelocityX() - this.getVelocityX(), entity.getVelocityY() - this.getVelocityY()};
        double product = positionDifference[0] * velocityDifference[0] + positionDifference[1] * velocityDifference[1];
        if (product >= 0)
            return Double.POSITIVE_INFINITY;
        return time;
    }
    //TODO
    public double[] getPositionCollisionWithEntity(Entity ship2){
        double[] collisionPosition = {0,0};
    
        return collisionPosition;
    }

    /**
     * Check if two entities overlap.
     *
     * @param       other
     *              | Second entity
     * @return      | True if the entities overlap
     */
    public boolean overlap(Entity other) {
        if (this == other) {
            return true;
        } else {
            double distance = this.getDistanceBetween(other);
            if (this.radius > distance) {
                return true;
            } else if (other.getRadius() > distance) {
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
    public double getDistanceBetween(Entity other) {
        if (this == other) return 0;
        double x1 = this.getPositionX();
        double x2 = other.getPositionX();
        double y1 = this.getPositionY();
        double y2 = other.getPositionY();
        return (Math.sqrt(Math.pow((x1 -x2), 2) + Math.pow((y1-y2),2)));
    }

    //TODO doc, implementation?
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
     * Resolve the bouncing of this entity with another given entity.
     *
     * @param other The other entity.
     * //TODO @post?
     */
    public void bounceOffEntity(Entity other){
        //(vxi, vyi) = vxi + Jx/mi, vyi + Jy/mi)
        //(vxj, vyj) = vxj + Jx/mj, vyj + JY/mj)
        //Jx = (J deltax) / sigma
        //Jy = (J deltay) / sigma

        //mi
        double thisMass = this.getMass();
        //mj
        double otherMass = other.getMass();

        double[] deltaR = {other.getPositionX() - this.getPositionX(), other.getPositionY() - this.getPositionY()};
        double[] deltaV = {other.getVelocityX() - this.getVelocityX(), other.getVelocityY() - this.getVelocityY()};
        double sigma = this.getRadius() + other.getRadius();

        //J = (2 mi mj * (deltav * deltar)/(radius*(mi + mj))
        double j = (2 * thisMass * otherMass * (deltaV[0] * deltaR[0] + deltaV[1] * deltaR[1])) / sigma * (thisMass + otherMass);

        //jx & jy
        double jx = (j * deltaR[0] / sigma);
        double jy = (j * deltaR[1] / sigma);


        double currentThisVelocityX = this.getVelocityX();      //vxi
        double currentShipVelocityY = this.getVelocityY();      //vyi
        double currentEntityVelocityX = other.getVelocityX();  //vxj
        double currentEntityVelocityY = other.getVelocityY();  //vyj

        double newThisVelocityX = currentThisVelocityX + jx / thisMass;   //vxi + Jx/mi
        double newThisVelocityY = currentShipVelocityY + jy / thisMass;   //vyi + Jy/mi
        double newEntityVelocityX = currentEntityVelocityX + jx / otherMass; //vxj + Jx/mj
        double newEntityVelocityY = currentEntityVelocityY + jy / otherMass; //vyj + Jy/mj

        this.setVelocityX(newThisVelocityX);
        this.setVelocityY(newThisVelocityY);
        other.setVelocityX(newEntityVelocityX);
        other.setVelocityY(newEntityVelocityY);
    }
}

interface Collideable{
    void collideWith(final Collideable other);
    void collideWith(final Ship ship);
    void collideWith(final Bullet bullet);
}
