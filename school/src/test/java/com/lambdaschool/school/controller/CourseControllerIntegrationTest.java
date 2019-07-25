package com.lambdaschool.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.model.Course;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.number.OrderingComparison.lessThan;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CourseControllerIntegrationTest
{
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void initialiseRestAssuredMockMvcWebApplicationContext()
    {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    //    GET /courses/courses
    @Test
    public void whenMeasuredReponseTime()
    {
        given().when().get("/courses/courses").then().time(lessThan(5000L));
    }

    @Test
    public void givenPostARestaurant() throws Exception
    {
        String course3Name = "Klingon";
        Course c3 = new Course(course3Name, null);
        c3.setCourseid(100);

        ObjectMapper mapper = new ObjectMapper();
        String stringR3 = mapper.writeValueAsString(c3);

        given().contentType("application/json").body(stringR3).when().post("/courses/course/add").then().statusCode(201);
    }

}
