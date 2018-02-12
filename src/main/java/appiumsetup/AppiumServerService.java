package appiumsetup;

import io.appium.java_client.service.local.AppiumDriverLocalService;

public class AppiumServerService {

    private static AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();

    public void startServer() {

        service.start();

    }

    public void stopServer() {

        service.stop();

    }

}
