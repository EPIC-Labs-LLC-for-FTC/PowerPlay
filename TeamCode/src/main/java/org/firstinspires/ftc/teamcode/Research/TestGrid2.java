package org.firstinspires.ftc.teamcode.Research;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.LazySusan;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.LiftSlider;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Mecanum_Wheels;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.NewClaw;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.TiltSlider;
import java.util.*;
@TeleOp(name="Test Grid---2", group="Research")
//@Disabled
public class TestGrid2 extends LinearOpMode {


    Stack<Wheels> stack = new Stack<Wheels>();
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
                Wheels wheels = new Wheels("forward",24,24,24,24,2);
                
            }
            if(a1){
                mw.encoderDrive(0.6,-24,-24,-24,-24,2);
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
