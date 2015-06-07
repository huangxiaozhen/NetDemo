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

//���� TCP �ͻ����Լ�����˽��в���
//��������ʵ�Ͼ��� Socket �ı��

public class TestTCP
{

	// TCP ��� �����쳣�Ĵ���ȫ������Ҫ���� try catch ������Ϊ�򿪵���Դ��Ҫ
	// ��ȫ��ȷ���ر�
	@Test
	public void client1()
	{
		// 1.����socket
		Socket socket = null;
		OutputStream os = null;

		try
		{
			socket = new Socket("127.0.0.1", 9090);

			// 2. �õ�socket �������
			os = socket.getOutputStream();

			// 3. ������������˷�����Ϣ
			os.write("������ǿͻ���".getBytes());

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			// 4. ���� �� socket �ر�
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
		// 1.����serversocket ������������Ķ˿�
		ServerSocket ss = null;

		// 2.����ss ��accept �ķ��������������ͻ���Ϣ
		Socket socket = null;

		// 3.���������������������մӿͻ��˴��ݹ�������Ϣ
		InputStream is = null;

		try
		{
			ss = new ServerSocket(9090);
			//�谭ʽ
			socket = ss.accept();
			is = socket.getInputStream();

			// 4.��������ȡ����
			byte[] b = new byte[20];
			int len;
			StringBuffer stringBuffer = new StringBuffer();
			while ((len = is.read(b)) != -1)
			{
				stringBuffer.append(new String(b, 0, len));
			}
			
			System.out.println( stringBuffer.toString() );
			
			//5. �õ��ͻ��˵�������Ϣ
			System.out.println( "�ͻ��˵ĵ�ַ:" + socket.getInetAddress().getHostAddress()  
					+ "�ͻ��˵�������" + socket.getInetAddress().getHostName() );

		} catch (Exception e)
		{
			e.printStackTrace();
		}finally
		{
			//5. �ر� �� socket serversocket
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

	// �ͷ���
	@Test
	public void client() throws UnknownHostException, IOException
	{
		// 1. ���� Socket ��Ҫָ���������˵� IP ��ַ���Լ��˿ں�
		Socket socket = new Socket("127.0.0.1", 9091);

		// 2.�õ������
		OutputStream os = socket.getOutputStream();

		// 3. ������������
		os.write("���ǿͷ���".getBytes());

		// 4.�رն�Ӧ�����Լ�socket
		os.close();
		socket.close();
	}

	@Test
	public void server() throws IOException
	{
		// 1.���� serverScoket ���� ͨ��������ָ���Լ��� �˿ں�
		ServerSocket ss = new ServerSocket(9091);

		// 2. ����serversocket ��accept ������һ�� socket �Ķ��󡣽��м�����ȡ�ͻ��˵�socket
		Socket socket = ss.accept();

		// 3. ����socket �����getInputStream�������õ�socket��������
		InputStream is = socket.getInputStream();
		byte[] b = new byte[20];
		int len;

		while ((len = is.read(b)) != -1)
		{
			// 4. �õ��������е����ݣ���ӡ������̨
			String str = new String(b, 0, len);
			System.out.println(str);
		}

		// 5. �ر���Ӧ�����Լ�socket serversocket�Ķ���
		is.close();
		socket.close();
		ss.close();
	}
}
