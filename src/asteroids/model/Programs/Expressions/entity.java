package asteroids.model.Programs.Expressions;


import asteroids.model.Entity;
import asteroids.model.Program;
import asteroids.model.Programs.Variable;
import asteroids.part3.programs.SourceLocation;

import java.util.Set;

public abstract class entity extends Expression<Entity>{


    public entity(SourceLocation location){
        super(location);
    }


    @Override
    public void setProgram(Program program) {
        super.setProgram(program);
    }

    @Override
    public Entity evaluate(Object[] args, Set<Variable> variables) throws IllegalArgumentException {
        return evaluate();
    }
}
