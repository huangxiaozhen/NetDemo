package me.huangzhen.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/*
 * 网络通信的第一个要素：IP地址。  通过 IP 地址唯一的定位互联网上的一台主机
 */
//关于网路编程，有两个方面需要注意的 1. 如何知道要和那一台主机进行通信。这里就要知道怎么样去定位网络上的一台主机
//2. 如何进行通信  有两种方式 1）tcp  2）udp
// 关于通信的第一点  如何表示网络上的主机的地址 包含 IP 以及  域名
//3. inetaddress 中有两个方法 第一个 gethostname  gethostaddress

// ip 与 端口号的组合得出 套接字 Socket

public class TestNetDemo1
{
	public static void main(String[] args) throws UnknownHostException
	{
		//1. 定义inetAddress
		InetAddress inet = null;
		try
		{
			inet = InetAddress.getByName("www.ganghongyue.com");
		} catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		
		System.out.println( inet );
		
		//2. inet对应的类的方法读取inet中的属性
		System.out.println( inet.getHostName() );
		System.out.println( inet.getHostAddress() );
		
		//3.也可以通过 ip地址直接获取 inetaddress
		inet = InetAddress.getByName("115.28.216.163");
		System.out.println( inet);
		
		
		//4. 通过inetaddress 获取本机的信息
		inet = InetAddress.getLocalHost();
		System.out.println( inet.getHostName() );
		System.out.println( inet.getHostAddress() );
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
