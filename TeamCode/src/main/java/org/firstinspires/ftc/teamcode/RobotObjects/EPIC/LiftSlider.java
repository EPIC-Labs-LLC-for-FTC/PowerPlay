package org.firstinspires.ftc.teamcode.RobotObjects.EPIC;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LiftSlider {
    public DcMotorEx arm;
    double power = 0.3;
    public LinearOpMode parent;
    int currentPosition=0;
    public Telemetry telemetry;


    private ElapsedTime runtime = new ElapsedTime();
    public LiftSlider(HardwareMap hardwareMap){
        arm = hardwareMap.get(DcMotorEx.class,"slider");

    }

    public void noEncoderInitialize(){
        arm.setDirection(DcMotorSimple.Direction.REVERSE);


    }
    //initialize for TeleOp
    public void initialize() {
        arm.setDirection(DcMotorSimple.Direction.REVERSE);

        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setTargetPosition(0);
        currentPosition = 0;

    }

    public void initialize2() {
        //arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setDirection(DcMotorSimple.Direction.REVERSE);
        //arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        int currentPosition = arm.getCurrentPosition();
//        arm.setTargetPosition(1000);
//        arm.setPower(0.4);
//        parent.sleep(2000);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //arm.setDirection(DcMotorSimple.Direction.FORWARD);
        //arm.setTargetPosition(0);
        arm.setTargetPosition(0);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setPower(0);

    }

    public void lift(double power, int milliseconds, int direction) {
        if(direction==-1){
        //    arm.setDirection(DcMotorSimple.Direction.REVERSE);
            currentPosition += 70 * direction;

        }
        else
        {
            currentPosition += 100 * direction;

            //    arm.setDirection(DcMotorSimple.Direction.FORWARD);
        }
        arm.setTargetPosition(currentPosition);
        arm.setPower(power);
        parent.sleep(milliseconds);
    }

    public double getCurrentPosition(){
        return arm.getCurrentPosition();
    }

    public void extendTicks(double power, int encoderTicks) {
        int targetPosition = encoderTicks;
        arm.setPower(power);
        arm.setTargetPosition(targetPosition);
        currentPosition = targetPosition;


        telemetry.addData("extenArmPosition", arm.getCurrentPosition());
        telemetry.addData("extendArmtargetPosition", targetPosition);

        telemetry.update();
    }

    public void liftEncoder(double power, int level) {
        liftEncoder(power,level,2.0);
    }
    public void liftEncoder(double power, int level,double timeoutS) {
        int targetPosition = 0;
        //arm.setDirection(direction);
        if(level==1)
            targetPosition = 0;
        else if(level==2)
            targetPosition = 1300;
        else if(level==3)
            targetPosition = 2800;
        else if(level==4) {
//            targetPosition = 700;
//            arm.setTargetPosition(targetPosition);
//            currentPosition = targetPosition;

            //parent.sleep(1000);
            targetPosition = 4300;
        }

        else if(level==5) {
            targetPosition = 400;
        }

        else if(level==6) {
            targetPosition = 200;
        }
        arm.setTargetPosition(targetPosition);
        currentPosition = targetPosition;

        // reset the timeout time and start motion.
        runtime.reset();
        arm.setPower(power);

        while (parent.opModeIsActive() &&
                (runtime.seconds() < timeoutS) &&
                (arm.isBusy()))
        {
            telemetry.addData("slider position: ",targetPosition);
            telemetry.update();
        }
        //parent.sleep(500);
        arm.setPower(0);
        telemetry.addData("extendArmPosition", arm.getCurrentPosition());
        telemetry.addData("extendArmtargetPosition", targetPosition);

        telemetry.update();
        //parent.sleep(2000);
        //arm.setPower(0);
    }

    public void setPower(double power){
        arm.setPower(power);
    }
}