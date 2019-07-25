package com.lambdaschool.school.controller;

import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.service.CourseService;
import com.lambdaschool.school.view.CountStudentsInCourses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/courses")
public class CourseController
{
    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/courses", produces = {"application/json"})
    public ResponseEntity<?> listAllCourses()
    {
        ArrayList<Course> myCourses = courseService.findAll();
        return new ResponseEntity<>(myCourses, HttpStatus.OK);
    }

    @GetMapping(value = "/course/{courseId}",
            produces = {"application/json"})
    public ResponseEntity<?> findCourseById(
            @PathVariable
                    Long courseId)
    {
        Course c = courseService.findCourseById(courseId);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @GetMapping(value = "/studcount", produces = {"application/json"})
    public ResponseEntity<?> getCountStudentsInCourses()
    {
        return new ResponseEntity<>(courseService.getCountStudentsInCourse(), HttpStatus.OK);
    }

    @PostMapping(value = "/course/add",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> addNewCourse(@Valid
                                              @RequestBody
                                                      Course newCourse) throws URISyntaxException
    {
        newCourse = courseService.save(newCourse);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();

        URI newCourseUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{courseid}").buildAndExpand(newCourse.getCourseid()).toUri();
        responseHeaders.setLocation(newCourseUri);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }


    @DeleteMapping("/courses/{courseid}")
    public ResponseEntity<?> deleteCourseById(@PathVariable long courseid)
    {
        courseService.delete(courseid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
