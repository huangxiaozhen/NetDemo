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

//�ӿͻ��˷���ͼƬ������ˣ�������ܵ�ͼƬ���浽һ���ط�
//Ȼ����߿ͻ��� ���͵�ͼƬ�Ѿ��յ�

public class TestTCP3
{

	@Test
	public void client()
	{

		// 1. ����Socket����ָ������˵�ip��ַ�Լ��˿ں�
		Socket socket = null;
		// 2. �����ļ��ڵ�����������ͼƬ���뵽�ڴ���
		FileInputStream fis = null;
		// 3. ���� Socket ��������������ͼƬ����������
		OutputStream os = null;
		// 4. ����Socket ���������������Է���˵���Ϣ
		InputStream is = null;

		try
		{
			socket = new Socket("127.0.0.1", 9090);
			fis = new FileInputStream(new File("customer.jpg"));
			os = socket.getOutputStream();
			is = socket.getInputStream();

			// 5. ��ȡͼƬ���͵������
			byte[] b = new byte[20];
			int len;
			while ((len = fis.read(b)) != -1)
			{
				os.write(b, 0, len);
			}

			// 6.��ʾ�ر������
			socket.shutdownOutput();

			// 7.�������Է���˵���Ϣ����ӡ������̨
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
			// 8.�رո������Լ�socket
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
		// 1. ����ServerSocket ���������˵Ķ˿�
		ServerSocket ss = null;
		// 2. ����Socket �����տͻ��˵�����
		Socket socket = null;
		// 3. ����socket �����������������Կͻ��˵��������Ϣ
		InputStream is = null;
		// 4. �����ļ�����������ͻ��˴��ݹ�����ͼƬ�洢������
		FileOutputStream fos = null;
		// 5. ����socket����������߿ͻ���ͼƬ�Ѿ����ܳɹ�
		OutputStream os = null;

		try
		{
			ss = new ServerSocket(9090);
			socket = ss.accept();
			is = socket.getInputStream();
			fos = new FileOutputStream( new File( "customer2.jpg" ) );
			os = socket.getOutputStream();
			
			//6.���������տͻ������������Ϣ�������浽����
			byte[] b = new byte[20];
			int len;
			while( ( len = is.read(b) ) != -1 )
			{
				fos.write(b, 0, len);
			}
			
			//7. ���ͻ��˷�����Ϣ�������Ѿ����ܳɹ�
			os.write("���͵�ͼƬ���ܳɹ�".getBytes());  
			
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}finally
		{
			//8. �رո�������socket
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
 