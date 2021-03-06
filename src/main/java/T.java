import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Properties;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.PropertiesConfigurationLayout;

public class T {
	public static void main(String[] args) throws Exception {
		//Properties prop = new Properties();// 属性集合对象
		File f = new File("src/main/java/agent.properties");
		//FileInputStream fis = new FileInputStream(f);// 属性文件输入流
		//prop.load(fis);// 将属性文件流装载到Properties对象中
		//fis.close();// 关闭流
		PropertiesConfiguration prop = new PropertiesConfiguration(f);
		PropertiesConfigurationLayout proLayout = prop.getLayout();
		prop.setProperty("zone.id", "0000");
		Writer w = new FileWriter(f);
		proLayout.save(w);
		// 获取属性值，sitename已在文件中定义
		// 获取属性值，country未在文件中定义，将在此程序中返回一个默认值，但并不修改属性文件
		// System.out.println("获取属性值：country=" + prop.getProperty("country",
		// "中国"));

		// 修改sitename的属性值
	/*	prop.setProperty("password", "heihei1");
		// 文件输出流
		FileOutputStream fos = new FileOutputStream("src/test.properties");
		// 将Properties集合保存到流中
		prop.store(fos, "Copyright (c) Boxcode Studio");
		fos.close();// 关闭流
	*/	
		System.out.println("获取修改后的属性值：password=" + prop.getProperty("zone.id"));
	}
}
