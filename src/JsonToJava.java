import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonToJava {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, JsonGenerationException, JsonMappingException, IOException {
		
		
		
		ArrayList<CustomerDetails> a = new ArrayList<CustomerDetails>();
		
		// TODO Auto-generated method stub
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business", "root", "root");
		
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select * from CustomerInfo where purchasedDate=CURDATE() and Location ='Asia'");
		
		while(rs.next()) {
			
			CustomerDetails c = new CustomerDetails();
		
			c.setCourseName(rs.getString(1));
			c.setPurchaseDate(rs.getString(2));
			c.setAmount(rs.getString(3));
			c.setLocation(rs.getString(4));
			
			
			a.add(c);
			//System.out.println(rs.getString(1));
			//rs.getInt(2);
			
			//System.out.println(c.getAmount());
			//System.out.println(c.getCourseName());
			//System.out.println(c.getLocation());
			//System.out.println(c.getPurchaseDate());
		}
		
		for(int i=0; i<a.size(); i++) {
			
			ObjectMapper o = new ObjectMapper();
			o.writeValue(new File("H:\\SDET_BY_RahulShetty\\JsonJava\\target\\customerInfo"+i+".json"), a.get(i));
		}
		
		
		conn.close();
	}

}
