package org.firstinspires.ftc.teamcode.Research;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Mecanum_Wheels;

@TeleOp(name="Test Motion", group="Research")
public class TestMotion extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    double power = 0;

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
        waitForStart();
        runtime.reset();

        mw.move(0.3,0,0,0);
        sleep(1000);


        //while(opModeIsActive()){

        //}
        telemetry.addData("power", power);
        telemetry.update();
    }
}
