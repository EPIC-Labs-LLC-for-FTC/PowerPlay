package org.firstinspires.ftc.teamcode.Research;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.DistSensor;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.FieldSetup;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.LazySusan;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.LiftSlider;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Mecanum_Wheels;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.NewClaw;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.TiltSlider;

import java.util.HashMap;
import java.util.Map;

@TeleOp(name="Test Grid With Distance Sensor", group="Research")
//@Disabled
public class TestGridWithDistanceSensor extends LinearOpMode {
    Map<String, Double> distances = new HashMap<String, Double>();
    FieldSetup fs = null;
    DistSensor distanceSensors = null;
    Mecanum_Wheels mw = null;
    double lefty;
    double leftx;
    double righty;
    double rightx;
    boolean x1 = false;
    boolean b1 = false;
    boolean y1 = false;
    boolean a1 = false;
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    double power = 1;
    LazySusan lazy = null;
    NewClaw claw = null;
    LiftSlider arm = null;
    TiltSlider pankit = null;
//    private DcMotor frontLeft = null;
//    private DcMotor frontRight = null;
//    private DcMotor backRight = null;
//    private DcMotor backLeft = null;
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initializing");
        telemetry.update();

        distanceSensors = new DistSensor(hardwareMap);
        distanceSensors.parent = this;
        distanceSensors.telemetry = telemetry;
        distanceSensors.initialize();
        DistanceUnit unit = DistanceUnit.INCH;
        mw = new Mecanum_Wheels(hardwareMap);
        mw.IsAutonomous = true;
        mw.initialize();
        mw.telemetry = telemetry;
        mw.parent = this;
        lazy = new LazySusan(hardwareMap);
        lazy.telemetry = this.telemetry;
        lazy.parent = this;
        lazy.initialize();


        claw = new NewClaw(hardwareMap);
        claw.telemetry = this.telemetry;
        claw.parent = this;
        claw.initialize(0.0);
        arm = new LiftSlider(hardwareMap);
        arm.telemetry = this.telemetry;
        arm.parent = this;
        arm.initialize2();



        pankit = new TiltSlider(hardwareMap);
        pankit.parent = this;
        pankit.telemetry = telemetry;
        pankit.initialize();

        mw.leftErrorAdjustment = 1;
        mw.rightErrorAdjustment = 1;

        distances = distanceSensors.getDistances(unit);
        telemetry.addData("Right Position in " + unit.name() , distances.get("Right"));
        telemetry.addData("Left Position in " + unit.name() , distances.get("Left"));
        telemetry.addData("Back Position in " + unit.name() , distances.get("Back"));
        telemetry.addData("Status", "Initialized");
        telemetry.update();


        fs = new FieldSetup("Blue","Right",distanceSensors,unit);
        mw.distanceSensor = distanceSensors;
        mw.fs = fs;
        waitForStart();
        runtime.reset();



