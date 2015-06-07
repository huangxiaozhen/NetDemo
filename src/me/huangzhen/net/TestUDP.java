
package me.huangzhen.net;

import static org.junit.Assert.*;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.junit.After;
import org.junit.Test;

//����java ������ UDP�ĳ���
//������������Ҫ���� 1. ����UDP desocket  2. ����UDP ���͵����ݰ�

public class TestUDP
{

	

	@Test
	public void send()
	{
		//1. ���� UDP ��socket
		DatagramSocket ds = null;
		//2. �������͵����ݰ�
		DatagramPacket dp = null;
		
		try
		{
			ds =  new DatagramSocket();
			
			//3, ��Ҫ���͵�����
			byte[] b = "���ǿͻ���".getBytes();
			dp = new DatagramPacket(b, 0, b.length, InetAddress.getByName("127.0.0.1"), 9090);
			
			//4. ����UDP socket ��send ����
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
		//1. ����datagramsocekt ���󣬷������Ҫָ���Ӷ˿ں�
		DatagramSocket ds = null;
		//2. �������յİ�
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
