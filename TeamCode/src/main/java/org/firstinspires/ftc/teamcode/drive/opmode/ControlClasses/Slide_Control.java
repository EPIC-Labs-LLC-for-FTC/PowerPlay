package org.firstinspires.ftc.teamcode.drive.opmode.ControlClasses;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Slide_Control {
    public DcMotorEx slider;
    public Slide_Control(HardwareMap hardwareMap){
        slider = hardwareMap.get(DcMotorEx.class, "slider");
        slider.setPower(0);
    }
    
    public void initialize() {
        slider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slider.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void retract(){
        slider.setPower(1);
    }

    public void lift(int target) {
        if (target == 1){
            slider.setPower(0.7);
        }

        if(target == 2){
            slider.setPower(0.4);
        }

        if(target == 3){
            slider.setPower(0);
        }
       
    }

    public double sliderPosition(){
        return slider.getPower();
    }
    
    
}
