package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.DistSensor;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.FieldSetup;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.LazySusan;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.LiftSlider;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Mecanum_Wheels;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.NewClaw;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.ScanSleeve;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.TiltSlider;

import java.util.HashMap;
import java.util.Map;

@Autonomous(name="New Left Auton", group="Competition")

public class NewLeftAutonMotion extends LinearOpMode  {
    private ElapsedTime runtime = new ElapsedTime();
    Map<String, Double> distances = new HashMap<String, Double>();
    FieldSetup fs = null;
    DistSensor distanceSensors = null;
    Mecanum_Wheels mecanum = null;//new Mecanum_Wheels(hardwareMap);
    LazySusan lazy = null;
    LiftSlider arm = null;
    NewClaw claw = null;
    ScanSleeve scanner = null;
    TiltSlider pankit = null;
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




        distanceSensors = new DistSensor(hardwareMap);
        distanceSensors.parent = this;
        distanceSensors.telemetry = telemetry;
        distanceSensors.initialize();
        DistanceUnit unit = DistanceUnit.INCH;
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
        pankit = new TiltSlider(hardwareMap);
        pankit.parent = this;
        pankit.telemetry = telemetry;
        pankit.initialize();
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


        distances = distanceSensors.getDistances(unit);
        telemetry.addData("Right Position in " + unit.name() , distances.get("Right"));
        telemetry.addData("Left Position in " + unit.name() , distances.get("Left"));
        telemetry.addData("Back Position in " + unit.name() , distances.get("Back"));
        telemetry.addData("Status", "Initialized");
        telemetry.update();


        fs = new FieldSetup("Blue","Right",distanceSensors,unit);
        mecanum.distanceSensor = distanceSensors;
        mecanum.fs = fs;
        double distance = 27;

        waitForStart();
        runtime.reset();
        mecanum.encoderDrive(0.6, distance, -distance, -distance, distance, 2);
        try {
            distance = 66;
            mecanum.encoderDriveWithDistanceSensor(0.6, distance, distance, distance, distance, 4, "x2");
            distance = 2;
            mecanum.encoderDrive(0.6, -distance, -distance, -distance, -distance, 1);
        }
        catch (Exception ex) {
            telemetry.addData("Error: ","Error occurred in Encoder Drive with distance sensor");
        }
        double ldist = distanceSensors.getLeft(unit);
        //4 inches for the drop
        ldist = ldist - 4.0;
        mecanum.encoderDrive(0.6, -ldist, ldist, ldist, -ldist, 1);
        telemetry.addData("Back Position in " + unit.name() , distanceSensors.getBack(unit));
        telemetry.update();
        //arm.liftEncoder(1, 4, 2.5);
        sleep(1000);// remove this slip when you uncomment the  lift encoder
        sleep(500);

        lazy.rotate(0.525);
        sleep(500);
        claw.release();
        sleep(500);
//
        lazy.rotate(0.0);
        //arm.liftEncoder(1, 5, 2.5);
        sleep(1000);// remove this slip when you uncomment the  lift encoder
        claw.release();
        mecanum.encoderDrive(0.8,0,0,-40.5,-40.5,2);
        mecanum.encoderDrive(0.6,5,5,-5,-5,2);
        double back1 = distanceSensors.getBack(unit);
        double back2 = distanceSensors.getBack2(unit);
        double diff = back2 - back1;
        mecanum.encoderDrive(0.3,-diff,-diff,diff,diff,2);
        mecanum.encoderDrive(0.6,-28,-28,-28,-28,2);
        //mecanum.encoderDrive(0.6,-6,-6,4,4,2);
        back1 = distanceSensors.getBack(unit);
        back2 = distanceSensors.getBack2(unit);
        while(i<5 || runtime.seconds()<24) {
            claw.grab();
//            sleep(500);
//            pankit.extendTicks(0.4,700);
            sleep(500);

            //arm.liftEncoder(1, 3, 2.5);
            sleep(1000);// remove this slip when you uncomment the  lift encoder
                    try {
                        mecanum.encoderDriveWithDistanceSensor(0.5, 41, 41, 41, 41, 2, "w2");
                    } catch (Exception ex) {
                        telemetry.addData("Error: ", "Error occurred in Encoder Drive with distance sensor");
                    }
//                    double ldist = distanceSensors.getLeft(unit);
//                    //4 inches for the drop
//                    ldist = ldist - 4.0;
//                    mw.encoderDrive(0.6, -ldist, ldist, ldist, -ldist, 1);
//                    arm.liftEncoder(1, 3, 2.5);
                    sleep(500);
            lazy.rotate(0.65);
            sleep(750);
            claw.release();
            sleep(750);
            lazy.rotate(0.0);
            sleep(750);
            //pankit.extendTicks(0.4,0);
            if(i<4 || runtime.seconds()<25) {
                double newback1 = distanceSensors.getBack(unit);
                double newback2 = distanceSensors.getBack2(unit);
                double back1diff = newback1 - back1;
                double back2diff = newback2 - back2;
                mecanum.encoderDrive(0.3, -back1diff, -back1diff, -back2diff, -back2diff, 2);
                sleep(500);
                if (i < 2)
                    //arm.liftEncoder(1, 6, 2.5);
                    sleep(1000);// remove this slip when you uncomment the  lift encoder
                else
                    //arm.liftEncoder(1, 7, 2.5);
                    sleep(1000);// remove this slip when you uncomment the  lift encoder
                //claw.release();
                sleep(500);
//                    ldist = distanceSensors.getLeft(unit);
//                    //4 inches for the drop
//                    ldist = ldist - 4.0;
//                    mw.encoderDrive(0.6, ldist, -ldist, -ldist, ldist, 1);
//
//                    try {
//                        mw.encoderDriveWithDistanceSensor(0.5, -41, -41, -41, -41, 2, "wx0");
//                    } catch (Exception ex) {
//                        telemetry.addData("Error: ", "Error occurred in Encoder Drive with distance sensor");
//                    }
            }
            else{
                break;
            }
            i++;
        }
        //parking

        double newback1 = distanceSensors.getBack(unit);
        double newback2 = distanceSensors.getBack2(unit);

        double back1diff = newback1 - 30;
        double back2diff = newback2 - 30;
        if(parkingSpot==2) {


        }
        else if(parkingSpot==1){


            back1diff = newback1 - 2.5;
            back2diff = newback2 - 2.5;
        }
        else if (parkingSpot==1) {
            back1diff = newback1 - 54;
            back2diff = newback2 - 54;
        }

        mecanum.encoderDrive(0.6,back2diff,back2diff,back1diff,back1diff,2);
        //}
        scanner.releaseCamera();
        //arm.initialize();
    }
}