package org.firstinspires.ftc.teamcode.Research;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Mecanum_Wheels;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequenceBuilder;

public class AutonTestPractice extends OpMode {

    @Override
    public void init() {

    }

    @Override
    public void loop() {
        //just a practice thingy
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Trajectory Auton = drive.trajectoryBuilder(new Pose2d(35,35))
                .forward(36)
                .splineTo(new Vector2d(45,43),Math.toRadians(90))
                .splineToSplineHeading(new Pose2d(40, 40, Math.toRadians(90)), Math.toRadians(0))
                .build();

    }
}
