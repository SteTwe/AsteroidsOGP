package asteroids.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.facade.Facade;
import asteroids.part2.facade.IFacade;
import asteroids.util.ModelException;


public class testclassWorld {
    private static final double EPSILON = 0.0001;

    IFacade facade;

    @Before
    public void setUp() {
        facade = new Facade();
    }


    @Test
    public void testWorldEntities() throws ModelException{
        World world = facade.createWorld(1000,800);
        //Test setters
        world.setWidth(1050);
        assertEquals(1050, facade.getWorldSize(world)[0], EPSILON);
        world.setHeight(850);
        assertEquals(850, facade.getWorldSize(world)[1], EPSILON);

        // Create some ships
        Ship ship1 = facade.createShip(250,350,10,5,200,0,1.0E20);
        Ship ship2 = facade.createShip(150,60,7,8,150,0.1, 1.1E20);
        Ship ship3 = facade.createShip(600,400,15,3,250,1,2.0E20);
        // Create some bullets
        Bullet bulletShip1 = facade.createBullet(0,0,15,2,2);
        Bullet bullet2 = facade.createBullet(15, 50, 2, 3, 9);
        
        // Add bullet2 to world, then load on ship and add ship to world
        facade.addBulletToWorld(world, bullet2);
        facade.loadBulletOnShip(ship1, bulletShip1);
        facade.addShipToWorld(world, ship1);
        // Expected : 1
        System.out.println(facade.getWorldBullets(world));
        
        facade.loadBulletOnShip(ship1, bullet2);
        // Expected : empty , bullet cannot be on ship and world at same time
        assertTrue(facade.getWorldBullets(world).isEmpty());

    }
}


