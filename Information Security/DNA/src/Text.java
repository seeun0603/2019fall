import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Text {
	File file = new File("Output.txt");
    
	public void text() {
        try {
            FileWriter fw = new FileWriter(file);
            if(User.ASYM == User.BSYM) { //수신자와 발신자의 대칭키 값이 같으면 DH가 정상적으로 진행됨
            	fw.write(Integer.toString((int) User.ASYM)); //생성된 비밀키를 텍스트파일에 씀
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void delete() {
    	if( file.exists() ){
    		System.out.println();
    		if(file.delete())  //파일 삭제
    			System.out.println("파일삭제 완료"); 
    		else 
    			System.out.println("파일삭제 실패"); 
    	}
    	else{ 
    		//System.out.println("파일이 존재하지 않습니다."); 
    	}
    }
}