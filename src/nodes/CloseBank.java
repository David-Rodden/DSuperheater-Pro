package nodes;

import Utils.CollectiveOreRequirement;
import org.rspeer.script.task.Task;

public class CloseBank extends Task {
    private final CollectiveOreRequirement collectiveRequirement;

    public CloseBank(final CollectiveOreRequirement collectiveRequirement) {
        this.collectiveRequirement = collectiveRequirement;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        return 0;
    }
}