        while(opModeIsActive()) {

            x1 = gamepad1.x;
            b1 = gamepad1.b;
            y1 = gamepad1.y;
            a1 = gamepad1.a;
            if(y1) {
                try {
                    mw.encoderDriveWithDistanceSensor(0.6, 70, 70, 70, 70, 4, "x2");
                    mw.encoderDrive(0.6, -2, -2, -2, -2, 1);
                }
                catch (Exception ex) {
                    telemetry.addData("Error: ","Error occurred in Encoder Drive with distance sensor");
                }
                double ldist = distanceSensors.getLeft(unit);
                //4 inches for the drop
                ldist = ldist - 4.0;
                mw.encoderDrive(0.6, -ldist, ldist, ldist, -ldist, 1);
                telemetry.addData("Back Position in " + unit.name() , distanceSensors.getBack(unit));
                telemetry.update();
                sleep(500);
                arm.liftEncoder(1, 4, 2.5);
//                //sleep(500);
                lazy.rotate(0.525);
                sleep(500);
                claw.release();
                sleep(500);
//
                lazy.rotate(0.0);
                arm.liftEncoder(1, 5, 2.5);
                claw.release();
                mw.encoderDrive(0.8,-40.5,-40.5,0 ,0,2);
                mw.encoderDrive(0.6,-5,-5,5,5,2);
                mw.encoderDrive(0.6,-18,-18,-18,-18,2);
                claw.grab();
                sleep(500);
                arm.liftEncoder(1,2,1);
                //mw.encoderDrive(0.6, ldist, -ldist, -ldist, ldist, 1);
//




//                //left turn
//                mw.encoderDrive(1,-6,-6,6,6, 1.0);
                //sleep(500);
//                mw.leftErrorAdjustment-=0.025;
//                if(mw.leftErrorAdjustment<0.0
//                    mw.leftErrorAdjustment = 0;
//                mw.rightErrorAdjustment-=0.025;
//                if(mw.rightErrorAdjustment<0.0)
//                    mw.rightErrorAdjustment = 0;
            }
            if(b1){
                int i = 0;
                    arm.liftEncoder(1, 5, 2.5);
                    claw.release();
                    mw.encoderDrive(0.6, -6, -6, -6, -6, 1);

                while(i<5) {
                    claw.grab();
                    sleep(500);
                    arm.liftEncoder(1, 2, 2.5);
                    try {
                        mw.encoderDriveWithDistanceSensor(0.5, 41, 41, 41, 41, 2, "w2");
                    } catch (Exception ex) {
                        telemetry.addData("Error: ", "Error occurred in Encoder Drive with distance sensor");
                    }
                    double ldist = distanceSensors.getLeft(unit);
                    //4 inches for the drop
                    ldist = ldist - 4.0;
                    mw.encoderDrive(0.6, -ldist, ldist, ldist, -ldist, 1);
                    arm.liftEncoder(1, 3, 2.5);
                    sleep(500);
                    lazy.rotate(0.5);
                    sleep(750);
                    claw.release();
                    sleep(750);
                    lazy.rotate(0.0);
                    sleep(750);
                    if(i<3)
                        arm.liftEncoder(1, 6, 2.5);
                    else
                        arm.liftEncoder(1, 7, 2.5);
                    claw.release();
                    sleep(500);
                    ldist = distanceSensors.getLeft(unit);
                    //4 inches for the drop
                    ldist = ldist - 4.0;
                    mw.encoderDrive(0.6, ldist, -ldist, -ldist, ldist, 1);

                    try {
                        mw.encoderDriveWithDistanceSensor(0.5, -41, -41, -41, -41, 2, "wx0");
                    } catch (Exception ex) {
                        telemetry.addData("Error: ", "Error occurred in Encoder Drive with distance sensor");
                    }
                    i++;
                }
            }
            if(x1){
                int i = 0;
                arm.liftEncoder(1, 5, 2.5);
                claw.release();
                mw.encoderDrive(0.6, -6, -6, -6, -6, 1);
                mw.encoderDrive(0.6,-6,-6,4,4,2);
                while(i<5) {
                    claw.grab();
                    sleep(500);
                    pankit.extendTicks(0.4,700);
                    sleep(500);

                    arm.liftEncoder(1, 3, 2.5);
//                    try {
//                        mw.encoderDriveWithDistanceSensor(0.5, 41, 41, 41, 41, 2, "w2");
//                    } catch (Exception ex) {
//                        telemetry.addData("Error: ", "Error occurred in Encoder Drive with distance sensor");
//                    }
//                    double ldist = distanceSensors.getLeft(unit);
//                    //4 inches for the drop
//                    ldist = ldist - 4.0;
//                    mw.encoderDrive(0.6, -ldist, ldist, ldist, -ldist, 1);
//                    arm.liftEncoder(1, 3, 2.5);
//                    sleep(500);
                    lazy.rotate(0.65);
                    sleep(750);
                    claw.release();
                    sleep(750);
                    lazy.rotate(0.05);
                    sleep(750);
                    pankit.extendTicks(0.4,0);
                    if(i<3)
                        arm.liftEncoder(1, 6, 2.5);
                    else
                        arm.liftEncoder(1, 7, 2.5);
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
                    i++;
                }
//                mw.encoderDrive(0.6,45.5,37.5,20.5,12.5,2);
//                sleep(500);
//                mw.encoderDrive(0.6,-17.5,-17.5,10,10,2);
//                arm.liftEncoder(1,4,1);
//                sleep(1000);
//                lazy.rotate(0.5);
//                sleep(1000);
//                claw.release();
//                mw.encoderDrive(0.6,-6,5,6,-5,1);
//                mw.encoderDrive(0.6,-24.5,-24.5,-24.5,-24.5,2);
                //mw.encoderDrive(0.6,-45.5,-37.5,-12.5,-20.5,2);
//                mw.encoderDrive(0.6,16.5,16.5,16.5,16.5,2);
//                sleep(1000);
//                mw.encoderDrive(0.6,29,21,4,-4,2);
//                sleep(1000);
//                mw.encoderDrive(0.6,-29,-21,-4,4,2);
//                sleep(1000);
//
//                mw.encoderDrive(0.6,-16.5,-16.5,-16.5,-16.5,2);

//                mw.encoderDrive(0.8,42,42,0 ,0,2);
//                arm.liftEncoder(1,4,2.5);
//                claw.release();
//                arm.liftEncoder(1,2,1);


            }
            if(a1){
                //move right
                mw.encoderDrive(0.6, 2, -2, -2, 2, 2);

                mw.encoderDrive(0.6, -63, -63, -63, -63, 2);

                telemetry.addData("Back Position in " + unit.name() , distanceSensors.getBack(unit));
                telemetry.update();

//                mw.encoderDrive(0.6, 63, 63, 63, 63, 2);
//
//                arm.liftEncoder(1, 4, 2.5);
//                //move left
//                mw.encoderDrive(0.6, -2, 2, 2, -2, 2);
//                //sleep(500);
//                lazy.rotate(0.50);
//                sleep(1000);
//
//                sleep(1000);
//                claw.release();
//                sleep(500);
//
//                lazy.rotate(0.0);
//                arm.liftEncoder(1, 5, 2.5);
//                //start second command
//                mw.encoderDrive(0.8,-42.5,-42.5,0 ,0,2);
//                mw.encoderDrive(0.6,-15,-15,-15,-15,2);
//                claw.grab();
//                sleep(500);
//                arm.liftEncoder(1,2,1);
//                //start third command
//                mw.encoderDrive(0.6,45.5,37.5,20.5,12.5,2);
//                sleep(500);
//                mw.encoderDrive(0.6,-17.5,-17.5,10,10,2);
//                mw.encoderDrive(0.6,-6,5,6,-5,1);
//                mw.encoderDrive(0.6,-24.5,-24.5,-24.5,-24.5,2);
//

            }
//            if(b1) {
//
//                mw.leftErrorAdjustment+=0.025;
//                if(mw.leftErrorAdjustment>1.0)
//                    mw.leftErrorAdjustment = 1;
//                mw.rightErrorAdjustment+=0.025;
//                if(mw.rightErrorAdjustment>1.0)
//                    mw.rightErrorAdjustment = 1;
//            }
//
//            lefty = gamepad1.left_stick_y;
//            leftx = gamepad1.left_stick_x;
//            righty = gamepad1.right_stick_y;
//            rightx = -gamepad1.right_stick_x;



            //mw.move(lefty, righty, leftx, rightx);
            distances = distanceSensors.getDistances(unit);
            telemetry.addData("lefty", lefty);
            telemetry.addData("righty", righty);
            telemetry.addData("leftx", leftx);
            telemetry.addData("rightx", rightx);
            telemetry.addData("lefterroradjustments", mw.leftErrorAdjustment);
            telemetry.addData("righterroradjustments", mw.rightErrorAdjustment);
            telemetry.addData("Right Position in " + unit.name() , distances.get("Right"));
            telemetry.addData("Left Position in " + unit.name() , distances.get("Left"));
            telemetry.addData("Back Position in " + unit.name() , distances.get("Back"));
            telemetry.update();
        }
    }
}
