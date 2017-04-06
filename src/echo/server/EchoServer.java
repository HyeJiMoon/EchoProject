/*
 *에코프로그램이란 클라이언트 메세지를 그대로 다시 전달하는 방식의 서버 
 *채팅 기초 1단계 */
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
			System.out.println("서버생성");
			
			Socket socket=server.accept(); //접속자가 있을 때까지 무한대기! 
			InetAddress inet=socket.getInetAddress();
			String ip=inet.getHostAddress();
			
			System.out.println(ip+"접속자발견");
			
			//클라이언트의 데이터를 받기위해 입력스트림 얻기!!
			//바이트 --> 문자 --> 버퍼 기반 
			BufferedReader buffr=new BufferedReader(new InputStreamReader(socket.getInputStream())); //마이크
			BufferedWriter buffw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); //이어폰
			
			//클라이언트의 말 듣기 !
			 //여기에 while은 첫번째사람만 계쏙들어올수있어 갇혀있어서 다른사람은 못들ㅇㅓ와 다른접속자는 저 위에자나
			//정리:대화는 가능하지만 , 실행부가 아래의 while문 안에 갇혀있으므로, 더이상 추가접속자에 대한 접속허용은 불가하다 . 결론: 최초 가장 빨리들어온 사람용 서버 
			String msg;
			while(true){
				msg=buffr.readLine();//듣기 //1줄의 말 //무한대기상태였ㄷㅏ가 클라이언트가 엔터치면 받아들인다!
				System.out.println("클라이언트가 보낸 말 : " +msg);
			
				//메세지 다시보내기
				buffw.write(msg+"\n"); //한줄 보내기
				buffw.flush();//버퍼비우기
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new EchoServer();	
	}
}
