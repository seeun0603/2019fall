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
	   private ActionListener MyListener = new MyListener(); //��ư�� ���� �̺�Ʈ ó������
	   private JButton send,decode,exit; //��ư
	   private String sName="", rName="", plain="", k; 
	   private JTextField senderTF, receiverTF, M, detext, kTF;
	   private JFrame f,r;
	   private static String decrytText=""; 
	   
	public void security() {
		  f = new JFrame("SECURITY"); //������ ����. �߽���, ������, �޽��� �Է¹��� ùȭ��.
	      JPanel panel1 = new JPanel(); //�г� ����. �߽���, ������ �Է¹޴� ������Ʈ.
	      JLabel senderN = new JLabel("sender"); //�߽��� ���̺� ����
	      senderTF = new JTextField(10);//�ؽ�Ʈ�ʵ� ����. �߽��� �Է¹���.
	      JLabel receiverN = new JLabel("receiver");//������ ���̺� ����
	      receiverTF = new JTextField(10);//�ؽ�Ʈ �ʵ� ����. ������ �Է¹���.

	      //�гο� ������Ʈ �߰�
	      panel1.add(senderN);
	      panel1.add(senderTF);
	      panel1.add(receiverN);
	      panel1.add(receiverTF);
	      
	      JPanel panel2 = new JPanel(); //�г� ����. �޽��� �Է¹޴� ������Ʈ.
	      send = new JButton("����"); //���� ��ư ����.
	      JLabel Message = new JLabel("������ �޽���"); //�޽��� ���̺� ����
	      M = new JTextField(10); //�ؽ�Ʈ�ʵ� ����. �޽��� �Է¹���.
	      exit = new JButton("����"); //���� ��ư ����.
	      
	      //�гο� ������Ʈ �߰�
	      panel2.add(Message);
	      panel2.add(M);
	      panel2.add(send);
	      panel2.add(exit);
	      
	      //�����ӿ� �г� �߰�
	      f.add(panel1); 
	      f.add(panel2); 
	      
	      // ������ ũ�� ����(����, ����)
	      f.setSize(500, 300);
	      //������ ��ġ ����(����, ����)
	      f.setLocation(800, 300);
	      // close ��ư�� ������ ��� ���α׷��� ����
	      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      // �������� ��ġ�� �帧��� ���̰� ��
	      f.setLayout(new FlowLayout());
	      // �������� ȭ�鿡 ���̵��� ��
	      f.setVisible(true);
	      
	      send.addActionListener(new MyListener()); //���� ��ư������ �� �̺�Ʈ ó��
	      exit.addActionListener(new MyListener()); //���� ��ư������ �� �̺�Ʈ ó��
	}

   public void makeReceiver(){ //������ â
	  Des d = new Des();
      r = new JFrame(rName); //������ ����. �����ڰ� ��ȣȭ Ȯ��, ��ȣȭ �����ϴ� ȭ��.
      byte[] C = d.getCipher(); //��Ʈ�� �̷��� ��ȣ���� �����ϴ� �迭
      JPanel panel3 = new JPanel(); //�г� ����. ��ȣ�� ����ϴ� ������Ʈ
      JPanel panel4 = new JPanel(); //�г� ����. ��ȣ�� ����ϴ� ������Ʈ
      JPanel panel5 = new JPanel(); //�г� ����. ��ȣ�� ����ϴ� ������Ʈ
      decode = new JButton("��ȣȭ"); //��ȣȭ ��ư ����
      
      JLabel Crtext = new JLabel("��ȣ��"); //��ȣ�� ���̺� ����
      JTextField crtext = new JTextField(); //��ȣ�� ����� �ؽ�Ʈ �ʵ� ����
      String Cr=""; //����� ��Ʈ���� ��ȣ��
      for(int i=0;i<C.length;i++) { //��ȣ���� ��Ʈ�� ����� �迭�� ��Ʈ������ ��ȯ
         if(i % 8 == 0) //8��Ʈ �����̹Ƿ� 8��Ʈ ���� ���� ó��
            Cr += " ";
         if(C[i] == 0) 
            Cr += '0';
         else 
            Cr +='1';
      }
      crtext.setText(Cr); //��ȣ�� ����ϴ� �ؽ�Ʈ �ʵ忡 ��ȣ�� ���
      crtext.setEditable(false); //��¸��ϰ� �����Ҽ� ������ ����
      
      JLabel Key = new JLabel("Ű"); //Ű ���̺� ����
      kTF = new JTextField(15);//Ű �Է��� �ؽ�Ʈ�ʵ� ����
      
      JLabel Detext = new JLabel("��ȣ��"); //��ȣ�� ���̺� ����
      detext = new JTextField(15);//��ȣ�� ����� �ؽ�Ʈ�ʵ� ����
      detext.setEditable(false); //��¸��ϰ� �����Ҽ� ������ ����
      
      //�гο� ������Ʈ �߰�
      panel3.add(Crtext);
      panel3.add(crtext);
      
      panel4.add(Key);
      panel4.add(kTF);
      panel4.add(decode);
      
      panel5.add(Detext);
      panel5.add(detext);
      
      //�����ӿ� �г� �߰�
      r.add(panel3);
      r.add(panel4);
      r.add(panel5);
      
      //������ ����
      r.setSize(800, 300);
      r.setLocation(500, 300);
      r.setLayout(new FlowLayout());
      r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      r.setVisible(true);
      
      decode.addActionListener(new MyListener()); //��ȣȭ ��ư������ �� �̺�Ʈ ó��
      exit.addActionListener(new MyListener()); //���� ��ư������ �� �̺�Ʈ ó��
   }
   
   class MyListener implements ActionListener {
      
        @Override
        public void actionPerformed(ActionEvent e) {

           if(e.getSource() == send) { //���� ��ư�� �������� ��
              sName = String.valueOf(senderTF.getText()); //�߽��� �̸� ����
              rName = String.valueOf(receiverTF.getText()); //������ �̸� ����
         
              System.out.println("***"+sName +"��  "+rName +"���� ����***"); 
              
              User.alice.setName(sName); //�߽��� �̸� ����
              User.bob.setName(rName); //������ �̸� ����
              
              plain = String.valueOf(M.getText()); //�߽��ڰ� ������ �޽��� ����
              System.out.println("�޽���: "+plain+"\n");
               
               f.dispose(); //ù â�� ����
               getText(); //��ȣȭ ����
               Text t = new Text();
               t.text(); //Ű �� ����� �ؽ�Ʈ���� �����
               makeReceiver(); //������ â ����
                }
           
           if(e.getSource() == decode) { //��ȣȭ ��ư�� �������� ��
        	   Text t = new Text();
        	   
        	   k = String.valueOf(kTF.getText()); //�Էµ� Ű ����
        	   if(k.equals(Integer.toString((int) User.BSYM))) { //DH�� ��ĪŰ�� �Է¹��� Ű�� ��
        		   detext.setText(decrytText);
        		   System.out.println("��ȣȭ: "+decrytText);
        	   }
        	   else
        		   detext.setText("Ű���� �ùٸ��� �ʽ��ϴ�.");
        	   
        	   t.delete(); //���� ����
           }

           if(e.getSource() == exit) { //���� ��ư�� �������� ��
              System.exit(0); //�ý��� ����
           }
        }
   }
   
   public void getText() { 
      int i=0;
      int cnt = plain.length()/8+1;
      byte[] message = null;
      
      //�Է¹��� ���ڿ��� 8�ڸ��� ������
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
    
      //8�ڸ��� ����Ʈ���� ��Ʈ�� �����ϱ�
      for(i = 0; i < block.size(); i++) {
    	  byte[] msg  = new byte[64];
         String a = "";
         message = block.get(i).getBytes();
           for(int j =0;j<message.length;j++) {
              int dec = Integer.parseInt(Byte.toString(message[j]));
             String by = String.format("%08d", Integer.parseInt(Integer.toBinaryString(dec).toString()));
             a +=by;
          }
           //��Ʈ�� 64����Ʈ ����� �ֱ�   
           for(int k=0;k<a.length();k++) {
              if(a.charAt(k) == '0' )
                 msg[k] = 0;
              else 
                 msg[k] = 1;
           }
           decrytText = Des.test(msg); //DES�� ���� ������� ��ȣȭ ��Ʈ���� ����
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