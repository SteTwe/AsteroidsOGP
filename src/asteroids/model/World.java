package asteroids.model;

/**
 * Created by stef on 1-4-17.
 */
public class World {


    public World(double width, double height){
        this.setWidth(width);
        this.setHeight(height);

    }

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
}
