import base.BaseTest;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ser.Serializers;
import io.cucumber.java.DataTableType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.ExcelUtil;

public class DemoTest extends BaseTest {

    public static void main(String[] args){

    }

    @Parameters({"username","password"})
    @Test
    public  void basicTest(String username, @Optional("Dummy") String password) {
        System.out.println("baseTest Called "+username+" "+password);
    }

    @DataProvider(name = "getLoginData2")
    public  Object[][] getLoginData(){
        Object[] user4 = {"user4", "pass4"};
        Object[][] userdata = {
                {"user1", "passe"},
                {"user2", "pass2"},
                {"user3", "pass3"},
                user4
        };
        return userdata;
    }

    @DataProvider(name = "getLoginDataFromExcel")
    public  Object[][] getLoginDataFromExcel(){
        String filePath = "src/test/resources/testdata/userdetails.xlsx";
        String sheetName = "Sheet1";
        return ExcelUtil.getTestData(filePath,sheetName);
    }

    @Test(dataProvider = "getLoginData2")
    public  void dataTest(String username, String password) {
        System.out.println("dataTest Called "+username+" "+password);
    }

    @Test(dataProvider =  "getLoginDataFromExcel")
    public  void testDataFromExcel(Double userId, String firstName, String lastName, String gender, String country, Double age) {
        System.out.println("testDataFromExcel Called "+firstName+" "+lastName+" "+age);
    }

}
