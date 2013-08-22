import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class DivideChar {
	private PrintStream p;
	private boolean bookStart = true;
	private String charStartExp="¡î";
	private boolean isInit;
	public DivideChar() {
		this(System.out);
	}

	
	public DivideChar(PrintStream print) {
		this.p = print;
	}

	public void print(Object o) {
		p.print(o);
	}

	public void println(Object o) {
		p.println(o);
	}
	public void println() {
		p.println();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String bookPath = "C:" + File.separatorChar + "Users"
				+ File.separatorChar + "Erica" + File.separatorChar
				+ "Downloads" + File.separatorChar+"Ê÷ÏÂÒ°ºü"+File.separatorChar + "Âù»Ä¼Ç.txt";
		DivideChar dc = new DivideChar();
		File book = new File(bookPath);
		dc.divided(book);
		
	}
	public void divided(File book) {
		File character = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(book));
			String line;
			while ((line = br.readLine()) != null) {
				if (bookStart) {
					p.close();
					character = new File("d:\\moon\\"+book.getName());
					p = new PrintStream(character);
					bookStart = false;
				}
				if (line.matches("\\s{1,15}µÚ(.*)¾í(.*)")) {
					System.out.println(line);
					character = new File("d:\\moon\\"+line+".txt");
					p.close();
					p = new PrintStream(character);
					continue;
				}
				if(line==null || line.length()==0||line.trim().length()==0){
					continue;
				}
				println(line);
				println();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void setCharStartExp(String charStartExp) {
		this.charStartExp = charStartExp;
	}


	public String getCharStartExp() {
		return charStartExp;
	}


	public void setInit(boolean isInit) {
		this.isInit = isInit;
	}


	public boolean isInit() {
		return isInit;
	}


}
