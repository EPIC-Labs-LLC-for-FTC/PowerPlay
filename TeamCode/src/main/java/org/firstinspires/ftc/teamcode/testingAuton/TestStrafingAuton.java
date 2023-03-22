package org.firstinspires.ftc.teamcode.testingAuton;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name = "TestStrafinAuton")
@Disabled

public class TestStrafingAuton extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        int x = 0;
        int y = 0;
        int header = 0;

        // Based on the sleeve, side will equal 1 for right and -1 for left
        int side = 1;

        // Header also will be changed
        if (side == 1 ) {header = 90;}
        else {header = 270;}

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        // FORWARD TO JUNCTION and CHANGE HEADING SO OUTTAKE FACES JUNCTION
        TrajectorySequence step1 = drive.trajectorySequenceBuilder(new Pose2d(x, y, Math.toRadians(0)))
                .lineToLinearHeading(new Pose2d(x+100, y, Math.toRadians(header)))
                .build();

        // DROP CONE --> outtake arm dropping, outtake arm back

        // CYCLE --> strafe left, arm+wrist to ground pos, finger open
        TrajectorySequence cycle1 = drive.trajectorySequenceBuilder(new Pose2d(x+100,y,Math.toRadians(header)))
                .strafeLeft(20*side)
                .build();

        // BACKWARD TO CONES
        TrajectorySequence cycle2 = drive.trajectorySequenceBuilder(new Pose2d(x+100-20, y,Math.toRadians(header)))
                .back(30)
                .build();

        // GRAB CONE --> close finger, wrist+arm to back pos, open finger, arm+wrist down to 1st pos

        // FORWARD then STRAFE TO HIGH JUNCTION
        TrajectorySequence cycle3 = drive.trajectorySequenceBuilder(new Pose2d(x+100-20, y-20,Math.toRadians(header)))
                .forward(30)
                .strafeRight(20*side)
                .build();

        // DROP CONE --> outtake arm dropping, outtake arm back

        // REPEAT CYCLE 3x

        drive.followTrajectorySequence(step1);

        for (int i = 0; i < 3; i++) {
            drive.followTrajectorySequence(cycle1);
            drive.followTrajectorySequence(cycle2);
            drive.followTrajectorySequence(cycle3);

        }





    }


}
