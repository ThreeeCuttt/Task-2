import java.time.LocalDate;
import java.util.Scanner;

public class TaskManager {

    private final ITaskHandler handler;
    private final Scanner scanner;
    private final int userId;

    public TaskManager(ITaskHandler handler, int userId) {
        this.handler = handler;
        this.userId = userId;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            TaskPrinter.printMenu();

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> handleAdd();
                case "2" -> handleShowAll();
                case "3" -> handleComplete();
                case "0" -> {
                    TaskPrinter.printSuccess("Выходим...");
                    return;
                }
                default -> TaskPrinter.printError(
                        "Неверная кнопка. Попробуй еще раз."
                );
            }
        }
    }

    private void handleAdd() {
        System.out.print("Название задачи: ");
        String name = scanner.nextLine();

        System.out.print("Описание задачи: ");
        String description = scanner.nextLine();

        LocalDate deadline = null;

        while (deadline == null) {
            System.out.print(
                    "Дедлайн в формате ГГГГ-ММ-ДД " +
                            "(например, 2026-12-31) " +
                            "или нажмите Enter, чтобы пропустить: "
            );

            String dateInput = scanner.nextLine();

            if (dateInput.isBlank()) {
                break;
            }

            try {
                deadline = LocalDate.parse(dateInput);
            } catch (Exception e) {
                TaskPrinter.printError(
                        "Неверный формат даты! Попробуйте еще раз."
                );
            }
        }

        Priority priority = Priority.MEDIUM;

        System.out.print(
                "Приоритет (LOW, MEDIUM, HIGH) " +
                        "[по умолчанию MEDIUM]: "
        );

        String priorityInput = scanner.nextLine().toUpperCase();

        try {
            if (!priorityInput.isBlank()) {
                priority = Priority.valueOf(priorityInput);
            }
        } catch (IllegalArgumentException e) {
            TaskPrinter.printError(
                    "Такого приоритета нет. Установлен MEDIUM."
            );
        }

        Task newTask = new Task(
                0,
                name,
                description,
                deadline,
                priority
        );

        // В режиме БД здесь будет ID пользователя.
        // В режиме памяти это значение 0 и нигде не используется.
        newTask.setUserId(userId);

        if (handler.addTask(newTask)) {
            TaskPrinter.printSuccess(
                    "Задача '" + name + "' добавлена!"
            );
        } else {
            TaskPrinter.printError(
                    "Не удалось добавить задачу."
            );
        }
    }

    private void handleShowAll() {
        TaskPrinter.printAllTasks(handler.getAllTasks());
    }

    private void handleComplete() {
        System.out.print("ID задачи, которую выполнил: ");

        try {
            int id = Integer.parseInt(scanner.nextLine());

            if (handler.completeTask(id)) {
                TaskPrinter.printSuccess("Задача выполнена!");
            } else {
                TaskPrinter.printError(
                        "Задачи с таким ID нет."
                );
            }
        } catch (NumberFormatException e) {
            TaskPrinter.printError(
                    "Нужно вводить цифры!"
            );
        }
    }
}


