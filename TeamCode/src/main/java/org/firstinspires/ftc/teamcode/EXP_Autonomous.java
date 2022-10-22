package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Claw;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Mecanum_Wheels;

import java.net.PortUnreachableException;

@Autonomous(name="EXP_Autonomous")


public class EXP_Autonomous extends LinearOpMode {
    //Configuration used: 4wheelConfig
//    private ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {
//        FtcDashboard dashboard = FtcDashboard.getInstance();
//        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());



        DcMotorEx frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        DcMotorEx frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        DcMotorEx backRight = hardwareMap.get(DcMotorEx.class, "backRight");
        DcMotorEx backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
//        DistanceSensor distanceRight = hardwareMap.get(DistanceSensor.class, "distanceRight");
//        DistanceSensor distanceLeft = hardwareMap.get(DistanceSensor.class, "distanceLeft");
        double speed = 0.2;
        waitForStart();

//        while(opModeIsActive()){
//            telemetry.addData("Distance Left: ", distanceLeft.getDistance(DistanceUnit.INCH));
//            telemetry.addData("Distance Right: ", distanceRight.getDistance(DistanceUnit.INCH));
//            telemetry.update();

//            if (distanceLeft.getDistance(DistanceUnit.INCH) < 4.5){
//                frontRight.setPower(1);
//                frontLeft.setPower(1);
//                backLeft.setPower(-1);
//                backRight.setPower(-1);
//                telemetry.addData("Distance Left: ", distanceLeft.getDistance(DistanceUnit.INCH));
//                telemetry.addData("Distance Right: ", distanceRight.getDistance(DistanceUnit.INCH));
//                telemetry.update();
//            } else if (distanceRight.getDistance(DistanceUnit.INCH) < 4.5){
//              frontRight.setPower(-1);
//              frontLeft.setPower(-1);
//              backLeft.setPower(1);
//              backRight.setPower(1);
//              telemetry.addData("Distance Left: ", distanceLeft.getDistance(DistanceUnit.INCH));
//              telemetry.addData("Distance Right: ", distanceRight.getDistance(DistanceUnit.INCH));
//              telemetry.update();
//          } else {
//                frontRight.setPower(0);
//                frontLeft.setPower(0);
//                backLeft.setPower(0);
//                backRight.setPower(0);
//            }
//        }
//
//        telemetry.addData("Distance Left: ", distanceLeft.getDistance(DistanceUnit.INCH));
//        telemetry.addData("Distance Right: ", distanceRight.getDistance(DistanceUnit.INCH));
//        telemetry.update();
        //go forward
        frontRight.setPower(1);
        backRight.setPower(1);
        frontLeft.setPower(1);
        backLeft.setPower(1);
        sleep(1000);
        //turn to cones
        frontRight.setPower(.2);
        backRight.setPower(.2);
        frontLeft.setPower(0);
        backLeft.setPower(0);
        sleep(1000);

        //turn to cones to the left
        frontRight.setPower(-.2);
        backRight.setPower(-.2);
        frontLeft.setPower(.2);
        backLeft.setPower(.2);



    }

}