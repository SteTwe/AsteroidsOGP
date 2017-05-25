package asteroids.model.Programs.Statements;


import asteroids.model.Program;
import asteroids.model.Programs.Variable;
import asteroids.part3.programs.SourceLocation;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class sequenceOfStatements extends Statement{

    private List<Statement> statements;
    private boolean activeBreak;
    private boolean failedToAdvance;

    public sequenceOfStatements(List<Statement> statements, SourceLocation location){
        super(location);
        this.statements = statements;
    }

    @Override
    public void execute() {
        failedToAdvance = false;
        activeBreak = false;
        SourceLocation location = getProgram().getLocation();
        for (int i =0; i < statements.size(); i++){
            Statement statement = statements.get(i);
            SourceLocation nextLocation;
            if (i == statements.size() -1)  nextLocation = null;
            else nextLocation = statements.get((i-1)).getSourceLocation();
            if(i == statements.size()-1 || nextLocation.getLine()> location.getLine()||(nextLocation.getLine()==location.getLine()&&nextLocation.getColumn()>location.getColumn())){
                statement.execute();

                if (statement.failedToAdvance()){
                    failedToAdvance = true;
                    return;
                }

                if (statement.activeBreakStatement()){
                    activeBreak = true;
                    return;
                }
            }
        }
    }


    @Override
    public Optional execute(Object[] actualArgs, Set<Variable> localVariables) {
        activeBreak = false;
        for (int i = 0; i< statements.size(); i++){
            Statement statement = statements.get(i);
            Optional result = statement.execute(actualArgs, localVariables);
            if (result.isPresent()) {
                if (statement.activeBreakStatement()) activeBreak= true;
                return result;
            }
            if (statement.activeBreakStatement()) {
                activeBreak= true;
                return Optional.empty();
            }
        }




        return Optional.empty();
    }

    @Override
    public void setProgram(Program program) {
        super.setProgram(program);
        for(Statement statement: statements) statement.setProgram(program);
    }

    @Override
    public boolean failedToAdvance() {
        return failedToAdvance;
    }

    @Override
    public boolean activeBreakStatement() {
        return activeBreak;
    }
}
