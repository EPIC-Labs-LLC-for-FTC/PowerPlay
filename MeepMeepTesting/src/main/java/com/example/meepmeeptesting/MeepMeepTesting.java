package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import com.acmerobotics.roadrunner.geometry.Vector2d;

import com.noahbres.meepmeep.MeepMeep;

import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;

import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;



public class MeepMeepTesting {

    public static void main(String[] args) {

        MeepMeep meepMeep = new MeepMeep(600);



        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)

                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width

                .setConstraints(57.19292464206345, 57.19292464206345, Math.toRadians(327.6913200000001), Math.toRadians(327.6913200000001), 10)

                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(35.0, -58.0, Math.toRadians(90)))



                                .forward(40)
                                .splineTo(new Vector2d(26.3,-2.8), Math.toRadians(200))
                                .setReversed(true)
                                .splineTo(new Vector2d(59.9,-11.5),Math.toRadians(0))
                                .setReversed(false)
                                .splineTo(new Vector2d(26.3,-2.8), Math.toRadians(140))
                                .setReversed(true)
                                .splineTo(new Vector2d(59.9,-11.5),Math.toRadians(0))
                                .setReversed(false)
                                .splineTo(new Vector2d(26.3,-2.8), Math.toRadians(140))
                                .setReversed(true)
                                .splineTo(new Vector2d(59.9,-11.5),Math.toRadians(0))
                                .setReversed(false)
                                .splineTo(new Vector2d(26.3,-2.8), Math.toRadians(140))
                                .setReversed(true)
                                .splineTo(new Vector2d(59.9,-11.5),Math.toRadians(0))
                                .setReversed(false)
                                .splineTo(new Vector2d(26.3,-2.8), Math.toRadians(140))
                                .setReversed(true)
                                .splineTo(new Vector2d(59.9,-11.5),Math.toRadians(0))
                                .setReversed(false)
                                .splineTo(new Vector2d(26.3,-2.8), Math.toRadians(140))




                                .build()

                );





        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_KAI_DARK)

                .setDarkMode(true)

                .setBackgroundAlpha(0.95f)

                .addEntity(myBot)

                .start();

    }

}

