package net.avdw.economy.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import org.pmw.tinylog.Logger;

public abstract class AFactory<I extends AGood, O extends AGood> extends AThread
{

    private final List<BlockingQueue<I>> inputs;
    private final List<BlockingQueue<O>> outputs;

    public AFactory(BlockingQueue<I> input, BlockingQueue<O>... outputs)
    {
        this.inputs = new ArrayList();
        this.inputs.add(input);
        this.outputs = Arrays.asList(outputs);
    }

    public AFactory(BlockingQueue<I> input, List<BlockingQueue<O>> outputs)
    {
        this.inputs = new ArrayList();
        this.inputs.add(input);
        this.outputs = outputs;
    }

    public AFactory(List<BlockingQueue<I>> inputs, BlockingQueue<O> output)
    {
        this.inputs = inputs;
        this.outputs = new ArrayList();
        this.outputs.add(output);
    }

    public AFactory(List<BlockingQueue<I>> inputs, List<BlockingQueue<O>> outputs)
    {
        this.inputs = inputs;
        this.outputs = outputs;
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
                    consume(input.take());

                    O good = produce();

                    for (BlockingQueue<O> output : outputs)
                    {
                        output.put(good);
                    }
                } catch (InterruptedException ex)
                {
                    Logger.warn(ex);
                }
            });
        }

        Logger.debug(String.format("stopped"));
    }

    public abstract void consume(I good);

    public abstract O produce();
}
