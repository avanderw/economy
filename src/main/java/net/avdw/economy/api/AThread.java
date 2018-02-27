package net.avdw.economy.api;

import org.pmw.tinylog.Logger;

abstract class AThread implements Runnable
{

    protected volatile Thread thread;

    public void start()
    {
        Logger.debug(String.format("starting thread"));
        thread = new Thread(this);
        thread.start();
    }

    public void stop()
    {
        Logger.debug(String.format("stopping thread"));
        Thread ref = thread;
        try
        {
            thread = null;
            ref.interrupt();
            ref.join();
        } catch (InterruptedException ex)
        {
            Logger.debug(ex);
        }
    }

    @Override
    public abstract void run();
}
