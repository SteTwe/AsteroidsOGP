package asteroids.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;


/**
 * A class representing an Asteroid Ship involving a position, a velocity, an angle and a radius
 *
 * @author Joachim & Stef
 * @version v1.1
 * @invar The x-coordinate must be valid for any ship.
 * | isValidPositionX(positionX)
 * @invar The y-coordinate must be valid for any ship.
 * | isValidPositionY(positionY)
 * @invar The velocity in the x-direction must be valid for any ship.
 * | isValidVelocityX(velocityX)
 * @invar The velocity in the y-direction must be valid for any ship.
 * | isValidVelocityY(velocityY)
 * @invar The angle must be valid for any ship.
 * | isValidAngle(angle)
 * @invar The radius of a ship must be higher than or equal to the minimum radius for all ships.
 * | isValidRadius(getRadius)
 */

public class Ship extends Entity{

    /**
     * Initialize a ship with x-position, y-position, velocity in x-direction, velocity in y-direction, angle, radius.
     *
     * @param positionX The x-coordinate of this new ship.
     * @param positionY The y-coordinate of this new ship.
     * @param velocityX The velocity in the x-direction of this new ship.
     * @param velocityY The velocity in the y-direction of this new ship.
     * @param angle     The angle (orientation) which this new ship is facing in.
     * @param radius    The radius of this new ship.
     * @param mass      The mass of this new ship.
     *
     */
    public Ship(double positionX, double positionY, double velocityX, double velocityY, double radius, double angle, double mass) throws IllegalArgumentException {
        super (positionX, positionY, velocityX, velocityY, radius);
        this.setAngle(angle);
        this.setMass(mass);
    }

    /**********
     * VARIABLES
     **********/
    /**
     * Variable holding the angle of the ship.
     */
    private double angle;

    /**
     * Variable holding the mass of the ship.
     */
    private double mass;


    /**********
     * ANGLE RELATED
     **********/
    /**
     * Return the angle (orientation) of this ship.
     *
     * @return The angle of this ship.
     * | return this.angle
     */
    public double getAngle() {
        return this.angle;
    }

    /**
     * Variable holding the minimum angle this ship must have, being zero; converted to radians.
     */
    private double minAngle = Math.toRadians(0);

    /**
     * Variable holding the maximum angle this ship can have, being 360°;converted to radians.
     */
    private static double maxAngle = 2 * Math.PI;

    /**
     * Set the new angle for this ship.
     *
     * @param angle The given angle to be added to the current angle of this ship.
     * @pre The given angle must be a valid angle for this ship
     * | assert isValidAngle(angle)
     * @pre The sum of the current angle and the given angle must be smaler than the maximum angle.
     * If it is bigger, the angle must be equal to (current angle plus the given angle) modulo maxAngle.
     * | if getAngle() + angle > maxAngle
     * |       this.angle = (getAngle() + angle) % maxAngle
     * | else
     * |       this.angle = (getAngle() + angle)
     */
    private void setAngle(double angle) {
        assert isValidAngle(angle);
        if ((this.getAngle() + angle) < maxAngle) {
            this.angle = getAngle() + angle;
        } else if ((this.getAngle() + angle) > maxAngle) {
            this.angle = ((getAngle() + angle) % maxAngle);
        }
    }

    /**
     * Return true if angle is valid for this ship.
     *
     * @param angle The given angle to be added to the current angle of this ship.
     * @return true if the angle is a number.
     * | !Double.isNaN(angle)
     */
    private boolean isValidAngle(double angle) {
        return (!Double.isNaN(angle));
    }



    /**********
     * MOVEMENT RELATED
     **********/
    /**
     * Method to increase (or decrease) the velocity of this ship.
     *
     * @param amount The amount of velocity that needs to be added to the current velocity.
     * @pre The amount must be greater than zero, or it will be set to zero.
     * @post The new velocityX and velocityY are set. If there computed total is bigger than the speed of light
     * they should be scaled down relatively.
     * | if (newVelocity > SPEED_OF_LIGHT
     * |       setVelocityX((newVelocityX / newVelocity) * SPEED_OF_LIGHT);
     * |       setVelocityY((newVelocityY / newVelocity) * SPEED_OF_LIGHT);
     * | else
     * |       setVelocityX(newVelocityX)
     * |       setVelocityY(newVelocityY)
     */
    @Deprecated
    public void thrust(double amount) {
        if (amount < 0)
            amount = 0;
        double newVelocityX = getVelocityX() + amount * Math.cos(getAngle());
        double newVelocityY = getVelocityY() + amount * Math.sin(getAngle());
        double newVelocity = computeVelocity(newVelocityX, newVelocityY);
        if (newVelocity > SPEED_OF_LIGHT) {
            setVelocityX((newVelocityX / newVelocity) * SPEED_OF_LIGHT);
            setVelocityY((newVelocityY / newVelocity) * SPEED_OF_LIGHT);
        } else {
            setVelocityX(newVelocityX);
            setVelocityY(newVelocityY);
        }
    }

