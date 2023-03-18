package org.firstinspires.ftc.teamcode.RobotObjects.EPIC;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class OuttakeSlide2023 {
    public DcMotorEx outtakeSlide;
    public Telemetry telemetry;
    public LinearOpMode parent;
    private double extended = 1;
    private double home = 0.2;


    public OuttakeSlide2023(HardwareMap hardwareMap){
        outtakeSlide = hardwareMap.get(DcMotorEx.class,"outtakeSlide");
        outtakeSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        outtakeSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        outtakeSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        outtakeSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void set(int position){
        outtakeSlide.setTargetPosition(position);
        outtakeSlide.setPower(0.8);
        outtakeSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
