package nodes;

import Utils.CollectiveOreRequirement;
import Utils.Utilities;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.script.task.Task;

public class OpenBank extends Task {
    private final CollectiveOreRequirement collectiveRequirement;

    public OpenBank(final CollectiveOreRequirement collectiveRequirement) {
        this.collectiveRequirement = collectiveRequirement;
    }

    @Override
    public boolean validate() {
        return Bank.isClosed() && !collectiveRequirement.hasSufficientOre();
    }

    @Override
    public int execute() {
        Bank.open();
        Time.sleepUntil(Bank::isOpen, Utilities.GENERIC_SLEEP);
        return Utilities.GENERIC_SLEEP;
    }

    @Override
    public String toString() {
        return "Opening bank";
    }
}
