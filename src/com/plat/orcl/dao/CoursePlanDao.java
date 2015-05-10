package com.plat.orcl.dao;

import java.util.List;

import com.plat.orcl.domain.CoursePlan;

public interface CoursePlanDao {

	void addCoursePlan(CoursePlan cp);

	void deleteCoursePlanByCid(String cid);

	void deleteCoursePlanByTid(String tid);

	void deleteCoursePlan(CoursePlan cp);

	void updateCoursePlan(CoursePlan oldcp, CoursePlan newcp);

	List<CoursePlan> findAll();

	List<CoursePlan> findCoursePlanByCid(String cid);

	List<CoursePlan> findCoursePlanByTid(String tid);

	List<CoursePlan> findCoursePlanAllByCid(String cid);

	List<CoursePlan> findCoursePlanAllByTid(String tid);


}