package nodes;

import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.script.task.Task;

import java.util.regex.Pattern;

public class WithdrawRunes extends Task {
    private final String acceptableStaves;

    public WithdrawRunes() {
        acceptableStaves = "Staff of fire, Fire battlestaff, Mystic fire staff, Lava battlestaff, Mystic lava staff, Steam battlestaff, Mystic steam staff, Smoke battlestaff, Mystic smoke staff, Tome of fire";
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public int execute() {
        if (!Equipment.contains(Pattern.compile(acceptableStaves)) && !Bank.contains("Fire rune")) return -1;
        // get fire & nature runes from bank
        return 0;
    }

    @Override
    public String toString() {
        return "Withdrawing runes from bank";
    }
}
