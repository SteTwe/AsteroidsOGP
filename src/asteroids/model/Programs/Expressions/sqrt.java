package asteroids.model.Programs.Expressions;

import asteroids.model.Program;
import asteroids.model.Programs.Variable;
import asteroids.part3.programs.SourceLocation;

import java.util.Set;

public class sqrt extends Expression<Double> {

    Expression<Double> expression;

    public sqrt(Expression<Double> e, SourceLocation location){
        super(location);
        this.expression = e;
    }





    @Override
    public Double evaluate() throws IllegalArgumentException {
        return Math.sqrt(expression.evaluate());
    }

    @Override
    public Double evaluate(Object[] args, Set<Variable> variables) throws IllegalArgumentException {
        return Math.sqrt(expression.evaluate(args, variables));
    }

    @Override
    public void setProgram(Program program) {
        super.setProgram(program);
        expression.setProgram(program);
    }
}
