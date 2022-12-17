package org.firstinspires.ftc.teamcode.drive.opmode.RobotObjects.EPIC;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Armclaw {
    public Servo Arm;
    public Servo Grab;

    public double GrabMin = 0;
    public double GrabMax = 1;
    public double Armgrab = 0.2;
    public double Armlow = -0.4;
    public double Armmid = -0.8;

    public double liftPower = -0.2;
    public LinearOpMode parent;
    public Telemetry telemetry;
    public double pos = 0.0;

    public Armclaw(HardwareMap hardwareMap) {
        Arm = hardwareMap.get(Servo.class, "arm");
        Grab = hardwareMap.get(Servo.class, "grab");

        Grab.setPosition(GrabMax);
    }



}
