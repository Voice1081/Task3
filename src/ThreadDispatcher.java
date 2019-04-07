import java.util.ArrayList;

public class ThreadDispatcher {
    private static ThreadDispatcher instance;
    ArrayList<String> pull;
    private ThreadMonitor monitor;
    int changes;
    private ThreadDispatcher(){
        pull = new ArrayList<>();
        changes = 0;
        monitor = new ThreadMonitor();
        Add(monitor);
    }

    public static ThreadDispatcher getInstance(){
        if(instance == null)
            synchronized (ThreadDispatcher.class)
            {
                if(instance == null)
                    instance = new ThreadDispatcher();
            }
        return instance;
    }

    synchronized void update(String id){
        pull.remove(id);
        changes += 1;
    }


    public void Add(Threaded th){
        th.id = th.getClass().getName() + changes;
        th.setDispatcher(this);
        synchronized (this) {
            pull.add(th.id);
            changes += 1;
        }
        Thread t = new Thread(th, th.id);
        t.start();
    }

}
