import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws InterruptedException, CancellationException, ExecutionException {
        // Создаем пул потоков
        ExecutorService threadPool = Executors.newFixedThreadPool(4);

        // Создаем Callable потоки
        CallableSocialThread thread1 = new CallableSocialThread("Поток-1");
        CallableSocialThread thread2 = new CallableSocialThread("Поток-2");
        CallableSocialThread thread3 = new CallableSocialThread("Поток-3");
        CallableSocialThread thread4 = new CallableSocialThread("Поток-4");

        List<CallableSocialThread> threadsList = new ArrayList<>();
        threadsList.add(thread1);
        threadsList.add(thread2);
        threadsList.add(thread3);
        threadsList.add(thread4);
        // invokeAll() возвращает лист фючеров, когда все Future.isDone() == true
        List<Future<String>> tasksList = threadPool.invokeAll(threadsList);

        //Соберем все результаты в лист. Future.get() не заблокирует main,
        // тк уже отработал ExecutorService.invokeAll()
        List<String> tasksResultList = tasksList.stream()
                .map(task -> {
                    try {
                        return task.get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toList());
        System.out.println("Статистика вывода сообщений:");
        tasksResultList
                .forEach(System.out::print);

        System.out.println();
        System.out.println("Определим поток, завершившийся первым: ");
        // Я так понял, что после того как выполнится первый поток, остальным будет передан interrupt
        String resultOfFirstCompleteTask = threadPool.invokeAny(threadsList);
        threadPool.shutdown();

        // Дождемся полного завершения потоков, чтобы вывести результат invokeAny() в конце.
        while(true) {
            if (threadPool.isTerminated()) {
                System.out.printf("Первым %s", resultOfFirstCompleteTask);
                break;
            }
        }
        
    }

}




