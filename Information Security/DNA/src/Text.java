import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Text {
	File file = new File("Output.txt");
    
	public void text() {
        try {
            FileWriter fw = new FileWriter(file);
            if(User.ASYM == User.BSYM) { //�����ڿ� �߽����� ��ĪŰ ���� ������ DH�� ���������� �����
            	fw.write(Integer.toString((int) User.ASYM)); //������ ���Ű�� �ؽ�Ʈ���Ͽ� ��
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void delete() {
    	if( file.exists() ){
    		System.out.println();
    		if(file.delete())  //���� ����
    			System.out.println("���ϻ��� �Ϸ�"); 
    		else 
    			System.out.println("���ϻ��� ����"); 
    	}
    	else{ 
    		//System.out.println("������ �������� �ʽ��ϴ�."); 
    	}
    }
}