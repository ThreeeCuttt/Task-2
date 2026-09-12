import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Где хранить задачи?");
        System.out.println("1. В памяти");
        System.out.println("2. В базе данных");
        System.out.print("Твой выбор: ");

        String choice = scanner.nextLine();

        ITaskHandler handler;
        int userId = 0;

        if ("1".equals(choice)) {
            handler = new InMemoryTaskHandler();

            TaskPrinter.printSuccess(
                    "Выбран режим хранения задач в памяти."
            );

        } else if ("2".equals(choice)) {
            handler = new DatabaseTaskHandler();

            System.out.print("Введите имя пользователя: ");
            String name = scanner.nextLine();

            System.out.print("Введите email пользователя: ");
            String email = scanner.nextLine();

            UserService userService = new UserService();

            User user = new User(name, email);
            User savedUser = userService.save(user);

            userId = savedUser.getId();

            TaskPrinter.printSuccess(
                    "Пользователь сохранён в БД. ID: " + userId
            );

            TaskPrinter.printSuccess(
                    "Выбран режим хранения задач в базе данных."
            );

        } else {
            TaskPrinter.printError("Неверный вариант.");
            return;
        }

        TaskManager manager = new TaskManager(handler, userId);
        manager.start();
    }
}
