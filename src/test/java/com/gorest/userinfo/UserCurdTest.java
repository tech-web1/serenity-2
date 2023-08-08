package com.gorest.userinfo;

import com.gorest.constants.EndPoints1;
import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.RestRequests;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class UserCurdTest extends  TestBase {


    String token = "f8f231911770bd29394ba6350b92e3e88b00d7f482a4b763d244d3d42f395d19";
    static int id = 4298663;
    static String name = "Roy" + TestUtils.getRandomValue();
    static String email = "roy2@gmail.com" + TestUtils.getRandomValue();
    static  String gender = "male"+ TestUtils.getRandomValue();
    static String status = "active"+ TestUtils.getRandomValue();


    @Test
    public void getAllUserData()
    {
        Response response = SerenityRest.given()
                .when()
                .get(EndPoints1.GET_ALL_Data);
        response.then().statusCode(200);
        response.then().log().all();

    }
    @Test
    public void createUser()
    {
        String email = TestUtils.getRandomValue() + "rajaram1@gmail.com";
        UserPojo userpojo = new UserPojo();
        userpojo.setName("Ram");
        userpojo.setEmail(email);
        userpojo.setGender("male");
        userpojo.setStatus("active");
        SerenityRest.given()
                .contentType(ContentType.JSON)
                .headers("Content-Type", "application/json", new Object[]{"Authorization", "Bearer " + token})
                .when()
                .body( userpojo)
                .post(EndPoints1.GET_ALL_Data)
                .then().log().all().statusCode(201);
    }


        @Test
        public void verifyUserUpdatedSuccessfully() {


            String email = TestUtils.getRandomValue() + "rajaram1@gmail.com";
            UserPojo userpojo = new UserPojo();
            userpojo.setName("Ram");
            userpojo.setEmail(email);
            userpojo.setGender("male");
            userpojo.setStatus("active");

        SerenityRest.given()
                .contentType(ContentType.JSON)
                .basePath(RestAssured.basePath+"/users")
                .headers("Content-Type", "application/json",new Object[]{"Authorization", "Bearer " + token})
                .pathParams("id", id)
                .when()
                .body(userpojo)
                .put(EndPoints1.UPDATE_User_BY_Id)
                .then().log().all().statusCode(200);

    }

        @Test

            public void deleteUser() {
                RestRequests.given()
                        .basePath(RestAssured.basePath+"/users")
                        .headers("Content-Type", "application/json",new Object[]{"Authorization", "Bearer " + token})
                        .pathParam("id", id)
                        .when()
                        .delete(EndPoints1.UPDATE_User_BY_Id)
                        .then()
                        .statusCode(204);

                RestRequests.given()
                        .pathParam("id", id)
                        .when()
                        .get(EndPoints1.DELETE_User_BY_ID)
                        .then().statusCode(404);


    }



    }

