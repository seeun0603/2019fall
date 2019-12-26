import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.JLabel;

public class GUI
{
	   private ActionListener MyListener = new MyListener(); //버튼에 대한 이벤트 처리위함
	   private JButton send,decode,exit; //버튼
	   private String sName="", rName="", plain="", k; 
	   private JTextField senderTF, receiverTF, M, detext, kTF;
	   private JFrame f,r;
	   private static String decrytText=""; 
	   
	public void security() {
		  f = new JFrame("SECURITY"); //프레임 생성. 발신자, 수신자, 메시지 입력받을 첫화면.
	      JPanel panel1 = new JPanel(); //패널 생성. 발신자, 수신자 입력받는 컴포넌트.
	      JLabel senderN = new JLabel("sender"); //발신자 레이블 생성
	      senderTF = new JTextField(10);//텍스트필드 생성. 발신자 입력받음.
	      JLabel receiverN = new JLabel("receiver");//수신자 레이블 생성
	      receiverTF = new JTextField(10);//텍스트 필드 생성. 수신자 입력받음.

	      //패널에 컴포넌트 추가
	      panel1.add(senderN);
	      panel1.add(senderTF);
	      panel1.add(receiverN);
	      panel1.add(receiverTF);
	      
	      JPanel panel2 = new JPanel(); //패널 생성. 메시지 입력받는 컴포넌트.
	      send = new JButton("전송"); //전송 버튼 생성.
	      JLabel Message = new JLabel("전송할 메시지"); //메시지 레이블 생성
	      M = new JTextField(10); //텍스트필드 생성. 메시지 입력받음.
	      exit = new JButton("종료"); //종료 버튼 생성.
	      
	      //패널에 컴포넌트 추가
	      panel2.add(Message);
	      panel2.add(M);
	      panel2.add(send);
	      panel2.add(exit);
	      
	      //프레임에 패널 추가
	      f.add(panel1); 
	      f.add(panel2); 
	      
	      // 프레임 크기 지정(가로, 세로)
	      f.setSize(500, 300);
	      //프레임 위치 지정(가로, 세로)
	      f.setLocation(800, 300);
	      // close 버튼을 눌렀을 경우 프로그램을 종료
	      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      // 프레임의 배치를 흐름대로 보이게 함
	      f.setLayout(new FlowLayout());
	      // 프레임을 화면에 보이도록 함
	      f.setVisible(true);
	      
	      send.addActionListener(new MyListener()); //전송 버튼눌렀을 때 이벤트 처리
	      exit.addActionListener(new MyListener()); //종료 버튼눌렀을 때 이벤트 처리
	}

   public void makeReceiver(){ //수신자 창
	  Des d = new Des();
      r = new JFrame(rName); //프레임 생성. 수신자가 암호화 확인, 복호화 진행하는 화면.
      byte[] C = d.getCipher(); //비트로 이뤄진 암호문을 저장하는 배열
      JPanel panel3 = new JPanel(); //패널 생성. 암호문 출력하는 컴포넌트
      JPanel panel4 = new JPanel(); //패널 생성. 복호문 출력하는 컴포넌트
      JPanel panel5 = new JPanel(); //패널 생성. 복호문 출력하는 컴포넌트
      decode = new JButton("복호화"); //복호화 버튼 생성
      
      JLabel Crtext = new JLabel("암호문"); //암호문 레이블 생성
      JTextField crtext = new JTextField(); //암호문 출력할 텍스트 필드 생성
      String Cr=""; //출력할 스트링형 암호문
      for(int i=0;i<C.length;i++) { //암호문이 비트로 저장된 배열을 스트링으로 변환
         if(i % 8 == 0) //8비트 단위이므로 8비트 마다 띄어쓰기 처리
            Cr += " ";
         if(C[i] == 0) 
            Cr += '0';
         else 
            Cr +='1';
      }
      crtext.setText(Cr); //암호문 출력하는 텍스트 필드에 암호문 출력
      crtext.setEditable(false); //출력만하고 편집할수 없도록 설정
      
      JLabel Key = new JLabel("키"); //키 레이블 생성
      kTF = new JTextField(15);//키 입력할 텍스트필드 생성
      
      JLabel Detext = new JLabel("복호문"); //복호문 레이블 생성
      detext = new JTextField(15);//복호문 출력할 텍스트필드 생성
      detext.setEditable(false); //출력만하고 편집할수 없도록 설정
      
      //패널에 컴포넌트 추가
      panel3.add(Crtext);
      panel3.add(crtext);
      
      panel4.add(Key);
      panel4.add(kTF);
      panel4.add(decode);
      
      panel5.add(Detext);
      panel5.add(detext);
      
      //프레임에 패널 추가
      r.add(panel3);
      r.add(panel4);
      r.add(panel5);
      
      //프레임 설정
      r.setSize(800, 300);
      r.setLocation(500, 300);
      r.setLayout(new FlowLayout());
      r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      r.setVisible(true);
      
      decode.addActionListener(new MyListener()); //복호화 버튼눌렀을 때 이벤트 처리
      exit.addActionListener(new MyListener()); //종료 버튼눌렀을 때 이벤트 처리
   }
   
