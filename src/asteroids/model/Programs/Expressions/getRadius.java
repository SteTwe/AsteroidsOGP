package asteroids.model.Programs.Expressions;


import asteroids.model.Entity;
import asteroids.model.Program;
import asteroids.model.Programs.Variable;
import asteroids.part3.programs.SourceLocation;

import java.util.Set;

public class getRadius extends Expression<Double>{
    private Expression<? extends Entity> entity;

    public getRadius(Expression<? extends Entity> entity, SourceLocation location){
        super(location);
        this.entity = entity;
    }

    @Override
    public Double evaluate() throws IllegalArgumentException {
        return entity.evaluate().getRadius();
    }

    @Override
    public Double evaluate(Object[] args, Set<Variable> variables) throws IllegalArgumentException {
        return entity.evaluate(args, variables).getRadius();
    }

    @Override
    public void setProgram(Program program) {
        super.setProgram(program);
        entity.setProgram(program);
    }
}
