package me.huangzhen.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

// TCP ���Եڶ������� �ͻ��˸�����˷�����Ϣ��������ܵ���Ϣ���ٸ��ͻ��˷�����Ϣ������
// ��ʵ���˿ͻ���������֮���һ��ͨ�ŵĹ���

public class TestTCP2
{
	@Test
	public void client2()
	{
		//1. ����socket����
		Socket socket = null;
		//2. �������������
		OutputStream os = null;
		//3. �������������󣬽��մӷ���˴��ݹ�������Ϣ
		InputStream is = null;
		
		try
		{
			socket = new Socket("127.0.0.1",9090);
			os = socket.getOutputStream();
			is = socket.getInputStream();
			
			//4. �����˷�����Ϣ
			os.write("���ǿͻ���".getBytes());
			
			//PS.��Ҫ��ʾ��ָ������Ѿ�����
			socket.shutdownOutput();
			
			//5.���մӷ���˴��ݹ�������Ϣ
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
			//6. ��ϵ�������Լ�socket
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
		//1. ���� ServerSocket ����ָ������˶˵Ķ˿ں�
		ServerSocket ss = null;
		//2. ����serverSocket ����ķ��������մӿͻ�����Ϣ
		Socket socket = null;
		//3. ������������������Ϣ
		InputStream is = null;		
		//4. �����������������ͻ��˴�����Ϣ
		OutputStream os = null;
		
		try
		{
			ss =  new ServerSocket(9090);
			socket = ss.accept();
			is = socket.getInputStream();
			os = socket.getOutputStream();
			
			//5. ���ղ���ӡ�ӿͻ��˴��ݹ�������Ϣ
			byte[] b = new byte[20];
			int len;
			StringBuilder sb = new StringBuilder();
			//�ֽ�����������������ʽ�ģ����Գ���������������У���Ҫ�ڿͻ�����ʵ��ָ�� ����Ѿ�����
			//read ֪���ͻ�������������˳���ִ��123�еĲ���
			while( ( len = is.read(b) ) != -1 ) 
			{
				sb.append( new String( b,0,len ) );
			}
			
			System.out.println( sb.toString() );
			
			//6. ��ͻ��˷�����Ϣ
			os.write( "�Ѿ�����������Ϣ".getBytes() );
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}finally
		{
			//7. �رո�������socket
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
