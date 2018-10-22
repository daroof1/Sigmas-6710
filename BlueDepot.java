package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;

public class BlueDepot extends LinearOpMode {
    // Declare OpMode Members
    HardwareSigma2018 robot = null;
    VuforiaLocalizer vuforia;

    @Override
    public void runOpMode() throws InterruptedException
    {
        //String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        //System.out.println("--RedNear log@" + currentDateTimeString + "--");

        /* -------- initializations ---------- */
        /*
         * Initialize the standard drive system variables.
         * The init() method of the hardware class does most of the work here
         */
        robot = new HardwareSigma2018();
        telemetry.addData("HERE", 1);
        robot.init(hardwareMap);

        // Vuforia SetUp
        final VuforiaLocalizer.CameraDirection CAMERA_CHOICE = BACK;
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        // VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = "\"AZ/y08v/////AAABmW2F+VgsfEqurugLGLrbk3xyH7N9tvzPux4eQg9rPK+SIv5GDmkrIx0vgN\" +\n" +
                "        \"TWk38gl/twDCosIHE+QKNfrRJ52UEUApnutRqNvEbblyi/uhiqOnJsEBVJnZeiI/Ix+ZZdt2i7g+juzZqYVINYv1p0mOWPDdP\" +\n" +
                "        \"L77UyWLdwdeHKYe7LJo3SbAbzrH5enUwDRalJ2MmSsXg3xm9rXJlS1RQ2RoDSIVhh101KgF33QlDFnK/8yBRqHbEMfxsb5df8\" +\n" +
                "        \"gIWnFv/wkQWwUFd1fH/w0VWLjWfX5O5HuvAZJ5fDSq2rVy+i0EbKLsXn/heEQuRJgU409sMEpOKxMYX7em673DL9qP7A3p6dp\" +\n" +
                "        \"SeoOTH5QDmcv0EJ\";";
        parameters.cameraDirection   = CAMERA_CHOICE;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Load the data sets that for the trackable objects. These particular data
        // sets are stored in the 'assets' part of our application.
        VuforiaTrackables targetsRoverRuckus = this.vuforia.loadTrackablesFromAsset("RoverRuckus");
        VuforiaTrackable blueRover = targetsRoverRuckus.get(0);
        blueRover.setName("Blue-Rover");
        VuforiaTrackable redFootprint = targetsRoverRuckus.get(1);
        redFootprint.setName("Red-Footprint");
        VuforiaTrackable frontCraters = targetsRoverRuckus.get(2);
        frontCraters.setName("Front-Craters");
        VuforiaTrackable backSpace = targetsRoverRuckus.get(3);
        backSpace.setName("Back-Space");

        // For convenience, gather together all the trackable objects in one easily-iterable collection */
        List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();
        allTrackables.addAll(targetsRoverRuckus);

        // Make sure the motors are set up correctly
        robot.FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.BackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.BackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }
}
