package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by Jeremy and Raghav on 10/18/2017.
 */

public class NewRobot
{
    private ColorSensor topColorSens = null;
    private ColorSensor forwardColorSens = null;
    private ColorSensor floorColorSens = null;

    public static final String VUFORIA_KEY = "AepnoMf/////AAAAGWsPSj5vh0WQpMc0OEApBsgbZVwduMSeEZFjXMlBPW7WiZRgwGXsOTLiGMxL4qjU0MYpZitHxs4E/nOUHseMX+SW0oopu6BnWL3cAqFIptSrdMpy4y6yB3N6l+FPcGFZxzadvRoiOfAuYIu5QMHSeulfQ1XApDhBQ79lNUXv9LZ7bngBI3BEYVB+slmTGHKhRW2NI5fUtF+rLRiou4ZcNir2eZh0OxEW4zAnTnciVB2R28yyHkYz8xJtACm+4heWLdpw/zf66LRpvTGLwkASci7ZkGJp4NrG5Of4C0b3+iq/EeEmX2PiY5lq2fkUE0dejdztmkFWYBW7c/Y+bIYGER/3gt6I8UhAB78cR7p2mOaY"; //Key used for Vuforia.
    private VuforiaLocalizer vuforia = null;
    private RelicRecoveryVuMark vuMark = null;
    private VuforiaTrackables relicTrackables = null;
    private VuforiaTrackable relicTemplate = null;

    public NewRobot(HardwareMap hardwareMap)
    {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        //Comment out if you don't want camera view on robo phone
        //VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
        vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        relicTrackables = vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");

        relicTrackables.activate();
        vuMark = RelicRecoveryVuMark.from(relicTemplate);
    }

    public char getGlyphCipher()
    {
        vuMark = RelicRecoveryVuMark.from(relicTemplate);

        if (vuMark.equals(RelicRecoveryVuMark.CENTER))
            return 'c';
        else if (vuMark.equals(RelicRecoveryVuMark.LEFT))
            return 'l';
        else if (vuMark.equals(RelicRecoveryVuMark.RIGHT))
            return 'r';
        else
            return '?';
    }

    public float getHueValue(ColorSensor in_ColorSens)
    {
        float hsvValues[] = {0F,0F,0F};
        Color.RGBToHSV(in_ColorSens.red(), in_ColorSens.green(), in_ColorSens.blue(), hsvValues);
        return hsvValues[0];
    }

    public ColorSensor getTopColorSens()
    {
        return topColorSens;
    }

    public ColorSensor getForwardColorSens()
    {
        return forwardColorSens;
    }

    public ColorSensor getFloorColorSens()
    {
        return floorColorSens;
    }

}
