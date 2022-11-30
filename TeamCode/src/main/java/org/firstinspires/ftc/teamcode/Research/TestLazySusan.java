package org.firstinspires.ftc.teamcode.Research;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.LazySusan;

@TeleOp(name="Test LazySu", group="Research")

public class TestLazySusan extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    LazySusan lazy = null;
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        lazy = new LazySusan(hardwareMap);
        lazy.parent = this;
        lazy.telemetry = telemetry;
        lazy.initialize();

        telemetry.addData("position", lazy.getPosition());
        telemetry.update();
        waitForStart();
    }
}
