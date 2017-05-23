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
}
