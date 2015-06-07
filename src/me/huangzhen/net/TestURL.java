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

// URL ͳһ��Դ��λ�� ��Ӧ�Ż�����Ψһ����Դ·��

//1. ��ȡ URL �����ݲ��Ҵ�ӡ������̨
//2. ��ȡ�� URL �����ݣ����������һ���µ��ļ�

public class TestURL
{
	@Test
	public void test() throws IOException
	{
		//1. ����һ��URL��Ķ���
		URL url = null;
		try
		{
			url = new URL("http://localhost:8080/examples/hello.html");
			
			//2. URL һЩ���õķ����ĵ���
			System.out.println( url.getProtocol() );
			System.out.println( url.getHost() );
			System.out.println( url.getPort());
			System.out.println( url.getFile() );
			System.out.println( url.getRef() );
			System.out.println( url.getQuery() );
			
			//3. �����ļ��Ķ�ȡ���������������̨
			/*InputStream is = url.openStream();
			byte[] b = new byte[20];
			int len;
			StringBuilder sb = new StringBuilder();
			
			while( ( len = is.read(b)  ) != -1 )
			{
				sb.append( new String( b,0,len ) );
			}
			
			System.out.println( sb.toString() );*/
			
			//4. ��ȡ��д�뵽һ���µ��ļ���
			
			//���� URLConnection����
			URLConnection urlConnection =  url.openConnection();
			
			//��ȡ
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
