package asteroids.model.Programs;

/**
 * Created by joachim on 22/05/2017.
 */
public class AdditionExpression {

    public <D extends Number> double add(D one, D two){

        return one.doubleValue() + two.doubleValue();
    }
}
