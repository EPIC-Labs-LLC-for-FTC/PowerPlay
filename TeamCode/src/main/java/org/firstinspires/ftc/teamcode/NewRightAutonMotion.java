package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.LazySusan;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.LiftSlider;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Mecanum_Wheels;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.NewClaw;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.ScanSleeve;

@Autonomous(name="New Right Auton", group="Competition")
public class NewRightAutonMotion extends LinearOpMode  {
    private ElapsedTime runtime = new ElapsedTime();
    Mecanum_Wheels mecanum = null;//new Mecanum_Wheels(hardwareMap);
    LazySusan lazy = null;
    LiftSlider arm = null;
    NewClaw claw = null;
    ScanSleeve scanner = null;
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

        scanner = new ScanSleeve(hardwareMap);
        scanner.telemetry = this.telemetry;
        scanner.parent = this;
        scanner.initialize();
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
        mecanum.rightErrorAdjustment=1.00;//0.95;//0.973*0.973;
        mecanum.ticksAdjustment = 0.97561*0.976220382;
        claw = new NewClaw(hardwareMap);
        claw.telemetry = this.telemetry;
        claw.parent = this;
        claw.initialize(0.0);
        arm = new LiftSlider(hardwareMap);
        arm.telemetry = this.telemetry;
        arm.parent = this;
        arm.initialize();
        //claw.grab();
        int parkingSpot = 0;
        int i = 0;
        //while (!isStarted() && !isStopRequested()) {
            parkingSpot = scanner.getParkingSpot();
//            if(parkingSpot!=0 || i>20)
//                break;
//            i++;
//            sleep(50);
//        }
        scanner.releaseCamera();
        waitForStart();

        //while(opModeIsActive()) {
        mecanum.encoderDrive(0.6, 60, 60, 60, 60, 2);

        arm.liftEncoder(1, 4, 2.5);
        //move left
        mecanum.encoderDrive(0.6, -2, 2, 2, -2, 2);
        //sleep(500);
        lazy.rotate(0.50);
        sleep(1000);
        claw.release();
        sleep(500);

        lazy.rotate(0.0);
        arm.liftEncoder(1, 5, 2.5);

        claw.release();
        //second cone pickup drive
        mecanum.encoderDrive(0.8,-42.5,-42.5,0 ,0,2);
        mecanum.encoderDrive(0.6,-16,-16,-16,-16,2);
        claw.grab();
        sleep(500);
        arm.liftEncoder(1,2,1);

        //second cone pickup delivery

        mecanum.encoderDrive(0.6,45.5,37.5,20.5,12.5,2);
        sleep(500);
        arm.liftEncoder(1,4,1);
        sleep(1000);
        lazy.rotate(0.5);
        sleep(1000);
        claw.release();

        sleep(500);

        lazy.rotate(0.0);
        arm.liftEncoder(1, 5, 2.5);
        mecanum.encoderDrive(0.6,-17.5,-17.5,10,10,2);
        mecanum.encoderDrive(0.6,-6,5,6,-5,1);
        mecanum.encoderDrive(0.6,2,-2,-2,2, 3.0);

        //parking

        if(parkingSpot==2)
            mecanum.encoderDrive(0.6,2.5,2.5,2.5,2.5,2);
        else if(parkingSpot==3)
            mecanum.encoderDrive(0.6,-22.5,-22.5,-22.5,-22.5,2);
        else if (parkingSpot==1)
            mecanum.encoderDrive(0.6,24.5,24.5,24.5,24.5,2);
        //}
        scanner.releaseCamera();
        //arm.initialize();
    }

}
