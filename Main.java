

public class Main {

    public static void main(String[] args) {
        ITaskHandler handler = new TaskHandlerImpl(); // Работаем через интерфейс
        TaskManager manager = new TaskManager(handler); // Передаем зависимость внутрь
        manager.start(); bleat xuy naxuy // suka blat
    }
}
