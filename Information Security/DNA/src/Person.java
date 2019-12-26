

public class Person {
	public String name;
	private long pbKey;
	public long sendM=0; 
	public int a=0;
	DHAlgorithm dh = new DHAlgorithm();
	

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setA(int a) {
		this.a = a;
	}
	
	public void setKey(long key) {
		this.pbKey = key;
	}
	
	public long getKey() {
		return pbKey;
	}
		
	public int getRandom(int p) {
		//p보다 작은 난수 생성
		a = (int)(Math.random() * (p-1)) + 1;
		//난수 값 저장
		setA(a);
		return a;
	}
	
	//보내는 메시지 생성
	public long sendM(int a, int g, int p) {
		sendM = dh.pow(g,a)% p;
		return sendM;
	}
	
	//DH 알고리즘을 통해 공개키 만들기
	public long getPbKey(long sendM,  int p) {
		pbKey = dh.operate(sendM, a, p);
		setKey(pbKey);
		return pbKey;
	}
}