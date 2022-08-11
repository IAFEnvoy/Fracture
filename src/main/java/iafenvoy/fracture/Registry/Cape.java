package iafenvoy.fracture.Registry;

import iafenvoy.fracture.Items.Capes.CapeItem;
import iafenvoy.fracture.Utils.Enum.Teams;

public class Cape {
    public static final CapeItem human = new CapeItem("human", "human", Teams.HUMAN);
    public static final CapeItem nether = new CapeItem("nether", "nether", Teams.NETHER);
    public static final CapeItem undead = new CapeItem("undead", "undead", Teams.UNDEAD);
    public static final CapeItem end = new CapeItem("end", "end", Teams.END);
}
