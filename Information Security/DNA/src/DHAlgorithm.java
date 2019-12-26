

public class DHAlgorithm {
	private long pbKey=0;

	public long pow(long a, int b) {
	      long result=1;
	      
	      for(int i = 1; i<b;i++)
	      {
	    	  result *=a;
	      }
	      return result;
	}
	
	public long operate(long sendM, int a, int p) {
		pbKey =  pow(sendM, a)% p;
		return pbKey;
	}
}

