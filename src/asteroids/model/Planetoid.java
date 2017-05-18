package asteroids.model;

/**
 * Created by joachim on 18/05/2017.
 */
public class Planetoid extends MinorPlanet {

    public Planetoid(double positionX, double positionY, double velocityX, double velocityY, double radius) throws IllegalArgumentException{
        super(positionX, positionY, velocityX, velocityY, radius);
    }

    @Override
    public void collideWith(Bullet bullet) {
        this.terminate();
        bullet.terminate();
    }

    @Override
    public void collideWith(Collideable other) {
        other.collideWith(this);
    }

    @Override
    public void collideWith(Ship ship) {
        ship.teleportShip();
    }

    public void collideWith(Planetoid planetoid){
        this.bounceOffEntity(planetoid);
    }

    public void collideWith(Asteroid asteroid){
        this.bounceOffEntity(asteroid);
    }
}
