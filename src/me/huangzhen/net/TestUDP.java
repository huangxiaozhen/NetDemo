
package me.huangzhen.net;

import static org.junit.Assert.*;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.junit.After;
import org.junit.Test;

//测试java 网络中 UDP的程序
//其中有两个重要的类 1. 关于UDP desocket  2. 关于UDP 发送的数据包

public class TestUDP
{

	

	@Test
	public void send()
	{
		//1. 创建 UDP 的socket
		DatagramSocket ds = null;
		//2. 创建发送的数据包
		DatagramPacket dp = null;
		
		try
		{
			ds =  new DatagramSocket();
			
			//3, 需要发送的数据
			byte[] b = "我是客户端".getBytes();
			dp = new DatagramPacket(b, 0, b.length, InetAddress.getByName("127.0.0.1"), 9090);
			
			//4. 调用UDP socket 的send 方法
			ds.send(dp);
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}finally
		{
			ds.close();
		}
		
	}
	
	@Test
	public void receive()
	{
		//1. 创建datagramsocekt 对象，服务端需要指明队端口号
		DatagramSocket ds = null;
		//2. 创建接收的包
		DatagramPacket dp = null;
		
		try
		{
			ds = new DatagramSocket(9090);
			byte[] buf = new byte[1024];
			dp = new DatagramPacket(buf, 0, buf.length);
			
			ds.receive(dp);
			
			String  string = new String( dp.getData() , 0, dp.getLength());
			
			System.out.println(  string );
			
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}finally{
			ds.close();
		}
		
	}
	
	

}
