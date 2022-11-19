package org.firstinspires.ftc.teamcode.Research;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Arm;

@TeleOp(name="TestArm", group="Research")
@Disabled
public class TestArm extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private Arm arm;
    @Override
    public void runOpMode() throws InterruptedException {
        //telemetry.addData("Status", "Initialized");
        //telemetry.update();


        arm = new Arm(hardwareMap);
        arm.telemetry = this.telemetry;
        arm.parent = this;
        arm.initialize();


        waitForStart();
        runtime.reset();



        while(opModeIsActive()){

            arm.liftEncoder(0.3,1);
            sleep(2000);
            arm.liftEncoder(0.3,2);

            sleep(2000);
            arm.liftEncoder(0.3,3);
        }
    }
}
