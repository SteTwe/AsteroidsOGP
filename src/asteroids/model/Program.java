package asteroids.model;


import asteroids.model.Programs.Function;
import asteroids.model.Programs.Statements.Statement;
import asteroids.model.Programs.Variable;
import asteroids.part3.programs.SourceLocation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Program {

    private List<Function> functions;
    private Statement main;
    private Set<Variable> variables = new HashSet<>();
    private List<Object> results;

    private SourceLocation location = new SourceLocation(0,0);
    private double executionTimeLeft;
    private Ship ship;

    public Program(List<Function> functions, Statement main){
        this.functions =functions;
        this.main = main;
        main.setProgram(this);
        for (Function function : functions) function.setProgram(this);
        executionTimeLeft = 0;
    }

    public List<Object> execute(double duration) throws IllegalArgumentException{
        executionTimeLeft = executionTimeLeft + duration;
        main.execute();
        if (main.activeBreakStatement()) throw new IllegalArgumentException();
        location = new SourceLocation(0,0);
        List<Object> result = results;
        results = null;
        return result;
    }

    public void setShip(Ship ship) {
        if (ship.getProgram() != null && ship.getProgram() != this) throw new IllegalArgumentException();
        this.ship = ship;
    }

    public Ship getShip() {
        return ship;
    }

    public List<Object> getResults() {
        return results;
    }

    public Set<Variable> getVariables() {
        return new HashSet<>(variables);
    }

    public void addVariable (Variable variable){
        variables.add(variable);
    }
    public Variable getVariable(String variableName){
        return this.variables.stream().filter(variable -> variable.getVariableName().equals(variableName)).reduce((first, second) -> second).get();
    }

    public double getExecutionTimeLeft() {
        return executionTimeLeft;
    }

    public void advanceTime(){
        executionTimeLeft -= 0.2;
    }

    public SourceLocation getLocation() {
        return location;
    }

    public void setLocation(SourceLocation location) {
        this.location = location;
    }

    public Function getFunction(String functionName) {
        return this.functions.stream().filter(function -> function.getName().equals(functionName)).reduce((first, second) -> second).get();

    }

    public void addResult(Object result){
        results.add(result);
    }
}