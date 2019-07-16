package kavinex;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class UzsakymasMapper implements RowMapper<Uzsakymas> {
		
   public Uzsakymas mapRow(ResultSet rs, int rowNum) throws SQLException {
	   
      Uzsakymas uzsakymas = new Uzsakymas();
      uzsakymas.setId(rs.getInt("id"));
      uzsakymas.setPav(rs.getString("pav"));
      uzsakymas.setTrukme_ruosimo(rs.getInt("trukme_ruosimo"));
      uzsakymas.setTrukme_kaitinimo(rs.getInt("trukme_kaitinimo"));
      
      return uzsakymas;
   }
}	
	
