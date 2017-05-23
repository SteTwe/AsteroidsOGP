package asteroids.model;

/**
 * Created by joachim on 18/05/2017.
 */
public class Planetoid extends MinorPlanet {

    public Planetoid(double positionX, double positionY, double velocityX, double velocityY, double radius, double totalTraveledDistance) throws IllegalArgumentException{
        super(positionX, positionY, velocityX, velocityY, radius);
        setTotalTraveledDistance(totalTraveledDistance);
    }

    private double totalTraveledDistance;

    public void setTotalTraveledDistance(double totalTraveledDistance) {
        this.totalTraveledDistance = totalTraveledDistance;
        if (this.getRadius() < getMinRadius()) this.terminate();
    }

    public double getTotalTraveledDistance() {
        return totalTraveledDistance;
    }

    @Override
    public void collideWith(Collideable other) {
        other.collideWith(this);
    }

    @Override
    public void collideWith(Ship ship) {
        ship.teleportShip();
    }

    @Override
    public void collideWith(MinorPlanet minorPlanet) {
        this.bounceOffEntity(minorPlanet);
    }

    @Override
    public void move(double duration) {
        super.move(duration);
        setTotalTraveledDistance(getTotalTraveledDistance() + getTotalVelocity() * duration);
        if (getRadius() < getMinRadius()) terminate();

    }

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
}
