package org.firstinspires.ftc.teamcode.drive.opmode.ControlClasses;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Slide_Control {
    public DcMotorEx slider;
    public Slide_Control(HardwareMap hardwareMap){
        slider = hardwareMap.get(DcMotorEx.class, "slider");
        slider.setPower(0);
        slider.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    
    public void initialize() {
        slider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slider.setTargetPosition(0);
        slider.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void retract(){
        slider.setPower(1);
    }

    public void lift(int target) {
        slider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        if (target == 1){
            slider.setTargetPosition(500);
            slider.setPower(1);
        }

        if (target == 2){
            slider.setTargetPosition(1000);
            slider.setPower(1);
        }

        if (target == 3){
            slider.setTargetPosition(100);
            slider.setPower(1);
        }

        if (target == 4){
            slider.setTargetPosition(0);
            slider.setPower(1);
        }
       
    }

    public double sliderPosition(){
        return slider.getCurrentPosition();
    }
    
    public void specificLift(double power) {
        slider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slider.setPower(power);
    }

    public void stop() {
        slider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slider.setPower(0);
    }

}
