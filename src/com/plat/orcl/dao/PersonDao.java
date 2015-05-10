package com.plat.orcl.dao;

import java.util.List;

import com.plat.orcl.domain.Person;

public interface PersonDao {

	void addPerson(Person p);

	void deletePerson(String pid);
	
	void deletePersonByString(String xid, String yid);

	void deletePersonCascade(String pid);
	
	void deletePersonByStringCascade(String xid, String yid);

	void updatePerson(Person p);

	List<Person> findAll();

	Person findPersonById(String pid);

	List<Person> findPersonByString(String xid, String yid);

	Person findPersonAllById(String pid);

	List<Person> findPersonAllByString(String xid, String yid);

	Person findPersonAllByIdPassword(String pid, String password);

	Person findPersonByIdPassword(String pid, String password);

}