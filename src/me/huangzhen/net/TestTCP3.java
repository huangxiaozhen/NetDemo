package me.huangzhen.net;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

//从客户端发送图片到服务端，服务端受到图片保存到一个地方
//然后告诉客户端 发送的图片已经收到

public class TestTCP3
{

	@Test
	public void client()
	{

		// 1. 创建Socket对象，指定服务端的ip地址以及端口号
		Socket socket = null;
		// 2. 创建文件节点输入流，将图片输入到内存中
		FileInputStream fis = null;
		// 3. 创建 Socket 输出流，将读入的图片输出到服务端
		OutputStream os = null;
		// 4. 创建Socket 输入流，接收来自服务端的信息
		InputStream is = null;

		try
		{
			socket = new Socket("127.0.0.1", 9090);
			fis = new FileInputStream(new File("customer.jpg"));
			os = socket.getOutputStream();
			is = socket.getInputStream();

			// 5. 读取图片，送到输出流
			byte[] b = new byte[20];
			int len;
			while ((len = fis.read(b)) != -1)
			{
				os.write(b, 0, len);
			}

			// 6.显示关闭输出流
			socket.shutdownOutput();

			// 7.接收来自服务端的消息并打印到控制台
			StringBuilder sb = new StringBuilder();
			while ((len = is.read(b)) != -1)
			{
				sb.append(new String(b, 0, len));
			}

			System.out.println(sb.toString());

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			// 8.关闭各种流以及socket
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

			if (fis != null)
			{
				try
				{
					fis.close();
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
		// 1. 创建ServerSocket ，定义服务端的端口
		ServerSocket ss = null;
		// 2. 创建Socket ，接收客户端的请求
		Socket socket = null;
		// 3. 创建socket 的输入流，接收来自客户端的输出的信息
		InputStream is = null;
		// 4. 创建文件输出流，将客户端传递过来的图片存储到本地
		FileOutputStream fos = null;
		// 5. 创建socket输出流，告诉客户端图片已经接受成功
		OutputStream os = null;

		try
		{
			ss = new ServerSocket(9090);
			socket = ss.accept();
			is = socket.getInputStream();
			fos = new FileOutputStream( new File( "customer2.jpg" ) );
			os = socket.getOutputStream();
			
			//6.输入流接收客户端输出流的信息，并保存到本地
			byte[] b = new byte[20];
			int len;
			while( ( len = is.read(b) ) != -1 )
			{
				fos.write(b, 0, len);
			}
			
			//7. 给客户端发送消息，告诉已经接受成功
			os.write("发送的图片接受成功".getBytes());  
			
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}finally
		{
			//8. 关闭各种流，socket
			if ( os != null )
			{
				try
				{
					os.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
				
			}
			
			if ( fos != null )
			{
				try
				{
					fos.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
				
			}
			
			if ( is != null )
			{
				try
				{
					is.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
				
			}
			
			if ( socket != null )
			{
				try
				{
					socket.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
				
			}
			
			if ( ss != null )
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
 