

public class User {
	static Person alice = new Person();
	static Person bob = new Person();
	static long ASYM, BSYM;
	
	public static byte[] userkey() {
		byte[] Key= new byte[64];
		int g=3, p=17;
		long sendA=0, sendB=0;
		
		System.out.println();
		sendA = alice.sendM(alice.getRandom(p), g, p);
		System.out.println(alice.getName()+"�� ���۰�:" + sendA);
		sendB = bob.sendM(bob.getRandom(p), g, p);
		System.out.println(bob.getName()+"�� ���۰�:" + sendB+"\n");
		
		ASYM = alice.getPbKey(sendB, p);
		System.out.println(alice.getName()+"�� ��ĪŰ: "+ASYM);
		BSYM = bob.getPbKey(sendA, p);
		System.out.println(bob.getName()+"�� ��ĪŰ: "+BSYM+"\n");
		
		int temp;
		long firstKey = alice.getKey();
	
		for(int i = 0; i <64 ; i++) {
			Key[64-i-1] = (byte)((byte) firstKey % 2);
			temp = (byte)((byte) firstKey / 2);
			firstKey = temp;
		}
		return Key;
	}

}
