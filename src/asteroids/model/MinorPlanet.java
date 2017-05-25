package asteroids.model;

//import be.kuleuven.cs.som.annotate.*;

/**
 * Created by joachim on 18/05/2017.
 */
public abstract class MinorPlanet extends Entity {

    public MinorPlanet(double positionX, double positionY, double velocityX, double velocityY, double radius) throws IllegalArgumentException {
        super(positionX, positionY, velocityX, velocityY, radius);
    }

    //m = 4/3 * pi * r^3 * density
    //density = 2.65 * 10^12kg/km^3 (found in entity)
    public double getMass(){
        return ((4/3) * Math.PI * Math.pow(this.getRadius(), 3) * getDensityAsteroids());
    }


    @Override
    public void collideWith(Bullet bullet) {
        bullet.terminate();
        this.terminate();
    }

    @Override
    public void collideWith(Collideable other) {
        if (other instanceof MinorPlanet){
            this.bounceOffEntity((MinorPlanet) other);
        }
    }

    @Override
    public abstract void collideWith(Ship ship);
}