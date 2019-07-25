package com.lambdaschool.school.service;

import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.view.CountStudentsInCourses;

import java.util.ArrayList;

public interface CourseService
{
    ArrayList<Course> findAll();

    Course findCourseById(long courseid);

    ArrayList<CountStudentsInCourses> getCountStudentsInCourse();

    Course save(Course course);

    void delete(long id);
}
