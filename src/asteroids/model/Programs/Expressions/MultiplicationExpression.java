package asteroids.model.Programs;

/**
 * Created by joachim on 22/05/2017.
 */
public class MultiplicationExpression {

    public <D extends Number> double multiply(D one, D two){

        return one.doubleValue() * two.doubleValue();
    }
}
