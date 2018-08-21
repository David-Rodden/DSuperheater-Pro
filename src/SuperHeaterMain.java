import Utils.CollectiveOreRequirement;
import Utils.OreRequirement;
import nodes.*;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.TaskScript;

@ScriptMeta(developer = "Dungeonqueer", desc = "Superheats ores", name = "DSuperHeater Pro")
public class SuperHeaterMain extends TaskScript implements RenderListener {
    private CollectiveOreRequirement collectiveRequirement;

    @Override
    public int loop() {
        if (Bank.isOpen()) {
            Bank.close();
            Time.sleepUntil(Bank::isClosed, 1000);
        }
        return 0;
    }

    @Override
    public void onStart() {
        collectiveRequirement = new CollectiveOreRequirement(new OreRequirement("Iron ore", 1), new OreRequirement("Coal", 2));
        submit(new Superheat(collectiveRequirement), new OpenBank(collectiveRequirement), new DepositOres(collectiveRequirement), new WithdrawOres(collectiveRequirement), new CloseBank(collectiveRequirement));
    }

    @Override
    public void notify(final RenderEvent renderEvent) {

    }
}