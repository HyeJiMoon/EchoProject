/*
�ڹٸ� �̿��Ͽ� ������ ���α׷��� �ۼ��Ѵ�

������ - ��ȭ�� �޴� ��� 
*/

package echo.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
	//��ȭ�� ���������� ������ �˷��ֱ� ���� ��ü 
	//��, ���� ��ȭ�� ��������!!!!!!!!!!!!!!!!!!!!! ex �������� ������ 
	//������ Ŭ���̾�Ʈ�� ã�ƿ��� ��ٸ��Ƿ� Ŭ���̾�Ʈ�� ����� ��Ʈ��ȣ�� �����ϸ� �ȴ�!
	//��Ģ=��Ʈ��ȣ�� �����Ӱ� ���ϸ� �ȴ�.
	//���� 1)0~1023 �̹� �ý����� �����ϰ� �ִ�.
	//���� 2) ������ ���α׷����� ������ ex) ����Ŭ 1521 mysql 3306 ,web 80 ���� ����õ 7777,8888,9999
	
	ServerSocket server;
	int port=8888;
	Socket socket;
	
	
	public MyServer() {
		try {
			server=new ServerSocket(port);
			System.out.println("��������"); //������ �ƴϾ�! ������ ��ȭ�� ���� �� �����⸸ ������� 
			
			//Ŭ���̾�Ʈ�� ������ ��ٸ���. �׸��� ������ ���� �� ���� ���Ѵ���Ѵ�. �� ������ ������! 
			//��ġ ��Ʈ���� read()�迭�� ���� ! ������ ��� �� ������ ��ٸ��ڳ�!!
			while(true){
				socket=server.accept(); //���Ѵ��!
				System.out.println("�����ڹ߰�");
				
				//������ �̿��Ͽ� �����͸� �ް����ϴ� ��쿣 �Է½�Ʈ���� , �����͸� �������� �ϴ� ��쿣 ��½�Ʈ�� 
				InputStream is=socket.getInputStream(); //byte��� ����o �ѱ�x
				InputStreamReader reader=null;//���� �ø���!
				reader = new InputStreamReader(is, "utf-8");
				
				int data;
				
				while(true){	
					data=reader.read(); // 1����Ʈ �о����
					System.out.print((char)data);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new MyServer();
	}

}
