public class Frame {
    private String page;
    private int hit;
    private int miss;
    private int count;

    public Frame(String page) {
        this.page = page;
        this.hit = 0;
        this.miss = 0;
        this.count = 0;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Frame{" +
                "page='" + page + '\'' +
                ", hit=" + hit +
                ", miss=" + miss +
                ", count=" + count +
                '}';
    }
}
