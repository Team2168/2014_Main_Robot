package org.team2168.commands.auto;

public class Center_RotHotGoal_2Ball extends AutoCommandGroup{ 

	public Center_RotHotGoal_2Ball() {
		this(1.5); //default 1.5 second delay
	}
	
	public Center_RotHotGoal_2Ball(double firstHotGoalTimeOut) {
		//wait for hot goal, assume camera is facing right hot goal
		addSequential(new Center_RotHotGoal_1Ball(firstHotGoalTimeOut));
		
		//do stuff to get second ball and fire
	}
	
	public int numBalls() {
		return 2;
	}
}