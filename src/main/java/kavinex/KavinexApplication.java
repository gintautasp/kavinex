package kavinex;

import java.util.List;

// import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class KavinexApplication {

	public static void main(String[] args) {
		
		// SpringApplication.run( KavinexApplication.class, args );
		
		ApplicationContext context = new ClassPathXmlApplicationContext( "file:src/beans.xml" );
	
		Uzsakymai uzsakymai = (Uzsakymai) context.getBean( "uzsakymai" );
		uzsakymai.nuskaityti(); // tik ivedimas
		uzsakymai.ruostiPatiekalus();
		uzsakymai.patiekti();
		uzsakymai.isnesioti(); 							// tik išvedimas		

/*		
 		pagal:
 		
		https://www.tutorialspoint.com/spring/spring_jdbc_example.htm
		
	    UzsakymasJDBCTemplate uzsakymasJDBCTemplate = 
	    	         (UzsakymasJDBCTemplate)context.getBean("uzsakymasJDBCTemplate");
	    	      
	    	      System.out.println("------Listing Multiple Records--------" );
	    	      List<Uzsakymas> uzsakymai = uzsakymasJDBCTemplate.listUzsakymai();
	    	      
	    	      for (Uzsakymas record : uzsakymai) {
	    	         System.out.print("ID : " + record.getId() );
	    	         System.out.print(", Pav : " + record.getPav() );
	    	         System.out.println(
	    	        		", Trukme ruošimo : " 
	    	        		+ record.getTrukme_ruosimo()
	    	        );

	    	         System.out.println(
	    	        		", Trukme kaitinimo : " 
	    	        		+ record.getTrukme_kaitinimo()
	    	        );
	    	      }		
*/	    	      
	}
}
