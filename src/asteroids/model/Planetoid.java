package asteroids.model;

/**
 * A class representing a MinorPlanet of the type Planetoid, involving a position, a velocity and a radius.
 * @author Stef & Joachim
 * @version 1.0
 *
 */
public class Planetoid extends MinorPlanet {

    /**
     * Create a new planetoid.
     * @param positionX The x-coordinate of the planetoid.
     * @param positionY The y-coordinate of the planetoid.
     * @param velocityX The x-velocity of the planetoid.
     * @param velocityY The y-velocity of the planetoid.
     * @param radius    The radius of the planetoid.
     * @param totalTravelledDistance The total distance the planetoid has already travelled.
     * @throws IllegalArgumentException
     */
    public Planetoid(double positionX, double positionY, double velocityX, double velocityY, double radius, double totalTravelledDistance) throws IllegalArgumentException{
        super(positionX, positionY, velocityX, velocityY, radius);
        setTotalTravelledDistance(totalTravelledDistance);
    }

    /**
     * Variable holding the total distance that the planetoid has travelled.
     */
    private double totalTravelledDistance;

    /**
     * Set the travelle distance of this planetoid to a given distance.
     *
     * @param totalTravelledDistance The given distance.
     */
    public void setTotalTravelledDistance(double totalTravelledDistance) {
        this.totalTravelledDistance = totalTravelledDistance;
        if (this.getRadius() < getMinRadius()) this.terminate();
    }

    /**
     * Return the total distance travelled by this planetoid.
     *
     * @return result == this.totalTravelledDistance
     */
    public double getTotalTravelledDistance() {
        return totalTravelledDistance;
    }

    /**
     * Move the planetoid a given amount of time.
     *
     * @param duration  The amount of time to move.
     * @see implementation
     */
    @Override
    public void move(double duration) {
        super.move(duration);
        setTotalTravelledDistance(getTotalTravelledDistance() + getTotalVelocity() * duration);
        if (getRadius() < getMinRadius()) terminate();

    }

    /**
     * Terminate the planetoid. If the radius of the planetoid is higher or equal to 30, spawn 2 asteroids.
     * @effect if(radius >= 30) world.addAsteroid(asteroid1) && world.addAsteroid(asteroid2)
     */
    public void terminate(){
        double radius = this.getRadius();
        super.terminate();
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

    /**
     * Resolve the collision of a Planetoid with another Entity.
     * @see implementation
     */
    @Override
    public void collide(Entity other) {
        if (other instanceof Ship){
            ((Ship) other).teleportShip();
        }
        else super.collide(other);
    }
}
