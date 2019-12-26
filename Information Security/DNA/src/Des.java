import java.util.ArrayList;

public class Des {
	static byte[] key = new byte [64];
	static byte[] C = new byte[64];
	static byte[] D = new byte[64];
	static String decryptText="";
	public static String test(byte[] M) {
		
		key = User.userkey();
		
		byte[][] roundkey = SubkeyMake.RoundkeyGenerate(key);
		byte[][] decryptkey = DecryptionkeyMake.DecryptkeyGenerate(key);
		
		//�� ���
		System.out.print("�� : ");
		
		for(int i = 0; i < M.length; i++) {
			if(i % 8 == 0)
				System.out.printf(" ");
			System.out.printf("%d", M[i]);
		}
		System.out.println();
		
		//1. �� M�� IP�Լ��� ��ģ��. (�ʱ���ġ)
		M = Feistel.initialPermutation(M);
		//2. �� M�� f�Լ��� ��ģ��.
		M = Feistel.fFunction(M, roundkey);
		//3. ������ ����ġ
		C = Feistel.finalPermutation(M);
		
		System.out.printf("��ȣ�� : ");
		for(int i = 0; i < C.length; i++) {
			if(i % 8 == 0)
				System.out.printf(" ");
			System.out.printf("%s",C[i]);
		}
		System.out.println();

		////////////////��ȣȭ////////////////////
		
		C = Feistel.initialPermutation(C);
		C = Feistel.fFunction(C, decryptkey);
		D = Feistel.finalPermutation(C);
		
		//���ڸ� ���ڿ��� ��ȯ
		String deM="";
		for(int i=0;i<D.length;i++) {
			if(D[i] == 0)
				deM += '0';
			else 
				deM +='1';
		}
		
		//���ڿ��� 8�ڸ��� �и�
		int k=0;
		int cnt = deM.length()%8+1;
		ArrayList<String> dBlock = new ArrayList<>();
		while(k<cnt) {
			for(int j=0;j<deM.length();j= j+8) {
				if(j+8>deM.length())
					dBlock.add(k, deM.substring(j, deM.length()));
				else
					dBlock.add(k, deM.substring(j, j+8));
				k++;
			}
		}
		
		//�������� ���ڿ��� ��ȯ
		for(String a : dBlock) {
			int result =binaryStringtoInteger(a);
			if(result == 32)
				decryptText += " ";
			else {
				decryptText += (char)result;
			}
		}
		return decryptText;
	}
	
	public static int binaryStringtoInteger(String a) {
		char[] numbers = a.toCharArray();
	    int result = 0;
	    for(int i=numbers.length - 1; i>=0; i--)
	        if(numbers[i]=='1')
	            result += Math.pow(2, (numbers.length-i - 1));
	    return result;
	}
	
	public byte[] getCipher() {
		return C;
	}
	
	public byte[] getDec() {
		return D;
	}
	
	public String getDText() {
		return decryptText;
	}
	
	
}
