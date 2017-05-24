package asteroids.model.Programs.Expressions;

import asteroids.model.Program;
import asteroids.model.Programs.Variable;
import asteroids.part3.programs.SourceLocation;

import java.util.Objects;
import java.util.Set;

/**
 * Created by joachim on 24/05/2017.
 */
public abstract class Expression<T>{
    private SourceLocation location;
    private Program program;

    protected Expression(SourceLocation sourceLocation){
        this.location = sourceLocation;
    }

    public abstract T evaluate() throws IllegalArgumentException;

    public abstract T evaluate(Object[] actualArgs, Set<Variable> variables) throws IllegalArgumentException;

    public void setProgram(Program program) {
        this.program = program;
    }

    public Program getProgram() {
        return program;
    }
}