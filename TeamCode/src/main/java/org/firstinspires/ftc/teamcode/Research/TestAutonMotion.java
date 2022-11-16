package org.firstinspires.ftc.teamcode.Research;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Arm;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.LazySusan;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Mecanum_Wheels;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.NewClaw;

@Autonomous(name="TestAutonomous", group="Research")
public class TestAutonMotion extends LinearOpMode  {
    private ElapsedTime runtime = new ElapsedTime();
    Mecanum_Wheels mecanum = null;//new Mecanum_Wheels(hardwareMap);
    LazySusan lazy = null;
    Arm arm = null;
    NewClaw claw = null;
    //forward
    //mecanum.encoderDrive(speed,15,15,15,15,15,15, 2.0);
    //backward
    //mecanum.encoderDrive(speed,-5,-5,-5,-5,-5,-5, 1.0);
    //left
    //mecanum.encoderDrive(speed,-40,0,40,40,0,-40, 4.0);
    //right
    //mecanum.encoderDrive(speed,24,0,-24,-24,0,24, 3.0);
    //left turn
    //mecanum.encoderDrive(speed,-6,0,-6,6,0,6, 1.0);
    //right turn
    //mecanum.encoderDrive(speed,6,0,6,-6,0,-6, 1.0);
    @Override
    public void runOpMode() throws InterruptedException {

        lazy = new LazySusan(hardwareMap);
        lazy.telemetry = this.telemetry;
        lazy.parent = this;
        lazy.initialize();
        mecanum = new Mecanum_Wheels(hardwareMap);
        mecanum.IsAutonomous = true;
        mecanum.velocity = 400;
        mecanum.telemetry = this.telemetry;
        mecanum.parent = this;
        mecanum.initialize();
        mecanum.rightErrorAdjustment=0.95;//0.973*0.973;
        mecanum.ticksAdjustment = 0.97561*0.976220382;
        arm = new Arm(hardwareMap);
        arm.telemetry = this.telemetry;
        arm.parent = this;
        arm.initialize();
        claw = new NewClaw(hardwareMap);
        claw.telemetry = this.telemetry;
        claw.parent = this;
        claw.initialize();
        claw.grab();
        waitForStart();
//        double distance = 56;
//        mecanum.encoderDrive(0.8,distance,distance,distance,distance,3);
//        distance = -28;
//        sleep(500);
//        mecanum.encoderDrive(0.8,distance,distance,distance,distance,3);
//
//        int parkingSpot = 3;
       // distance = 28;

        double distance = 36;
        mecanum.encoderDrive(0.8,distance,distance,distance,distance,3);
        lazy.rotate(0.3);
        arm.lift(0.3,500);
        claw.release();
        //right
        //mecanum.encoderDrive(speed,24,0,-24,-24,0,24, 3.0);

    }

}
