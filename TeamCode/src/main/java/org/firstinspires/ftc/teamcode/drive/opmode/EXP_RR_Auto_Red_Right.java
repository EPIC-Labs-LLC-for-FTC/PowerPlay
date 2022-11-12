package org.firstinspires.ftc.teamcode.drive.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous (name="EXP_RR_Auto_Red_Right")
public class EXP_RR_Auto_Red_Right extends LinearOpMode {
    @Override
    public void runOpMode() {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        waitForStart();
        TrajectorySequence myTrajectory = drive.trajectorySequenceBuilder(new Pose2d())
                .forward(74)
                .turn(Math.toRadians(60))
                //Place Cone
                .turn(Math.toRadians(-138))
                .forward(20)
                //Grab Cone
                .back(20)
                .turn(Math.toRadians(-55))
                //place cone
                .turn(Math.toRadians(55))
                .forward(20)
                //Grab Cone
                .back(20)
                .turn(Math.toRadians(-55))
                //place cone
                .turn(Math.toRadians(55))
                .forward(20)
                //Grab Cone
                .back(20)
                .turn(Math.toRadians(-55))
                //place cone
                .turn(Math.toRadians(55))
                .forward(20)
                //Grab Cone
                .back(20)
                .turn(Math.toRadians(-55))
                //place cone
                .turn(Math.toRadians(55))
                .forward(20)
                //Grab Cone
                .back(20)
                .turn(Math.toRadians(-55))
                .build();

        waitForStart();

        if(isStopRequested()) return;

        drive.followTrajectorySequence(myTrajectory);
    }
}
