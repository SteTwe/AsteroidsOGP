package asteroids.model.Programs.Expressions;

import asteroids.model.Program;
import asteroids.model.Programs.Variable;
import asteroids.part3.programs.SourceLocation;

import java.util.Set;

/**
 * Created by stef on 24-5-17.
 */
public class lessThan extends Expression<Boolean> {
    private Expression<?extends Double> expression1;
    private Expression <? extends Double> expression2;

    public lessThan(Expression <?extends Double> expression1, Expression <?extends Double> expression2, SourceLocation location){
        super(location);
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public Boolean evaluate() throws IllegalArgumentException {
        return (expression1.evaluate() < expression2.evaluate());
    }

    @Override
    public Boolean evaluate(Object[] args, Set<Variable> variables) throws IllegalArgumentException {
        return expression1.evaluate(args, variables) < expression2.evaluate(args, variables);
    }

    @Override
    public void setProgram(Program program) {
        super.setProgram(program);
        expression1.setProgram(program);
        expression2.setProgram(program);
    }
}
