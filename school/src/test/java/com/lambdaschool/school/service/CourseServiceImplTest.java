package com.lambdaschool.school.service;

import com.lambdaschool.school.SchoolApplication;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseServiceImplTest
{
    @Autowired
    CourseService courseService;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks((this));
    }


    @Test
    public void AfindCourseById()
    {
        assertEquals("JavaScript", courseService.findCourseById(2).getCoursename());
    }

    @Test(expected = EntityNotFoundException.class)
    public void BdeleteNotFound()
    {
        courseService.delete(1000);
        assertEquals(12, courseService.findAll().size());
    }

    @Test
    public void CdeleteFound()
    {
        courseService.delete(1);
        assertEquals(11, courseService.findAll().size());
    }

    @Test
    public void Dsave()
    {
        String course3Name = "Klingon";
        Course c3 = new Course(course3Name);
        Instructor sally = new Instructor("Sally");
        sally.setInstructid(1);
        c3.setInstructor(sally);

        Course addCourse = courseService.save(c3);

        assertNotNull(addCourse);

        Course foundCourse = courseService.findCourseById(addCourse.getCourseid());
        assertEquals(addCourse.getCoursename(), foundCourse.getCoursename());
    }

}
