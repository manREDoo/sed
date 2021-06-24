package top.manred.asyndata.asyn;

public enum DbOperate {
    INSERT(1), UPDATE(2), DELETE(3);

    private int value;

    DbOperate(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static DbOperate getOpt(int opt) {
        switch (opt) {
            case 1:
                return INSERT;
            case 2:
                return UPDATE;
            case 3:
                return DELETE;
            default:
                return null;
        }

    }

}