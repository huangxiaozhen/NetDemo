package me.huangzhen.net;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.server.ServerCloneException;

import org.junit.Test;

//测试 TCP 客户端以及服务端进行测试
//网络编程其实上就是 Socket 的编程

public class TestTCP
{

	// TCP 编程 对于异常的处理全部都需要利用 try catch 处理，因为打开的资源需要
	// 完全的确保关闭
	@Test
	public void client1()
	{
		// 1.定义socket
		Socket socket = null;
		OutputStream os = null;

		try
		{
			socket = new Socket("127.0.0.1", 9090);

			// 2. 得到socket 的输出流
			os = socket.getOutputStream();

			// 3. 从输出流向服务端发送消息
			os.write("你好我是客户端".getBytes());

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			// 4. 进行 流 socket 关闭
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
	public void server1()
	{
		// 1.定义serversocket ，定义服务器的端口
		ServerSocket ss = null;

		// 2.调用ss 的accept 的方法，用来监听客户消息
		Socket socket = null;

		// 3.创建输入流对象，用来接收从客户端传递过来的消息
		InputStream is = null;

		try
		{
			ss = new ServerSocket(9090);
			//阻碍式
			socket = ss.accept();
			is = socket.getInputStream();

			// 4.输入流读取数据
			byte[] b = new byte[20];
			int len;
			StringBuffer stringBuffer = new StringBuffer();
			while ((len = is.read(b)) != -1)
			{
				stringBuffer.append(new String(b, 0, len));
			}
			
			System.out.println( stringBuffer.toString() );
			
			//5. 得到客户端的主机信息
			System.out.println( "客户端的地址:" + socket.getInetAddress().getHostAddress()  
					+ "客户端的主机名" + socket.getInetAddress().getHostName() );

		} catch (Exception e)
		{
			e.printStackTrace();
		}finally
		{
			//5. 关闭 流 socket serversocket
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

	// 客服端
	@Test
	public void client() throws UnknownHostException, IOException
	{
		// 1. 创建 Socket 需要指定服务器端的 IP 地址，以及端口号
		Socket socket = new Socket("127.0.0.1", 9091);

		// 2.得到输出流
		OutputStream os = socket.getOutputStream();

		// 3. 具体的输出过程
		os.write("我是客服端".getBytes());

		// 4.关闭对应的流以及socket
		os.close();
		socket.close();
	}

	@Test
	public void server() throws IOException
	{
		// 1.创建 serverScoket 对象 通过构造器指定自己的 端口号
		ServerSocket ss = new ServerSocket(9091);

		// 2. 调用serversocket 的accept ，返回一个 socket 的对象。进行监听获取客户端的socket
		Socket socket = ss.accept();

		// 3. 调用socket 对象的getInputStream方法，得到socket的输入流
		InputStream is = socket.getInputStream();
		byte[] b = new byte[20];
		int len;

		while ((len = is.read(b)) != -1)
		{
			// 4. 得到输入流中的内容，打印到控制台
			String str = new String(b, 0, len);
			System.out.println(str);
		}

		// 5. 关闭相应的流以及socket serversocket的对象
		is.close();
		socket.close();
		ss.close();
	}
}
