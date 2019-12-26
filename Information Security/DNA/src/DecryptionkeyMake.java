
public class DecryptionkeyMake {
	public static byte[][] DecryptkeyGenerate(byte[] k){	
		byte[] key = new byte[64]; 
		byte[] pcFirstKey = new byte[56]; 
		byte[] leftKey = new byte[28];
		byte[] rightKey = new byte[28];
		byte[] tempKey = new byte[56];
		byte[][] droundKey = new byte[16][]; 
		
		for(int i = 0; i < 64; i++) {
			key[i] = k[i];
		}
		//1. key 초기치환
		pcFirstKey = KeySchedule.keyPermutation1(key);
		
		//2. 56bits의 key -> 28bits의 두 키 leftKey, rightKey로 나눔
		System.arraycopy(pcFirstKey, 0, leftKey, 0, leftKey.length);
		System.arraycopy(pcFirstKey, 28, rightKey, 0, rightKey.length);
		
		//3. key shift
		for(int i = 1; i <= 16; i++ ) {
			if( i==1 ) {
				KeySchedule.dshift0(leftKey, rightKey);
				System.arraycopy(leftKey, 0, tempKey, 0, leftKey.length);
				System.arraycopy(rightKey, 0, tempKey, 28, rightKey.length);
				droundKey[i-1] = KeySchedule.keyPermutation2(tempKey);
			}
			else if( i==2 || i==9|| i==16) {
				KeySchedule.dshift1(leftKey, rightKey);
				System.arraycopy(leftKey, 0, tempKey, 0, leftKey.length);
				System.arraycopy(rightKey, 0, tempKey, 28, rightKey.length);
				droundKey[i-1] = KeySchedule.keyPermutation2(tempKey);
			}
			else {
				KeySchedule.dshift2(leftKey, rightKey);
				System.arraycopy(leftKey, 0, tempKey, 0, leftKey.length);
				System.arraycopy(rightKey, 0, tempKey, 28, rightKey.length);
				droundKey[i-1] = KeySchedule.keyPermutation2(tempKey);
			}
		}
		return droundKey;
	}

}
