public class Frame {
    private String page;
    private int hit;
    private int miss;
    private long instantUse;
    private int clockBit;

    public Frame(String page) {
        this.page = page;
        this.hit = 0;
        this.miss = 0;
        this.instantUse = 0;
        this.clockBit = 1;
    }

    public void incrementHit(){
        this.hit++;
    }

    public void incrementMiss(){
        this.miss++;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public int getMiss() {
        return miss;
    }

    public void setMiss(int miss) {
        this.miss = miss;
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

    @Override
    public String toString() {
        return "Frame{" +
                "page='" + page + '\'' +
                ", hit=" + hit +
                ", miss=" + miss +
                ", instantUse=" + instantUse +
                '}';
    }
}
