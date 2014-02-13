package org.team2168.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Execute a set of commands conditionally.
 *
 * Example usage:
 * addSequential(new Conditional(<Command for true>,<Command for false>) {
 *     protected boolean condition() {
 *         return <condition>;
 *     }
 * });
 *
 * Thanks FRC2729. http://goo.gl/tWB4jl
 */
public abstract class Conditional extends Command {
    private final Command _ifTrue,_ifFalse;
    private Command _running = null;

    public Conditional(Command ifTrue,Command ifFalse) {
        _ifTrue = ifTrue;
        _ifFalse = ifFalse;
    }
    
    protected abstract boolean condition();

    protected void initialize() {
        if(condition()) {
            _running = _ifTrue;
        } else {
            _running = _ifFalse;
        }
        if(_running != null) {
            _running.start();
        }
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return _running == null || !_running.isRunning();
    }

    protected void end() {
        if(_running != null && _running.isRunning()) {
            _running.cancel();
        }
    }

    protected void interrupted() {
        end();
    }
}
