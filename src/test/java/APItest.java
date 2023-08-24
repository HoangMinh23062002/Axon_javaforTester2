import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import payload.payload;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matcher.*;

import java.sql.SQLOutput;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APItest {
    public static void main(String[] args) {
        Response responsee = RestAssured.get("https://61bc10bed8542f0017824524.mockapi.io/name");
        System.out.println("status code" + responsee.asString());

        System.out.println("status code" + responsee.getStatusCode());

        System.out.println("status code" + responsee.getBody().asString());

        System.out.println("status code" + responsee.getHeader("content-type"));
        int statuscode = responsee.getStatusCode();
        Assert.assertEquals(statuscode, 200);

// Post
        RestAssured.baseURI = "https://61bc10bed8542f0017824524.mockapi.io";
        String response = given().log().all()
                .header("Content-Type", "application/json")
                .body(payload.addPlace()).when().post("/name")
                .then().log().all().assertThat().statusCode(201)
                .extract().response().asString();


        System.out.println(response);
        JsonPath js = new JsonPath(response);  // for parsing
        String PlaceADD = js.getString("name");
        System.out.println("Them"+PlaceADD);

// update
        Response resp1 = given().log().all()
                .header("Content-Type", "application/json")
                .body("\"name\":\"" + PlaceADD + "\",\n" +
                        "\"class\":\"PNV23A-USA\"")
                .when()
                .put("class");
        System.out.println(resp1);
        int status = resp1.getStatusCode();
        Assert.assertEquals(status,200);


    }

}
















