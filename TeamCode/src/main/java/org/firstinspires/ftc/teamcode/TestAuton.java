package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name = "TestAuton")

public class TestAuton extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        TrajectorySequence traj1 = drive.trajectorySequenceBuilder(new Pose2d(-36, 59.4, Math.toRadians(270)))
                .forward(30)
                .splineTo(new Vector2d(-30,6),Math.toRadians(-35))
                .build();

        TrajectorySequence cycle =drive.trajectorySequenceBuilder(new Pose2d(-30,6,Math.toRadians(-35)))
                .setReversed(true)
                .splineTo(new Vector2d(-60, 12.2),Math.toRadians(-180))
                .setReversed(false)
                .splineTo(new Vector2d(-30,6),Math.toRadians(-35))
                .build();


        drive.followTrajectorySequence(traj1);

        for (int i = 0; i < 5; i++) {
            drive.followTrajectorySequence(cycle);
        }





    }
}
