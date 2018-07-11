package my.pcmange;

public class PCInfo {
	
		private int iId;           // PC를 식별하기위한 번호
	    private String strCpu;    // CPU의 종류
	    private int iMem;        // 메모리의 용량
	    private long lStart;      // 사용시작 시간
	    private long lStop;      // 사용종료 시간
	    private int iTotal;       // 총 매출액
	    
	    /* PCInfo 클래스의 기본 생성자 */
	    public PCInfo() {
	        iId=0;
	        strCpu="";
	        iMem=0;
	        lStart=0;
	        lStop=0;
	        iTotal=0;
	    }
	    
	    /* PC의 정보를 입력 */
	    public void setPCInof(int id, String cpu, int mem)    {
	        iId = id;
	        strCpu = cpu;
	        iMem = mem;
	        iTotal = 0;
	    }
	    
	    /* PC의 사용시작 시간을 기록 */
	    public void start(long time)    {
	        lStart = time;
	    }
	    
	    /* PC의 사용종료 시간을 입력받아 사용금액 반환 */
	    public int stop(long time)    {
	        int iPay = 0;
	        lStop = time;
	        iPay = (int)((lStop-lStart)/60000*20);
	        iTotal=+iPay;
	        return iPay;
	    }

	    /* 각 PC별 매출액과 총 매출액 반환 */
	    public int getTotal()    {
	        return iTotal;
	    }
	
	

}
