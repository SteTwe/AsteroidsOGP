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
 * Created by joachim on 12/03/2017.
 */
public class testclass {
    private static final double EPSILON = 0.0001;

    IFacade facade;

    @Before
    public void setUp() {
        facade = new Facade();
    }
}


