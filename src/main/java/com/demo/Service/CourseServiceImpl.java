package com.demo.Service;

import java.util.List;


import java.util.ArrayList;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Entity.Course;
import com.demo.Exception.BusinessException;
import com.demo.Exception.controllerException;

import com.demo.dao.CourseDao;

@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	private CourseDao courseDao;
	/*List<Course> list;
	public courseServiceIMPL() {
		list=new ArrayList();
		list.add(new Course(200,"java"));
		list.add(new Course(201,"angular"));
		list.add(new Course(202,"C++"));
	}*/
	
	@Override
	public List<Course> getCourses() {
		// TODO Auto-generated method stub
		return courseDao.findAll();
	}

	@Override
	public Course getCourse(long courseId) {
	/*	Course c=null;
		for(Course course:list) {
			if(course.getId()==courseId) {
				c=course;
				break;
			}
		}*/
		return courseDao.getOne(courseId);
	}

	@Override
	public Course addCourse(Course course) {
		if(course.getTitle().isEmpty()||course.getTitle().length()==0) {
			throw new BusinessException("601","Please enter proper name");
		}
		try {
			courseDao.save(course);
            return course;
		}catch(IllegalArgumentException e) {
			throw new BusinessException("602","given course is null"+e.getMessage());
		}catch(Exception e) {
			throw new BusinessException("603","Somthing is wrong here");
			
		}
		
		
	}

	@Override
	public Course updateCourse(Course course) {
		/*list.forEach(e->{
			if(e.getId()==course.getId()) {
				e.setTitle(course.getTitle());
			}
		});*/
        courseDao.save(course);
		
		return course;
	}

	@Override
	public void deleteCourse(long parseLong) {
		// TODO Auto-generated method stub
		/*list=this.list.stream().
				filter(e->e.getId()!=parseLong).
				collect(Collectors.toList());*/
		Course Entity=courseDao.getOne(parseLong);
		courseDao.delete(Entity);
		
	}

}
