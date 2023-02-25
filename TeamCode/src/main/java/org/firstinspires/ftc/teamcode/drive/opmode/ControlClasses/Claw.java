package org.firstinspires.ftc.teamcode.drive.opmode.ControlClasses;


import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Claw {

    //public Servo claw, arm;

    public Servo claw;

    public DistanceSensor leftDistance, rightDistance, front2Distance, frontDistance;

    public CRServo arm;

    public Claw(HardwareMap hardwareMap){
        claw = hardwareMap.get(Servo.class, "claw");
        arm = hardwareMap.get(CRServo.class, "arm");
        leftDistance = hardwareMap.get(DistanceSensor.class,"leftDistance");
        rightDistance = hardwareMap.get(DistanceSensor.class,"rightDistance");
        front2Distance = hardwareMap.get(DistanceSensor.class,"front2Distance");
        frontDistance = hardwareMap.get(DistanceSensor.class,"frontDistance");

    }

    public void initialize(){
        claw.setPosition(0);
//        arm.setPosition(0);
        arm.setPower(-0.3);
    }

    public void open(){
        claw.setPosition(1);
    }

    public void close(){
        claw.setPosition(0);
    }

    public void lift(int target){
//        if (target == 1){
//            arm.setPosition(0.3);
//        }
//
//        if (target == 2){
//            arm.setPosition(0.1);
//        }

    }

    public void stop() { arm.setPower(0); }

    public void specificLift(double amount){
        arm.setPower(amount);
    }

    public void clawControl(double amount) { claw.setPosition(amount);}


    public double armPosition(){
//        return arm.setPosition(0);
        return arm.getPower();
    }

    public double getDistance(DistanceSensor sensor){
        return sensor.getDistance(DistanceUnit.INCH);
    }

}