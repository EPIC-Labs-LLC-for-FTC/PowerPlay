package org.firstinspires.ftc.teamcode.RobotObjects.EPIC;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class New_Slide_Intake {
    public Servo finger;
    public Servo arm;
    public Servo outtake;
    public Servo turret;
    public DcMotorEx intakeSlide;
    public DcMotorEx outtakeSlide;

    private double fingeropen;
    private double fingerclose;

    private double stack5;
    private double stack4;
    private double stack3;
    private double stack2;
    private double ground;

    private int tallJunction;
    private int mediumJunction;
    private int smallJunction;

    private double recieving;
    private double dropping;

    public LinearOpMode parent;
    private ElapsedTime runtime = new ElapsedTime();

    public Telemetry telemetry;

    public New_Slide_Intake(HardwareMap hardwareMap) {
        intakeSlide = hardwareMap.get(DcMotorEx.class,"intakeSlide");
        outtakeSlide = hardwareMap.get(DcMotorEx.class,"outtakeSlide");
        finger = hardwareMap.get(Servo.class,"finger");
        arm = hardwareMap.get(Servo.class, "arm");
        outtake = hardwareMap.get(Servo.class, "outtake");
        turret = hardwareMap.get(Servo.class, "turret");

    }
}
