package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.util.Vector;

public class Meep {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(57.19292464206345, 57.19292464206345, Math.toRadians(327.6913200000001), Math.toRadians(327.6913200000001), 10)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-33, -62, 1.5708))
                                //go to highest junction
                                .forward(62)
                                .turn(Math.toRadians(-90))
                                //put cone
                                .waitSeconds(1)
                                //go to other cones
                                .turn(Math.toRadians(180))
                                .strafeTo(new Vector2d(-57, -12))
                                //get cone
                                .waitSeconds(1)
                                //go back to junction
                                .turn(Math.toRadians(180))
                                .strafeTo(new Vector2d(-36, 0))
                                .waitSeconds(1)
                                //repeat getting and placing cone
/*
                                //repeat 1
                                .turn(Math.toRadians(180))
                                .strafeTo(new Vector2d(-57, -12))
                                //get cone
                                .waitSeconds(1)
                                //go back to junction
                                .turn(Math.toRadians(180))
                                .strafeTo(new Vector2d(-32, 0))
                                .waitSeconds(1)

                                //repeat 2
                                .turn(Math.toRadians(180))
                                .strafeTo(new Vector2d(-57, -12))
                                //get cone
                                .waitSeconds(1)
                                //go back to junction
                                .turn(Math.toRadians(180))
                                .strafeTo(new Vector2d(-32, 0))
                                .waitSeconds(1)*/

                                //park
                                .back(5)
                                .splineTo(new Vector2d(-57, -34), Math.toRadians(-100))
                                .build()
                );


        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}