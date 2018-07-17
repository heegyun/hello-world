package my.day11;

public class ThreadTest implements Runnable {

	@Override
	public void run() {
		
		System.out.println(Thread.currentThread().getName());
		System.out.println("");
		
		for(int i=0;i<10;i++){
			System.out.println(i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
	}

}
