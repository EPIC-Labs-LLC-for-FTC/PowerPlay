package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Arm;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.LazySusan;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Mecanum_Wheels;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.NewClaw;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Teleop", group="Competition")
public class TeleOp extends LinearOpMode {
    double wheelpower = 0.6;
    double lefty;
    double leftx;
    double righty;
    double rightx;
    boolean x1 = false;
    boolean b1 = false;
    boolean y1 = false;
    boolean a1 = false;
    boolean x2 = false;
    boolean b2 = false;
    boolean y2 = false;
    boolean a2 = false;

    boolean dpadUp2 = false;
    boolean dpadDown2 = false;
    boolean dpadRight2 = false;
    boolean dpadLeft2 = false;

    boolean dpadUp1 = false;
    boolean dpadDown1 = false;
    boolean dpadRight1 = false;
    boolean dpadLeft1 = false;

    LazySusan lazy = null;
    NewClaw claw = null;
    Arm arm = null;
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
        lazy = new LazySusan(hardwareMap);
        lazy.telemetry = this.telemetry;
        lazy.parent = this;
        lazy.initialize2();

        Mecanum_Wheels mw = new Mecanum_Wheels(hardwareMap);
        mw.initialize();
        mw.telemetry = telemetry;
        mw.parent = this;
        claw = new NewClaw(hardwareMap);
        claw.telemetry = this.telemetry;
        claw.parent = this;
        claw.initialize(0.0);
        arm = new Arm(hardwareMap);
        arm.telemetry = this.telemetry;
        arm.parent = this;
        arm.initialize2();

        mw.leftErrorAdjustment = wheelpower;
        mw.rightErrorAdjustment = wheelpower;
        waitForStart();
        runtime.reset();



        while(opModeIsActive()) {

            x1 = gamepad1.x;
            b1 = gamepad1.b;
            a1 = gamepad1.a;
            y1 = gamepad1.y;

            dpadDown1 = gamepad1.dpad_down;
            dpadLeft1 = gamepad1.dpad_left;
            dpadRight1 = gamepad1.dpad_right;
            dpadUp1 = gamepad1.dpad_up;

            x2 = gamepad2.x;
            b2 = gamepad2.b;
            a2 = gamepad2.a;
            y2 = gamepad2.y;


            dpadUp2 = gamepad2.dpad_up;
            dpadDown2 = gamepad2.dpad_down;
            dpadRight2 = gamepad2.dpad_right;
            dpadLeft2 = gamepad2.dpad_left;


//            if(x1) {
//                mw.leftErrorAdjustment-=0.025;
//                if(mw.leftErrorAdjustment<0.0)
//                    mw.leftErrorAdjustment = 0;
//                mw.rightErrorAdjustment-=0.025;
//                if(mw.rightErrorAdjustment<0.0)
//                    mw.rightErrorAdjustment = 0;
//            }
//            else if(b1) {
//
//                mw.leftErrorAdjustment+=0.025;
//                if(mw.leftErrorAdjustment>1.0)
//                    mw.leftErrorAdjustment = 1;
//                mw.rightErrorAdjustment+=0.025;
//                if(mw.rightErrorAdjustment>1.0)
//                    mw.rightErrorAdjustment = 1;
//            }

            lefty = gamepad1.left_stick_y;
            leftx = gamepad1.left_stick_x;
            righty = gamepad1.right_stick_y;
            rightx = -gamepad1.right_stick_x;

            if(dpadUp1){
                wheelpower = 0.6;
            }
            else if(dpadLeft1) {
                wheelpower = 0.4;
            }
            else if(dpadDown1) {
                wheelpower = 0.2;
            }
            if(dpadDown2) {
                arm.liftEncoder(0.2, 1);
                sleep(500);
            }
            else if(dpadRight2) {
                arm.liftEncoder(0.2, 2);
                sleep(500);
            }
            else if(dpadUp2) {
                arm.liftEncoder(0.2, 3);
                sleep(500);
            }
            else if(dpadLeft2) {
                arm.liftEncoder(0.2, 4);
                sleep(500);
            }
            else if(x2)
                claw.grab();
            else if(b2)
                claw.release();
            else if(y2) {
                arm.lift(0.3,500,1);
            }
            else if(a2) {
                arm.lift(0.3,500,-1);
            }

            mw.leftErrorAdjustment = wheelpower;
            mw.rightErrorAdjustment= wheelpower;

            mw.move(lefty, righty, leftx, rightx);

            telemetry.addData("lefty", lefty);
            telemetry.addData("righty", righty);
            telemetry.addData("leftx", leftx);
            telemetry.addData("rightx", rightx);
            telemetry.addData("lefty", lefty);

            telemetry.addData("armPosition", arm.getCurrentPosition());


            telemetry.addData("lefterroradjustments", mw.leftErrorAdjustment);
            telemetry.addData("righterroradjustments", mw.rightErrorAdjustment);
            telemetry.update();
        }
    }
}