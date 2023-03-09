package org.firstinspires.ftc.teamcode.RobotObjects.EPIC;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Claw2023 {
    //Configuration used: 6wheelConfig
    public Servo finger;
    //public DcMotorEx liftMotor;
    public Telemetry telemetry;
    public LinearOpMode parent;
    public double fingerMin = 0.4;
    public double fingerMax = 0.6;
    public boolean isGrabbed = false;
    public boolean isReleased = false;

    public Claw2023(HardwareMap hardwareMap) {
        finger = hardwareMap.get(Servo.class,"finger");
        //arm = hardwareMap.get(DcMotorEx.class,"arm");
        //arm.setDirection(DcMotor.Direction.REVERSE);

        //liftMotor = hardwareMap.get(DcMotorEx.class,"Lift");

    }

    public void initiateClaw() {

    }

    public void grab()
    {
        finger.setPosition(fingerMin);
        parent.sleep(1000);
        telemetry.addData("Finger Claw 1:%d", finger.getPosition());
        telemetry.update();
        isReleased = false;
        isGrabbed = true;
    }

    public void release() {
        finger.setPosition(fingerMax);
        parent.sleep(1000);
        telemetry.addData("Finger Claw 1:%d", finger.getPosition());
        telemetry.update();
        isReleased = true;
        isGrabbed = false;
    }
}
