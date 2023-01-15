package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Mecanum_Wheels;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Scanner;
import org.openftc.easyopencv.OpenCvCamera;

@Autonomous(name = "PowerPlayParkingAuton")
public class POWERPLAY_Right_ParkingAuton extends LinearOpMode {
    OpenCvCamera webcam;
    Scanner scanner;
    public DcMotorEx slide1;

    public Servo arm1;
    public Servo arm2;
    public Servo outtakeArm;
    public Servo finger1;
    public Servo finger2;
    public Servo wrist1;
    public Servo wrist2;
    @Override
    public void runOpMode() throws InterruptedException {
        double distance = 0;
        scanner = new Scanner(hardwareMap);
        scanner.telemetry = this.telemetry;
        scanner.parent = this;
        scanner.initialize();
        Mecanum_Wheels mecanum = new Mecanum_Wheels(hardwareMap);
        mecanum.leftErrorAdjustment = 1;
        mecanum.parent = this;
        mecanum.IsAutonomous = true;
        mecanum.velocity = 400;
        mecanum.telemetry = this.telemetry;
        mecanum.initialize();
        finger1 = hardwareMap.get(Servo.class, "fingerRight");
        finger2 = hardwareMap.get(Servo.class, "fingerLeft");
        wrist1 = hardwareMap.get(Servo.class, "wristRight");
        wrist2 = hardwareMap.get(Servo.class, "wristLeft");
        outtakeArm= hardwareMap.get(Servo.class, "outtakeArm");
        arm1 = hardwareMap.get(Servo.class,"armRight");
        arm2 = hardwareMap.get(Servo.class, "armLeft");

        wrist2.setDirection(Servo.Direction.REVERSE);
        finger2.setDirection(Servo.Direction.REVERSE);
        arm2.setDirection(Servo.Direction.REVERSE);

        slide1 = hardwareMap.get(DcMotorEx.class, "slideRight");

        slide1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        slide1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        slide1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        wrist1.setPosition(0.3);
        wrist2.setPosition(0.3);

//        outtakeArm.setPosition(0.11);
        arm1.setPosition(0.44);
        arm2.setPosition(0.44);
        int parkingSpot = 0;
        parkingSpot = scanner.getParkingSpot();
        scanner.releaseCamera();

        waitForStart();
        sleep(100);
        //forward
        distance = 51;
        mecanum.encoderDrive(0.8,distance,distance,distance,distance,2);



        if(parkingSpot==3){
            //strafe right
            distance = 46;
            mecanum.encoderDrive(0.8,distance,-distance,-distance,distance,2);

        }
        if(parkingSpot==2){

        }
        if(parkingSpot==1){
            //strafe left
            distance = 51;
            mecanum.encoderDrive(0.8,-distance,distance,distance,-distance,2);
        }









    }
    private void raiseSlide(int index){
        slide1.setPower(0.8);
        slide1.setTargetPosition(index);
        slide1.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }
}
