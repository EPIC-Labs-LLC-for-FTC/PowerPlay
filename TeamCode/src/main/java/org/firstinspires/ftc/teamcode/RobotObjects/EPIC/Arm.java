package org.firstinspires.ftc.teamcode.RobotObjects.EPIC;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Arm {
    public DcMotorEx arm;
    double power = 0.3;
    public LinearOpMode parent;

    public Telemetry telemetry;
    public Arm(HardwareMap hardwareMap){
        arm = hardwareMap.get(DcMotorEx.class,"arm");

    }
    //initialize for TeleOp
    public void initialize() {

    }

    public void lift(double power, int milliseconds) {
        arm.setPower(power);
        parent.sleep(milliseconds);
    }
}
