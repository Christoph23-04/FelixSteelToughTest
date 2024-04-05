package fsteel.gameclock;

public abstract class AbstractGameProcess implements GameProcess{

    private long processStartTime;
    private boolean isProcessRunning;
    private Thread processThread;

    public AbstractGameProcess(){
        isProcessRunning = false;
        processStartTime = -1;
        GameProcessManager.registerProcess(this);
    }

    @Override
    public void destroyProcess() {
        GameProcessManager.removeProcess(this);
    }

    @Override
    public final void onProcessStart(int startType) {
        expectedThreadStart(startType);
        processThread = new Thread(new Runnable() {
            @Override
            public void run() {
                externalProcess();
            }
        });
        processThread.setPriority(Thread.MAX_PRIORITY);
        isProcessRunning = true;
        processStartTime = System.nanoTime();
        processThread.start();
    }

    @Override
    public final void onProcessStop(int stopType) {
        expectedThreadInterruption(stopType);
        isProcessRunning = false;
        processThread.interrupt();
        processThread = null;
    }

    @Override
    public boolean isProcessRunning() {
        return isProcessRunning;
    }

    @Override
    public long getProcessStartTime() {
        return processStartTime;
    }

    /**
     * @returns null if there is currently no thread available, because the game isn't running
     **/
    @Override
    public Thread getProcessThread(){
        return processThread;
    }

    protected void expectedThreadStart(int startType){}
    protected void expectedThreadInterruption(int stopType){}
    protected abstract void externalProcess();

}