   class MyListener implements ActionListener {
      
        @Override
        public void actionPerformed(ActionEvent e) {

           if(e.getSource() == send) { //전송 버튼이 눌러졌을 때
              sName = String.valueOf(senderTF.getText()); //발신자 이름 저장
              rName = String.valueOf(receiverTF.getText()); //수신자 이름 저장
         
              System.out.println("***"+sName +"가  "+rName +"에게 전송***"); 
              
              User.alice.setName(sName); //발신자 이름 설정
              User.bob.setName(rName); //수신자 이름 설정
              
              plain = String.valueOf(M.getText()); //발신자가 보내는 메시지 저장
              System.out.println("메시지: "+plain+"\n");
               
               f.dispose(); //첫 창만 종료
               getText(); //암호화 진행
               Text t = new Text();
               t.text(); //키 값 저장된 텍스트파일 만들기
               makeReceiver(); //수신자 창 띄우기
                }
           
           if(e.getSource() == decode) { //복호화 버튼이 눌러졌을 때
        	   Text t = new Text();
        	   
        	   k = String.valueOf(kTF.getText()); //입력된 키 저장
        	   if(k.equals(Integer.toString((int) User.BSYM))) { //DH의 대칭키와 입력받은 키를 비교
        		   detext.setText(decrytText);
        		   System.out.println("복호화: "+decrytText);
        	   }
        	   else
        		   detext.setText("키값이 올바르지 않습니다.");
        	   
        	   t.delete(); //파일 삭제
           }

           if(e.getSource() == exit) { //종료 버튼이 눌러졌을 때
              System.exit(0); //시스템 종료
           }
        }
   }
   
   public void getText() { 
      int i=0;
      int cnt = plain.length()/8+1;
      byte[] message = null;
      
      //입력받은 문자열을 8자리씩 나누기
      ArrayList<String> block = new ArrayList<>();
      while(i<cnt) {
         for(int j=0;j<plain.length();j= j+8) {
            if(j+8>plain.length())
               block.add(i,plain.substring(j, plain.length()));
            else
               block.add(i, plain.substring(j, j+8));
            i++;
         }
      }
    
      //8자리를 바이트에서 비트로 변경하기
      for(i = 0; i < block.size(); i++) {
    	  byte[] msg  = new byte[64];
         String a = "";
         message = block.get(i).getBytes();
           for(int j =0;j<message.length;j++) {
              int dec = Integer.parseInt(Byte.toString(message[j]));
             String by = String.format("%08d", Integer.parseInt(Integer.toBinaryString(dec).toString()));
             a +=by;
          }
           //비트를 64바이트 사이즈에 넣기   
           for(int k=0;k<a.length();k++) {
              if(a.charAt(k) == '0' )
                 msg[k] = 0;
              else 
                 msg[k] = 1;
           }
           decrytText = Des.test(msg); //DES를 통해 만들어진 암호화 비트들을 저장
           if(decrytText.length() == plain.length()) {
        	   break;
           }
      }
   }
   
   public String getSName(){
      return sName;
   }
   
   public String getRName(){
      return rName;
   }
   
   public String getPlain(){
      return plain;
   }
   
   public String getDText() {
      return decrytText;
   }
}