    /**
     * Turn the ship by adding a given angle to the current orientation. Angle has to be in radians and must be between 0 and 2π.
     *
     * @param angle The given angle that has to be added to the current angle.
     * @post Set the angle to the current angle plus the given angle.
     * | result == setAngle(getAngle() + angle)
     */
    public void turn(double angle) {
        setAngle(angle);
    }

    /**
     * Constant holding the speed of light.
     */
    private static double SPEED_OF_LIGHT = 300000;

    public void thrustOn(){
        double newVelocityX = getVelocityX() + getAcceleration() * Math.cos(getAngle());
        double newVelocityY = getVelocityY() + getAcceleration() * Math.sin(getAngle());
        double newVelocity = computeVelocity(newVelocityX, newVelocityY);
        if (newVelocity > SPEED_OF_LIGHT) {
            setVelocityX((newVelocityX / newVelocity) * SPEED_OF_LIGHT);
            setVelocityY((newVelocityY / newVelocity) * SPEED_OF_LIGHT);
            setActiveThruster();
        }
        else {
            setVelocityX(newVelocityX);
            setVelocityY(newVelocityY);
            setActiveThruster();
        }

    }

    private boolean activeThruster = false;

    private void setActiveThruster(){activeThruster = true;}

    private void resetActiveThruster(){activeThruster = false;}

    public boolean getActiveThruster(){
        return this.activeThruster;
    }

    public void thrustOff(){
        double oldVelocityX = getVelocityX() - getAcceleration() * Math.cos(getAngle());
        double oldVelocityY = getVelocityY() - getAcceleration() * Math.sin(getAngle());
        double oldVelocity = computeVelocity(oldVelocityX, oldVelocityY);
        if (oldVelocity > SPEED_OF_LIGHT){
            setVelocityX((oldVelocityX/oldVelocity) * SPEED_OF_LIGHT);
            setVelocityY((oldVelocityY/oldVelocity) * SPEED_OF_LIGHT);
            resetActiveThruster();
        }
        else {
            setVelocityX(oldVelocityX);
            setVelocityY(oldVelocityY);
            resetActiveThruster();
        }
    }

    private static double thrustForce = 1.1 * Math.pow(10, 18);

    public static double getThrustForce() {
        return thrustForce;
    }

    public double getAcceleration(){
        double acceleration = getThrustForce()/ getShipMass();
        if (acceleration < 0)
            return 0;
        else
            return acceleration;
    }


