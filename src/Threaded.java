public abstract class Threaded implements Runnable{
    protected ThreadDispatcher dispatcher;
    public String id;
    public ThreadDispatcher getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(ThreadDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    private void notifyDispatcher(){
        if(dispatcher != null)
            dispatcher.update(id);
    }

    public abstract void doRun();

    @Override
    public void run(){
        try{
            doRun();
        }
        finally {
            notifyDispatcher();
        }
    }
}
