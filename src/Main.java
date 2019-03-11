public class Main {

    public static void main(String[] args) {
        BufferManager bufferManager = new BufferManager();

        bufferManager.fecth(1);
        bufferManager.fecth(10);
        bufferManager.fecth(20);
        bufferManager.fecth(10);
        bufferManager.fecth(20);
        bufferManager.fecth(11);
        bufferManager.fecth(12);
        bufferManager.fecth(7);
        bufferManager.fecth(2);
        bufferManager.fecth(3);
        bufferManager.fecth(5);
        bufferManager.fecth(6);
        bufferManager.fecth(7);

        bufferManager.displayCache();
        System.out.println("----------------------------------");
        bufferManager.displayStats();

    }
}
