/*
자바를 이용하여 서버측 프로그램을 작성한다

서버란 - 전화를 받는 사람 
*/

package echo.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
	//대화를 나누기전에 접속을 알려주기 위한 객체 
	//즉, 아직 대화는 못나눈다!!!!!!!!!!!!!!!!!!!!! ex 초인종을 만들어보자 
	//서버는 클라이언트가 찾아오길 기다리므로 클라이언트와 약속한 포트번호만 보유하면 된다!
	//원칙=포트번호는 자유롭게 정하면 된다.
	//예외 1)0~1023 이미 시스템이 점유하고 있다.
	//예외 2) 유명한 프로그램들은 피하자 ex) 오라클 1521 mysql 3306 ,web 80 따라서 쌤추천 7777,8888,9999
	
	ServerSocket server;
	int port=8888;
	Socket socket;
	
	
	public MyServer() {
		try {
			server=new ServerSocket(port);
			System.out.println("서버생성"); //가동이 아니야! 가동은 전화가 오는 것 저나기만 만들었어 
			
			//클라이언트의 접속을 기다린다. 그리고 접속이 있을 떄 까지 무한대기한다. 즉 지연에 빠진다! 
			//마치 스트림의 read()계열과 같다 ! 파일이 흡수 될 때까지 기다리자나!!
			while(true){
				socket=server.accept(); //무한대기!
				System.out.println("접속자발견");
				
				//소켓을 이용하여 데이터를 받고자하는 경우엔 입력스트림을 , 데이터를 보내고자 하는 경우엔 출력스트림 
				InputStream is=socket.getInputStream(); //byte기반 영문o 한글x
				InputStreamReader reader=null;//빨대 늘리자!
				reader = new InputStreamReader(is, "utf-8");
				
				int data;
				
				while(true){	
					data=reader.read(); // 1바이트 읽어들임
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
