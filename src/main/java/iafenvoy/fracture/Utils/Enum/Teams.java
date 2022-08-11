package iafenvoy.fracture.Utils.Enum;

import iafenvoy.fracture.Utils.CommandUtil;

public enum Teams {
    NONE("none", "§f[None]"),
    HUMAN("human", "§b[Human]"),
    UNDEAD("undead", "§7[Undead]"),
    NETHER("nether", "§4[Nether]"),
    END("end", "§9End");

    private final String translateKey;
    private final String prefix;

    Teams(String translateKey, String prefix) {
        this.translateKey = translateKey;
        this.prefix = prefix;
    }

    public static Teams getByName(String name) {
        for (Teams t : Teams.values())
            if (t.translateKey.equals(name))
                return t;
        return Teams.NONE;
    }

    public static void initTeam() {
        try {
            for (Teams t : Teams.values()) {
                if (t == Teams.NONE)
                    continue;
                CommandUtil.addTeam(t.translateKey, t.prefix);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTranslateKey() {
        return this.translateKey;
    }

    public String getPrefix() {
        return this.prefix;
    }
}
