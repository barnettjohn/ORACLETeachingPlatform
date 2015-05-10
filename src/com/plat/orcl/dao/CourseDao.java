package com.plat.orcl.dao;

import java.util.List;

import com.plat.orcl.domain.Course;

public interface CourseDao {

	void addCourse(Course c);

	void deleteCourse(String cid);
	
	void deleteCourseByString(String xid, String yid);

	void updateCourse(Course c);

	List<Course> findAll();

	Course findCourseById(String cid);

	List<Course> findCourseByString(String xid, String yid);

	Course findCourseAllById(String cid);

	List<Course> findCourseAllByString(String xid, String yid);

	void deleteCourseCascade(String cid);

	void deleteCourseByStringCascade(String xid, String yid);

	
}