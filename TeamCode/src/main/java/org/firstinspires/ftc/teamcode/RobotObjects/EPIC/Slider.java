package org.firstinspires.ftc.teamcode.RobotObjects.EPIC;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.Servo;

public class Slider {
    //Configuration used: 6wheelConfig
    public DcMotorEx slider;
    public Servo wrist1;
    public Servo wrist2;
    public Servo arm1;
    public Servo arm2;
    public Servo finger1;
    public Servo finger2;
    public Servo outtakeArm;

    double backcorrection = 1.5;
    double armGround = 0;
    double armDropping = 0.439;

    double wristGround = 0.45;
    double wristDropping = 0.68;

    double fingerOpen = 0.16;
    double fingerClose1 = 0.56;
    double fingerClose2 = 0.41;

    //public DcMotorEx xRail;

    public boolean IsAutonomous = false;

    public double leftErrorAdjustment = 1.0;
    public double rightErrorAdjustment = 1.0;

    public double mecanumWheelCircumference = 12; //inches
    public double omniWheelCircumference = 12; //inches



    public LinearOpMode parent;

    public int velocity = 200;

    private ElapsedTime runtime = new ElapsedTime();

    public Telemetry telemetry;

    public Slider(HardwareMap hardwareMap) {
        slider = hardwareMap.get(DcMotorEx.class,"slideRight");
        finger1 = hardwareMap.get(Servo.class, "fingerRight");
        finger2 = hardwareMap.get(Servo.class, "fingerLeft");
        wrist1 = hardwareMap.get(Servo.class, "wristRight");
        wrist2 = hardwareMap.get(Servo.class, "wristLeft");
        outtakeArm= hardwareMap.get(Servo.class, "outtakeArm");
        arm1 = hardwareMap.get(Servo.class,"armRight");
        arm2 = hardwareMap.get(Servo.class, "armLeft");

        //xRail = hardwareMap.get(DcMotorEx.class, "xRail");
    }

    //initialize for TeleOp
    public void initialize() {
        double reset = 0;
        slider.setPower(reset);

        slider.setDirection(DcMotorSimple.Direction.REVERSE);

        slider.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        wrist2.setDirection(Servo.Direction.REVERSE);
        finger2.setDirection(Servo.Direction.REVERSE);
        arm2.setDirection(Servo.Direction.REVERSE);



            slider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            slider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            outtakeArm.setPosition(0.442);
            wrist1.setPosition(0.6);
            wrist2.setPosition(0.6);
            arm1.setPosition(armDropping);
            arm2.setPosition(armDropping);
    }

    public int getCurrentPosition(){
        return slider.getCurrentPosition();
    }

    public void encoderDrive(double speed, int ticks, double timeoutS) {
        // Ensure that the opmode is still active
        if (parent.opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            slider.setTargetPosition(ticks);


            // Turn On RUN_TO_POSITION`
            slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            slider.setPower(speed*rightErrorAdjustment);

            // keep looping while we are still active, and there is time left, and both motors are running.
//            `Thread thSlider = new Thread(() -> {`
                while (parent.opModeIsActive() &&
                        (runtime.seconds() < timeoutS) &&
                        (slider.isBusy())) {
                    // Display it for the driver.
                    telemetry.addData("height: ", ticks);
                    telemetry.addData("slider current position: ", slider.getCurrentPosition());
                    telemetry.update();
                }
//            },"runSlider");
//            thSlider.setPriority(Thread.NORM_PRIORITY);
//            try {
//                thSlider.join();
//            }
//            catch (Exception ex){
//
//            }
//            thSlider.start();
        }
        // Stop all motion;
        slider.setPower(0);


        // Turn off RUN_TO_POSITION
        //slider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        //  sleep(250);   // optional pause after each move

    }

    public void Forward(){
        slider.setDirection(DcMotorSimple.Direction.FORWARD);
    }


    public void Reverse(){
        slider.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void setZeroPower(){slider.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);}
    public void move(double lefty){
        slider.setPower(-lefty*rightErrorAdjustment); // should work same as above

    }
    public void dropCone(double armRecieving, double wristRecieving){
        if (parent.opModeIsActive()) {
            //raise slide
            slider.setTargetPosition(2000);


            slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            runtime.reset();
            slider.setPower(1*rightErrorAdjustment);
            while (parent.opModeIsActive() && (runtime.seconds() < 2) && (slider.isBusy())) {
                telemetry.addData("height: ",2000 );
                telemetry.addData("slider current position: ", slider.getCurrentPosition());
                telemetry.update();
            }
        }
        slider.setPower(0);
        outtakeArm.setPosition(1);
        parent.sleep(1400);
        outtakeArm.setPosition(0.446);
        parent.sleep(800);
        //drop the slide
        if (parent.opModeIsActive()) {
            //raise slide
            slider.setTargetPosition(-20);


            slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            runtime.reset();
            slider.setPower(1*rightErrorAdjustment);
            while (parent.opModeIsActive() && (runtime.seconds() < 1) && (slider.isBusy())) {
                telemetry.addData("height: ",-20 );
                telemetry.addData("slider current position: ", slider.getCurrentPosition());
                telemetry.update();
            }
        }
        arm1.setPosition(armRecieving);
        arm2.setPosition(armRecieving);
        wrist1.setPosition(wristRecieving);
        wrist2.setPosition(wristRecieving);
        finger1.setPosition(fingerOpen);
        finger2.setPosition(fingerOpen);
        parent.sleep(800);
    }
    public void pickUpCone(){
        finger1.setPosition(fingerClose1);
        finger2.setPosition(fingerClose2);
        parent.sleep(750);
        arm1.setPosition(armDropping);
        arm2.setPosition(armDropping);
        wrist1.setPosition(wristDropping);
        wrist2.setPosition(wristDropping);
        parent.sleep(800);
        finger1.setPosition(fingerOpen);
        finger2.setPosition(fingerOpen);
        parent.sleep(750);
    }
}
