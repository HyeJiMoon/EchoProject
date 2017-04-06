/*
 *�������α׷��̶� Ŭ���̾�Ʈ �޼����� �״�� �ٽ� �����ϴ� ����� ���� 
 *ä�� ���� 1�ܰ� */
package echo.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	ServerSocket server;
	int port=7777;
	
	public EchoServer() {
		try {
			server=new ServerSocket(port);
			System.out.println("��������");
			
			Socket socket=server.accept(); //�����ڰ� ���� ������ ���Ѵ��! 
			InetAddress inet=socket.getInetAddress();
			String ip=inet.getHostAddress();
			
			System.out.println(ip+"�����ڹ߰�");
			
			//Ŭ���̾�Ʈ�� �����͸� �ޱ����� �Է½�Ʈ�� ���!!
			//����Ʈ --> ���� --> ���� ��� 
			BufferedReader buffr=new BufferedReader(new InputStreamReader(socket.getInputStream())); //����ũ
			BufferedWriter buffw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); //�̾���
			
			//Ŭ���̾�Ʈ�� �� ��� !
			 //���⿡ while�� ù��°����� �����ü��־� �����־ �ٸ������ ���餷�ÿ� �ٸ������ڴ� �� �����ڳ�
			//����:��ȭ�� ���������� , ����ΰ� �Ʒ��� while�� �ȿ� ���������Ƿ�, ���̻� �߰������ڿ� ���� ��������� �Ұ��ϴ� . ���: ���� ���� �������� ����� ���� 
			String msg;
			while(true){
				msg=buffr.readLine();//��� //1���� �� //���Ѵ����¿������� Ŭ���̾�Ʈ�� ����ġ�� �޾Ƶ��δ�!
				System.out.println("Ŭ���̾�Ʈ�� ���� �� : " +msg);
			
				//�޼��� �ٽú�����
				buffw.write(msg+"\n"); //���� ������
				buffw.flush();//���ۺ���
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new EchoServer();	
	}
}
