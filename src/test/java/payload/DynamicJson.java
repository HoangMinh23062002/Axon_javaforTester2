package payload;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson {
    @Test(dataProvider = "BooksData")
    public void addBoook(String isbn, String aisle) {
        Response responses = RestAssured.get("https://61bc10bed8542f0017824524.mockapi.io/product");
        int statuss = responses.getStatusCode();
        Assert.assertEquals(statuss, 200);
        System.out.println("status là " + statuss);

        RestAssured.baseURI = "https://61bc10bed8542f0017824524.mockapi.io/";
        String response = given().header("Content-Type", "application/json")
                .body(payload.Addbook(isbn, aisle))
                .when().post("product").then().assertThat().statusCode(201).extract().response().asString();
        System.out.println(response);

        //delete book
    }

    @DataProvider(name = "BooksData")
    public Object[][] getData() {
        // array is thw collection of element
        // multidinensional array is collection of arra
        return new Object[][]{{"hmm", "34645"}, {"tu", "98455"}, {"huong", "345678"}};

    }
}



























