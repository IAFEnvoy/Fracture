package iafenvoy.fracture.Utils.Enum;

public enum Teams {
    NONE("none"),
    HUMAN("human"),
    UNDEAD("undead"),
    NETHER("nether"),
    END("end");
    private String translateKey;

    Teams(String translateKey) {
        this.translateKey = translateKey;
    }

    public String getTranslateKey() {
        return this.translateKey;
    }

    public static Teams getByName(String name){
      for(Teams t : Teams.values())
        if(t.translateKey.equals(name))
          return t;
      return Teams.NONE;
    }
}
