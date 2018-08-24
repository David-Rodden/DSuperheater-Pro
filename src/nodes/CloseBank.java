package nodes;

import Utils.CollectiveOreRequirement;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class CloseBank extends Task {
    private final CollectiveOreRequirement collectiveRequirement;

    public CloseBank(final CollectiveOreRequirement collectiveRequirement) {
        this.collectiveRequirement = collectiveRequirement;
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public int execute() {
        Log.info("Closing this bank");
        return 0;
    }

    @Override
    public String toString() {
        return "Closing bank";
    }
}
