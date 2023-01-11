package org.firstinspires.ftc.teamcode.drive.opmode.ControlClasses;


import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw {

    //public Servo claw, arm;

    public Servo claw;

    public CRServo arm, align;

    public Claw(HardwareMap hardwareMap){
        claw = hardwareMap.get(Servo.class, "claw");
        arm = hardwareMap.get(CRServo.class, "arm");
        align = hardwareMap.get(CRServo.class, "clawalign");

    }

    public void initialize(){
        claw.setPosition(0);
//        arm.setPosition(0);
        arm.setPower(0);
        align.setPower(0);
    }

    public void open(){
        claw.setPosition(1);
    }

    public void close(){
        claw.setPosition(0.5);
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

    public void alignment(double amount) {align.setPower(amount);}

    public double armPosition(){
//        return arm.setPosition(0);
        return arm.getPower();
    }

}