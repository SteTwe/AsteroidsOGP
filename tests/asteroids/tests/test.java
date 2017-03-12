package asteroids.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import asteroids.model.Ship;
import asteroids.facade.Facade;
import asteroids.part1.facade.IFacade;
import asteroids.util.ModelException;

/**
 * Created by Stef on 12/03/2017.
 */
public class test {
    IFacade facade;

    @Before
    public void setUp() {
        facade = new Facade();
    }
    
    @Test
    public void testMotion() throws ModelException{
    	Ship ship1 = facade.createShip(50,50,500,300,15,0);
    	facade.turn(ship1,5);
//    	facade.thrust(ship1, 0);
    	Ship ship2 = facade.createShip(40,10,20,30,12, 0);
    	double distance = facade.getDistanceBetween(ship1, ship2);
    	assertNotNull(distance);
    }
}


