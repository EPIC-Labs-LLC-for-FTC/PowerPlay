package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Outtake2023;

@TeleOp(name = "TestOuttake")
public class TestOuttake extends LinearOpMode{

    public Outtake2023 outtake;

    @Override
    public void runOpMode() throws InterruptedException {
        outtake = new Outtake2023(hardwareMap);
        outtake.telemetry = telemetry;
        outtake.parent = this;
        outtake.drop();
        waitForStart();
        while(opModeIsActive()){
        outtake.recieve();
        sleep(2000);
        outtake.drop();
        sleep(2000);

    }
}}
