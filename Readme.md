FRC2014_Main_Robot
==================

This repository is for development of the 2014 Robot for the FIRST Robotics Competition.
It is written in Java and uses the Command Based Robot framework.

##Subsystems
The following sections identify the subsystems on the 2014 robot; what they do and with what sensors/actuators. This list will evolve as the season progresses. The bullets below each subsystem lists the relevant inputs/outputs it has.

###Drivetrain
The drivetrain has a total of 6 CIM motors, 3 on each side. Each is driven by a Talon motor controller. Assume that each set of three controllers is drive from a single PWM channel with a three way PWM Y cable.
Each side of the drive train has a single gear box. Attached directly to the output shaft of the gear box is a single 256 tick/rev encoder.
The transmission/wheel gear ratio is 27/24. Wheels are nominally 3" diameter.
* Two Talon motor controllers
* Two Encoders

A gyro will be available onboard the robot for rotational position measurements.
* One Gyro

###Catapult
The catapult subsystem will be responsible for firing the ball into goals. This mechanism will be implemented as multiple subsystems to allow for concurrent operation of its components.

####Catapult_Winch
The winch will be responsible for reloading the catapult and causing it to fire.
This subsystem consists of two gear boxes attached to a drum. The drum is conencted to a ratchet which will only allow it to rotate in one direction (reload). A strap connected to the back of the catapult is wound around the drum to draw it down.

The winch is driven by two 550 motors going to 100:1 planetary gear boxes. Assume one Talon motor controller per gear box connected to a single PWM channel through a Y cable. The winch is instrumented with a 256 tick/rev encoder. Assume the encoder is directly coupled to the drum: 1 rev of drum = 256 ticks. Note that the encoder becomes decoupled from the drum when fired. There is also a potentiometer mounted at the point of rotation of the catapult. It is always coupled to the catapult and is used to monitor the angle of the catapult to aid in stepping through the firing sequence.
* One Encoder
* One Talon Motor Controller
* One Potentiometer

A limit switch (digital input) is available to detect the zero/home position of the catapult.
* One Digital Input

This subsystem also includes the firing mechanism, a dog en/disengages the drum from the ratchet.  Assume this is controlled by a single pneumatic actuator.
* One double solenoid.

####Catapult_Tusks
This subsystem controlls the exit angle of the ball when it is fired from the catapult. This will be implemented as a multi-position mechanism at the back of the catapult. It will have three positions. The three positions are: Long range, Short range, and Truss shot (in order from fully extended to fully retracted).
* Two double solenoid.

####Catapult_Arrest
This mechanism is responsible for the position at which the catapult stops when it is fired. It is also responsible for disipating the shock load experienced by the robot when the catapult is fired. It is composed of a fixed length strap and rubber block acting as a damper. It has evolved into a completely mechanical device and as such does not need to be represented in our code.


###Intake
The intake will be responsible for picking up/passing balls into/out of our robot. This mechanism will be implemented as multiple subsystems to allow for concurrent operation of its components.

####Intake_Rollers
The rollers/wheels on the intake will make it easier for the driver to pick up balls. Rollers are arranged on the rear face of the intake. The rear face of the intake will run off of two 775? motors off their own dedicated motor controllers, connected by a PWM Y cable. Motor polarity will be handled in hardware as necessary.
* One Talon Motor Controller

####Intake_Position
The vertical position of the intake will be controlled by pneumatics. Two pneumatic actuators on oppoosite sides of the robot (left/right) will be responsible for raising/lowering the intake. 
* One Double Solenoid

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
