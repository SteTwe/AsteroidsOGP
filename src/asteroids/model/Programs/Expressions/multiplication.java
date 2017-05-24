package asteroids.model.Programs.Expressions;


import asteroids.model.Program;
import asteroids.model.Programs.Variable;
import asteroids.part3.programs.SourceLocation;

import java.util.Set;

public class multiplication extends Expression<Double>{
    private Expression<?extends Double> expression1;
    private Expression<?extends Double> expression2;

    public multiplication(Expression<?extends Double> expression1, Expression<?extends Double> expression2, SourceLocation location){
        super(location);
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public Double evaluate() throws IllegalArgumentException {
        double multiplication = expression1.evaluate() * expression2.evaluate();
        return multiplication;
    }

    @Override
    public Double evaluate(Object[] args, Set<Variable> variables) throws IllegalArgumentException {
        double multiplication = expression1.evaluate(args, variables) * expression2.evaluate(args, variables);
        return multiplication;
    }

    @Override
    public void setProgram(Program program) {
        super.setProgram(program);
        expression1.setProgram(program);
        expression2.setProgram(program);
    }
}
