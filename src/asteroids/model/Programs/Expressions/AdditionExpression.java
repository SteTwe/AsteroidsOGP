package asteroids.model.Programs.Expressions;

public class AdditionExpression {

    public <D extends Number> double add(D one, D two){

        return one.doubleValue() + two.doubleValue();
    }
}
