package nodes;

import utils.CollectiveOreRequirement;
import utils.OreRequirement;
import utils.Utilities;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;

public class WithdrawOres extends org.rspeer.script.task.Task {
    private final CollectiveOreRequirement collectiveRequirement;

    public WithdrawOres(final CollectiveOreRequirement collectiveRequirement) {
        this.collectiveRequirement = collectiveRequirement;
    }

    @Override
    public boolean validate() {
        return Bank.isOpen() && !collectiveRequirement.hasSufficientOre();
    }

    @Override
    public int execute() {
        final int space = Inventory.getFreeSlots() / collectiveRequirement.requiredSpaceForBar();
        for (final OreRequirement requirement : collectiveRequirement.getOreRequirements()) {
            final String oreName = requirement.getName();
            final int uniqueOreForBar = space * requirement.getAmount();
            final int currentOreCount = Inventory.getCount(oreName);
            if (currentOreCount == uniqueOreForBar) continue;
            final int oreToWithdraw = uniqueOreForBar - currentOreCount;
            if (Bank.getCount(oreToWithdraw) < oreToWithdraw) return -1; // kill the program
            Bank.withdraw(oreName, oreToWithdraw);
            Time.sleepUntil(() -> Inventory.getCount(oreName) == uniqueOreForBar, Utilities.GENERIC_SLEEP);
        }
        return Utilities.GENERIC_SLEEP;
    }

    @Override
    public String toString() {
        return "Withdrawing ores from bank";
    }
}
