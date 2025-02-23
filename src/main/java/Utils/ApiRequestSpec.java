package Utils;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;

public class ApiRequestSpec {
    public static String baseUrl = "http://10.195.105.66:7000";

    public static RequestSpecification getRequestSpec() {
        return given()
                .baseUri(baseUrl)
                .header("Content-type", "application/json");
    }

    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType("application/json")
                .build();
    }
}
