package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Entity.Course;
import com.demo.Exception.BusinessException;
import com.demo.Exception.controllerException;
import com.demo.Service.CourseService;
import com.demo.dao.CourseDao;


@RestController
public class MyController {
	@Autowired
	private CourseService cs;
	@GetMapping("/home")
	public String home() {
		return "Welcome to springboot";
	}
	
	@GetMapping("/courses")
	public List<Course> getCourses(){
		return this.cs.getCourses();
		
	}
	@GetMapping("/courses/{courseId}")
	public Course getCourse(@PathVariable String courseId) {
		return this.cs.getCourse(Long.parseLong(courseId));
	}
	
	@PostMapping("/courses")
	public ResponseEntity<?> addCourse(@RequestBody Course course){
		try{
			Course coursesave=cs.addCourse(course);
			return new ResponseEntity<Course>(coursesave,HttpStatus.CREATED);
			}catch(BusinessException e) {
			controllerException ce=new controllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<controllerException>(ce,HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			controllerException ce=new controllerException("611","Somthing went wrong");
            return new ResponseEntity<controllerException>(ce,HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping("/courses/{courseId}")
	public Course updateCourse(@RequestBody Course course) {
		return this.cs.updateCourse(course);
	}
	
	@DeleteMapping("/courses/{courseId}")
	public ResponseEntity<HttpStatus> deleteCourse(@PathVariable String courseId) {
		try {
			this.cs.deleteCourse(Long.parseLong(courseId));
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
