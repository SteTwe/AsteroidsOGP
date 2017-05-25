package asteroids.model.Programs.Expressions;


import asteroids.model.Program;
import asteroids.model.Programs.Variable;
import asteroids.part3.programs.SourceLocation;

import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;

public class readVariable extends Expression<Object> {
    private String variableName;

    public readVariable(String variableName, SourceLocation location){
        super(location);
        this.variableName = variableName;
    }

    @Override
    public void setProgram(Program program) {
        super.setProgram(program);
    }

    @Override
    public Object evaluate() throws IllegalArgumentException {
        return getProgram().getVariable(variableName).getValue();
    }

    @Override
    public Object evaluate(Object[] actualArgs, Set<Variable> variables) throws IllegalArgumentException {
        Optional<Variable> variable =  variables.stream().filter(var -> var.getVariableName().equals(variableName)).findFirst();
        if (variable.isPresent()) return variable.get().getValue();
        else return getProgram().getVariable(variableName).getVariableName();
    }
}
