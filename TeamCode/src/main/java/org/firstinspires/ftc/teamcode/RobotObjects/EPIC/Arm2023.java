package org.firstinspires.ftc.teamcode.RobotObjects.EPIC;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.ServoImplEx;

public class Arm2023 {
    //Configuration used: 6wheelConfig
    public ServoImplEx arm;
    //public DcMotorEx liftMotor;
    public Telemetry telemetry;
    public LinearOpMode parent;
    public double dropping = 0.2;
    public double transition = 0.5;
    public double initialize = 0.45;
    public double level5 = 0.82;
    public double level4 = 0.79;
    public double level3 = 0.82;
    public double level2 = 0.87;
    public double ground = 0.88;

    public Arm2023(HardwareMap hardwareMap) {
        arm = hardwareMap.get(ServoImplEx.class,"arm");

    }

    public void doDropping()
    {
        arm.setPosition(dropping);
        telemetry.addData("arm Claw 1:%d", arm.getPosition());
        telemetry.update();
    }
    public void doLevel5()
    {
        arm.setPosition(level5);
        telemetry.addData("arm Claw 1:%d", arm.getPosition());
        telemetry.update();
    }
    public void doLevel4()
    {
        arm.setPosition(level4);
        telemetry.addData("arm Claw 1:%d", arm.getPosition());
        telemetry.update();
    }
    public void doLevel3()
    {
        arm.setPosition(level3);
        telemetry.addData("arm Claw 1:%d", arm.getPosition());
        telemetry.update();
    }
    public void doLevel2()
    {
        arm.setPosition(level2);
        telemetry.addData("arm Claw 1:%d", arm.getPosition());
        telemetry.update();
    }
    public void doTransition(){
        arm.setPosition(transition);
        telemetry.addData("arm 1:%d", arm.getPosition());
        telemetry.update();

    }
    public void doInitialize()
    {
        arm.setPosition(initialize);
        telemetry.addData("arm  1:%d", arm.getPosition());
        telemetry.update();
    }
    public void doGround()
    {
        arm.setPosition(ground);
        telemetry.addData("arm 1:%d", arm.getPosition());
        telemetry.update();
    }
    public double getPosition(){
        return arm.getPosition();
    }
    public void armset(double position ){
        arm.setPosition(arm.getPosition()+position);
    }
}
