import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FindString {
	private List<String> ignoreType = new ArrayList<String>();
	
	private List<String> ignorePath = new ArrayList<String>();

	public static void main(String[] args) throws IOException {
		String workspace = "D:\\workspace\\rds\\";
//		String workspace = "D:\\workspace\\temperature_control_system\\";
		String agent = workspace + "rds-agent\\src\\main";
		String manager = workspace + "rds-manager\\";
		String test =workspace+"rds-test\\";
		File f = new File(workspace);
		FindString fs = new FindString();
		fs.getIgnoreType().add("class");
		fs.getIgnoreType().add("index");
		fs.getIgnoreType().add("jar");
		fs.getIgnoreType().add("bak");
		fs.getIgnoreType().add("log");
		//fs.getIgnoreType().add("sh");
		fs.getIgnoreType().add("sql");
		fs.getIgnoreType().add("rb");
		//fs.getIgnoreType().add("xml");
		fs.getIgnoreType().add("swp");
		fs.getIgnoreType().add("so");
		fs.getIgnoreType().add("zip");
		fs.getIgnorePath().add(".git");	
		fs.getIgnoreType().add("properties");	
		fs.getIgnoreType().add("sh");	
		fs.getIgnorePath().add("test");	
		fs.getIgnoreType().add("develop");
		//fs.getIgnoreType().add("properties");
		fs.traverse(f, 0, "javax.xml.ws");
		//monitor.command.uri.suffix
	}

	/**
	 * 
	 * 遍历文件/文件夹中的所有文件，看文件中是否存在所查找的字符串
	 * @param f
	 * @param level
	 * @param findStr
	 *
	 */
	public void traverse(File f, int level, String findStr) {
		if (f.isFile()) {
			if (!(f.isHidden()) && (!isIgnoreType(f.getName()))) {
				findStrInFile(f, findStr);
			}
		} else {
			File[] eChilds = f.listFiles();
			if (eChilds != null) {
				for (int i = 0; i < eChilds.length; i++) {
					String name = eChilds[i].getName();
					if(isIgnorePathName(name)&&(!eChilds[i].isFile())){
						continue;
					}
					traverse(eChilds[i], level + 1, findStr);
				}
			}
		}
	}

	public void findStrInFile(File f, String findStr) {
		try {
			boolean isFind = false;
			int line = 0;
			BufferedReader read = new BufferedReader(new FileReader(f));
			String str = null;
			while ((str = read.readLine()) != null) {
				line++;
				if (str.indexOf(findStr) != -1) {
					if (!isFind) {
						System.out.println(f.getAbsolutePath());
					}
					isFind = true;
					System.out.println("\t" + line + ":\t" + str);
				}
			}
			read.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void findFile(File f,String fileName){
		if (f.isFile()) {
			if (f.getName()!= null && f.getName().indexOf(fileName) != -1) {
				System.out.println(f.getAbsolutePath());
			}
		} else {
			File[] eChilds = f.listFiles();
			if (eChilds != null) {
				for (int i = 0; i < eChilds.length; i++) {
					findFile(eChilds[i], fileName);
				}
			}
		}
	}

	public boolean isIgnoreType(String fileName) {
		String[] fileNames = fileName.split("\\.");

		String fileType = fileNames[fileNames.length - 1];
		for (String type : ignoreType) {
			if (type.equals(fileType)) {
				return true;
			}
		}
		return false;
	}

	public boolean isIgnorePathName(String fileName) {

		for (String path : ignorePath) {
			/*if (type.equals(fileName)) {
				return true;
			}*/
			if (fileName.indexOf(path) != -1) {
				return true;
			}
		}
		return false;
	}
	
	public List<String> getIgnoreType() {
		return ignoreType;
	}

	public void setIgnoreType(List<String> ignoreType) {
		this.ignoreType = ignoreType;
	}

	public List<String> getIgnorePath() {
		return ignorePath;
	}

	public void setIgnorePath(List<String> ignorePath) {
		this.ignorePath = ignorePath;
	}

}
