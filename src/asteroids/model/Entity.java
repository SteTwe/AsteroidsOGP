package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

import java.util.DoubleSummaryStatistics;


/**
 * A class representing an Asteroid entity involving a position, a velocity, and a radius.
 * Created by joachim on 09/04/2017.
 * @author joachim
 */
public abstract class Entity implements Collideable{

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
        setPosition(positionX, positionY);
        setVelocity(velocityX, velocityY);
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
    private boolean isValidPosition(double positionX){
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
        if (isValidPosition(positionX))
            this.positionX = positionX;
    }

    public void setPosition(double positionX, double positionY) throws IllegalArgumentException{
        if (!isValidPosition(positionX)) throw new IllegalArgumentException("The given position is not valid.");
        if (!isValidPosition(positionY)) throw new IllegalArgumentException("The given position is not valid.");
        this.positionX = positionX;
        this.positionY = positionY;
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
        if (isValidPosition(positionY))
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

    public void setVelocity(double velocityX, double velocityY){
        if (Double.isNaN(velocityX) || Double.isNaN(velocityY)) return;
        double velocity = (Math.sqrt(Math.pow(velocityX, 2) + Math.pow(velocityY, 2)));
        if (isValidVelocity(velocity)){
            this.velocityX = velocityX;
            this.velocityY =velocityY;
        }
        else {
            this.velocityX = velocityX * SPEED_OF_LIGHT / velocity;
            this.velocityY = velocityY * SPEED_OF_LIGHT / velocity;
        }
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

    private boolean isValidVelocity(double velocity){
        if (velocity > SPEED_OF_LIGHT) return false;
        return true;
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
     * @post Gives the total velocity for this entity.
     *          | totalVelocity = (Math.sqrt(Math.pow(velocityX, 2) + Math.pow(velocityY, 2)))
     *          | if (isValidVelocity(totalVelocity)
     *          | return totalVelocity
     *          | else
     *          | return SPEED_OF_LIGHT
     */
    public double getTotalVelocity() {
        double totalVelocity = (Math.sqrt(Math.pow(getVelocityX(), 2) + Math.pow(getVelocityY(), 2)));
        if (isValidVelocity(totalVelocity))
            return totalVelocity;
        else
            return SPEED_OF_LIGHT;
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
            return planetoid.radius - planetoid.getTotalTraveledDistance() * 0.000001;
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
            return ((radius >= minRadiusBullet) && (!Double.isNaN(radius)));
        }
        else if (this instanceof Ship){
            return ((radius >= minRadiusShip) && (!Double.isNaN(radius)));
        }

        else if (this instanceof MinorPlanet){
            return ((radius >= minRadiusMinorPlanet) && (!Double.isNaN(radius)));
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
    private double minDensityShip = 1.42 * Math.pow(10, 12);

    /**
     * Calculate and return the mass of bullets, planetoids and asteroids.
     *
     * @return  Returns the calculated mass of the entity.
     *          | result == (4/3) * Math.PI * Math.pow(radius,3) * massDensityEntity
     */
    public double calcMass(){
        if (this instanceof Planetoid){
            double massPlanetoid = 4/3. * Math.PI * Math.pow(this.getRadius(), 3) * densityPlanetoids;
            return massPlanetoid;
        }
        if (this instanceof Asteroid){
            double massAsteroid = 4/3. * Math.PI * Math.pow(this.getRadius(), 3) * densityAsteroids;
            return massAsteroid;
        }
        if (this instanceof Bullet){
            double massBullet = 4/3. * Math.PI * Math.pow(this.getRadius(), 3) * densityBullet;
            return massBullet;
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
        return this.calcMass();
    }

    public double getMinShipMass(){
        return 4/3. * Math.PI * Math.pow(this.getRadius(), 3) * minDensityShip;
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
        if (this != null) {
            status = true;
            if (getWorld() != null) {
                getWorld().removeEntity(this);
            }
            if (this instanceof Bullet) {
                Bullet bullet = (Bullet) this;
                if (bullet.getShip() != null) {
                    bullet.getShip().removeBulletShip(bullet);
                    bullet.setShip(null);
                }
            }
            if (this instanceof Planetoid){
                Planetoid planetoid = (Planetoid) this;
                double radius = planetoid.getRadius();
                if (radius >= 30){
                    double newRadius = radius/2;
                    double velocity = getTotalVelocity();
                    double orientation = 2 * Math.PI * Math.random();
                    Asteroid asteroid1 = new Asteroid(getPositionX() + radius * Math.sin(orientation),getPositionY() - radius * Math.cos(orientation), velocity * Math.sin(orientation), velocity* Math.cos(orientation), newRadius);
                    Asteroid asteroid2 = new Asteroid(getPositionX() - radius * Math.sin(orientation),getPositionY() - radius * Math.cos(orientation), -velocity * Math.sin(orientation), -velocity * Math.cos(orientation), newRadius);
                    if (getWorld() != null){
                        getWorld().addEntity(asteroid1);
                        getWorld().addEntity(asteroid2);
                    }
                }
            }
        }

    }

    /**
     * Return true if this entity's status (if it's already terminated or not)
     * @return
     */
    public boolean isTerminated(){
        return status;
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
        if (getWorld() == null) return Double.POSITIVE_INFINITY;
        double[] velocity = {getVelocityX(), getVelocityY()};
        double[] position = {getPositionX(), getPositionY()};
        double[] worldSize = {getWorld().getWidth(), getWorld().getHeight()};
        double timeX = Double.POSITIVE_INFINITY;
        double timeY = Double.POSITIVE_INFINITY;

        //Xdirection towards max
        if (velocity[0] > 0) timeX = (worldSize[0] - position[0] - getRadius()) / velocity[0];
        //Xdirection towards 0
        if (velocity[0] < 0) timeX = -(position[0] - getRadius()) / velocity[0];
        //Ydirection towards max
        if (velocity[1] > 0) timeY = (worldSize[1] - position[1] - getRadius()) / velocity[1];
        //Ydirection towards 0
        if (velocity[1] < 0) timeY = -(position[1] - getRadius()) / velocity[1];

        //time cannot be negative
        if (timeX < 0) timeX = Double.POSITIVE_INFINITY;
        if (timeY < 0) timeY = Double.POSITIVE_INFINITY;

        double time = Math.min(timeX, timeY);
        return time;
    }

    public double[] getMovementPrediction(double t){
        if (!isValidDuration(t)) throw new IllegalArgumentException("Duration is not valid.");
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
        if (collisionPosition == null || !Double.isFinite(collisionPosition[0]) || !Double.isFinite(collisionPosition[1])) return null;
        double velocity[] = {getVelocityX(), getVelocityY()};

        //If Xvelocity positive: moving towards max width, else moving towards 0
        double boundaryX;
        if (velocity[0] > 0) boundaryX = getWorld().getWidth();
        else boundaryX = 0;

        //If Yvelocity positive: moving towards max height, else moving towards 0
        double boundaryY;
        if (velocity[1] >0) boundaryY = getWorld().getHeight();
        else boundaryY = 0;

        //If Xvelocity positive: distance to collision = (max width - current position), else distance = current position
        double distanceX;
        if (velocity[0] > 0) distanceX = boundaryX - collisionPosition[0];
        else distanceX = collisionPosition[0];

        //If Yvelocity positive: distance to collision = (max height - current position), else distance = current position
        double distanceY;
        if (velocity[1] > 0) distanceY = boundaryY - collisionPosition[1];
        else distanceY = collisionPosition[1];

        //If x is shortest distance: collisionPosition = (boundary, collisionPositionY), else collisionPosition = (collisionPositionX, boundary)
        if (distanceX <= distanceY) collisionPosition = new double[]{boundaryX, collisionPosition[1]};
        else collisionPosition = new double[]{collisionPosition[0], boundaryY};

        return collisionPosition;
    }

    /**
     * Method executing the change in velocity in order to resolve a collision with a boundary.
     */
    public void collideWithBoundary(){
        if (getCollisionPositionWithBoundary()[0] == 0 || getCollisionPositionWithBoundary()[0] == getWorld().getHeight())
            setVelocityX(-getVelocityX());
        if (getCollisionPositionWithBoundary()[1] == 0 || getCollisionPositionWithBoundary()[1] == getWorld().getWidth())
            setVelocityY(-getVelocityY());
    }

    //TODO: DOC
    public double getTimeCollisionWithEntity(Entity ship2){
        double time = Double.POSITIVE_INFINITY;
        if (ship2 == null)
            throw new IllegalArgumentException("ship2 does not exist");
        double[] positionDifference = {ship2.getPositionX() - this.getPositionX(), ship2.getPositionY() - this.getPositionY()};
        double[] velocityDifference = {ship2.getVelocityX() - this.getVelocityX(), ship2.getVelocityY() - this.getVelocityY()};

        double productVR = positionDifference[0] * velocityDifference[0] + positionDifference[1] * velocityDifference[1];
        double productVV = Math.pow(velocityDifference[0], 2) + Math.pow(velocityDifference[1], 2);
        double productRR = Math.pow(positionDifference[0], 2) + Math.pow(positionDifference[1], 2);

        double d = Math.pow(productVR,2) - (productVV) * (productRR - Math.pow(this.getRadius() + ship2.getRadius(),2));
        //double d = Math.pow(productVR, 2) - (productVV)*(productRR)-Math.pow(this.getRadius()+ship2.getRadius(), 2);
        if (productVR >= 0)
            return Double.POSITIVE_INFINITY;
        else if (d <= 0)
            time = Double.POSITIVE_INFINITY;
        else
            time = -((productVR) + (Math.sqrt(d))) / (productVV);
        return time;
    }

    //TODO
    public double[] getPositionCollisionWithEntity(Entity other){
        double time = this.getTimeCollisionWithEntity(other);
        //If no collision: return null
        if (time == Double.POSITIVE_INFINITY) return null;

        // Predict positions
        double positionThis[] = this.getMovementPrediction(time);
        double positionOther[] = other.getMovementPrediction(time);
        double deltaR[] = new double[]{positionOther[0] - positionThis[0], positionOther[1] - positionThis[1]};
        double radius = this.getRadius() / (this.getRadius() + other.getRadius());

        double collisionPositionX = positionThis[0] + deltaR[0] * radius;
        double collisionPositionY = positionThis[1] + deltaR[1] * radius;
        return new double[]{collisionPositionX, collisionPositionY};
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
     * @param        other
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
        double mi = this.getMass();
        //mj
        double mj = other.getMass();

        double[] deltaR = {other.getPositionX() - this.getPositionX(), other.getPositionY() - this.getPositionY()};
        double[] deltaV = {other.getVelocityX() - this.getVelocityX(), other.getVelocityY() - this.getVelocityY()};
        double sigma = Math.sqrt((deltaR[0] * deltaR[0]) + (deltaR[1] * deltaR[1]));

        //J = (2 mi mj * (deltav * deltar)/(radius*(mi + mj))
        double j = (2 * mi * mj * (deltaV[0] * deltaR[0] + deltaV[1] * deltaR[1])) / sigma * (mi + mj);

        //jx & jy
        double jx = (j * deltaR[0] / sigma);
        double jy = (j * deltaR[1] / sigma);


        double currentIVelocityX = this.getVelocityX();      //vxi
        double currentIVelocityY = this.getVelocityY();      //vyi
        double currentJVelocityX = other.getVelocityX();  //vxj
        double currentJVelocityY = other.getVelocityY();  //vyj

        double newIVelocityX = currentIVelocityX + jx / mi;   //vxi + Jx/mi
        double newIVelocityY = currentIVelocityY + jy / mi;   //vyi + Jy/mi
        double newJVelocityX = currentJVelocityX - jx / mj; //vxj - Jx/mj
        double newJVelocityY = currentJVelocityY - jy / mj; //vyj - Jy/mj

        this.setVelocity(newIVelocityX, newIVelocityY);
        other.setVelocity(newJVelocityX, newJVelocityY);
    }

}

interface Collideable{
    void collideWith(final Collideable other);
    void collideWith(final Ship ship);
    void collideWith(final Bullet bullet);
    void collideWith(final MinorPlanet minorPlanet);
}


