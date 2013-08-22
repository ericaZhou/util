import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 选择一个目录，将目录下的所有文件名打印出来
 * @author Erica
 *
 */
public class PrintContent {
	private List<String> results = new ArrayList<String>();
	public List<String> getResults() {
		return results;
	}

	public void setResults(List<String> results) {
		this.results = results;
	}

	public static void main(String[] args) {
		boolean flag =true;
		String FileName = "e://";
		File E =new File(FileName);
		PrintContent pc = new  PrintContent(); 
		pc.printFile(E, 0);
		FileWriter fw = null;
        try {
        	fw = new FileWriter("d://test.txt",true);
			for(String s : pc.getResults()){
				s+="\r\n";
	            fw.write(s);
			}
			fw.close();
        }catch (IOException e1) {
            e1.printStackTrace();
            System.out.println("写入失败");
            System.exit(-1);
        }
	}
	
	public void printFile(File f,int level){
		if(f.isFile()){
			if(!f.isHidden()){
				results.add(this.getPrefix(level)+"|-"+f.getName());
			}
		}else{
			results.add(this.getPrefix(level)+"|-"+f.getName());
			File[] eChilds = f.listFiles();
			if(eChilds!=null){
				for(int i=0 ;i<eChilds.length;i++){
					printFile(eChilds[i], level+1);
				}
			}
		}
	
	}
	
	public String getPrefix(int level){
		String result = "";
		while(level>0){
			result += "  ";
			level--;
		}
		return result;
	}
}
