package kavinex;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class UzsakymasJDBCTemplate implements UzsakymasDAO, Skaitymas  {
	
	   private DataSource dataSource;
	   private JdbcTemplate jdbcTemplateObject;	
	   
	   private List<Uzsakymas> list_uzsakymai;
	   private Uzsakymas[] uzsakymai;
	   private int kiek_uzsakymu;
	   private int nr_uzsakymo =  0;
	   
	   public void pradeti() {
		   
		   list_uzsakymai = listUzsakymai();
		   uzsakymai = list_uzsakymai.toArray( new Uzsakymas [ 0 ] );
		   
		   kiek_uzsakymu = uzsakymai.length;
		   nr_uzsakymo = 0;
	   }

		public boolean nuskaitytasFragmentas() {
			
			return nr_uzsakymo < kiek_uzsakymu;
		}
		
		public void skaitytiFragmenta() {
			
			nr_uzsakymo++;
		}
		
		public Uzsakymas paimtiFragmenta() {
			
			return 	
				uzsakymai [ nr_uzsakymo ] 
			;		
				
		}
	   
	   public void setDataSource(DataSource ds) {
		   
		   dataSource = ds;
		   this.jdbcTemplateObject = new JdbcTemplate(dataSource);		   
	   }
	   
	   /** 
	      * This is the method to be used to create
	      * a record in the Student table.
	   */
	   // public void create(String name, Integer age);
	   
	   /** 
	      * This is the method to be used to list down
	      * a record from the Student table corresponding
	      * to a passed student id.
	   */
	   public Uzsakymas getUzsakymas(Integer id) {
		   
		      String SQL = "select * from uzsakymai where id = ?";
		      Uzsakymas uzsakymas = jdbcTemplateObject.queryForObject(
		    	SQL, 
		         new Object[]{id}, new UzsakymasMapper());
		      
		      return uzsakymas;		   
	   }
	   
	   /** 
	      * This is the method to be used to list down
	      * all the records from the Student table.
	   */
	   public List<Uzsakymas> listUzsakymai() {

	    	String SQL = "select * from uzsakymai";
	    	List <Uzsakymas> uzsakymai = jdbcTemplateObject.query(
	    			SQL
	    			, new UzsakymasMapper()
	    	);
	    	return uzsakymai;
	   }

}
