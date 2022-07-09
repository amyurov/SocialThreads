public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Создаем группу для потоков
        ThreadGroup socialThreadsGroup = new ThreadGroup("флудилка");

        // Создаем потоки:
        new Thread(socialThreadsGroup, new SociableThread(), "Поток1").start();
        new Thread(socialThreadsGroup, new SociableThread(), "Поток2").start();
        new Thread(socialThreadsGroup, new SociableThread(), "Поток3").start();
        new Thread(socialThreadsGroup, new SociableThread(), "Поток4").start();

        // Останавливаем поток main, чтобы порожденные потоки успели пообщаться
        Thread.sleep(15000);

        // Завершаем работу порожденных потоков
        System.out.println("Завершаем работу");
        socialThreadsGroup.interrupt();

        // Делаем в main небольшую пазу, чтобы после завершения потоки успели выйти из группы
        // Иначе метод destroy() выкидывает IllegalThreadStateException
        Thread.sleep(10);
        socialThreadsGroup.destroy();
    }
}
