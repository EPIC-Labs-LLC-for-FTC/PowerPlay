package org.firstinspires.ftc.teamcode.Research;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.LazySusan;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.LiftSlider;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Mecanum_Wheels;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.NewClaw;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.TiltSlider;

@TeleOp(name="Test Grid", group="Research")
//@Disabled
public class TestGrid extends LinearOpMode {

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
        telemetry.addData("Status", "Initialized");
        telemetry.update();

//        frontLeft  = hardwareMap.get(DcMotor.class, "frontLeft");
//        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
//        backRight  = hardwareMap.get(DcMotor.class, "backRight");
//        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
//
//        frontRight.setDirection(DcMotor.Direction.REVERSE);
//        backRight.setDirection(DcMotor.Direction.REVERSE);
        Mecanum_Wheels mw = new Mecanum_Wheels(hardwareMap);
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
        waitForStart();
        runtime.reset();



        while(opModeIsActive()) {

            x1 = gamepad1.x;
            b1 = gamepad1.b;
            y1 = gamepad1.y;
            a1 = gamepad1.a;
            if(y1) {
                mw.encoderDrive(0.6, 63, 63, 63, 63, 2);

                arm.liftEncoder(1, 4, 2.5);
                //move left
                mw.encoderDrive(0.6, -2, 2, 2, -2, 2);
                //sleep(500);
                lazy.rotate(0.50);
                sleep(1000);

                sleep(1000);
                claw.release();
                sleep(500);

                lazy.rotate(0.0);
                arm.liftEncoder(1, 5, 2.5);
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
                arm.liftEncoder(1, 5,2.5);
                claw.release();
                mw.encoderDrive(0.8,-42.5,-42.5,0 ,0,2);
                mw.encoderDrive(0.6,-16,-16,-16,-16,2);
                claw.grab();
                sleep(500);
                arm.liftEncoder(1,2,1);
//                mw.encoderDrive(1,12,12,-12 ,-12,2);
            }
            if(x1){
                mw.encoderDrive(0.6,45.5,37.5,20.5,12.5,2);
                sleep(500);
                mw.encoderDrive(0.6,-17.5,-17.5,10,10,2);
                arm.liftEncoder(1,4,1);
                sleep(1000);
                lazy.rotate(0.5);
                sleep(1000);
                claw.release();
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
                mw.encoderDrive(0.6, 63, 63, 63, 63, 2);

                arm.liftEncoder(1, 4, 2.5);
                //move left
                mw.encoderDrive(0.6, -2, 2, 2, -2, 2);
                //sleep(500);
                lazy.rotate(0.50);
                sleep(1000);

                sleep(1000);
                claw.release();
                sleep(500);

                lazy.rotate(0.0);
                arm.liftEncoder(1, 5, 2.5);
                //start second command
                mw.encoderDrive(0.8,-42.5,-42.5,0 ,0,2);
                mw.encoderDrive(0.6,-15,-15,-15,-15,2);
                claw.grab();
                sleep(500);
                arm.liftEncoder(1,2,1);
                //start third command
                mw.encoderDrive(0.6,45.5,37.5,20.5,12.5,2);
                sleep(500);
                mw.encoderDrive(0.6,-17.5,-17.5,10,10,2);
                mw.encoderDrive(0.6,-6,5,6,-5,1);
                mw.encoderDrive(0.6,-24.5,-24.5,-24.5,-24.5,2);


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

            telemetry.addData("lefty", lefty);
            telemetry.addData("righty", righty);
            telemetry.addData("leftx", leftx);
            telemetry.addData("rightx", rightx);
            telemetry.addData("lefterroradjustments", mw.leftErrorAdjustment);
            telemetry.addData("righterroradjustments", mw.rightErrorAdjustment);
            telemetry.update();
        }
    }
}
