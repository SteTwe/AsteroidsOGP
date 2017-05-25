package asteroids.model.Programs.Statements;


import asteroids.model.Program;
import asteroids.model.Programs.Expressions.Expression;
import asteroids.model.Programs.Variable;
import asteroids.part3.programs.SourceLocation;

import java.util.Optional;
import java.util.Set;

public class whileStatement extends Statement {
    private Expression<Boolean> condition;
    private Statement body;
    private boolean execBody;
    private boolean failedToAdvance;

    public whileStatement(Expression<Boolean> condition, Statement body, SourceLocation location){
        super(location);
        this.condition = condition;
        this.body = body;
    }

    public Expression<Boolean> getCondition() {
        return condition;
    }

    public void setCondition(Expression<Boolean> condition) {
        this.condition = condition;
    }

    

    @Override
    public boolean activeBreakStatement() {
        return false;
    }

    @Override
    public void execute() {
        failedToAdvance = false;
        if (!execBody){
            if (condition.evaluate()) execBody =true;
            else return;
        }
        body.execute();
        if (body.failedToAdvance()){
            failedToAdvance = true;
            return;
        }

        while (condition.evaluate() && !body.activeBreakStatement()){
            getProgram().setLocation(this.getSourceLocation());
            body.execute();
            if (body.failedToAdvance()){
                failedToAdvance = true;
                return;
            }
        }
        execBody = false;
    }

    @Override
    public Optional execute(Object[] actualArgs, Set<Variable> localVariables) {
        failedToAdvance = false;
        if (!execBody){
            if (condition.evaluate(actualArgs, localVariables)) execBody = true;
            else return Optional.empty();
        }
        Optional result = body.execute(actualArgs, localVariables);
        if (result.isPresent()) return  result;
        while ((condition.evaluate(actualArgs, localVariables)) && !body.activeBreakStatement()){
            result = body.execute(actualArgs, localVariables);
            if(result.isPresent()) return result;
        }
        execBody =false;
        return Optional.empty();
    }

    @Override
    public void setProgram(Program program) {
        super.setProgram(program);
        condition.setProgram(program);
        body.setProgram(program);
    }

    @Override
    public boolean failedToAdvance() {
        return failedToAdvance;
    }
}
