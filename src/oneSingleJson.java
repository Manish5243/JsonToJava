import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class oneSingleJson {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, JsonGenerationException, JsonMappingException, IOException {
		
		
		
		ArrayList<CustomerDetails> a = new ArrayList<CustomerDetails>();
		JSONArray ja = new JSONArray();
		
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
			
			//ObjectMapper o = new ObjectMapper();
			//o.writeValue(new File("H:\\SDET_BY_RahulShetty\\JsonJava\\target\\customerInfo"+i+".json"), a.get(i));
			
			//create Json string from json object  ---> Gson jar
			Gson g = new Gson();
			String JsonString = g.toJson(a.get(i));
			ja.add(JsonString);
		}
 
		//Json simple jar
		
		JSONObject jo = new JSONObject();
		jo.put("data", ja);
		//System.err.println(jo.toJSONString());
		
		String unescape = StringEscapeUtils.unescapeJava(jo.toJSONString());
		//System.out.println(unescape);
		String Str1 = unescape.replace("\"{", "{");
		String Str2 = Str1.replace("}\"", "}");
		System.out.println(Str2);
		
		try (FileWriter file = new FileWriter("H:\\SDET_BY_RahulShetty\\JsonJava\\target\\Single.json")){
			file.write(Str2);
			//System.out.println("File copied");
			//System.out.println("\nJson Object : "+ jo);
		}
		
		//
		
		
		conn.close();
	}

}
