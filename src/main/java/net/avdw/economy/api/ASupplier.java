package net.avdw.economy.api;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import org.pmw.tinylog.Logger;

public abstract class ASupplier<T> extends AThread
{

    private final List<BlockingQueue> outputs;

    public ASupplier(BlockingQueue... outputs)
    {
        this.outputs = Arrays.asList(outputs);
    }

    @Override
    public void run()
    {
        Thread.currentThread().setName(this.getClass().getSimpleName());
        Logger.debug(String.format("started"));

        while (thread == Thread.currentThread())
        {
            try
            {
                T good = produce();

                for (BlockingQueue output : outputs)
                {
                    output.put(good);
                }
            } catch (InterruptedException ex)
            {
                Logger.debug(ex);
            }
        }
        Logger.debug(String.format("stopped"));
    }

    public abstract T produce();
}
