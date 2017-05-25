package asteroids.model.Programs.Statements;

import asteroids.model.Program;
import asteroids.model.Programs.Expressions.Expression;
import asteroids.model.Programs.Variable;
import asteroids.part3.programs.SourceLocation;

import java.util.Optional;
import java.util.Set;

/**
 * Created by stef on 25-5-17.
 */
public class ifStatement extends Statement{
    private boolean failedToAdvance;
    private Expression<? extends Boolean> condition;
    private Statement bodyIf;
    private Statement bodyElse;
    private boolean execIf;
    private boolean execElse;
    private boolean activeBreak;

    public ifStatement(Expression<? extends Boolean> condition, Statement bodyIf, Statement bodyElse, SourceLocation location){
        super(location);
        this.condition = condition;
        this.bodyElse = bodyElse;
        this.bodyIf = bodyIf;
    }

    @Override
    public void execute() {
        setActiveBreak(false);
        failedToAdvance = false;
        if(!execIf && !execElse){
            if (condition.evaluate()) execIf = true;
            else {
                if (bodyElse != null) execElse =true;
            }
        }

        if (execIf){
            bodyIf.execute();
            if (bodyIf.failedToAdvance()){
                setFailedToAdvance(true);
                return;
            }
            else {
                if (bodyIf.activeBreakStatement()) setActiveBreak(true);
                else setActiveBreak(false);
                execIf = false;
            }
        }
        if (execElse) {
            bodyElse.execute();
            if (bodyElse.failedToAdvance()) {
                setFailedToAdvance(true);
                return;
            }
            else{
                if (bodyElse.activeBreakStatement()) setActiveBreak(true);
                else setActiveBreak(false);
                execElse = false;

            }
        }
    }

    @Override
    public Optional execute(Object[] actualArgs, Set<Variable> localVariables) {
        setActiveBreak(false);
        failedToAdvance = false;
        if (condition.evaluate(actualArgs, localVariables)) execIf =true;
        else if (bodyElse != null) execElse = true;

        if (execIf){
            Optional result = bodyIf.execute(actualArgs, localVariables);
            if (bodyIf.activeBreakStatement()) setActiveBreak(true);
            else setActiveBreak(false);
            return result;
        }
        if (execElse) {
            Optional result = bodyElse.execute(actualArgs, localVariables);
            if (bodyElse.activeBreakStatement()) setActiveBreak(true);
            else setActiveBreak(false);
            return result;
        }
        return Optional.empty();
    }

    public Statement getBodyElse() {
        return bodyElse;
    }

    public Statement getBodyIf() {
        return bodyIf;
    }

    public void setBodyElse(Statement bodyElse) {
        this.bodyElse = bodyElse;
    }

    public void setBodyIf(Statement bodyIf) {
        this.bodyIf = bodyIf;
    }

    public boolean failedToAdvance() {
        return failedToAdvance;
    }

    public boolean hasActiveBreak() {
        return activeBreak;
    }

    public void setActiveBreak(boolean activeBreak) {
        this.activeBreak = activeBreak;
    }

    public void setFailedToAdvance(boolean failedToAdvance) {
        this.failedToAdvance = failedToAdvance;
    }

    @Override
    public void setProgram(Program program) {
        super.setProgram(program);
        condition.setProgram(program);
        bodyIf.setProgram(program);
        if (bodyElse != null) bodyElse.setProgram(program);
    }

}
