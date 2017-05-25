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
    public double getNumberOfBouncesLeft(){
        return numberOfBounces;
    }

    public void resetBounces(){
        numberOfBounces = 2;
    }


    /******************
     * COLLISION RELATED
     **************/

    public void collideWithBoundary(){
        super.collideWithBoundary();
        this.bounce();
        if (getNumberOfBouncesLeft() < 0) this.terminate();
    }

    @Override
    public void terminate() {
        super.terminate();
        if (this.getShip() != null) ship.removeBulletShip(this);
    }

    @Override
    public void collide(Entity other) {
        if (this.getBulletSource() == other){
            this.setPosition(other.getPositionX(), other.getPositionY());
            ((Ship) other).loadBullet(this);
        }
        else {
            other.terminate();
            this.terminate();
        }
    }
}

