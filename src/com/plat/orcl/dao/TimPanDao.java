package com.plat.orcl.dao;

import java.util.List;

import com.plat.orcl.domain.TimePlan;

public interface TimPanDao {

	void addTimePlan(TimePlan tp);

	void deleteTimePlan(String tid);

	void deleteTimePlanCascade(String tid);

	void updateTimePlan(TimePlan tp);

	List<TimePlan> findAll();

	TimePlan findTimePlanById(String tid);

	TimePlan findTimePlanAllById(String tid);

	List<TimePlan> findTimePlanByString(String xid, String yid);

	List<TimePlan> findTimePlanAllByString(String xid, String yid);

}