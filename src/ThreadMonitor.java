import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class ThreadMonitor extends Threaded {

    private BufferedWriter writer;

    public ThreadMonitor(){
        File output = new File("output.txt");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        writer = new BufferedWriter(fileWriter);
    }

    @Override
    public void doRun(){
        int previousChanges = 0;
        while (true) {
            synchronized (dispatcher) {
                if (previousChanges != dispatcher.changes) {
                    previousChanges = dispatcher.changes;
                    for (String id : dispatcher.pull) {
                        writer.write(id + "\n");
                    }
                }
            }
        }
    }
}
