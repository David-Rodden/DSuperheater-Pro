package Utils;

import org.rspeer.runetek.api.component.tab.Inventory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CollectiveOreRequirement {
    private final List<OreRequirement> oreRequirements;

    public CollectiveOreRequirement(final OreRequirement... oreRequirements) {
        this.oreRequirements = Arrays.asList(oreRequirements);
    }

    public boolean hasSufficientOre() {
        for (final OreRequirement requirement : oreRequirements)
            if (Inventory.getCount(requirement.getName()) < requirement.getAmount()) return false;
        return true;
    }

    public List<OreRequirement> getOreRequirements() {
        return oreRequirements;
    }

    public int requiredSpaceForBar(){
        return oreRequirements.stream().mapToInt(OreRequirement::getAmount).sum();
    }
    public List<String> getOreNames() {
        return oreRequirements.stream().map(OreRequirement::getName).collect(Collectors.toList());
    }

    public int getUniqueOreCount() {
        return oreRequirements.size();
    }
}
