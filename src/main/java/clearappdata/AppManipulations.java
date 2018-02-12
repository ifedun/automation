package clearappdata;

import java.io.IOException;

public class AppManipulations {

    private static String forceStopCmd = "adb shell am force-stop ";
    private static String clearAppDataCmd = "adb shell pm clear ";
    private static String package_id;

    public AppManipulations(String package_id) {

        this.package_id = package_id;
    }

    public void forceStopApp(){

        Runtime runtime = Runtime.getRuntime();
        String command = forceStopCmd + package_id;

        try {
            runtime.exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void clearAppData(){

        Runtime runtime = Runtime.getRuntime();
        String command = clearAppDataCmd + package_id;

        try {
            runtime.exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
