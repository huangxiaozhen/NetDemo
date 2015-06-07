package me.huangzhen.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/*
 * ����ͨ�ŵĵ�һ��Ҫ�أ�IP��ַ��  ͨ�� IP ��ַΨһ�Ķ�λ�������ϵ�һ̨����
 */
//������·��̣�������������Ҫע��� 1. ���֪��Ҫ����һ̨��������ͨ�š������Ҫ֪����ô��ȥ��λ�����ϵ�һ̨����
//2. ��ν���ͨ��  �����ַ�ʽ 1��tcp  2��udp
// ����ͨ�ŵĵ�һ��  ��α�ʾ�����ϵ������ĵ�ַ ���� IP �Լ�  ����
//3. inetaddress ������������ ��һ�� gethostname  gethostaddress

// ip �� �˿ںŵ���ϵó� �׽��� Socket

public class TestNetDemo1
{
	public static void main(String[] args) throws UnknownHostException
	{
		//1. ����inetAddress
		InetAddress inet = null;
		try
		{
			inet = InetAddress.getByName("www.ganghongyue.com");
		} catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		
		System.out.println( inet );
		
		//2. inet��Ӧ����ķ�����ȡinet�е�����
		System.out.println( inet.getHostName() );
		System.out.println( inet.getHostAddress() );
		
		//3.Ҳ����ͨ�� ip��ֱַ�ӻ�ȡ inetaddress
		inet = InetAddress.getByName("115.28.216.163");
		System.out.println( inet);
		
		
		//4. ͨ��inetaddress ��ȡ��������Ϣ
		inet = InetAddress.getLocalHost();
		System.out.println( inet.getHostName() );
		System.out.println( inet.getHostAddress() );
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
