

public class SubkeyMake {
	public static byte[][] RoundkeyGenerate(byte[] k){	
		byte[] key = new byte[64]; 
		byte[] pcFirstKey = new byte[56]; 
		byte[] leftKey = new byte[28];
		byte[] rightKey = new byte[28];
		byte[] tempKey = new byte[56];
		byte[][] roundKey = new byte[16][]; 
		
		for(int i = 0; i < 64; i++) {
			key[i] = k[i];
		}
		//1. key �ʱ�ġȯ
		pcFirstKey = KeySchedule.keyPermutation1(key);
		
		//2. 56bits�� key -> 28bits�� �� Ű leftKey, rightKey�� ����
		System.arraycopy(pcFirstKey, 0, leftKey, 0, leftKey.length);
		System.arraycopy(pcFirstKey, 28, rightKey, 0, rightKey.length);
		
		//3. key shift
		for(int i = 1; i <= 16; i++ ) {
			if( i==1 || i==2 || i==9|| i==16) {
				KeySchedule.shift1(leftKey, rightKey);
				System.arraycopy(leftKey, 0, tempKey, 0, leftKey.length);
				System.arraycopy(rightKey, 0, tempKey, 28, rightKey.length);
				roundKey[i-1] = KeySchedule.keyPermutation2(tempKey);
			}
			else {
				KeySchedule.shift2(leftKey, rightKey);
				System.arraycopy(leftKey, 0, tempKey, 0, leftKey.length);
				System.arraycopy(rightKey, 0, tempKey, 28, rightKey.length);
				roundKey[i-1] = KeySchedule.keyPermutation2(tempKey);
			}
		}
		return roundKey;
	}
}
