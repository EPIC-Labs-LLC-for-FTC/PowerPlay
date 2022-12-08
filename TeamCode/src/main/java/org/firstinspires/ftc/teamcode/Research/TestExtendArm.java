package org.firstinspires.ftc.teamcode.Research;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Arm;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.ExtendArm;

@TeleOp(name="TestExtendArm", group="Research")
//@Disabled
public class TestExtendArm extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private ExtendArm arm;
    boolean x2;
    boolean b2;
    boolean a2;
    boolean y2;
    int ticks = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        //telemetry.addData("Status", "Initialized");
        //telemetry.update();


        arm = new ExtendArm(hardwareMap);
        arm.telemetry = this.telemetry;
        arm.parent = this;
        arm.initialize2();
        ticks = 1500;
        arm.extendTicks(1,ticks);

        waitForStart();
        runtime.reset();



        while(opModeIsActive()){
            x2 = gamepad2.x;
            y2 = gamepad2.y;
            b2 = gamepad2.b;
            a2 = gamepad2.a;
            if(x2){
               ticks -=250;
            }
            else if (y2){
                ticks +=500;
            }
            else if(b2){
                ticks += 1000;
            }
            else if(a2){
                ticks+= 100;
            }
            arm.extendTicks(1,ticks);


//            arm.liftEncoder(0.8,1);
//            sleep(2000);
//            arm.liftEncoder(0.8,2);
//
//            sleep(2000);
            //arm.liftEncoder(1,3);
            sleep(2000);
            arm.setPower(0);
        }
    }
}
