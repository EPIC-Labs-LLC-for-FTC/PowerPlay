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
    public DcMotorEx outtakeSlide;
    public Telemetry telemetry;
    public LinearOpMode parent;
    private double extended1 = 1;
    private double extended2 = 0;
    private double home1 = 0.2;
    private double home2 = 0.8;


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
    public void in(){
        intakeSlide1.setPosition(home1);
        intakeSlide2.setPosition(home2);
    }
}
