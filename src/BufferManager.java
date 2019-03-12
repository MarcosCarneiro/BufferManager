import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class BufferManager {
    private Map<Integer, Frame> buffer;
    private int BUFFER_LIMIT = 5;
    private AtomicLong momentCount = new AtomicLong(1);
    private SubstitutionPolicy substitutionPolicy;
    private List<Integer> clockList;
    private int currentClock;

    public BufferManager() {
        this.buffer = new HashMap<>();
        this.clockList = new LinkedList<>();
        this.currentClock = 0;
        substitutionPolicy = SubstitutionPolicy.CLOCK;
    }

    public Frame fecth(int key){
        if(buffer.containsKey(key)){
            Frame frame = buffer.get(key);
            frame.incrementHit();
            return this.addToBuffer(frame, key);
        }
        else if(buffer.size() < BUFFER_LIMIT){
            String page = FileReader.getLine(key);
            Frame frame = new Frame(page);
            frame.incrementMiss();
            return this.addToBuffer(frame, key);
        }
        else{
            return this.evict(key);
        }
    }

    private Frame evict(int key){
        int removeKey = 0;

        if(SubstitutionPolicy.LRU.equals(substitutionPolicy)){
            removeKey = this.lru();
        }
        else if(SubstitutionPolicy.CLOCK.equals(substitutionPolicy)){
            removeKey = this.clock();
        }
        else if(SubstitutionPolicy.MRU.equals(substitutionPolicy)){
            removeKey = this.mru();
        }
        else if(SubstitutionPolicy.FIFO.equals(substitutionPolicy)){
            removeKey = this.fifo();
        }

        buffer.remove(removeKey);
        String page = FileReader.getLine(key);
        Frame frame = new Frame(page);
        frame.incrementMiss();
        frame.activeClockBit();
        frame.setInstantUse(momentCount.getAndIncrement());

        int index = clockList.indexOf(removeKey);
        clockList.set(index, key);

        return buffer.put(key, frame);
    }

    public void displayCache(){
        buffer.forEach((key, frame) -> {
            System.out.println(key + " : " + frame.getPage());
        });
    }

    private int lru(){
        Frame minFrame = new Frame("");
        int minkey = 0;

        for (Map.Entry<Integer, Frame> entry : buffer.entrySet()) {
            Integer key = entry.getKey();
            Frame frame = entry.getValue();

            if (minkey == 0) {
                minFrame = frame;
                minkey = key;
            } else if (frame.getInstantUse() < minFrame.getInstantUse()) {
                minFrame = frame;
                minkey = key;
            }
        }

        return minkey;
    }

    private int clock(){
        boolean find = false;
        int key = 0;

        while(!find){
            key = clockList.get(currentClock);
            Frame frame = buffer.get(key);

            if(frame.getClockBit() == 1){
                frame.disabledClockBit();
                buffer.replace(key, frame);
            }
            else{
                find = true;
            }

            currentClock++;
            if(clockList.size()-1 <= currentClock){
                currentClock = 0;
            }
        }

        return key;
    }

    private int mru()
    {
        Frame minFrame = new Frame("");
        int minkey = 0;

        for (Map.Entry<Integer, Frame> entry : buffer.entrySet()) {
            Integer key = entry.getKey();
            Frame frame = entry.getValue();

            if (minkey == 0) {
                minFrame = frame;
                minkey = key;
            } else if (frame.getInstantUse() > minFrame.getInstantUse()) {
                minFrame = frame;
                minkey = key;
            }
        }

        return minkey;
    }

    private int fifo()
    {
        Frame minFrame = new Frame("");
        int minkey = 0;

        for (Map.Entry<Integer, Frame> entry : buffer.entrySet()) {
            Integer key = entry.getKey();
            Frame frame = entry.getValue();

            if (minkey == 0) {
                minFrame = frame;
                minkey = key;
            } else if (frame.getInstantUse() < minFrame.getInstantUse()) {
                minFrame = frame;
                minkey = key;
            }
        }

        return minkey;
    }

    private void addClock(int key){
        if(!this.clockList.contains(key)){
            this.clockList.add(key);
        }
    }

    private Frame addToBuffer(Frame frame, int key){
        frame.activeClockBit();
        frame.setInstantUse(momentCount.getAndIncrement());
        this.addClock(key);
        return buffer.put(key, frame);
    }

    public void displayStats(){
        buffer.forEach((key, frame) -> {
            System.out.println(key + " : " + " hit = " + frame.getHit() + " miss = " + frame.getMiss() + " momento de uso = " + frame.getInstantUse());
        });
    }
}
