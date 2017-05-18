package asteroids.model;
import be.kuleuven.cs.som.annotate.*;

import java.util.IllegalFormatCodePointException;

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

    private World world;

    public World getWorld(){
        return this.world;
    }


    /**
     * Set the source of the bullet to the given ship
     */
    public void setBulletsource(Ship ship){
        this.bulletSource = ship;
    }

    public void setWorld(World world){
        this.world = world;
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
                this.terminate();
                ((Ship) entity).terminate();
            }
        }
        if (entity instanceof Bullet){
            this.terminate();
            ((Bullet) entity).terminate();
        }

    }

    public void collideWithBoundary(){

    }

}
