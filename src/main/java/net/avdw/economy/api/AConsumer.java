package net.avdw.economy.api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import org.pmw.tinylog.Logger;

public abstract class AConsumer<I> extends AThread
{

    private final List<BlockingQueue<I>> inputs;

    public AConsumer(BlockingQueue<I> input)
    {
        this.inputs = new ArrayList();
        this.inputs.add(input);
    }

    public AConsumer(List<BlockingQueue<I>> inputs)
    {
        this.inputs = inputs;
    }

    @Override
    public void run()
    {
        Thread.currentThread().setName(this.getClass().getSimpleName());
        Logger.debug(String.format("started"));

        while (thread == Thread.currentThread())
        {
            inputs.forEach((input) ->
            {
                try
                {
                    if (thread.isInterrupted())
                    {
                        throw new InterruptedException("interrupted before being handled");
                    }
                    consume(input.take());
                } catch (InterruptedException ex)
                {
                    Logger.debug(ex);
                }
            });
        }
        Logger.debug(String.format("stopped"));
    }

    public abstract void consume(I good);
}
