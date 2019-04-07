import java.util.HashMap;

public class ThreadDispatcher {
    private static ThreadDispatcher instance;
    private HashMap<String, Threaded> pull;
    private ThreadDispatcher(){
        pull = new HashMap<>();
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

    public void update(String id){
        pull.remove(id);
    }

    public void Add(Threaded th){
        th.id = th.getClass().getName() + pull.size();
        pull.put(th.id, th);
        Thread t = new Thread(th, th.id);
        t.start();
    }

}
