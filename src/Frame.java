public class Frame {
    private String page;
    private long instantUse;
    private int clockBit;
    private long entryTime;
    private int accessCount;

    public Frame(String page) {
        this.page = page;
        this.instantUse = 0;
        this.clockBit = 1;
        this.entryTime = 0;
        this.accessCount = 0;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public long getInstantUse() {
        return instantUse;
    }

    public void setInstantUse(long instantUse) {
        this.instantUse = instantUse;
    }

    public int getClockBit() {
        return clockBit;
    }

    public void activeClockBit() {
        this.clockBit = 1;
    }

    public void disabledClockBit(){
        this.clockBit = 0;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(long entryTime) {
        this.entryTime = entryTime;
    }

    public int getAccessCount() {
        return accessCount;
    }

    public void incrementAccessCount() {
        this.accessCount++;
    }

    @Override
    public String toString() {
        return "Frame{" +
                "page='" + page + '\'' +
                ", instantUse=" + instantUse +
                ", clockBit=" + clockBit +
                ", entryTime=" + entryTime +
                ", accessCount=" + accessCount +
                '}';
    }
}
