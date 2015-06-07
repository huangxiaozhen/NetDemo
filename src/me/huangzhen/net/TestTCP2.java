package me.huangzhen.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

// TCP 测试第二个程序， 客户端给服务端发送消息；服务端受到消息后，再给客户端发送消息，这样
// 就实现了客户端与服务端之间的一个通信的过程

public class TestTCP2
{
	@Test
	public void client2()
	{
		//1. 创建socket对象
		Socket socket = null;
		//2. 创建输出流对象
		OutputStream os = null;
		//3. 创建输入流对象，接收从服务端传递过来的消息
		InputStream is = null;
		
		try
		{
			socket = new Socket("127.0.0.1",9090);
			os = socket.getOutputStream();
			is = socket.getInputStream();
			
			//4. 向服务端发送消息
			os.write("我是客户端".getBytes());
			
			//PS.需要显示的指定输出已经结束
			socket.shutdownOutput();
			
			//5.接收从服务端传递过来的消息
			byte[] b= new byte[20];
			int len;
			StringBuilder sb = new StringBuilder();
			while( ( len = is.read(b) ) != -1 )
			{
				sb.append( new String( b,0,len )  );
			}
			
			System.out.println( sb.toString() );
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			//6. 关系各种流以及socket
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
			
			
		}
		
	}
	
	@Test
	public void server2()
	{
		//1. 创建 ServerSocket 对象，指定服务端端的端口号
		ServerSocket ss = null;
		//2. 调用serverSocket 对象的方法来接收从客户端消息
		Socket socket = null;
		//3. 创建输入流，接收消息
		InputStream is = null;		
		//4. 创建输出流，用来向客户端传递消息
		OutputStream os = null;
		
		try
		{
			ss =  new ServerSocket(9090);
			socket = ss.accept();
			is = socket.getInputStream();
			os = socket.getOutputStream();
			
			//5. 接收并打印从客户端传递过来的消息
			byte[] b = new byte[20];
			int len;
			StringBuilder sb = new StringBuilder();
			//字节流的输入流是阻塞式的，所以程序会在这里阻塞中，需要在客户端现实的指明 输出已经结束
			//read 知道客户端输出结束，退出，执行123行的操作
			while( ( len = is.read(b) ) != -1 ) 
			{
				sb.append( new String( b,0,len ) );
			}
			
			System.out.println( sb.toString() );
			
			//6. 向客户端发送消息
			os.write( "已经接受您的消息".getBytes() );
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}finally
		{
			//7. 关闭各种流和socket
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
			
			
			
			if ( os!= null )
			{
				try
				{
					os.close();
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
