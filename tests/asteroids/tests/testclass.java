package asteroids.tests;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.facade.Facade;
import asteroids.part2.facade.IFacade;
import asteroids.util.ModelException;


public class testclass {
    private static final double EPSILON = 0.0001;

    IFacade facade;

    @Before
    public void setUp() {
        facade = new Facade();
    }


    @Test
    public void testWorldMethods() throws ModelException{
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

        // Add bullet2 to world, then load bulletship1 on ship1 and add ship1 to world
        facade.addBulletToWorld(world, bullet2);
        facade.loadBulletOnShip(ship1, bulletShip1);
        facade.addShipToWorld(world, ship1);
        // Expected : 1, bullet on ship doesn't count as bullet on world
        assertEquals(1,facade.getWorldBullets(world).size(), EPSILON);

        // Add bullet2 to ship1
        facade.loadBulletOnShip(ship1, bullet2);
        // Expected : empty , bullet cannot be on ship and world at same time
        assertTrue(facade.getWorldBullets(world).isEmpty());

        // Add ship2 & 3 to world
        facade.addShipToWorld(world, ship2);
        facade.addShipToWorld(world, ship3);
        // Expected : 3, ship1, 2 & 3
        assertEquals(3, facade.getWorldShips(world).size(), EPSILON);

        Bullet bullet1 = facade.createBullet(600,150,2,4,6);
        facade.addBulletToWorld(world, bullet1);
        // Expected : 4, 3 ships + 1 bullet
        assertEquals(4, facade.getEntities(world).size(), EPSILON);

        // Expected : ship1
       assertEquals(ship1, facade.getEntityAt(world, 250,350));

        // Expected: bullet1
        assertEquals(bullet1, facade.getEntityAt(world, 600,150));

        // Expected: null
        assertEquals(null, facade.getEntityAt(world, 15,956));

        // Terminate world
        facade.terminateWorld(world);
        // Expected : all empty, termination == removal of entities
        assertEquals(0, facade.getEntities(world).size(), EPSILON);
        assertEquals(0, facade.getWorldShips(world).size(), EPSILON);
        assertEquals(0,facade.getWorldBullets(world).size(), EPSILON);


    }

    @Test
    public void testShipMethods() throws ModelException{
        Ship ship = facade.createShip(5,6,15,20,15,0.2,6);
        System.out.println(ship.getVelocityX());
        System.out.println(ship.getVelocityY());
        System.out.println(ship.getVelocityX()/ship.getVelocityY());

        facade.setThrusterActive(ship, true);
        System.out.println(ship.getVelocityX());
        System.out.println(ship.getVelocityY());
        System.out.println(ship.getVelocityX()/ship.getVelocityY());

    }

    public void testBulletMethods() throws ModelException{

    }
}



