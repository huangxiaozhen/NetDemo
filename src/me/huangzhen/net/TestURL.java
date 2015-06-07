package me.huangzhen.net;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.FieldPosition;

import org.junit.Test;

// URL 统一资源定位符 对应着互联网唯一的资源路径

//1. 获取 URL 的内容并且打印到控制台
//2. 获取到 URL 的内容，但是输出到一个新的文件

public class TestURL
{
	@Test
	public void test() throws IOException
	{
		//1. 创建一个URL类的对象
		URL url = null;
		try
		{
			url = new URL("http://localhost:8080/examples/hello.html");
			
			//2. URL 一些常用的方法的调用
			System.out.println( url.getProtocol() );
			System.out.println( url.getHost() );
			System.out.println( url.getPort());
			System.out.println( url.getFile() );
			System.out.println( url.getRef() );
			System.out.println( url.getQuery() );
			
			//3. 进行文件的读取，并且输出到控制台
			/*InputStream is = url.openStream();
			byte[] b = new byte[20];
			int len;
			StringBuilder sb = new StringBuilder();
			
			while( ( len = is.read(b)  ) != -1 )
			{
				sb.append( new String( b,0,len ) );
			}
			
			System.out.println( sb.toString() );*/
			
			//4. 读取并写入到一个新的文件中
			
			//创建 URLConnection创建
			URLConnection urlConnection =  url.openConnection();
			
			//读取
			InputStream is1 = urlConnection.getInputStream();
			FileOutputStream fos = new FileOutputStream( new File( "abc.txt" ) );
			
			byte[] b1 = new byte[20];
			int len1;
			
			while( ( len1 = is1.read(b1)  ) != -1 )
			{
				fos.write(b1, 0, len1);
			}
			
			fos.close();
			is1.close();
			
			
			
			
		} catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	

}
