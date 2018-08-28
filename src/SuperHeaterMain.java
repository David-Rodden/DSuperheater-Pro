import utils.CollectiveOreRequirement;
import utils.OreRequirement;
import nodes.*;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.Task;
import org.rspeer.script.task.TaskScript;
import org.rspeer.ui.Log;

import java.awt.*;

@ScriptMeta(version = 1.1, developer = "Dungeonqueer", desc = "Superheats ores", name = "DSuperHeater Pro")
public class SuperHeaterMain extends TaskScript implements RenderListener {
    private CollectiveOreRequirement collectiveRequirement;



    @Override
    public void onStart() {
        collectiveRequirement = new CollectiveOreRequirement(new OreRequirement("Iron ore", 1), new OreRequirement("Coal", 2));
        submit(new Superheat(collectiveRequirement), new OpenBank(collectiveRequirement), new DepositOres(collectiveRequirement), new WithdrawOres(collectiveRequirement), new CloseBank(collectiveRequirement));
        Log.info("Added all tasks");

    }

    @Override
    public void notify(final RenderEvent renderEvent) {
        final Graphics graphics = renderEvent.getSource();
        graphics.setColor(Color.CYAN);
        final Task task = getCurrent();
        graphics.drawString("Task: " + (task == null ? "None" : task.toString()), 20, 20);
    }
}