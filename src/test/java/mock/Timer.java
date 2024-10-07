package mock;

    public class Timer implements Runnable{
    private Integer timeInterval;
    private Runnable runnable;


    public Timer(Integer timeInterval, Runnable runnable){
    	this.runnable = runnable;
        this.timeInterval = timeInterval;
    }

//    public void start() throws InterruptedException {
//        while (true) {
//            Thread.sleep(this.timeInterval);
//            runnable.run();
//        }
//    }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(this.timeInterval);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                runnable.run();
            }
        }
    }
