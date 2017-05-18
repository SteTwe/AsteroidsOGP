package asteroids.model;

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

    /******************
     * VARIABLES BULLET
     **************/
    /**
     * Variable holding the ship that the bullet is attached to.
     */
    private Ship ship;
    /**
     * Variable holding the ship from where the bullet has been fired.
     */
    private Ship bulletSource;


    /******************
     * SHIP RELATED
     **************/
    /**
     * Sets the ship of the current bullet to the given ship.
     * @param ship
     */
    //TODO
    public void setShip(Ship ship){
        this.ship = ship;
    }

    /**
     * Return the ship to which the current bullet is attached to.
     *
     * @return The ship
     *          | result == this.ship
     */
    public Ship getShip(){
        return this.ship;
    }

    /**
     * Return the source of the fired bullet as a Ship.
     */
    public Ship getBulletSource(){
        return this.bulletSource;
    }

    /**
     * Set the source of the fired bullet to the given ship
     */
    public void setBulletsource(Ship ship){
        this.bulletSource = ship;
    }


    /******************
     * BOUNCE RELATED
     **************/
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


    /******************
     * COLLISION RELATED
     **************/
    /**
     * Resolve collisions with bullets between this bullet and another entity, being a ship, a bullet or a minorplant.
     *
     * @param entity The entity this bullet collides with.
     * When two bullets collide, they are both terminated.
     * When this bullet collides with the ship where it's fired from, the bullet will be reloaded to the ship.
     * When this bullet collides with another ship, the ship and bullet both die.
     * When this bullet collides with an asteroid, the asteroid and bullet both die.
     * When this bullet collides with a planetoid, the planetoid and bullet both die.
     */
    @Override
    public void collide(Entity entity){
        if (entity instanceof Ship){
            if (this.getBulletSource() == entity){
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
        if (entity instanceof Asteroid){
            this.terminate();
            ((Asteroid) entity).terminate();
        }
        if (entity instanceof Planetoid){
            this.terminate();
            ((Planetoid) entity).terminate();
        }

    }

    public void collideWithBoundary(){

    }

}
