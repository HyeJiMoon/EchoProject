//서버와는 별도니까 메인있어야해
package echo.server;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EchoClient extends JFrame{
	JTextArea area;
	JScrollPane scroll;
	JPanel p_south;
	JTextField t_input;
	JButton bt_connect;
	
	//대화를 나눌 수 있는 소켓!
	//말걸 때 - 소켓으로부터 출력스트림
	//청취할 때 - 소켓으로부터 입력스트림
	Socket socket; //이제 종이컵을 소켓이라 부르자
	BufferedReader buffr; //청취용
	BufferedWriter buffw;//말걸기용
	
	public EchoClient() {
		area=new JTextArea();
		scroll = new JScrollPane(area);
		p_south=new JPanel();
		t_input=new JTextField(18);
		bt_connect=new JButton("입력");
		
		p_south.add(t_input);
		p_south.add(bt_connect);
		
		add(p_south,BorderLayout.SOUTH);
		add(scroll);
		
		bt_connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect();
			}
		});
		t_input.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				//엔터치면 알아먹어야지! 말을 먼저 하고 들을 수 있지!
				int key=e.getKeyCode();
				if(key==KeyEvent.VK_ENTER){
					send();
				}
			}
		});
		setSize(300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	//서버에 말보내기
	public void send(){
		
		//텍스트상자의 메시지 값을 얻어야 뿌릴 수 있지
		String msg=t_input.getText();
		//서버에 보내자
		try {
			buffw.write(msg+"\n"); //스트림을 통해 출력 ! //즉 서버측의 소켓에 데이터 전송 
			//그냥 (msg)는 글자만 주르륽 그리고 엔터는 줄바꿈과 커서가 동시에있으니까 뒤에 \n해주어야 비로소 엔터의기능을 한다구!
			//또한 그걸 확 올려주는 것까지 해주어야 완전히 올라감 
			buffw.flush(); //버퍼에 남아있을지도 모를 데이터를 대상으로 모두 출력시켜! -> 정리 : 원레 write면나가야하는데 버퍼처리된 메세지,데이터는 남아있을 수 있다? 
		
			//서버에서 날아온 메세지를 area에 출력해보자
			String data=buffr.readLine(); 
			area.append(data+"\n");
			t_input.setText("");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//서버에 접속하는 메서드 
	public void connect(){
		//상대방의 아이피를 알아야하니까 접속 먼저!
		try {
			socket=new Socket("localhost", 7777);
			//접속이 완료되었으니 ,스트림 얻어놓자 ! 왜? 대화나누려고!
			buffr=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new EchoClient();
	}
}
