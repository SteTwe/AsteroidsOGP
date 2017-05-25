package asteroids.model.Programs.Expressions;

import asteroids.model.Program;
import asteroids.model.Programs.Variable;
import asteroids.part3.programs.SourceLocation;

import java.util.Set;


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