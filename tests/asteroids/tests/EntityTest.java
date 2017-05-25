package asteroids.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import asteroids.model.Ship;
import org.junit.Before;
import org.junit.Test;

import asteroids.model.Entity;
import asteroids.model.World;


/**
 * Created by joachim on 25/05/2017.
 */
public class EntityTest {
    private Entity entity;
    private World world;
    private static final double EPSILON = 0.0001;

    private double minMass = 4/3. * Math.PI * Math.pow(entity.getRadius(), 3) * this.minDensityShip;
    private double minDensityShip = 1.42 * Math.pow(10, 12);
    private double minAngle = Math.toRadians(0);


    @Before
    public void createShip() {
        entity = new Ship(100,100,300,300,15, minAngle, minMass);
    }

    @Test
    public void testMove(){
        entity = new Ship(100,100,300,300,15, minAngle, minMass);
        entity.move(10);
        //double[] position = {entity.getPositionX(), entity.getVelocityY()};
        assertEquals(1000, entity.getPositionX(), EPSILON);
    }


}
