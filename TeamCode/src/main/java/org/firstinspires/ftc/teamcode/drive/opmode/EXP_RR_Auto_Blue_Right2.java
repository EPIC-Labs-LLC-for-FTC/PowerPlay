package org.firstinspires.ftc.teamcode.drive.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.drive.opmode.ControlClasses.Claw;
import org.firstinspires.ftc.teamcode.drive.opmode.ControlClasses.Slide_Control;
import org.firstinspires.ftc.teamcode.drive.opmode.RobotObjects.EPIC.Mecanum_Wheels;
import org.firstinspires.ftc.teamcode.drive.opmode.RobotObjects.EPIC.ScanSleeve;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous (name="EXP_RR_Auto_Blue_Right2")
public class EXP_RR_Auto_Blue_Right2 extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {

        Slide_Control slideControl = new Slide_Control(hardwareMap);
        Claw armClaw = new Claw(hardwareMap);
        slideControl.initialize();
        armClaw.initialize();

        waitForStart();

            armClaw.specificLift(1);
            sleep(1000);
            armClaw.stop();



    }
}
