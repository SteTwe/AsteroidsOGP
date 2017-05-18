package asteroids.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


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




    /**
     * Constant holding the speed of light.
     */
    private static double SPEED_OF_LIGHT = 300000;


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
     * MASS RELATED
     **********/
    public void setMass(double mass){
        if (isValidMass(mass))
            this.mass = mass;
        else
            //Temporary
            this.mass = getMinMass();
    }

    public double getShipMass(){
        double totalMass = this.mass;
        for (Bullet bullet : this.getBullets())
            totalMass += bullet.getBulletMass();
        return totalMass;
    }

    //TODO
    private boolean isValidMass(double mass){
        if ((mass >= getMinMass()) && (!Double.isNaN(mass)))
            return true;
        return false;
    }

    private double getMinMass(){
        return ((4/3)* Math.PI * Math.pow(this.getRadius(), 3) * getMinMassDensity());
    }

    public static double getMinMassDensity() {
        return minMassDensity;
    }

    private static double minMassDensity = 1.42 * Math.pow(10,12);


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
            world.removeBulletWorld(bullet);
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
                world.removeBulletWorld(bullet);
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
     * When this ship collides with a bullet fired by itself, the bullet is reloaded onto the ship
     * When this ship collides with a bullet fired by another ship, the ship AND the bullet die.
     */
    //TODO => ship and bullet collision
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
        this.removeBulletShip(bullet);
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


