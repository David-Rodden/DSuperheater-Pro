package nodes;

import Utils.CollectiveOreRequirement;
import Utils.Utilities;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.task.Task;

public class DepositOres extends Task {
    private final CollectiveOreRequirement collectiveRequirement;

    public DepositOres(final CollectiveOreRequirement collectiveRequirement) {
        this.collectiveRequirement = collectiveRequirement;
    }

    @Override
    public boolean validate() {
        return Bank.isOpen() && !collectiveRequirement.hasSufficientOre() && Inventory.contains("Steel bar");
    }

    @Override
    public int execute() {
        Bank.depositAllExcept(collectiveRequirement.getOreNames().toArray(new String[collectiveRequirement.getUniqueOreCount()]));
        return Utilities.GENERIC_SLEEP;
    }
}