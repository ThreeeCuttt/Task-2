import java.util.List;



public final class TaskPrinter {

    //todo Запрещаем создание объектов этого класса
    private TaskPrinter() {
        throw new UnsupportedOperationException("Это утилитный класс!");
    }

    public static void printMenu() {
        System.out.println("\n=== МЕНЮ УПРАВЛЕНИЯ ===");
        System.out.println("1. Добавить задачу");
        System.out.println("2. Показать все задачи");
        System.out.println("3. Выполнить задачу");
        System.out.println("0. Выход");
        System.out.print("Твой выбор: ");
    }

    public static void printAllTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Список пуст. Нечего делать, иди домой.");
            return;
        }
        System.out.println("\n--- ТВОИ ДЕЛА ---");
        tasks.forEach(System.out::println);
    }

    public static void printSuccess(String message) {
        System.out.println("[OK] " + message);
    }

    public static void printError(String message) {
        System.err.println("[ERROR] " + message);
    }


}
