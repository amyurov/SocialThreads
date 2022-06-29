public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Создаем группу для потоков
        ThreadGroup socialThreadsGroup = new ThreadGroup("флудилка");

        // Создаем потоки:
        SociableThread thread1 = new SociableThread(socialThreadsGroup, "Поток1");
        SociableThread thread2 = new SociableThread(socialThreadsGroup, "Поток2");
        SociableThread thread3 = new SociableThread(socialThreadsGroup, "Поток3");
        SociableThread thread4 = new SociableThread(socialThreadsGroup, "Поток4");

        // Порождаем потоки
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

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
