package org.firstinspires.ftc.teamcode.RobotObjects.EPIC;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IntakeSlide2023 {
    public ServoImplEx intakeSlide1;
    public ServoImplEx intakeSlide2;
    public Telemetry telemetry;
    public LinearOpMode parent;
    private double extended1 = 0;
    private double extended2 = 1;
    private double extendedAuton1 = 0.07;
    private double extendedAuton2 = 0.93;
    private double home1 = 0.9;
    private double home2 = 0.1;


    public IntakeSlide2023(HardwareMap hardwareMap){
        intakeSlide1 = hardwareMap.get(ServoImplEx.class,"intakeSlide1");
        intakeSlide2 = hardwareMap.get(ServoImplEx.class,"intakeSlide2");
        intakeSlide1.setDirection(Servo.Direction.REVERSE);
        intakeSlide2.setDirection(Servo.Direction.FORWARD);
    }
    public void out(){
        intakeSlide1.setPosition(extended1);
        intakeSlide2.setPosition(extended2);
    }

    public void out(boolean op){
        while(parent.opModeIsActive() && (intakeSlide1.getPosition()!=extended1 || intakeSlide2.getPosition()!=extended2)) {
            intakeSlide1.setPosition(extended1);
            intakeSlide2.setPosition(extended2);
        }
    }
    public void in(){
        while(parent.opModeIsActive() && (intakeSlide1.getPosition()!=home1 || intakeSlide2.getPosition()!=home2)){
            intakeSlide1.setPosition(home1);
            intakeSlide2.setPosition(home2);
        }
    }

    public void in(boolean op){
        while(parent.opModeIsActive() && (intakeSlide1.getPosition()!=home1 || intakeSlide2.getPosition()!=home2)){
            intakeSlide1.setPosition(home1);
            intakeSlide2.setPosition(home2);
        }
    }
    public void intakeSet(double position){
        intakeSlide1.setPosition(intakeSlide1.getPosition()-position);
        intakeSlide2.setPosition(intakeSlide2.getPosition()+position);
    }
    public void outAuton(){
        intakeSlide1.setPosition(extendedAuton1);
        intakeSlide2.setPosition(extendedAuton2);
    }
    public void outAuton(boolean op){
        while(parent.opModeIsActive() && (intakeSlide1.getPosition()!=extendedAuton1 || intakeSlide2.getPosition()!=extendedAuton2)) {
            intakeSlide1.setPosition(extendedAuton1);
            intakeSlide2.setPosition(extendedAuton2);
        }
    }

}
