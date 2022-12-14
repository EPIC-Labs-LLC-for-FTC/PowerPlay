package org.firstinspires.ftc.teamcode.drive.opmode.ControlClasses;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw {

    public Servo claw, arm1, arm2;

    public Claw(HardwareMap hardwareMap){
        claw = hardwareMap.get(Servo.class, "claw");
        arm1 = hardwareMap.get(Servo.class, "arm1");
        arm2 = hardwareMap.get(Servo.class, "arm2");
    }

    public void initialize(){
        claw.setPosition(0.5);
        arm1.setPosition(0);
        arm2.setPosition(0);
    }

    public void open(){
        claw.setPosition(0);
    }

    public void close(){
        claw.setPosition(0.5);
    }

    public void lift(int target){
        if (target == 1){
            arm1.setPosition(0.3);
            arm2.setPosition(0.3);
        }

        if (target == 2){
            arm1.setPosition(0.1);
            arm2.setPosition(0.1);
        }
        
    }

    public void specificLift(double amount){
        arm1.setPosition(amount);
        arm2.setPosition(amount);
    }

    public double armPosition(){
        return arm1.getPosition();
    }

}