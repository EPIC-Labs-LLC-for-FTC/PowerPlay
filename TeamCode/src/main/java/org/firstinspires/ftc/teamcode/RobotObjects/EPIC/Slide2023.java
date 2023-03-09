package org.firstinspires.ftc.teamcode.RobotObjects.EPIC;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Slide2023 {
    public DcMotorEx intakeSlide;
    public DcMotorEx outtakeSlide;
    public Telemetry telemetry;
    public LinearOpMode parent;


    public Slide2023(HardwareMap hardwareMap){
        intakeSlide = hardwareMap.get(DcMotorEx.class,"intakeSlide");
        outtakeSlide = hardwareMap.get(DcMotorEx.class,"outtakeSlide");
        intakeSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        outtakeSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        intakeSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        outtakeSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intakeSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        outtakeSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void outtakeSet(int position){
        outtakeSlide.setTargetPosition(position);
        outtakeSlide.setPower(0.8);
        outtakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }
    public void intakeSet(int position){
        intakeSlide.setTargetPosition(position);
        intakeSlide.setPower(0.8);
        intakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
