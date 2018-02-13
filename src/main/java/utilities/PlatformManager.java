package utilities;

public class PlatformManager {

    public enum Platform {
        ANDROID("Android"),
        IOS("iOS");

        private String platform;

        Platform(String platform) {
            this.platform = platform;
        }
    }

    public Platform getPlatform() {
        String property = System.getProperty("platform", "android").toLowerCase();
        Platform platform = Platform.ANDROID;

        if (property.equals("android"))
            platform = Platform.ANDROID;
        else if (property.equals("ios"))
            platform = Platform.IOS;
        return platform;
    }
}
