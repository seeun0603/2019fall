

public class Feistel {
	//초기치환
	public static byte[] IP = {
			58,	50,	42,	34,	26,	18,	10,	2,
			60,	52,	44,	36,	28,	20,	12,	4,
			62,	54,	46,	38,	30,	22,	14,	6,
			64,	56,	48,	40,	32,	24,	16,	8,
			57,	49,	41,	33,	25,	17,	9,	1,
			59,	51,	43,	35,	27,	19,	11,	3,
			61,	53,	45,	37,	29,	21,	13,	5,
			63,	55,	47,	39,	31,	23,	15,	7		
	};
	//마지막 치환 (역치환)
	public static byte[] FP = {
			40,	8,	48,	16,	56,	24,	64,	32,
			39,	7,	47,	15,	55,	23,	63,	31,
			38,	6,	46,	14,	54,	22,	62,	30,
			37,	5,	45,	13,	53,	21,	61,	29,
			36,	4,	44,	12,	52,	20,	60,	28,
			35,	3,	43,	11,	51,	19,	59,	27,
			34,	2,	42,	10,	50,	18,	58,	26,
			33,	1,	41,	9,	49,	17,	57,	25
	};
	//f 함수 - 확장 전치
	public static byte[] Ebox = {
			32,	1,	2,	3,	4,	5,
			4,	5,	6,	7,	8,	9,
			8,	9,	10,	11,	12,	13,
			12,	13,	14,	15,	16,	17,
			16,	17,	18,	19,	20,	21,
			20,	21,	22,	23,	24,	25,
			24,	25,	26,	27,	28,	29,
			28,	29,	30,	31,	32,	1
	};
	//f 함수 - sbox연산
	private final static byte[][][] Sbox =
	    {{
	    	{ 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 },
			{ 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 },
			{ 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 },
			{ 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 }
	    },
	    {
	        { 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10 },
			{ 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 },
			{ 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 },
			{ 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 }
	    },
	    {
	        { 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 },
			{ 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 },
			{ 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 },
			{ 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 }
	    },
	    {
	        { 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 },
			{ 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 },
			{ 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 },
			{ 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 }
	    },
	    {
	        { 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 },
			{ 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 },
			{ 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 },
			{ 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3 }
	    },
	    {
	        { 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 },
			{ 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 },
			{ 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 },
			{ 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 }
	    },
	    {
	        { 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 },
			{ 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 },
			{ 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 },
			{ 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 }
	    },
	    {
	        { 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 },
			{ 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 },
			{ 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 },
			{ 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}
	    }};
	//f 함수 - 평형전치
	public static byte[] P = {
			16,	7,	20,	21,	29,	12,	28,	17,
			1,	15,	23,	26,	5,	18,	31,	10,
			2,	8,	24,	14,	32,	27,	3,	9,
			19,	13,	30,	6,	22,	11,	4,	25
	};
	// <초기전치 (IP)>
	public static byte[] initialPermutation(byte[] M) {
		byte[] ip_M = new byte[64];
		for(int i = 0; i < ip_M.length; i++) {
			ip_M[i] = M[IP[i]-1];
		}
		return ip_M;
	}
	// <역전치 (FP)>
	public static byte[] finalPermutation(byte[] M) {
		byte[] fp_M = new byte[64];
		for(int i = 0; i < fp_M.length; i++) {
			fp_M[i] = M[FP[i]-1];
		}
		return fp_M;
	}
	// <f함수 - 평형 전치 (P)>
	public static byte[] pPermutation(byte[] M) {
		byte[] P_M = new byte[32];		 
		for(int i = 0; i < P_M.length; i++) {
			P_M[i] = M[P[i]-1];	 
		}
		return P_M;
	}
	// <f함수 - 확장 (Ebox)>
	public static byte[] expansion(byte[] R) {
		byte[] E_M = new byte[48];
		for(int i = 0; i < E_M.length; i++) {
			E_M[i] = R[Ebox[i]-1];
		}
		return E_M;
	}
	// <f함수 - 키 xor>
	public static byte[] xorK(byte[] R, byte[] key) {
		byte[] xorKey = new byte[48];
		for(int i = 0; i < xorKey.length; i++) {
			if((R[i]^key[i]) == 1)
				xorKey[i] = 1;
			else
				xorKey[i] = 0;
		}
		return xorKey;
	}
	// <f함수 - r,l xor>
	public static byte[] xorRL(byte[] R, byte[] L) {
		byte[] xorL = new byte[32];
		for(int i = 0; i < xorL.length; i++) {
			if((R[i]^L[i]) == 1)
				xorL[i] = 1;
			else
				xorL[i] = 0;
		}
		return xorL;
	}
	// <f함수>
	public static byte[] fFunction(byte[] m , byte[][] key) {
		byte[] swap;
		byte[] left = new byte[32];
		byte[] right = new byte[32];
		byte[] eRight = new byte[48];
		byte[] xorRight = new byte[48];
		byte[] C = new byte[64];
		
		System.arraycopy(m, 0, left, 0, 32);
		System.arraycopy(m, 32, right, 0, 32);
		
		swap = right;
		for(int i = 0; i < 16; i++ ) {
			//1.확장
			eRight = expansion(right);
			//2.xor연산
			xorRight = xorK(eRight, key[i]);
			//3. s-box넣기 위해 6비트씩 8개로 나누기
			byte[][] sbox_M = new byte[8][6];
		 	int x = 0;
		 	for(int j = 0; j < 8; j++) {
		 		for(int k = 0; k < 6; k++) {
		 			sbox_M[j][k] = xorRight[x];
		 			x++;
		 		}
		 	}
		 	//4. SBOX Substitution
		 	byte[] rowArr = new byte[2];
		 	byte[] colArr = new byte[4];
		 	int row, col, a, temp;
		 	
		 	byte[][] sboxRight = new byte[8][4];
		 	for(int j = 0; j < 8; j++) {
		 		rowArr[0] = sbox_M[j][0];
		 		rowArr[1] = sbox_M[j][5];
		 		for(int k = 1; k < 5; k++) {
		 			colArr[k-1] = sbox_M[j][k];
		 		}
		 		row = rowArr[0]*2 + rowArr[1]*1;
		 		col =  colArr[0]*8 + colArr[1]*4 + colArr[2]*2 + colArr[3]*1;
		 		a = Sbox[j][row][col];
		 		for(int k = 0; k < 4 ; k++) {
					sboxRight[j][4-k-1] = (byte)((byte) a % 2);
					temp = (byte)((byte) a / 2);
					a = temp;
				}
		 	}
		 	x = 0;
		 	
		 	byte[] pRight = new byte[32];
		 	for(int j = 0; j < 8; j++) {
		 		for(int k = 0; k < 4; k++) {
		 			pRight[x] = sboxRight[j][k];
		 			x++;
		 		}	 		
		 	}
		 	byte[] resultRight = new byte[32];
		 	resultRight = pPermutation(pRight);
		 	left = xorRL(left, resultRight);
		 	right = left;
		 	left = swap; 
		 	swap = right;
		}
		right = left;
		left = swap;
		System.arraycopy(left, 0, C, 0, 32);
		System.arraycopy(right, 0, C, 32, 32);
		return C;
	}
}
