import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExtractJson {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		
		ObjectMapper o=new ObjectMapper();
		CustomerDetailsAppium c=o.readValue(new File("H:\\SDET_BY_RahulShetty\\JsonJava\\target\\Single.json"), CustomerDetailsAppium.class);
		
		System.out.println(c.getCourseName());
	}

}
