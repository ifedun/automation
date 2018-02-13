package utilities;

import java.io.IOException;

public class AdbCommands {

    private static String package_id;

    public AdbCommands(String package_id) {

        this.package_id = package_id;
    }

    public void forceStopApp() {

        Runtime runtime = Runtime.getRuntime();
        String forceStopCmd = "adb shell am force-stop ";
        String command = forceStopCmd + package_id;

        try {
            runtime.exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void clearAppData() {

        Runtime runtime = Runtime.getRuntime();
        String clearAppDataCmd = "adb shell pm clear ";
        String command = clearAppDataCmd + package_id;

        try {
            runtime.exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
