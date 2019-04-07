import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class ThreadMonitor extends Threaded {

    @Override
    public void doRun() {
        int previousChanges = 0;
        while (true) {
            synchronized (dispatcher) {
                if (previousChanges != dispatcher.changes) {
                    previousChanges = dispatcher.changes;
                    for (String id : dispatcher.pull) {
                        System.out.println(id);
                    }
                }
            }
        }
    }
}
