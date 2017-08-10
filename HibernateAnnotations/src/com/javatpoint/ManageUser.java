package com.javatpoint;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.CountProjection;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;


public class ManageUser {
	private static SessionFactory factory; 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		factory = ((AnnotationConfiguration) new AnnotationConfiguration().
                configure()).
                //addPackage("com.xyz") //add package if used.
                addAnnotatedClass(User.class).
                buildSessionFactory();
	    //cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file  
	    
	    //creating session factory object  
	// factory=cfg.buildSessionFactory();  //getting sessioon factory object
	 ManageUser mu=new ManageUser();
	//Integer uid= mu.addUser(1,"pranithach","myp");
	//Integer uid2= mu.addUser(2,"pinky","kphb");
	//Integer uid3= mu.addUser(3,"pooja","mad");
	//System.out.println("uid=="+uid+"uid2"+uid2+"uid3"+uid3);
	//mu.listUsers();
	//mu.updateUser(uid, "location11");
	//mu.getfilteredUsers();
	 mu.totalUsers();
	//mu.getmultifilteredUsers();
	}
	public void totalUsers(){
		Session session=factory.openSession();
	  	 Criteria cr=session.createCriteria(com.javatpoint.User.class);
	  	 List count=cr.setProjection(Projections.rowCount()).list();
	  	 List sum=cr.setProjection(Projections.sum("userid")).list();
	  	 System.out.println("count.get(0)==="+count.get(0)+"sum of userid==="+sum.get(0));
	}
	
	public void getmultifilteredUsers(){
		Session session=factory.openSession();
	  	 Criteria cr=session.createCriteria(com.javatpoint.User.class);
	  	cr.setFirstResult(1);
	  	cr.setMaxResults(3);
	  	List results = cr.list();
	  	 System.out.println("results===="+results.size());
	  	 
	  	 Criterion userid=Restrictions.eq("userid", 4);
	  	 Criterion userName=Restrictions.eq("userName", "pranithach");
	  
	  List<User> crList=	cr.add(Restrictions.and(userid, userName)).list();
	  
	  
	 for(User cu:crList)
	 {
		System.out.println(cu.getUserid()+cu.getLocation()+cu.getUserName()); 
	 }
	 
	  	session.close();
		
	}
	public void getfilteredUsers(){
		Session session=factory.openSession();
	  	 Criteria cr=session.createCriteria(com.javatpoint.User.class);
	  	 Criteria cr1=session.createCriteria(com.javatpoint.User.class);
	 /* List<User> crList=	cr.add(Restrictions.eq("userName", "pinky")).list();
	 for(User cu:crList)
	 {
		System.out.println(cu.getUserid()+cu.getLocation()+cu.getUserName()); 
	 }*/
	 cr1.add(Restrictions.gt("userid", 2));
	List<User>descorderlist= cr1.addOrder(Order.desc("userid")).list();
	 
	 for(User d:descorderlist)
	 {
		System.out.println(d.getUserid()+d.getLocation()+d.getUserName()); 
	 }
	  	session.close();
		
	}
	public void updateUser(Integer uid, String location ){
	      Session session = factory.openSession();
	      Transaction tx = null;
	 
	         tx = session.beginTransaction();
	         User u = 
	                    (User)session.get(User.class, uid); 
	         u.setLocation(location);
			 session.update(u); 
	         tx.commit();
	      System.out.println("updated===");
	         session.close(); 
	     
	   }
	public void listUsers( ){
	      Session session = factory.openSession();
	      Transaction tx = null;
	 
	         tx = session.beginTransaction();
	         List<User> usersList = session.createQuery("from User").list(); 
	         for (User u:usersList){
	          
	            System.out.print("userid: " + u.getUserid()); 
	            System.out.print("  user Name: " + u.getUserName()); 
	            System.out.println("  location: " + u.getLocation()); 
	         }
	         tx.commit();
	  
	         session.close(); 
	      
	   }
	public  int addUser(int id,String uname,String location)
    {
		System.out.println("in adduser===");
		Integer uid=null;
  	  Session session=factory.openSession();//open session
  	  Transaction tr= session.beginTransaction();//begin transaction
  	System.out.println("before adding===");
  	User u=new User();
  	u.setUserid(id);
  	u.setUserName(uname);
  	u.setLocation(location);
  	System.out.println("after adding===");
  	uid=(Integer) session.save(u);//saving object
  	
  	tr.commit();//commit transaction
  	session.close();//close session
  	System.out.println("session closed===");
	return uid;
    }
	
	
}
