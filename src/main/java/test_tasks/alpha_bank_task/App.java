package test_tasks.alpha_bank_task;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.jdbc.Work;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.Client;
import com.ReasonInitializer;
import com.RejectionReasons;
import com.config.AppConfig;


import config.Clients;
import oracle.jdbc.OracleTypes;

import org.springframework.core.env.Environment;

public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException
    {
        
    	Class cls = Class.forName("oracle.jdbc.driver.OracleDriver");
    	System.out.println("Class found = " + cls.getName());
    	
    	AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    	
    	SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) context.getBean("sessionFactory");
    	
    	Session session = sessionFactoryImpl.openSession();
    	
    	List<Client> deniedClients = session.doReturningWork(new ReturningWork<List<Client>>() {
        	  @Override
        	   public List<Client>  execute(Connection connection) throws SQLException {
        		  
        		  List<Client> dc = new ArrayList<Client>();

        		  CallableStatement call = connection.prepareCall("{? = call chris.GETDENIEDCLIENTS(?, ?)}");
	        	    call.registerOutParameter( 1, OracleTypes.CURSOR); 
	        	    call.setString(2, "10-Jan-16 1:10:10.123000");
	        	    call.setString(3, "11-Jan-18 12:10:10.123000");
	        	    call.executeQuery();
	        	    
	        	  ResultSet rs = (ResultSet) call.getObject(1);

        	    if (!rs.isBeforeFirst() ) {    
        	        System.out.println("No data"); 
        	    } else{
        	    	
        	    	while (rs.next()) {
        	    		
        	    		dc.add(new Client( rs.getInt   ("idclient"),
        	    						   rs.getString("name"),
        	    						   rs.getString("lastname"),
        	    						   rs.getString("adress"),
        	    						   rs.getInt   ("creditamount"),
        	    						   rs.getInt   ("rejectionreasonid")));

        	    	}
        	    }

        	    
        	    return dc;
        	  }
        	});
    	
    	System.out.println("Количество отказов по клиентам : " + deniedClients.size());
    	Map <RejectionReasons, Client[] > result =  new EnumMap <RejectionReasons, Client[]>(RejectionReasons.class);
    	ReasonInitializer ri = new ReasonInitializer();
    	
    	Set<Integer> reasonIds = new HashSet<Integer>();
    	
    	for (Client cl : deniedClients) reasonIds.add(cl.getRejectionreasonid());
    		
    	for (Integer reasonId : reasonIds )	{
    		List<Client> tmp = new ArrayList<Client>();
    		for (Client cl : deniedClients) {
    			if(cl.getRejectionreasonid().equals(reasonId)){
    				tmp.add(cl);
    			}
    			
    		}
    		Client[] clientsArray = new Client[tmp.size()];
    		clientsArray = tmp.toArray(clientsArray);
    		result.put(ri.getDeniedReason((long)reasonId), clientsArray);
    		
    		
    	}
    	
    	
    	//===========================================================================
    	
    	for (Entry<RejectionReasons, Client[]> entry : result.entrySet()) {
    		System.out.println("Причина отказа : "+ entry.getKey());
    		for ( Client client : entry.getValue()) client.printinfo();
    		
        }
    	
    	session.close();
    	
    
    }
}
