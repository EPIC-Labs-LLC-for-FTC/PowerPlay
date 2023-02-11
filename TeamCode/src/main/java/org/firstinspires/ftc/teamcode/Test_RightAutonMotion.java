package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Arm;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.ExtendArm;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.LazySusan;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Mecanum_Wheels;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.NewClaw;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.ScanSleeve;

@Autonomous(name="Test Right Auton", group="Competition")
@Disabled
public class Test_RightAutonMotion extends LinearOpMode  {
    private ElapsedTime runtime = new ElapsedTime();
    Mecanum_Wheels mecanum = null;//new Mecanum_Wheels(hardwareMap);
    LazySusan lazy = null;
    ExtendArm arm = null;
    NewClaw claw = null;
    //ScanSleeve scanner = null;
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

//        scanner = new ScanSleeve(hardwareMap);
//        scanner.telemetry = this.telemetry;
//        scanner.parent = this;
//        scanner.initialize();
        lazy = new LazySusan(hardwareMap);
        lazy.telemetry = this.telemetry;
        lazy.parent = this;
        lazy.initialize2();
        mecanum = new Mecanum_Wheels(hardwareMap);
        mecanum.IsAutonomous = true;
        mecanum.velocity = 400;
        mecanum.telemetry = this.telemetry;
        mecanum.parent = this;
        mecanum.initialize();
        mecanum.rightErrorAdjustment=0.95;//0.973*0.973;
        mecanum.ticksAdjustment = 0.97561*0.976220382;
        claw = new NewClaw(hardwareMap);
        claw.telemetry = this.telemetry;
        claw.parent = this;
        claw.initialize(0.0);
        arm = new ExtendArm(hardwareMap);
        arm.telemetry = this.telemetry;
        arm.parent = this;
        arm.initialize();
        //claw.grab();
        int parkingSpot = 0;
        int i = 0;
        //while (!isStarted() && !isStopRequested()) {
//            parkingSpot = scanner.getParkingSpot();
//            if(parkingSpot!=0 || i>20)
//                break;
//            i++;
//            sleep(50);
//        }
        //scanner.releaseCamera();
        waitForStart();
//        double distance = 56;
//        mecanum.encoderDrive(0.8,distance,distance,distance,distance,3);
//        distance = -28;
//        sleep(500);
//        mecanum.encoderDrive(0.8,distance,distance,distance,distance,3);
//
//        int parkingSpot = 3;
       // distance = 28;

        //while(opModeIsActive()) {
//            arm.liftEncoder(0.3, 2);
            double ldistance = 55;
            double rdistance = 50;
            mecanum.encoderDrive(0.4, ldistance, ldistance, rdistance, rdistance, 5);
//            lazy.rotate(0.275);
//            sleep(1000);
//            ldistance = 4.75;
//            rdistance = 4.75
//            //right
//            mecanum.encoderDrive(0.4, ldistance, -ldistance, -rdistance, rdistance, 5);
//        sleep(3000);
//            claw.release();
//
//        sleep(500);
//            //left
//        mecanum.encoderDrive(0.4, -ldistance, ldistance, rdistance, -rdistance, 5);
//        sleep(1000);
//            //mecanum.encoderDrive(speed,-40,0,40,40,0,-40, 4.0);
//            //if(parkingSpot ==2){
//
//        ldistance = -12.0;
//        rdistance = -12.0;
//                mecanum.encoderDrive(0.4, ldistance, ldistance, rdistance, rdistance, 5);
//                if (parkingSpot==3)
//                {
//                    ldistance = 28;
//                    //right
//                    mecanum.encoderDrive(0.4, ldistance, -ldistance, -ldistance, ldistance, 5);
//                }
//                else if(parkingSpot==1){
//                    //left
//                    ldistance = 28;
//                    mecanum.encoderDrive(0.4, -ldistance, ldistance, ldistance, -ldistance, 5);
//                }
//            //}
//            //right
//            //mecanum.encoderDrive(speed,24,0,-24,-24,0,24, 3.0);
//        //}
//        lazy.initialize2();
//                sleep(500);
//                arm.liftEncoder(0.3,1);
//                sleep(500);
//        claw.release();
//        //scanner.releaseCamera();
//        //arm.initialize();
    }

}
