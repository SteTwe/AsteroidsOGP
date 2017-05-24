package asteroids.model.Programs.Expressions;

import asteroids.model.Program;
import asteroids.model.Programs.Variable;
import asteroids.part3.programs.SourceLocation;

import java.util.Set;

public class addition extends Expression<Double> {
    private Expression<? extends Double> e1;
    private Expression<? extends Double> e2;

    public addition(Expression <?extends Double> expression1, Expression <?extends Double> expression2, SourceLocation location){
        super(location);
        this.e1 = expression1;
        this.e2 = expression2;
    }

    @Override
    public Double evaluate() throws IllegalArgumentException {
        double addition = e1.evaluate() + e2.evaluate();
        return addition;
    }

    @Override
    public Double evaluate(Object[] args, Set<Variable> variables) throws IllegalArgumentException {
        double addition = e1.evaluate(args, variables) + e2.evaluate(args, variables);
        return addition;
    }

    @Override
    public void setProgram(Program program) {
        super.setProgram(program);
        e1.setProgram(program);
        e2.setProgram(program);
    }
}
