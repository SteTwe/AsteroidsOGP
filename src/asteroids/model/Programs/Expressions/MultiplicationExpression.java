package asteroids.model.Programs.Expressions;



public class MultiplicationExpression {

    public <D extends Number> double multiply(D one, D two){

        return one.doubleValue() * two.doubleValue();
    }
}
