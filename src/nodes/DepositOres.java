package nodes;

import utils.CollectiveOreRequirement;
import utils.Utilities;
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
        final String[] oresToKeep = collectiveRequirement.getOreNames().toArray(new String[collectiveRequirement.getUniqueOreCount()]);
        Bank.depositAllExcept(oresToKeep);
        return Utilities.GENERIC_SLEEP;
    }

    @Override
    public String toString() {
        return "Depositing ores";
    }
}
