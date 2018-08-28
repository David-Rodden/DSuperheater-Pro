package nodes;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;
import utils.CollectiveOreRequirement;
import utils.OreRequirement;
import utils.Utilities;

import java.util.List;

public class DepositBars extends Task {
    private final CollectiveOreRequirement collectiveRequirement;
    private final String[] whiteList;

    public DepositBars(final CollectiveOreRequirement collectiveRequirement) {
        this.collectiveRequirement = collectiveRequirement;
        final List<String> whiteList = collectiveRequirement.getOreNames();
        whiteList.add("Nature rune");
        this.whiteList = whiteList.toArray(new String[whiteList.size()]);
    }

    @Override
    public boolean validate() {
        return Bank.isOpen() && (collectiveRequirement.hasTooMuchOre() || Inventory.contains("Steel bar"));
    }

    @Override
    public int execute() {
        final int space = (Inventory.contains("Fire rune") ? 26 : 27) / collectiveRequirement.requiredSpaceForBar();
        for (final OreRequirement requirement : collectiveRequirement.getOreRequirements()) {
            final String oreName = requirement.getName();
            final int uniqueOreForBar = space * requirement.getAmount();
            final int currentOreCount = Inventory.getCount(oreName);
            if (currentOreCount <= uniqueOreForBar) continue;
            Log.info("current: " + currentOreCount + ", unique: " + uniqueOreForBar + ", diff: " + (currentOreCount - uniqueOreForBar));
            Bank.deposit(oreName, currentOreCount - uniqueOreForBar);
            Time.sleepUntil(() -> Inventory.getCount(oreName) == uniqueOreForBar, Utilities.GENERIC_SLEEP);
        }
        Bank.depositAllExcept(whiteList);
        return Utilities.GENERIC_SLEEP;
    }

    @Override
    public String toString() {
        return "Depositing bars";
    }
}
