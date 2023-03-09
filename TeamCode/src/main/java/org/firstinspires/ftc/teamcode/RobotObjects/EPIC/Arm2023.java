package org.firstinspires.ftc.teamcode.RobotObjects.EPIC;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Arm2023 {
    //Configuration used: 6wheelConfig
    public Servo arm;
    //public DcMotorEx liftMotor;
    public Telemetry telemetry;
    public LinearOpMode parent;
    public double dropping = 0.175;
    public double level5 = 0.4;
    public double level4 = 0.55;
    public double level3 = 0.58;
    public double level2 = 0.605;
    public double ground = 0.7;

    public Arm2023(HardwareMap hardwareMap) {
        arm = hardwareMap.get(Servo.class,"arm");

    }

    public void dropping()
    {
        arm.setPosition(dropping);
        telemetry.addData("Finger Claw 1:%d", arm.getPosition());
        telemetry.update();
    }
    public void level5()
    {
        arm.setPosition(level5);
        telemetry.addData("Finger Claw 1:%d", arm.getPosition());
        telemetry.update();
    }
    public void level4()
    {
        arm.setPosition(level4);
        telemetry.addData("Finger Claw 1:%d", arm.getPosition());
        telemetry.update();
    }
    public void level3()
    {
        arm.setPosition(level3);
        telemetry.addData("Finger Claw 1:%d", arm.getPosition());
        telemetry.update();
    }
    public void level2()
    {
        arm.setPosition(level2);
        telemetry.addData("Finger Claw 1:%d", arm.getPosition());
        telemetry.update();
    }
    public void ground()
    {
        arm.setPosition(ground);
        telemetry.addData("Finger Claw 1:%d", arm.getPosition());
        telemetry.update();
    }
    public double getPosition(){
        return arm.getPosition();
    }
}
