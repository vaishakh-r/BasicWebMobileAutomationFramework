package base;

import org.testng.annotations.BeforeSuite;
import utils.ConfigReader;


public class BaseTest {

    protected String environment;

    @BeforeSuite
    public void setup() {
        // Common setup code for all tests
        System.out.println("Setting up the test environment...");
        environment =  System.getProperty("environment", "dev");
    }

}