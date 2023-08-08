package com.gorest.userinfo;

import com.gorest.constants.EndPoints1;
import com.gorest.model.UserPojo;
import com.gorest.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class UserSteps {

    @Step
    public void getAllUserData()
    {
        Response response = SerenityRest.given()
                .when()
                .get(EndPoints1.GET_ALL_Data);
        response.then().statusCode(200);
        response.then().log().all();

    }

    @Step("Cresting store : name :{0}, email:{1},gender :{2},status:{3}")
    public void createUser(String token)
    {

        String email = TestUtils.getRandomValue() + "rajaram@gmail.com";
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

    @Step
    public void verifyUserUpdatedSuccessfully(int id,String token) {


        String email = TestUtils.getRandomValue() + "rajaram@gmail.com";
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

    @Step
    public void deleteUser(int id,String token) {
        SerenityRest.given()
                .basePath(RestAssured.basePath+"/users")
                .headers("Content-Type", "application/json",new Object[]{"Authorization", "Bearer " + token})
                .pathParam("id", id)
                .when()
                .delete(EndPoints1.UPDATE_User_BY_Id)
                .then()
                .statusCode(204);

        SerenityRest.given()
                .pathParam("id", id)
                .when()
                .get(EndPoints1.DELETE_User_BY_ID)
                .then().statusCode(404);


    }




}
