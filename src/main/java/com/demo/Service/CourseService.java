package com.demo.Service;

import java.util.List;

import com.demo.Entity.Course;

public interface CourseService {
public List<Course> getCourses();
	
	public Course getCourse(long courseId);
	
	public Course addCourse(Course course);
	
	public Course updateCourse(Course course);
	
	public void deleteCourse(long parseLong);


}
