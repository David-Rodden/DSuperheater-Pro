package nodes;

import Utils.CollectiveOreRequirement;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Magic;
import org.rspeer.runetek.api.component.tab.Spell;
import org.rspeer.script.task.Task;

public class Superheat extends Task {
    private final CollectiveOreRequirement collectiveOreRequirement;

    public Superheat(final CollectiveOreRequirement collectiveRequirement) {
        this.collectiveOreRequirement = collectiveRequirement;
    }

    @Override
    public boolean validate() {
        return Magic.canCast(Spell.Modern.SUPERHEAT_ITEM) && collectiveOreRequirement.hasSufficientOre();
    }

    @Override
    public int execute() {
        Magic.cast(Spell.Modern.SUPERHEAT_ITEM);
        Time.sleepUntil(Magic::isSpellSelected, 1000);
        if (!Magic.isSpellSelected()) return 0;
        final Item ore = Inventory.getFirst("Iron ore");
        if (ore == null) return 0;
        ore.interact("Superheat");
        return 0;
    }
}
