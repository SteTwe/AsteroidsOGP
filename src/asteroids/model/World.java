package asteroids.model;

import be.kuleuven.cs.som.annotate.*;


import java.util.HashSet;
import java.util.Set;


/**
 * Created by stef on 1-4-17.
 */
public class World {


    public World(double width, double height){
        this.setWidth(width);
        this.setHeight(height);

    }

    private Set<Ship> shipSet = new HashSet<>();
    private Set<Bullet> bulletSet = new HashSet<>();
    private Bullet bullet;

    private double width;
    private double height;

    private void setWidth(double width){
        if (isValidWidth(width))
            this.width = width;
    }

    private void setHeight(double height){
        if (isValidHeight(height))
            this.height = height;
    }

    private boolean isValidWidth(double width){
        return ((!Double.isNaN(width)) && width >= 0 && width <= Double.MAX_VALUE);
    }

    private boolean isValidHeight(double height){
        return ((!Double.isNaN(height)) && height >= 0 && height <= Double.MAX_VALUE);
    }

    public double getHeight() {
        return this.height;
    }

    public double getWidth() {
        return this.width;
    }

    public void addShip(Ship ship){
        if (getShipSet().contains(ship));
        else
            this.shipSet.add(ship);
    }

    public void removeShip(Ship ship){
        this.shipSet.remove(ship);
    }

    public void addBullet(Bullet bullet){
        if (getBulletSet().contains(bullet));
        else
            this.bulletSet.add(bullet);
    }

    public void removeBullet(Bullet bullet){
        this.bulletSet.remove(bullet);
    }

    public Set<? extends Bullet> getBulletSet(){
        return this.bulletSet;
    }

    public Set<? extends Ship> getShipSet() {
        return this.shipSet;
    }
}