    /**********
     * COLLISION RELATED
     **********/
    /**
     * Compute the distance between two spaceships. If the two compared ships are the same, distance is 0.
     *
     * @return The distance between this ship and the given other ship.
     * Calculated distance
     * | return (Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2)));
     * @param        other | Second spaceship
     */
    public double getDistanceBetween(Ship other) {
        double x1 = this.getPositionX();
        double y1 = this.getPositionY();
        double x2 = other.getPositionX();
        double y2 = other.getPositionY();
        if (this == other) {
            return 0;
        } else {
            return (Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2)));
        }
    }

    /**
     * Check if two ships overlap.
     *
     * @param other | Second spaceship
     * @return | True if the spaceships overlap
     */
    public boolean overlap(Ship other) {
        if (this == other) {
            return true;
        } else {
            double distance = getDistanceBetween(other);
            if (this.radius > distance) {
                return true;
            } else if (other.radius > distance) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * @param other The given other ship.
     * @return Returns the time until the given ships will collide.
     * @throws IllegalArgumentException If there is no other ship
     *                                  | other = null
     *                                  |       throw new IllegalArgumentException
     * @throws IllegalArgumentException If the ships overlap. This means the already collided or the spawn as an overlap.
     *                                  | this.overlap(other)
     *                                  |       throw new IllegalArgumentException
     */
    public double getTimeToCollision(Ship other) throws IllegalArgumentException {
        if (other == null)
            throw new IllegalArgumentException("ship2 does not exist");
        if (this.overlap(other))
            throw new IllegalArgumentException("the ships overlap");

        //difference in x-coordinate
        double diffPosX = (other.getPositionX() - this.getPositionX());

        //difference in y-coordinate
        double diffPosY = (other.getPositionY() - this.getPositionY());

        //total position difference
        double[] differencePosition = new double[]{diffPosX, diffPosY};

        //difference in velocity in the x-direction
        double diffVelX = (other.getVelocityX() - this.getVelocityX());

        //difference in velocity in the y-direction
        double diffVelY = (other.getVelocityY() - this.getVelocityY());

        //total velocity difference
        double[] differenceVelocity = new double[]{diffVelX, diffVelY};

        //position difference multiplication
        double diffPosMult = (Math.pow(differencePosition[0], 2) + (Math.pow(differencePosition[1], 2)));

        //velocity difference multiplication
        double diffVelMult = (Math.pow(differenceVelocity[0], 2) + (Math.pow(differenceVelocity[1], 2)));

        //velocity position multiplication
        double diffVelPosMult = ((differenceVelocity[0] * differencePosition[0]) + differenceVelocity[1] * differencePosition[1]);

        //sigma as defined by the assignment (just the sum of the radii of the ships involved)
        double sigma = (this.getRadius() + other.getRadius());
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
     * @param other The given other ship.
     * @return Returns the position where the given other ship and this ship will collide.
     * @post If the timToCollision is less than or equals zero there will be no collision
     * so there's no collision position either.
     * | if (getTimeToCollision(other) <= 0
     * |           return null
     * Calculate the collision position by multiplying the velocity with the TimeToCollision
     * (velocity * speed = position(distance))
     * | else
     * |           collisionPositionX = getTimeToCollision(other) * velocityX;
     * |           double collisionPositionY = getTimeToCollision(other) * velocityY;
     * |           return collision
     */
    public double[] getCollisionPosition(Ship other) {
        double time = this.getTimeToCollision(other);
        if (time == Double.POSITIVE_INFINITY) {
            return null;
        } else {
            double collisionPositionX = getTimeToCollision(other) * velocityX;
            double collisionPositionY = getTimeToCollision(other) * velocityY;
            return new double[]{collisionPositionX, collisionPositionY};
        }
    }


    /**********
     * MASS RELATED
     **********/
    /**
     * Set the mass of this ship to the given mass.
     *
     * @param mass  The given mass.
     * @post The mass of this ship is equal to the given mass.
     *       | new.getMass() == mass
     */
    public void setMass(double mass){
        if (isValidMass(mass))
            this.mass = mass;
        }

    /**
     * Return true if the mass is valid for this ship.
     *
     * @param mass The given mass.
     * @return True if the given mass is a number.
     *         | result = !Double.isNaN(mass)
     */
    private boolean isValidMass(double mass){
        return (!Double.isNaN(mass));
    }



    /**********
     * BULLET RELATED
     **********/
    private Set<Bullet> bulletSet = new HashSet<>();

    public Set<Bullet> getBullets(){
        return this.bulletSet;}

    public void loadBullet(Bullet bullet) throws IllegalArgumentException{
        if (!isValidBullet(bullet)) throw new IllegalArgumentException("Bullet is not valid for this ship");
        //add bullet to the set of bullets of this ship
        bulletSet.add(bullet);
        //if ship is linked to a world, remove bullet from world, bullet cannot be in world and on ship at the same time
        if (this.getWorld() != null){
            World world = this.getWorld();
            world.removeEntity(bullet);
        }
        //change the bullet's position to the position of the ship is loaded on
        bullet.setPositionX(this.getPositionX());
        bullet.setPositionY(this.getPositionY());
        bullet.setShip(this);
    }

    public void loadSetOfBullets(Collection<Bullet> bullets) throws IllegalArgumentException{
        for (Bullet bullet : bullets){
            if (!isValidBullet(bullet)) throw new IllegalArgumentException("Bullet is not valid for this ship");
            //add bullet to the set of bullets of this ship
            bulletSet.add(bullet);
            //if ship is linked to a world, remove bullet from world, bullet cannot be in world and on ship at the same time
            if (this.getWorld() != null){
                World world = this.getWorld();
                world.removeEntity(bullet);
            }
            //change the bullet's position to the position of the ship is loaded on
            bullet.setPositionX(this.getPositionX());
            bullet.setPositionY(this.getPositionY());
            bullet.setShip(this);
        }
    }

    public boolean isValidBullet(Bullet bullet){
        if (getBullets().contains(bullet)) return false;
        return true;
    }

    /**
     * Resolves a collision between this ship and another entity, being a ship or a bullet.
     *
     * @param entity The entity this ship collides with.
     * When two ships collide, they bounce of each other => velocity change
     * When this ship collides with a bullet fired by itself, the bullet is reloaded onto the ship.
     * When this ship collides with a bullet fired by another ship, the ship AND the bullet die.
     * When this ship collides with an asteroid, the ship dies. The asteroid is not affected.
     * When this ship collides with a planetoid, the ship is teleported to a random position in the world.
     *               If that position is occupied, the ship dies. The planteoid is not affected.
     */
    @Override
    public void collide(Entity entity) {
        if (entity instanceof Ship) {
            Ship other = (Ship) entity;
            //(vxi, vyi) = vxi + Jx/mi, vyi + Jy/mi)
            //(vxj, vyj) = vxj + Jx/mj, vyj + JY/mj)
            //Jx = (J deltax) / sigma
            //Jy = (J deltay) / sigma

            //mi
            double shipMass = this.getShipMass();
            //mj
            double otherMass = other.getShipMass();

            double[] deltaR = {other.getPositionX() - this.getPositionX(), other.getPositionY() - this.getPositionY()};
            double[] deltaV = {other.getVelocityX() - this.getVelocityX(), other.getVelocityY() - this.getVelocityY()};
            double sigma = this.getRadius() + other.getRadius();

            //J = (2 mi mj * (deltav * deltar)/(radius*(mi + mj))
            double j = (2 * shipMass * otherMass * (deltaV[0] * deltaR[0] + deltaV[1] * deltaR[1])) / sigma * (shipMass + otherMass);

            //jx & jy
            double jx = (j * deltaR[0] / sigma);
            double jy = (j * deltaR[1] / sigma);


            double currentShipVelocityX = this.getVelocityX();      //vxi
            double currentShipVelocityY = this.getVelocityY();      //vyi
            double currentEntityVelocityX = other.getVelocityX();  //vxj
            double currentEntityVelocityY = other.getVelocityY();  //vyj

            double newShipVelocityX = currentShipVelocityX + jx / shipMass;   //vxi + Jx/mi
            double newShipVelocityY = currentShipVelocityY + jy / shipMass;   //vyi + Jy/mi
            double newEntityVelocityX = currentEntityVelocityX + jx / otherMass; //vxj + Jx/mj
            double newEntityVelocityY = currentEntityVelocityY + jy / otherMass; //vyj + Jy/mj

            this.setVelocityX(newShipVelocityX);
            this.setVelocityY(newShipVelocityY);
            other.setVelocityX(newEntityVelocityX);
            other.setVelocityY(newEntityVelocityY);

        }
        if (entity instanceof Bullet) {
            Bullet bullet = (Bullet) entity;
            if (bullet.getBulletSource() == this) {
                bullet.setPositionX(this.getPositionX());
                bullet.setPositionY(this.getPositionY());
                this.loadBullet(bullet);
            } else {
                bullet.terminate();
                this.terminate();
            }
        }
        if (entity instanceof Asteroid){
            this.terminate();
        }
        if (entity instanceof Planetoid){
            this.teleportShip();
        }
    }

    public void teleportShip(){
        // Generate random positionX within bounds of world.
        double lowerX = 0 + getRadius();
        double upperX = getWorld().getWidth() - getRadius() + 1;
        double newPositionX = ThreadLocalRandom.current().nextDouble(lowerX, upperX);
        // Generate random positionY within bounds of world.
        double lowerY = 0 + getRadius();
        double upperY = getWorld().getHeight() - getRadius() + 1;
        double newPositionY = ThreadLocalRandom.current().nextDouble(lowerY, upperY);

        if (getWorld().getEntityAtPosition(newPositionX, newPositionY) == null){
            // Set new positions.
            this.setPositionX(newPositionX);
            this.setPositionY(newPositionY);
        }
        else {
            this.terminate();
        }
    }

    public void removeBulletShip(Bullet bullet){
        this.bulletSet.remove(bullet);
    }

    /**
     * Fire a bullet from the ship.
     *
     */
    public void fireBullet(){
        Bullet bullet = getBullets().iterator().next();
        // Remove bullet from ship
        this.removeBulletShip(bullet);
        // set ship as bulletSource
        bullet.setBulletsource(this);

        // bullet speed is 250 km/s in same direction ship is faced
        // calculate separate velocities with 2 equations:
        // sqrt(pow(newVelocityX, 2) + pow(newVelocityY, 2)) = 250 km/s  | basic formula for calculation of bullet speed must equal 250 km/s
        // newVelocityY / newVelocityX = this.velocityY / this.velocityX     | to maintain the current direction of the ship
        // rewrite equations to get the used equations for velocity calculations.
        double newVelocityX = Math.sqrt((Math.pow(250,2)) / (((Math.pow((this.getVelocityY()/this.getVelocityX()),2)) + 1)));
        double newVelocityY = ((this.getVelocityY() / this.getVelocityX()) * newVelocityX);
        bullet.setVelocityX(newVelocityX);
        bullet.setVelocityY(newVelocityY);

        // bullet position is next to the ship so that both don't overlap
        // new positionX is current positionX + both radii + 1 (adds a little space)
        double bulletRadius = bullet.getRadius();
        double shipRadius = this.getRadius();
        double newXPositionBullet = bullet.getPositionX() + bulletRadius + shipRadius + 1;
        bullet.setPositionX(newXPositionBullet);


    }
}


