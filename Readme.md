FRC2014_Main_Robot
==================

This repository is for development of the 2014 Robot for the FIRST Robotics Competition.
It is written in Java and uses the Command Based Robot framework.

##Subsystems
The following sections identify the notional subsystems on the 2014 robot; what they do and with what sensors/actuators. This list will evolve as the season progresses.

###Drivetrain
The drivetrain has a total of 6 CIM motors, 3 on each side. Each is driven by a Talon motor controller. Assume that each set of three controllers is drive from a single PWM channel with a three way PWM Y cable.
Each side of the drive train has a single gear box. Attached directly to the output shaft of the gear box is a single 256 tick/rev encoder.
The transmission/wheel gear ratio is 27/24. Wheels are nominally 3" diameter.

A gyro will be available onboard the robot for rotational position measurements.

###Catapult
The catapult subsystem will be responsible for firing the goal into goals. This mechanism will be implemented as multiple subsystems to allow for concurrent operation of its components.

####Catapult_Winch
The winch will control the angle of the catapult prior to it being fired. This will control the amount of force put into the ball when it is fired. It is also responsible for positioning the catapult such that it is read for a ball to be loaded in from the intake.

This subsystem consists of a gear box attached to a drum. A strap connected to the back of the catapult is wound around the drum to draw it down.

The winch consists of a gear box, driven by a mini-CIM motor. Assume one Talon motor controller. The angle of the catapult is instrumented with a 256 tick/rev encoder. It isn't clear yet how this encoder will be coupled to the catapult, so ratios are unknown at this time.

A limit switch (digital input) will likey need to be added to detect the zero/home position of the catapult at the beginning of a match.

This subsystem also includes the firing mechanism. Assume this is controlled by a single pneumatic actuator. One double solenoid.

####Catapult_Arrest
This mechanism is responsible for the position at which the catapult stops when it is fired. This is controlled by a single pneumatic actuator. Assume one double solenoid controlling the two positions of the actuator.

It is also responsible for disipating the shock load experienced by the robot when the catapult is fired. This is a mechanical function of the device and doesn't require software attention to operate properly.

####Catapult_Pegs
Need a better name for this one.
This subsystem controlls the exit angle of the ball when it is fired from the catapult. This will be implemented as a two position mechanism at the back of the catapult. Assume one double solenoid.

###Intake
The intake will be responsible for picking up/passing balls into/out of our robot. This mechanism will be implemented as multiple subsystems to allow for concurrent operation of its components.

####Intake_Rollers
The rollers on the intake will make it easier for the driver to pick up balls. The rollers on the intake are arranged on three faces, such that there are wheels facing to the left, right, and back of the robot.

Each face of the intake is driven by its own dedicated motor.
* Right and left run off a 550 or bag motor. They will either be both controlled from a single motor controller or each run their own dedicated controller (assume one Talons - if we need two controllers, will use PWM Y cable).
* The rear face of the intake will run off od one 550 or bag motor off it's own dedicated motor controller (assume one Talon).

If we get strapped for PWM outputs we could run all three sides from a single PWM output channel.

####Intake_Position
The vertical position of the intake will be controlled by pneumatics. Likely two pneumatic cylinders, back to back, giving the intake three possible positions it could be in. Assume two double solenoid valves controlling these actuators.

###Vision
The vision subsystem will consist of a camera with a beaglebone processing images. The beaglebone will communicate, through TCP messages, the results of it's image processing to the cRIO. Data communicated through this subsystem will likely consist of distance and angle information to the nearest goal, and whether or not it is "hot".

##Environment Setup
After cloning the repo and importing the project in eclipse, follow the steps below to configure your build path.
<ol>
<li>Right click on the project folder, Build Path > Configure Build Path.</li>
<li>Click the "Add External JARs" button.</li>
<li>Browse to the directory which you extracted the sunspotfrcsdk folder to. e.g "C:\Users\Jim\sunspotfrcsdk\lib".</li>
<li>Select the networktables-crio.jar, squak.jar, and wpilibj.jar files and click "Open".</li>
<li>Click "OK" to close the "Build Path" window.</li>
</ol>
