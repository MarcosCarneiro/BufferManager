import java.util.HashMap;
import java.util.Map;

public class BufferManager {
    private Map<Integer, Frame> buffer;
    private int BUFFER_LIMIT = 5;

    public BufferManager() {
        this.buffer = new HashMap<>();
    }

    public Frame fecth(int key){
        if(buffer.containsKey(key)){
            return buffer.get(key);
        }
        else if(buffer.size() < BUFFER_LIMIT){
            String line = FileReader.getLine(key);
            Frame frame = new Frame(line);
            buffer.put(key, frame);

            return buffer.get(key);
        }
        else{
            return this.evict(key);
        }
    }

    private Frame evict(int key){
        //add políticas de substituição
        return new Frame("");
    }

    public void displayCache(){
        buffer.forEach((key, frame) -> {
            System.out.println(key + " : " + frame.getPage());
        });
    }

    public void displayStats(){
        buffer.forEach((key, frame) -> {
            System.out.println(key + " : " + " hit = " + frame.getHit() + " miss = " + frame.getMiss());
        });
    }

    public Map<Integer, Frame> getBuffer() {
        return buffer;
    }

    public void setBuffer(Map<Integer, Frame> buffer) {
        this.buffer = buffer;
    }
}
