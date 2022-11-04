package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Research.BlueRightPipe;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Claw;
import org.firstinspires.ftc.teamcode.RobotObjects.EPIC.Mecanum_Wheels;
import org.firstinspires.ftc.teamcode.RobotObjects.Spinner;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Autonomous(name = "TestEncodrAuton")
public class TestEncoderAuton extends LinearOpMode {
    OpenCvCamera webcam;
    @Override
    public void runOpMode() throws InterruptedException {
        double distance = 0;
        double correctionFactor = 1;
//        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
//        BlueRightPipe detector = new BlueRightPipe(telemetry);
//        webcam.setPipeline(detector);
        Mecanum_Wheels wheels = new Mecanum_Wheels(hardwareMap);
//        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
//
//        {
//            @Override
//            public void onOpened()
//            {
//                webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
//            }
//
//            @Override
//            public void onError(int errorCode)
//            {
//                telemetry.addData("Error:","Camera cant stream");
//            }
//        });
        double speed = 0.6;
        Mecanum_Wheels mecanum = new Mecanum_Wheels(hardwareMap);
        mecanum.leftErrorAdjustment = 1;
//        Spinner spinner = new Spinner(hardwareMap);
//        double spinnerPower = 0.58;
//        double levelDistance = 11;
//        double backDistance = 5;
//        double level;
        mecanum.parent = this;
//        Claw claw = new Claw(hardwareMap);
//        claw.parent = this;
//        claw.telemetry = this.telemetry;
        //sleep(5000);
        mecanum.IsAutonomous = true;
        mecanum.velocity = 400;
        mecanum.telemetry = this.telemetry;
        mecanum.initialize();

        waitForStart();
//        sleep(2000);
//        claw.lift(detector.correctlocation);
        sleep(700);
        correctionFactor = 1.444;//.4;
        distance = 25;
        mecanum.encoderDrive(0.8,distance,distance,distance,distance,2);

        distance = 15;
        mecanum.encoderDrive(0.5,-distance,distance,distance,-distance,2);




    }
}
