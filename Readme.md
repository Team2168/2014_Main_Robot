FRC2014_Main_Robot
==================

This repository is for development of the 2014 Robot for the FIRST Robotics Competition.
It is written in Java and uses the Command Based Robot framework.

##Subsystems
The following sections identify the notional subsystems on the 2014 robot; what they do and with what sensors/actuators. This list will evolve as the season progresses. The bullets below each subsystem lists the relevant inputs/outputs it has.

###Drivetrain
The drivetrain has a total of 6 CIM motors, 3 on each side. Each is driven by a Talon motor controller. Assume that each set of three controllers is drive from a single PWM channel with a three way PWM Y cable.
Each side of the drive train has a single gear box. Attached directly to the output shaft of the gear box is a single 256 tick/rev encoder.
The transmission/wheel gear ratio is 27/24. Wheels are nominally 3" diameter.
* Two Talon motor controllers
* Two Encoders

A gyro will be available onboard the robot for rotational position measurements.
* One Gyro

###Catapult
The catapult subsystem will be responsible for firing the goal into goals. This mechanism will be implemented as multiple subsystems to allow for concurrent operation of its components.

####Catapult_Winch
The winch will control the angle of the catapult prior to it being fired. This will control the amount of force put into the ball when it is fired. It is also responsible for positioning the catapult such that it is read for a ball to be loaded in from the intake.

This subsystem consists of a gear box attached to a drum. A strap connected to the back of the catapult is wound around the drum to draw it down.

The winch consists of a gear box, driven by a mini-CIM motor. Assume one Talon motor controller. The angle of the catapult is instrumented with a 256 tick/rev encoder. It isn't clear yet how this encoder will be coupled to the catapult, so ratios are unknown at this time.
* One Encoder
* One Talon Motor controller

A limit switch (digital input) will likey need to be added to detect the zero/home position of the catapult at the beginning of a match.
* One Digital Input

This subsystem also includes the firing mechanism. Assume this is controlled by a single pneumatic actuator.
* One double solenoid.

####Catapult_Tusks
This subsystem controlls the exit angle of the ball when it is fired from the catapult. This will be implemented as a multi-position mechanism at the back of the catapult. It will have three positions. The three positions are: Long range, Short range, and Truss shot (in order from fully extended to fully retracted).
* Assume two double solenoid.

####Catapult_Arrest
This mechanism is responsible for the position at which the catapult stops when it is fired. It is also responsible for disipating the shock load experienced by the robot when the catapult is fired. It will be composed of a single spring and strap. It has evolved into a completely mechanical device and as such does not need to be represented in our code.


###Intake
The intake will be responsible for picking up/passing balls into/out of our robot. This mechanism will be implemented as multiple subsystems to allow for concurrent operation of its components.

####Intake_Rollers
The rollers on the intake will make it easier for the driver to pick up balls. Rollers are arranged on the rear face of the intake. The rear face of the intake will run off of one 550 or bag motor off it's own dedicated motor controller 
* assume one Talon

If we get strapped for PWM outputs we could run all three sides from a single PWM output channel.

####Intake_Position
The vertical position of the intake will be controlled by pneumatics. A single pneumatic actuator, giving the intake two possible positions it could be in. 
* assume One Double Solenoids

###Vision
The vision subsystem will consist of a camera with a beaglebone processing images. The beaglebone will communicate, through TCP messages, the results of it's image processing to the cRIO. Data communicated through this subsystem will likely consist of distance and angle information to the nearest goal, and whether or not it is "hot".
To allow for the target to stay in frame from all anticipated starting positions, the camera will be mounted on a single servo.
* One servo

##Environment Setup
After cloning the repo and importing the project in eclipse, follow the steps below to configure your build path.
<ol>
<li>Right click on the project folder, Build Path > Configure Build Path.</li>
<li>Click the "Add External JARs" button.</li>
<li>Browse to the directory which you extracted the sunspotfrcsdk folder to. e.g "C:\Users\Jim\sunspotfrcsdk\lib".</li>
<li>Select the networktables-crio.jar, squak.jar, and wpilibj.jar files and click "Open".</li>
<li>Click "OK" to close the "Build Path" window.</li>
</ol>
