package com.gorest.userinfo;

import com.gorest.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class UserCurdTestWithSteps extends TestBase {


    String token = "f8f231911770bd29394ba6350b92e3e88b00d7f482a4b763d244d3d42f395d19";

    static int id = 4298663;



    @Steps
    UserSteps userSteps;


    @Title("GetAll user data")
    @Test
    public void test001()
    {
        userSteps. getAllUserData();
    }


    @Title("Create User Data")
    @Test
    public void test002()
    {
        userSteps.verifyUserUpdatedSuccessfully( id,token);
    }


    @Title("Update User")
    @Test
    public void test003()
    {
        userSteps.verifyUserUpdatedSuccessfully(id,token);
    }

    @Title("Delete User Date")
    @Test
    public void test004()
    {
        userSteps.deleteUser( id, token);
    }
}
