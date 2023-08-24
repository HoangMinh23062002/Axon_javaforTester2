package JiraTest;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.io.File;

import static io.restassured.RestAssured.given;

public class Jiratest {
    public static void main(String[] args) {
        RestAssured.baseURI = "http://localhost:8080";
        SessionFilter session = new SessionFilter();

        String response = given().header("Content-Type", "application/json")
                .body("{ \"username\": \"hoangminh2306602\", \"password\": \"Minh23@02\" }")
                .log().all().filter(session).post("/rest/auth/1/session").then().log().all().extract().response().asString();
        System.out.println(response);
        String expectedMessage = "You are right";

        //Add comment
        String commentRespone = given().pathParams("key", "10005").header("Content-Type", "application/json").body("{\n" +
                "    \"body\": \""+expectedMessage+"\",\n" +
                "    \"visibility\": {\n" +
                "        \"type\": \"role\",\n" +
                "        \"value\": \"Administrators\"\n" +
                "    }\n" +
                "}").filter(session).when().post("/rest/api/2/issue/{key}/comment").then().log().all().assertThat().statusCode(201).extract().response().asString();
        JsonPath js = new JsonPath(commentRespone);
        String commentID = js.getString("id");
        System.out.println("dgdgbgjfbgj    "+commentID);

        //Add attachment
        given().header("X-Atlassian-Token", "no-check").filter(session).pathParams("key", "10005")

                .header("file-Type", "multipart/form-data").multiPart("file", new File("text.txt")).when().post("/rest/api/2/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);

        // Get issue
        String getArray = given().filter(session).pathParams("key", "10005")
                .queryParam("fields", "comment")
                .when().get("/rest/api/2/issue/{key}").then().assertThat().statusCode(200)
                .log().all().extract().response().asString();
        System.out.println(getArray);

        JsonPath js1 = new JsonPath(getArray);
        int commentCount = js1.getInt("fields.comment.comments.size()");
        System.out.println(commentCount);
        for (int i = 0; i < commentCount; i++) {
            String getID = js1.get("fields.comment.comments[" + i + "].id").toString();
            System.out.println(getID);
            if(getID.equalsIgnoreCase(commentID)){
               String message = js1.get("fields.comment.comments["+i+"].body").toString();
                System.out.println(message);
                Assert.assertEquals(message,expectedMessage);
            }
        }

    }
    }



























