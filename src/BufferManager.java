import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class BufferManager {
    private Map<Integer, Frame> buffer;
    private int BUFFER_LIMIT = 5;
    private AtomicLong momentCount = new AtomicLong(1);
    private SubstitutionPolicy substitutionPolicy;

    public BufferManager() {
        this.buffer = new HashMap<>();
        substitutionPolicy = SubstitutionPolicy.LRU;
    }

    public Frame fecth(int key){
        if(buffer.containsKey(key)){
            Frame frame = buffer.get(key);
            frame.incrementHit();
            frame.setCount(momentCount.getAndIncrement());
            return buffer.put(key, frame);
        }
        else if(buffer.size() < BUFFER_LIMIT){
            String page = FileReader.getLine(key);
            Frame frame = new Frame(page);
            frame.incrementMiss();
            frame.setCount(momentCount.getAndIncrement());
            return buffer.put(key, frame);
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

        buffer.remove(removeKey);
        String page = FileReader.getLine(key);
        Frame frame = new Frame(page);
        frame.incrementMiss();
        frame.setCount(momentCount.getAndIncrement());

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
            } else if (frame.getCount() < minFrame.getCount()) {
                minFrame = frame;
                minkey = key;
            }
        }

        return minkey;
    }

    public void displayStats(){
        buffer.forEach((key, frame) -> {
            System.out.println(key + " : " + " hit = " + frame.getHit() + " miss = " + frame.getMiss() + " count = " + frame.getCount());
        });
    }
}
