public class SleepWorker extends Threaded {
    @Override
    public void doRun() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
