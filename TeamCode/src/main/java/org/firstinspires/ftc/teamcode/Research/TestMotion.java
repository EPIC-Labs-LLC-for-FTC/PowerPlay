package org.firstinspires.ftc.teamcode.Research;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Mecanum_Wheels;

@TeleOp(name="Test Motion", group="Research")
//@Disabled
public class TestMotion extends LinearOpMode {

    double lefty;
    double leftx;
    double righty;
    double rightx;
    boolean x1 = false;
    boolean b1 = false;
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    double power = 1;

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
        mw.initialize();
        mw.telemetry = telemetry;
        mw.parent = this;

        mw.leftErrorAdjustment = 0.8;
        mw.rightErrorAdjustment = 0.8;
        waitForStart();
        runtime.reset();



        while(opModeIsActive()) {

//            x1 = gamepad1.x;
//            b1 = gamepad1.b;
//            if(x1) {
//                mw.leftErrorAdjustment-=0.025;
//                if(mw.leftErrorAdjustment<0.0)
//                    mw.leftErrorAdjustment = 0;
//                mw.rightErrorAdjustment-=0.025;
//                if(mw.rightErrorAdjustment<0.0)
//                    mw.rightErrorAdjustment = 0;
//            }
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
//
//
//
//            mw.move(lefty, righty, leftx, rightx);
            mw.encoderDrive(1,40.5,40.5,0 ,0,2);
            mw.encoderDrive(1,12,12,-12 ,-12,2);
            sleep(100000);

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
