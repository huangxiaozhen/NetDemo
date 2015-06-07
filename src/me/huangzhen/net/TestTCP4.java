package me.huangzhen.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import org.junit.Test;

//客户端给服务端发送文本，服务端把文本转成大写之后再传递后客户端

public class TestTCP4
{
	@Test
	public void client()
	{
		Socket socket = null;
		OutputStream os = null;
		InputStream is = null;

		try
		{
			socket = new Socket(InetAddress.getByName("127.0.0.1"), 9090);
			os = socket.getOutputStream();
			is = socket.getInputStream();
			
			System.out.println( "请输入字符串：" );
			Scanner scanner = new Scanner(System.in);
			String str =  scanner.next();
			
			
			os.write(str.getBytes());

			socket.shutdownOutput();

			byte[] b = new byte[20];
			int len;
			StringBuilder sb = new StringBuilder();

			while ((len = is.read(b)) != -1)
			{
				sb.append(new String(b, 0, len));
			}

			System.out.println("接收到从服务端传递过来的值是：" + sb.toString());

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			if (is != null)
			{
				try
				{
					is.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			
			if (os != null)
			{
				try
				{
					os.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}

			}
			
			if (socket != null)
			{
				try
				{
					socket.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}

			}

		}

	}
	
	@Test
	public void server()
	{
		ServerSocket ss = null;
		Socket socket = null;
		OutputStream os = null;
		InputStream is = null;
		
		try
		{
			ss = new ServerSocket(9090);
			socket =  ss.accept();
			os = socket.getOutputStream();
			is = socket.getInputStream();
			
			byte[] b = new byte[20];
			int len;
			StringBuilder sb = new StringBuilder();
			
			while( ( len = is.read(b) ) != -1  )
			{
				sb.append(  new String( b,0,len ) );
			}
			
			System.out.println("接收到从客户端传递过来的值是：" + sb.toString());
			
			String string =  sb.toString();
			string = string.toUpperCase();
			
			os.write(  string.getBytes() );
			
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}finally{
			
			if( os != null )
			{
				try
				{
					os.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			
			if( is != null )
			{
				try
				{
					is.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			
			if( socket != null )
			{
				try
				{
					socket.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			
			if( ss != null )
			{
				try
				{
					ss.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			
		}
	}
	

}
