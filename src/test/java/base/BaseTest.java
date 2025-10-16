package base;

import org.testng.annotations.BeforeSuite;
import utils.ConfigReader;


public class BaseTest {

    protected String environment;

    @BeforeSuite
    public void setup() {
        // Read Url from profile based on environment
        String urlFromProfile = System.getProperty("url","noUrl");
        String envFromProfile = System.getProperty("env","noEnv");

        System.out.println("URL from Profile: " + urlFromProfile);
        System.out.println("env from Profile: " + envFromProfile);

        // Common setup code for all tests
        System.out.println("Setting up the test environment...");
        environment =  System.getProperty("env", "dev");
        String url = ConfigReader.get("baseUrl");
        System.out.println("URL Config"+url);
    }

}