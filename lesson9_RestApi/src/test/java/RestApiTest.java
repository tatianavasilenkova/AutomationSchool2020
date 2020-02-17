import static io.restassured.RestAssured.given;
//import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import my.reqrest.UserPage;
import my.resources.Resources;
import my.resources.ResourcesPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class RestApiTest {

    private static RequestSpecification spec, spec2;

    @BeforeAll
    public static void init() {
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setProxy("gate.swissre.com", 9443)
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();

        spec2 = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setProxy("gate.swissre.com", 9443)
                .setBaseUri("https://reqres.in/api")
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();

    }

    @Test
    public void someTest() {
        Users[] users = given()
                .spec(spec)
                .when()
                .get("users")
                .then()
                .statusCode(200)
                .extract().as(Users[].class);
        assertEquals(1, users[0].getId());
        assertEquals("Bret", users[1].getUsername());
    }

    @Test
    public void getPostsTest() {
        Posts[] posts = given()
                .spec(spec)
                .when()
                .get("posts")
                .then()
                .statusCode(200)
                .extract().as(Posts[].class);
        assertEquals(1, posts[0].getId());
        assertEquals("qui est esse", posts[1].getTitle());
    }

    @Test
    public void getToDosTest() {
        ToDos[] todos = given()
                .spec(spec)
                .when()
                .get("todos")
                .then()
                .statusCode(200)
                .extract().as(ToDos[].class);
        assertEquals(1, todos[0].getId());
        assertEquals("quis ut nam facilis et officia qui", todos[1].getTitle());
    }

    @Test
    public void getPosts1Test() {
        Posts posts = given()
                .spec(spec)
                .when()
                .get("posts/1")
                .then()
                .statusCode(200)
                .extract().as(Posts.class);
        assertEquals(posts.getId(), 1);
        System.out.println(posts.getMyString());
        assertEquals("sunt aut facere repellat provident occaecati excepturi optio reprehenderit", posts.getTitle());
    }

    @Test
    public void getToDos1Test() {
        ToDos todos = given()
                .spec(spec)
                .when()
                .get("todos/1")
                .then()
                .statusCode(200)
                .extract().as(ToDos.class);
        assertEquals(1, todos.getUserId());
        assertEquals(false, todos.getCompleted());
    }

    @Test
    public void getPost2Test() {
        String payload = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        Response response = given()
                .spec(spec2)
                .urlEncodingEnabled(true)
                .body(payload)
                .when()
                .post("api/users")
                .then().statusCode(201)
                .and()
                .extract()
                .response();

        String responseBodyString = response.getBody().asString();
        System.out.println(response.path("createdAt").toString());
    }

    /**
     * Need to rechec LocalDateTime variable. Test Fails
     */
    @Test
    public void getPost3Test() {
        String payload = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        UserResponse userResponse = given()
                .spec(spec2)
                .urlEncodingEnabled(true)
                .body(payload)
                .when()
                .post("api/users")
                .then().statusCode(201)
                .and()
                .extract().as(UserResponse.class);


        System.out.println((userResponse.createdAt));
    }

    @Test
    public void getLoginTokenTest() {
        String payload = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "}";

        Response response = given()
                .spec(spec2)
                .urlEncodingEnabled(true)
                .body(payload)
                .when()
                .post("login")
                .then().statusCode(200)
                .and()
                .extract()
                .response();

        System.out.println(response);
    }

    @Test
    public void userListTest() {
        UserPage usersPage = given()
                .spec(spec2)
                .when()
                .get("users?page=2")
                .then()
                .statusCode(200)
                .extract()
                .as(UserPage.class);

        assertEquals(7, usersPage.getData()[0].getId());
        assertEquals("Michael", usersPage.getData()[0].getFirst_name());
    }

    /**
     * HomeWork:
     * 1. Get "List <Resources>" request and assertThat:
     * - total_pages >= 2
     */
    @Test
    public void listResourcesTotalPagesTest() {
        ResourcesPage resourcesPage = given()
                .spec(spec2)
                .when()
                .get("/unknown")
                .then()
                .statusCode(200)
                .extract()
                .as(ResourcesPage.class);

        assertThat(resourcesPage.getTotal_pages()).isGreaterThanOrEqualTo(2);
        }


  /** HomeWork:
            * 1. Get "List <Resources>" request and assertThat:
            * - Exactly one item exist with "name": "true red"    ????
            */
    @Test
    public void listResourcesNamesCountTest() {
        ResourcesPage resourcesPage = given()
                .spec(spec2)
                .when()
                .get("/unknown")
                .then()
                .statusCode(200)
                .extract()
                .as(ResourcesPage.class);

        int count = 0;
        for (int i = 0; i < resourcesPage.getData().length; i++) {
            String name = resourcesPage.getData()[i].getName();
            if (name.equals("true red")) {
                count++;
            }
        }
            assertEquals(1, count, "No such name or more items with such name exists");
        }

    /**
     * HomeWork:
     * 1. test that tests (post) LOGIN - UNSUCCESSFUL
     */
    @Test
    public void getLoginUnsuccessfullTest() {
        String payload = "{\n" +
                "    \"email\": \"peter@klaven\"\n" +
                "}";

        Response response = given()
                .spec(spec2)
                .urlEncodingEnabled(true)
                .body(payload)
                .when()
                .post("/login")
                .then()
                .statusCode(400)
                .and()
                .extract()
                .response();

        System.out.println(response);
    }
}
