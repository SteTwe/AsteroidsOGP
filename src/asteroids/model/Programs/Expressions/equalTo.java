package asteroids.model.Programs.Expressions;

import asteroids.model.Program;
import asteroids.model.Programs.Variable;
import asteroids.part3.programs.SourceLocation;

import java.util.Set;


public class equalTo extends Expression<Boolean> {
    private Expression e1;
    private Expression e2;

    public equalTo(Expression expression1, Expression expression2, SourceLocation location){
        super(location);
        this.e1 = expression1;
        this.e2 = expression2;
    }

    @Override
    public Boolean evaluate() throws IllegalArgumentException {
        return (e1.evaluate() == e2.evaluate());
    }

    @Override
    public Boolean evaluate(Object[] args, Set<Variable> variables) throws IllegalArgumentException {
        return (e1.evaluate(args, variables) == e2.evaluate(args, variables));
    }

    @Override
    public void setProgram(Program program) {
        super.setProgram(program);
        e1.setProgram(program);
        e2.setProgram(program);
    }
}


