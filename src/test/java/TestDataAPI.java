import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TestDataAPI {

    @Test
    public void getData () {
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");

        //task 1
        System.out.println("Success! Status code " + response.getStatusCode());

        //task 2
        System.out.println("Size of the data list is " + response.jsonPath().getList("data").size());

        //task 3-4
        for (int i = 0; i < response.jsonPath().getList("data").size(); i++) {
            String email = response.jsonPath().getString("data[" + i + "].email");
            System.out.println(email);
            Assert.assertTrue(email.contains("@"));
        }

        //task 5
        Assert.assertEquals(response.getHeader("Content-type"), "application/json; charset=utf-8");
    }
}