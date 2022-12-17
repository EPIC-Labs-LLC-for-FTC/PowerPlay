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
        if (target == 1 && slider.getCurrentPosition() != 500){
            slider.setTargetPosition(100);
            slider.setPower(0.7);
        }

        if (target == 2 && slider.getCurrentPosition() != 1000){
            slider.setTargetPosition(100);
            slider.setPower(0.7);
        }

        if (target == 3  && slider.getCurrentPosition() != 1500){
            slider.setTargetPosition(100);
            slider.setPower(0.7);
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

}
