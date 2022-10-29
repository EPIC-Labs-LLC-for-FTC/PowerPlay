package org.firstinspires.ftc.teamcode.drive.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous (name="EXP_RR_Auto_Blue_Left")
public class EXP_RR_Auto_Blue_Left extends LinearOpMode {
    @Override
    public void runOpMode() {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        TrajectorySequence EXP_Auto_Red_Left = drive.trajectorySequenceBuilder(new Pose2d())
                //go to highest junction
                .forward(59)
                .waitSeconds(0.1)
                .turn(Math.toRadians(-84))
                //put cone
                .waitSeconds(1)
                //go to other cones
                .turn(Math.toRadians(180))
                .waitSeconds(0.1)
                .forward(10)
                //get cone
                .waitSeconds(1)
                //go back to junction
                .turn(Math.toRadians(180))
                .waitSeconds(0.1)
                .waitSeconds(1)
                //repeat getting and placing cone
/*
                                //repeat 1
                                .turn(Math.toRadians(180))
                                .strafeTo(new Vector2d(-57, -12))
                                //get cone
                                .waitSeconds(1)
                                //go back to junction
                                .turn(Math.toRadians(180))
                                .strafeTo(new Vector2d(-32, 0))
                                .waitSeconds(1)

                                //repeat 2
                                .turn(Math.toRadians(180))
                                .strafeTo(new Vector2d(-57, -12))
                                //get cone
                                .waitSeconds(1)
                                //go back to junction
                                .turn(Math.toRadians(180))
                                .strafeTo(new Vector2d(-32, 0))
                                .waitSeconds(1)*/

                //park
                .back(5)
                .waitSeconds(0.1)
                .splineTo(new Vector2d(0, 0), Math.toRadians(0))
                .build();

        waitForStart();

        if(isStopRequested()) return;

        drive.followTrajectorySequence(EXP_Auto_Red_Left);
    }
}
