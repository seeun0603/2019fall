

import java.util.Arrays;

public class KeySchedule {
	private static int[] PC1 = {
			57, 49, 41, 33, 25, 17, 9,
	        1,  58, 50, 42, 34, 26, 18,
	        10, 2,  59, 51, 43, 35, 27,
	        19, 11, 3,  60, 52, 44, 36,
	        63, 55, 47, 39, 31, 23, 15,
	        7,  62, 54, 46, 38, 30, 22,
	        14, 6,  61, 53, 45, 37, 29,
	        21, 13, 5,  28, 20, 12, 4
	};
	private static int[] PC2 = {
			14, 17, 11, 24, 1,  5,
	        3,  28, 15, 6,  21, 10,
	        23, 19, 12, 4,  26, 8,
	        16, 7,  27, 20, 13, 2,
	        41, 52, 31, 37, 47, 55,
	        30, 40, 51, 45, 33, 48,
	        44, 49, 39, 56, 34, 53,
	        46, 42, 50, 36, 29, 32
	};
	///////////////////////////////////////////
	//1칸 shift 이동 함수
		public static void shift1(byte[] leftKey, byte[] rightKey) {
			//copyOf(원본 배열, 복사할 길이)
			
			//1. 왼쪽과 오른쪽의 키의 복사본을 만든다. 
			byte[] LKey = Arrays.copyOf(leftKey, leftKey.length);
			byte[] RKey = Arrays.copyOf(rightKey, rightKey.length);
			
			//2. 비트를 이동 시킨다.
			leftKey[0] = LKey[27]; 
			rightKey[0] = RKey[27];
			for(int i = 1; i < 28; i++) {
				leftKey[i] = LKey[(i-1)]; 
				rightKey[i] = RKey[(i-1)]; 
			}
		}
		//2칸 shift 이동 함수
		public static void shift2(byte[] leftKey, byte[] rightKey) {
			
			//1. 왼쪽과 오른쪽의 키의 복사본을 만든다. 
			byte[] LKey = Arrays.copyOf(leftKey, leftKey.length);
			byte[] RKey = Arrays.copyOf(rightKey, rightKey.length);
			
			//2. 비트를 이동 시킨다.
			leftKey[0] = LKey[26]; 
			rightKey[0] = RKey[26];
			leftKey[1] = LKey[27]; 
			rightKey[1] = RKey[27];
			for(int i = 2; i < 28; i++) {
				leftKey[i] = LKey[(i-2)]; 
				rightKey[i] = RKey[(i-2)]; 
			}
		}
		 public static void dshift0(byte[] leftKey, byte[] rightKey) {
				//copyOf(원본 배열, 복사할 길이)
				
				//1. 왼쪽과 오른쪽의 키의 복사본을 만든다. 
				byte[] LKey = Arrays.copyOf(leftKey, leftKey.length);
				byte[] RKey = Arrays.copyOf(rightKey, rightKey.length);
				
				//2. 비트를 이동 시킨다.
				for(int i = 0; i < 28; i++) {
					leftKey[i] = LKey[(i) % 28]; 
					rightKey[i] = RKey[(i) % 28]; 
				}
			}
		//1칸 shift 이동 함수
			public static void dshift1(byte[] leftKey, byte[] rightKey) {
				//copyOf(원본 배열, 복사할 길이)
				
				//1. 왼쪽과 오른쪽의 키의 복사본을 만든다. 
				byte[] LKey = Arrays.copyOf(leftKey, leftKey.length);
				byte[] RKey = Arrays.copyOf(rightKey, rightKey.length);
				
				//2. 비트를 이동 시킨다.
				for(int i = 0; i < 28; i++) {
					leftKey[i] = LKey[(i+1) % 28]; 
					rightKey[i] = RKey[(i+1) % 28]; 
				}
			}
			//2칸 shift 이동 함수
			public static void dshift2(byte[] leftKey, byte[] rightKey) {
				//1. 왼쪽과 오른쪽의 키의 복사본을 만든다. 
				byte[] LKey = Arrays.copyOf(leftKey, leftKey.length);
				byte[] RKey = Arrays.copyOf(rightKey, rightKey.length);
				
				//2. 비트를 이동 시킨다.
				for(int i = 0; i < 28; i++) {
					leftKey[i] = LKey[(i+2) % 28]; 
					rightKey[i] = RKey[(i+2) % 28]; 
				}
			}
			///////////////////////////////////////////////////////
	//PC1함수
	public static byte[] keyPermutation1(byte[] key) {
		//1. PC1 : 64bits -> 56bits
		byte[] permutationKey = new byte[56];
			
		for(int i = 0; i < 56; i++) 				
			permutationKey[i] = key[PC1[i]-1];
		return permutationKey;
	}
	//PC2함수
	public static byte[] keyPermutation2(byte[] key) {
		//2. PC2 : 56bits -> 48bits
		byte[] returnKey = new byte[48];
			
		for(int i = 0; i < returnKey.length; i++) 
			returnKey[i] = key[PC2[i]-1];			
		return returnKey;
	}
}
