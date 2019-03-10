public class Main {

    public static void main(String[] args) {
        BufferManager bufferManager = new BufferManager();

        bufferManager.fecth(1);
        bufferManager.fecth(10);
        bufferManager.fecth(100);

        bufferManager.displayCache();

    }
}
