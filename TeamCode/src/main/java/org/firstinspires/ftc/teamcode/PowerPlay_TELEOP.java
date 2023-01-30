package org.firstinspires.ftc.teamcode;

import android.util.Range;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Mecanum_Wheels;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Slider;


@TeleOp(name = "PowerPlay_TELEOP")
public class PowerPlay_TELEOP extends LinearOpMode {
    double lefty;
    double leftx;
    double righty;
    double lefty2;
    double rightx;
    double distance;

    double armGround = 0;
    double armDropping = 0.439;

    double wristGround = 0.45;
    double wristDropping = 0.64;

    public Slider slide1;

    public Servo arm1;
    public Servo arm2;
    public Servo outtakeArm;
    public Servo finger1;
    public Servo finger2;
    public Servo wrist1;
    public Servo wrist2;
    public DistanceSensor distanceSensor;

    int sliderPos = 0;


    @Override
    public void runOpMode() throws InterruptedException {
        Mecanum_Wheels wheels = new Mecanum_Wheels(hardwareMap);
        finger1 = hardwareMap.get(Servo.class, "fingerRight");
        finger2 = hardwareMap.get(Servo.class, "fingerLeft");
        wrist1 = hardwareMap.get(Servo.class, "wristRight");
        wrist2 = hardwareMap.get(Servo.class, "wristLeft");
        outtakeArm= hardwareMap.get(Servo.class, "outtakeArm");
        arm1 = hardwareMap.get(Servo.class,"armRight");
        arm2 = hardwareMap.get(Servo.class, "armLeft");
        distanceSensor = hardwareMap.get(DistanceSensor.class,"distance");


        wheels.initialize();
        wheels.telemetry = telemetry;
        wheels.parent = this;
        wheels.leftErrorAdjustment = 0.72;
        wheels.rightErrorAdjustment = 0.72;

        wrist2.setDirection(Servo.Direction.REVERSE);
        finger2.setDirection(Servo.Direction.REVERSE);
        arm2.setDirection(Servo.Direction.REVERSE);
//        outtakeArm.setDirection(Servo.Direction.REVERSE);

        //init position for outtake arm
        outtakeArm.setPosition(0.441);

        //

        slide1 = new Slider(hardwareMap);
        slide1.parent = this;
        slide1.telemetry = telemetry;
        slide1.initialize();


        wrist1.setPosition(0.6);
        wrist2.setPosition(0.6);


        arm1.setPosition(armDropping);
        arm2.setPosition(armDropping);



        waitForStart();
        while(opModeIsActive()){
            distance = distanceSensor.getDistance(DistanceUnit.INCH);
            lefty = gamepad1.left_stick_y;
            leftx = gamepad1.left_stick_x;
            righty = gamepad1.right_stick_y;
            rightx = -gamepad1.right_stick_x;
            lefty2 = gamepad2.left_stick_y;
            boolean b = gamepad1.b;
            boolean x = gamepad1.x;
            boolean a = gamepad1.a;
            boolean y = gamepad1.y;
            boolean dpadUp = gamepad1.dpad_up;
            boolean dpadDown = gamepad1.dpad_down;
            boolean dpadRight = gamepad1.dpad_right;
            boolean b2 = gamepad2.b;
            boolean x2 = gamepad2.x;
            boolean a2 = gamepad2.a;
            boolean y2 = gamepad2.y;
            boolean dpadUp2 = gamepad2.dpad_up;
            boolean dpadDown2 = gamepad2.dpad_down;
            boolean dpadRight2 = gamepad2.dpad_right;
            boolean dpadLeft2 = gamepad2.dpad_left;
            boolean leftBumper2 = gamepad2.left_bumper;
            boolean rightBumper2 = gamepad2.right_bumper;

            if(a){
                wheels.leftErrorAdjustment = 0.3;
                wheels.rightErrorAdjustment = 0.3;
            }
            if(y){
                wheels.leftErrorAdjustment = 0.72;
                wheels.rightErrorAdjustment = 0.72;
            }
            if(y2){
                finger1.setPosition(0.16);
                finger2.setPosition(0.16);
            }
            else if(x2){
                finger1.setPosition(0.56);
                finger2.setPosition(0.41);
            }
            if(a2){
                outtakeArm.setPosition(0.442);
            }
            if(b2){
                outtakeArm.setPosition(1);
            }
            if(leftBumper2){
                arm1.setPosition(armGround);
                arm2.setPosition(armGround);
                wrist1.setPosition(wristGround);
                wrist2.setPosition(wristGround);

            }
            if(rightBumper2){
                arm1.setPosition(armDropping);
                arm2.setPosition(armDropping);
                wrist1.setPosition(wristDropping);
                wrist2.setPosition(wristDropping);
                sleep(1000);
            }
            if(dpadDown2){
                arm1.setPosition(armGround);
                arm2.setPosition(armGround);
                wrist1.setPosition(wristGround);
                wrist2.setPosition(wristGround);
                outtakeArm.setPosition(0.439);
                slide1.Forward();
                slide1.encoderDrive(1,-20, 1);
                //slide1.move(1);
                sleep(1000);

            }
            if(dpadRight2){
                distance = distanceSensor.getDistance(DistanceUnit.INCH);
                arm1.setPosition(armDropping);
                arm2.setPosition(armDropping);
                wrist1.setPosition(wristDropping);
                wrist2.setPosition(wristDropping);
                sleep(1000);
                finger1.setPosition(0.16);
                finger2.setPosition(0.16);
                sleep(200);
                sliderPos = 2000;
                slide1.Reverse();
                slide1.encoderDrive(1,sliderPos,2);
                slide1.setZeroPower();


            }

            if(dpadUp2){
                distance = distanceSensor.getDistance(DistanceUnit.INCH);
                arm1.setPosition(armDropping);
                arm2.setPosition(armDropping);
                wrist1.setPosition(wristDropping);
                wrist2.setPosition(wristDropping);
                sleep(1000);
                finger1.setPosition(0.16);
                finger2.setPosition(0.16);
                sleep(200);
                sliderPos = 1000;
                slide1.Reverse();
                slide1.encoderDrive(1,sliderPos,2);

            }
            if(dpadLeft2){
                arm1.setPosition(armDropping);
                arm2.setPosition(armDropping);
                wrist1.setPosition(wristDropping);
                wrist2.setPosition(wristDropping);
                sleep(1000);
                finger1.setPosition(0.16);
                finger2.setPosition(0.16);
                sleep(200);
            }


            wheels.move(lefty,righty,leftx,rightx);
            distance = distanceSensor.getDistance(DistanceUnit.INCH);
            telemetry.addData("distance: ", distance);
            telemetry.addData("slide 1 current positon:", slide1.getCurrentPosition());
            telemetry.addData("slide 1 current positon:", slide1.getCurrentPosition());
            telemetry.addData("outtake arm position: ", outtakeArm.getPosition());
            telemetry.addData("finger1 position: ", finger1.getPosition());
            telemetry.addData("finger2 position: ", finger2.getPosition());
            telemetry.addData("wrist1 position: ", wrist1.getPosition());
            telemetry.addData("wrist2 position: ", wrist2.getPosition());
            telemetry.addData("arm1 position: ", arm1.getPosition());
            telemetry.addData("arm2 position: ", arm2.getPosition());
            telemetry.addData("lefty2: ", lefty2);


            telemetry.update();


        }



    }
}
