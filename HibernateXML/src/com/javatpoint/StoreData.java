package com.javatpoint;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class StoreData {
	private static SessionFactory factory; 
	public static void main(String[] args) {
		
	    Configuration cfg=new Configuration();  //create configuration object
	    cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file  
	      
	    //creating seession factory object  
	 factory=cfg.buildSessionFactory();  //getting sessioon factory object
	 StoreData sd=new StoreData();
	Integer eid= sd.addEmployee(10,"yyy","iiii");
	//Integer eid2= sd.addEmployee(2,"bbb","bb");
	//Integer eid3= sd.addEmployee(3,"ccc","cc");
	//System.out.println("eid=="+eid+"eid2"+eid2+"eid3"+eid3);
	//sd.listEmployees();
	 //sd.updateEmployee(eid,"pinky");
	
	    
	}

	
	public void updateEmployee(int eid,String fname){
		Session session=factory.openSession();
	  	  Transaction tr= session.beginTransaction();
	
	  	  Employee e=(Employee) session.get(Employee.class, eid);
	  	  e.setFirstName(fname);
	  	session.update(e);
	  	tr.commit();
	  	session.close();
		
	}
	public void listEmployees()
	{
		 Session session=factory.openSession();
	  	  Transaction tr= session.beginTransaction();	
	  	List<Employee>empList=session.createQuery("from Employee").list();
	  	for(Employee e:empList){
	  		System.out.println("list size=="+empList.size()+"id=="+e.getId()+"fname=="+e.getFirstName()+"lname=="+e.getLastName());
	  	
	  	}
	  	tr.commit();
	  	session.close();
	}
	public  int addEmployee(int id,String fname,String lname)
    {
		Integer eid=null;
  	  Session session=factory.openSession();//open session
  	  Transaction tr= session.beginTransaction();//begin transaction
  	  
  	Employee emp=new Employee(id,fname,lname);
  	eid=(Integer) session.save("emp2000", emp);
  	//eid=(Integer) session.save(emp);//saving object
  	tr.commit();//commit transaction
  	session.close();//close session
  	  
	return eid;
    }

}
