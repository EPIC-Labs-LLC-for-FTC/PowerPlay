package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Mecanum_Wheels;

@TeleOp(name = "PID_Distance")
public class PID_Distance extends LinearOpMode{
    Mecanum_Wheels wheels = new Mecanum_Wheels(hardwareMap);

    @Override
    public void runOpMode() throws InterruptedException {
        wheels.initialize();
        wheels.telemetry = telemetry;
        wheels.parent = this;
        wheels.leftErrorAdjustment = 0.72;
        wheels.rightErrorAdjustment = 0.72;

    }
}
