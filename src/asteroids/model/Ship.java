package asteroids.model;

import java.util.*;
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

    /**
     * Set holding the bullets of this ship.
     */
    private Set<Bullet> bulletSet = new HashSet<Bullet>();

    /**
     * Variable that holds the program that can be loaded on the ship.
     */
    private Program program;


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
    private void setAngle(double angle) throws IllegalArgumentException {
        if (isValidAngle(angle)){
            this.angle = angle;
        }
        else throw new IllegalArgumentException("Angle is not valid.");
    }

    /**
     * Returns if the given angle is valid for this ship.
     *
     * @param angle The angle to evaluate.
     * @see implementation
     */
    private boolean isValidAngle(double angle) {
        if (angle < 0) return false;
        if (angle > Math.PI * 2) return false;
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
    public void thrust(double amount) {
        if (amount < 0 || Double.isNaN(amount))
            amount = 0;
        double newVelocityX = getVelocityX() + amount * Math.cos(getAngle());
        double newVelocityY = getVelocityY() + amount * Math.sin(getAngle());
        double newVelocity = getTotalVelocity();
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
        assert(isValidAngle(getAngle() + angle));
        setAngle(this.getAngle() + angle);
    }

    /**
     * Constant holding the speed of light.
     */
    private static double SPEED_OF_LIGHT = 300000;

    /**
     * Variable holding the condition of the thruster.
     */
    private boolean thrusterEnabled = false;

    /**
     * Set thruster condition active.
     * @post thrusterEnabled == true
     */
    public void thrustOn(){
        thrusterEnabled = true;}

    /**
     * Reset condition of the thruster.
     * @post thrusterEnabled == false
     */
    public void thrustOff(){
        thrusterEnabled = false;}

    /**
     * Return the condition of the thruster.
     *
     * @return Return the condition of the thruster.
     *         | result == thrusterEnabled
     */
    public boolean isThrusterEnabled(){
        return thrusterEnabled;
    }

    /**
     * Constant holding the thrust force.
     */
    private static double thrustForce = 1.1 * Math.pow(10, 18);

    /**
     * Return the acceleration of the ship.
     *
     * @return Return the acceleration of the ship.
     *         |result == acceleration
     */
    public double getAcceleration(){
        if (!isThrusterEnabled()){
            return 0;
        }
        double acceleration = thrustForce/ getMass();
        if (acceleration < 0)
            return 0;
        else
            return acceleration;
    }

    /**
     * Move the ship for a given amount of time.
     *
     * @param duration  The amount of time to move.
     * @effect The ship moves for the given period. If the thruster is enabled, the ship accelerates.
     */
    @Override
    public void move(double duration) {
        super.move(duration);
        if (isThrusterEnabled()){
            thrust(getAcceleration() * duration);
        }
    }

    /**********
     * COLLISION RELATED
     **********/
    /**
     * Resolve the collision of a ship with another entity.
     *
     * @param other The other entity
     * @effect If the ship collides with another ship, they bounce of eachother.
     *         | if (other instanceof Ship) this.bounceOffEntity(other)
     * @effect If the ship collides with another entity, the other entity collides with the ship.
     *         | else other.collide(this)
     */
    @Override
    public void collide(Entity other) {
        if (other instanceof Ship){
            this.bounceOffEntity(other);
        }
        else other.collide(this);
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
    public void setMass(double mass) {
        if (isValidMass(mass)) {
            this.mass = mass;
        }
        else {
            this.mass = getMinShipMass();
        }
    }

    /**
     * Return the total mass of the ship.
     *
     * @return The mass of the ship + the mass of the bullets loaded onto it.
     *         | see implementation
     */
    @Override
    public double getMass(){
        double totalmass = this.mass;
        if(getBullets().isEmpty()) return this.mass;
        else {
            for (Bullet bullet : getBullets()){
                totalmass += bullet.getMass();
            }
        }
        return totalmass;
    }

    /**
     * Return true if the mass is valid for this ship.
     *
     * @param mass The given mass.
     * @return True if the given mass is a number.
     *         | result = !Double.isNaN(mass)
     */
    private boolean isValidMass(double mass){
        if (mass < getMinShipMass()) {
            return false;
        }
        return (!Double.isNaN(mass));
    }


    /**********
     * BULLET RELATED
     **********/
    /**
     * Return the bullets loaded onto this ship.
     *
     * @return Return the bullets loaded onto this ship.
     */
    public Set<Bullet> getBullets(){
        return new HashSet<Bullet>(bulletSet);
    }

    /**
     * Load the given bullet onto the ship.
     *
     * @param bullet Bullet to load onto the ship.
     * @throws IllegalArgumentException Bullet is already loaded onto the ship.
     * @post Bullet is loaded on the ship.
     *       | bulletSet.contains(bullet)
     */
    public void loadBullet(Bullet bullet) throws IllegalArgumentException{
        if (!isValidBullet(bullet)) throw new IllegalArgumentException("Bullet is not valid for this ship");
        //add bullet to the set of bullets of this ship
        bulletSet.add(bullet);
        //if bullet is linked to a world, remove bullet from world, bullet cannot be in world and on ship at the same time
        if (bullet.getWorld() != null){
            World world = bullet.getWorld();
            world.removeEntity(bullet);
        }
        //change the bullet's position to the position of the ship is loaded on
        bullet.setPosition(this.getPositionX(), this.getPositionY());
        bullet.setShip(this);
        bullet.resetBounces();
    }

    /**
     * Load a set of bullets onto the ship.
     *
     * @param bullets Bullets to load onto the ship.
     * @throws IllegalArgumentException Bullet is already loaded onto the ship.
     * @post Bullets are loaded on the ship.
     *       | for (Bullet bullet : bulletSet)
     *       |      bulletSet.contains(bullet)
     */
    public void loadSetOfBullets(Collection<Bullet> bullets) throws IllegalArgumentException{
        for (Bullet bullet : bullets){
            if (!isValidBullet(bullet)) throw new IllegalArgumentException("Bullet is not valid for this ship");
            this.loadBullet(bullet);
        }
    }

    /**
     * Return true if given bullet is not loaded on ship.
     * @param bullet The given bullet.
     * @return  True if bullet is not loaded on ship.
     *          | result == !getBullets().contains(bullet)
     */
    public boolean isValidBullet(Bullet bullet){
        if (bullet.getWorld() != null && bullet.getBulletSource() == null) return false;
        if (bullet == null) return false;
        if (bullet.isTerminated()) return false;
        if (bullet.getShip() != null && bullet.getShip() != this) return false;
        if (bullet.getBulletSource() != this && bullet.getBulletSource() != null) return false;
        return (bullet.isInRange(this));
    }

    /**
     * Teleport ship to a random location. If location is already occupied, terminate ship.
     */
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

    /**
     * Remove given bullet from ship.
     *
     * @param bullet The bullet to remove.
     * @post Bullet is not loaded on bullet.
     *       | !bulletSet.contains(bullet)
     */
    public void removeBulletShip(Bullet bullet) throws IllegalArgumentException{
        if (bullet.getShip() != this) throw new IllegalArgumentException("The bullet is not loaded on this ship.");
        this.bulletSet.remove(bullet);
        bullet.setShip(null);
    }

    /**
     * Fire a bullet from the ship.
     */
    public void fireBullet(){
        if (this.getWorld() == null) return;
        Bullet bullet = getBullets().iterator().next();
        double orientation = this.getAngle();

        double newVelocityX = 250 * Math.cos(orientation);
        double newVelocityY = 250 * Math.sin(orientation);

        // Remove bullet from ship
        this.removeBulletShip(bullet);
        // set ship as bulletSource
        bullet.setBulletsource(this);
        double bulletRadius = bullet.getRadius();
        double shipRadius = this.getRadius();
        double newXPositionBullet = this.getPositionX() + (bulletRadius + shipRadius) * Math.cos(orientation);
        double newYPositionBullet = this.getPositionY() + (bulletRadius + shipRadius) * Math.sin(orientation);

        bullet.setVelocity(newVelocityX, newVelocityY);
        bullet.setPosition(newXPositionBullet, newYPositionBullet);
        bullet.setWorld(this.getWorld());
        if (this.getWorld().entityOutOfBounds(bullet)) {
            bullet.terminate();
            return;
        }
        try{
            this.getWorld().addEntity(bullet);
        }
        catch (Exception e){
            //Exception --> overlap
            for (Entity entity : this.getWorld().getEntitySet()){
                if (entity.overlap(bullet)){
                    bullet.collide(entity);
                }
            }
            bullet.terminate();
        }

        double distanceToBorderWorld = getWorld().getWidth() - bullet.getPositionX();
        distanceToBorderWorld = Math.min(distanceToBorderWorld, bullet.getPositionX());
        distanceToBorderWorld = Math.min(distanceToBorderWorld, getWorld().getHeight() -bullet.getPositionY());
        distanceToBorderWorld = Math.min(distanceToBorderWorld, bullet.getPositionY());
        if (distanceToBorderWorld <= 0) bullet.terminate();
    }

    /**********
     * PROGRAM RELATED
     **********/

    /**
     * Set the program of this ship to the given program.
     *
     * @param program The given program.
     * @post The program of this ship equals the given program.
     *       | ship.getProgram() == program
     */
    public void setProgram(Program program) {
        this.program = program;
        if (program != null) program.setShip(this);
    }

    /**
     * Get the program loaded on this ship.
     *
     * @return This ship's program.
     *         | result == program
     */
    public Program getProgram() {
        return program;
    }

    /**
     * Execute the program for a given duration.
     */
    public List<Object> executeProgram(double duration){
        return program.execute(duration);
    }
}
