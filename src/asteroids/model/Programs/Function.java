package asteroids.model.Programs;

import asteroids.model.Program;
import asteroids.model.Programs.Statements.Statement;
import asteroids.part3.programs.SourceLocation;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


public class Function {

    private String name;
    private Statement body;
    private SourceLocation location;
    private Program program;
    private boolean breakActive;

    public Function(String name, Statement body, SourceLocation location){
        this.name = name;
        this.body = body;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Statement getBody() {
        return body;
    }

    public void setBody(Statement body) {
        this.body = body;
    }

    public void setBreakActive(boolean active){
        this.breakActive = active;
    }

    public boolean isBreakActive() {
        return breakActive;
    }

    public void setProgram(Program program){
        this.program = program;
        body.setProgram(program);
    }

    public Program getProgram() {
        return program;
    }

    public Object calculate(Object[] args){
        setBreakActive(false);
        Set<Variable> localVariables = new HashSet<>();
        try {
            Optional result = body.execute(args, localVariables);
            if (body.activeBreakStatement()){
                setBreakActive(true);
                return null;
            }
            else setBreakActive(false);
            return result.get();
        } catch (Exception e){
            throw new IllegalArgumentException();
        }

    }
}
