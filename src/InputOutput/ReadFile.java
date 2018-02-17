package InputOutput;
import java.io.*;

public class ReadFile {
	public String first_line;
	public String second_line;
	
	@SuppressWarnings("deprecation")
	public ReadFile(String name){
		File file=new File(name);
	    FileInputStream fis = null;
	    BufferedInputStream bis = null;
	    DataInputStream dis = null;
	 
	    try {
	      fis = new FileInputStream(file);
	 
	      // Here BufferedInputStream is added for fast reading.
	      bis = new BufferedInputStream(fis);
	      dis = new DataInputStream(bis);
	
	      first_line=dis.readLine();
	      second_line=dis.readLine();

	 
	      // dispose all the resources after using them.
	      fis.close();
	      bis.close();
	      dis.close();
	 
	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	}
}
