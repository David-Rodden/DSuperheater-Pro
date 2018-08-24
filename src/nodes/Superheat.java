package nodes;

import Utils.CollectiveOreRequirement;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.Player;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Magic;
import org.rspeer.runetek.api.component.tab.Spell;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

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
        ore.interact("Cast");
        final Player myPlayer = Players.getLocal();
        if(myPlayer == null)    return 0;
        Time.sleepUntil(myPlayer::isAnimating, 1000);
        Time.sleepUntil(() -> !myPlayer.isAnimating(), 1000);
        return 0;
    }

    @Override
    public String toString() {
        return "Superheating";
    }
}
