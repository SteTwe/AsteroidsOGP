package asteroids.model;
import be.kuleuven.cs.som.annotate.*;

/**
 * Created by stef on 2-4-17.
 * @author Joachim & Stef
 */
public class Bullet extends Entity{

    /**
     * Initialize a bullet with x-position, y-position, velocity in x-direction, velocity in y-direction, radius.
     *
     * @param positionX The x-coordinate of this new bullet.
     * @param positionY The y-coordinate of this new bullet.
     * @param velocityX The velocity in the x-direction of this new bullet.
     * @param velocityY The velocity in the y-direction of this new bullet.
     * @param radius    The radius of this new bullet.
     */

    public Bullet(double positionX, double positionY, double velocityX, double velocityY, double radius) throws IllegalArgumentException {
        super(positionX, positionY, velocityX, velocityY, radius);
    }

    /**
     * Sets the ship of the current bullet to the given ship.
     * @param ship
     */
    //TODO
    public void setShip(Ship ship){

    }

    /**
     * Return the ship to which the current bullet is attached to.
     *
     * @return The ship
     *          | result == this.ship
     */
    public Ship getBulletShip(){
        return  this.bulletShip;
    }

    private Ship bulletShip;


    /**
     * Return the source of this bullet as a Ship.
     */
    public Ship getBulletSource(){
        return this.bulletSource;
    }

    private Ship bulletSource;

    /**
     * Set the source of the bullet to the given ship
     */
    public void setBulletsource(Ship ship){
        this.bulletSource = ship;
    }
    /**
     *
     * @return
     */
    @Override
    public double getMass(){
        double radius = getRadius(); //getting getRadius from superclass Entity
        return(3/4 * Math.PI * Math.pow(radius, 3) * getDensity());
    }

    /**
     * Constant holding the minimum radius of a bullet.
     */
    public static double minRadius = 1;

    @Basic
    public double getMinRadius(){
        return minRadius;
    }

    /**
     * Constant holding the density of a bullet following the given formula.
     * 7.8 * 10^12 kg/m^3
     */
    private double density = 7.8 * Math.pow(10, 12);

    public double getDensity(){
        return density;
    }
    /**
     * Decrements the number of bounces until destruction
     */
    public void bounce(){
        numberOfBounces--;
    }

    /**
     * Variable holding the amount of bounces until destruction of this bullet.
     */
    public double numberOfBounces = 2;

    /**
     * Returns the number of bounces left until destruction of this bullet.
     * @return
     */
    public double getNumberOfBounces(){
        return this.numberOfBounces;
    }

    @Override
    public void collide(Entity entity){
        if (entity instanceof Ship){
            if (this.getBulletSource() == entity){
                this.setPositionX(entity.getPositionX());
                this.setPositionY(entity.getPositionY());
                ((Ship) entity).loadBullet(this);
            }
            else{
                this.terminateBullet();
                ((Ship) entity).terminateShip();
            }
        }
        if (entity instanceof Bullet){
            this.terminateBullet();
            ((Bullet) entity).terminateBullet();
        }

    }

    public void collideWithBoundary(){

    }

    public void terminateBullet(){
        super.terminate();
        if (this.getWorld() != null)
            getWorld().removeBulletWorld(this);
    }

}
