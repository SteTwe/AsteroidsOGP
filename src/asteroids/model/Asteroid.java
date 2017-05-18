package asteroids.model;

/**
 * Created by joachim on 18/05/2017.
 */
public class Asteroid extends MinorPlanet{

    public Asteroid(double positionX, double positionY, double velocityX, double velocityY, double radius) throws IllegalArgumentException{
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
        ship.terminate();
    }


    public void collideWith(Asteroid asteroid){
        this.bounceOffEntity(asteroid);
    }

    public void collideWith(Planetoid planetoid){
        this.bounceOffEntity(planetoid);
    }
}

