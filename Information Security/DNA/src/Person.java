

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
		//p���� ���� ���� ����
		a = (int)(Math.random() * (p-1)) + 1;
		//���� �� ����
		setA(a);
		return a;
	}
	
	//������ �޽��� ����
	public long sendM(int a, int g, int p) {
		sendM = dh.pow(g,a)% p;
		return sendM;
	}
	
	//DH �˰����� ���� ����Ű �����
	public long getPbKey(long sendM,  int p) {
		pbKey = dh.operate(sendM, a, p);
		setKey(pbKey);
		return pbKey;
	}
}