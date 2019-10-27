package top.manred.asyndata.asyn;

import java.util.concurrent.atomic.AtomicInteger;

public class AsyncObject {
    private int opt;
    // 同步状态
    private AtomicInteger syncStatus = new AtomicInteger(SYNC_UNCOMMIT);
    public final static int SYNC_UNCOMMIT = 0; // 尚未提交同步
    public final static int SYNC_COMMITED = 1; // 已经提交同步
    public final static int SYNC_SAVING = 2; // 正在同步中

    public int getSyncStatus() {
        return syncStatus.get();
    }

    public boolean setSyncStatus(int newSyncStatus, int expectStatus) {
        boolean retValue = this.syncStatus.compareAndSet(expectStatus, newSyncStatus);
        return retValue;
    }

    public DbOperate getOpt() {
        return DbOperate.getOpt(this.opt);
    }

    public void setOpt(DbOperate opt) {
        this.opt = opt.getValue();
    }
}
