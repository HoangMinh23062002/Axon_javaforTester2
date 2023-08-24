package E2ETesting;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class EcommerceTest {
    public static void main(String[] args) {
        RequestSpecification AddNew = new RequestSpecBuilder().setBaseUri("https://reqres.in")

                .build();
        given().header("Content-Type", "application/json").log().all().spec(AddNew).param("name","minh").param("job","student");
       String Addname = AddNew.when().post("/api/users").then().log().all().extract().response().asString();
        JsonPath js = new JsonPath(Addname);
        System.out.println(Addname);
        String nameID = js.get("id");
        System.out.println(nameID);
    }
}
