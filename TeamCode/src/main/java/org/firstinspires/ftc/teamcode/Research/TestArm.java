package org.firstinspires.ftc.teamcode.Research;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="TestArm", group="Research")
public class TestArm extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotorEx armExt;
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();


        armExt = hardwareMap.get(DcMotorEx.class, "armExt");
        armExt.setDirection(DcMotorSimple.Direction.FORWARD);


        waitForStart();
        runtime.reset();



        while(opModeIsActive()){

            armExt.setPower(0.3);
            sleep(5000);
            armExt.setPower(-0.3);
            sleep(5000);
        }
    }
}
