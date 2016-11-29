package base;

import static org.junit.Assert.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import base.PersonDAL;


public class Person_Test {
		
	private static PersonDomainModel person1;
	private static PersonDomainModel person2;
	private static UUID person1UUID = UUID.randomUUID();
	private static UUID person2UUID = UUID.randomUUID();
	
	
	@BeforeClass
	public static void personInstance() throws Exception{
		
		Date person1Birth = null;
		Date person2Birth = null;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		 person1 = new PersonDomainModel();
		 person2 = new PersonDomainModel();
		 
		try {
			person1Birth = dateFormat.parse("1994-11-27");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		person1.setPersonID(person1UUID);
		person1.setFirstName("Mingkun");
		person1.setMiddleName("a");
		person1.setLastName("Chen");
		person1.setBirthday(person1Birth);
		person1.setCity("Elkton");
		person1.setStreet("702 Stone Gate Blvd");
		person1.setPostalCode(21921);
		
		person2.setPersonID(person1UUID);
		person2.setFirstName("Jesse");
		person1.setMiddleName("W");
		person1.setLastName("Nelson");
		person1.setBirthday(person2Birth);
		person1.setCity("Maplewood");
		person1.setStreet("19 Cedar Street");
		person1.setPostalCode(21921);
		
	}
	@Before
	public void Person_1(){
		
		
		base.PersonDAL.addPerson(person1);
	}
	
	@Test 
	public void Test1(){
		
		
		assertTrue(base.PersonDAL.getPerson(person1UUID).getFirstName().equalsIgnoreCase("Mingkun"));
	}
	
	
	@Before
	public void person_2(){
		
		base.PersonDAL.addPerson(person2);
		PersonDomainModel updated = base.PersonDAL.getPerson(person2UUID);
		updated.setFirstName("Jim");
		base.PersonDAL.updatePerson(updated);
		
	}
	
	
	
	
	@Test
	public void Test2(){
		
		assertTrue(base.PersonDAL.getPerson(person2UUID).getFirstName().equalsIgnoreCase("Jim"));
		
	}
	
	
	@Test
	public void Test3(){
		
		ArrayList<PersonDomainModel> array_list =  base.PersonDAL.getPersons();
		
		assertTrue( array_list.size() == 2);
	}
	
	@Test
	public void Test4(){
		
		base.PersonDAL.deletePerson(person1UUID);
		
		ArrayList<PersonDomainModel> array_list =  base.PersonDAL.getPersons();
		
		assertTrue( array_list.size() == 1);
		
		
	}
	
	@After
	public void closeTest(){
		
		base.PersonDAL.deletePerson(person2UUID);
		
	}
	
	

}
