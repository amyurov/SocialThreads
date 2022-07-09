public class SociableThread extends Thread {

    public SociableThread() {
    }

    public  SociableThread(ThreadGroup group, String name) {
        super(group, name);
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (isInterrupted()) return;
                sleep(3000);
                System.out.printf("Hello i'm %s%n", getName());
            }
        } catch (InterruptedException ex) {

        } finally {
            System.out.printf("%s is done%n", getName());
        }
    }

}

