import java.util.Random;
import java.util.concurrent.Callable;

public class CallableSocialThread implements Callable<String> {

    private String name;

    public CallableSocialThread(String name) {
        this.name = name;
    }

    @Override
    public String call() {
        Thread.currentThread().setName(name);
        Random random = new Random();
        int count = 0;
        try {
            while (count < random.nextInt(7)) {
                count++;
                Thread.sleep(2000);
                System.out.printf("Hello i'm %s%n", Thread.currentThread().getName());
            }
        } catch (InterruptedException ignored) {

        } finally {
            System.out.printf("%s is done%n", Thread.currentThread().getName());
        }
        return String.format("завершился %s отправив в консоль %d сообщений%n", Thread.currentThread().getName(), count);
    }
}
