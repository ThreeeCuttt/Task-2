import java.time.LocalDate;
import java.util.Scanner;

public class TaskManager {
    private final ITaskHandler handler;
    private final Scanner scanner;

    public TaskManager(ITaskHandler handler) {
        this.handler = new TaskHandlerImpl(); // Наш механизм работы с данными
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            TaskPrinter.printMenu(); // Просим нарисовать меню
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> handleAdd();
                case "2" -> handleShowAll();
                case "3" -> handleComplete();
                case "0" -> {
                    TaskPrinter.printSuccess("Выходим...");
                    return; // break из цикла, конец программы
                }
                default -> TaskPrinter.printError("Неверная кнопка. Попробуй еще раз.");
            }
        }
    }

    private void handleAdd() {
        System.out.print("Название задачи: ");
        String name = scanner.nextLine();

        System.out.print("Описание задачи: ");
        String description = scanner.nextLine();

        // Ввод дедлайна с валидацией формата (ГГГГ-ММ-ДД)
        LocalDate deadline = null;
        while (deadline == null) {
            System.out.print("Дедлайн (в формате ГГГГ-ММ-ДД, например 2026-12-31) или нажмите Enter, чтобы пропустить: ");
            String dateInput = scanner.nextLine();
            if (dateInput.isBlank()) {
                break; // Пользователь не захотел указывать дедлайн
            }
            try {
                deadline = LocalDate.parse(dateInput);
            } catch (Exception e) {
                TaskPrinter.printError("Неверный формат даты! Попробуйте еще раз.");
            }
        }

        // Ввод приоритета
        Priority priority = Priority.MEDIUM; // По умолчанию
        System.out.print("Приоритет (LOW, MEDIUM, HIGH) [по умолчанию MEDIUM]: ");
        String priorityInput = scanner.nextLine().toUpperCase();
        try {
            if (!priorityInput.isBlank()) {
                priority = Priority.valueOf(priorityInput);
            }
        } catch (IllegalArgumentException e) {
            TaskPrinter.printError("Такого приоритета нет. Установлен MEDIUM.");
        }

        // Создаем задачу со всеми новыми полями
        Task newTask = new Task(0, name, description, deadline, priority);
        handler.addTask(newTask);
        TaskPrinter.printSuccess("Задача '" + name + "' добавлена!");
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
                TaskPrinter.printError("Задачи с таким ID нет.");
            }
        } catch (NumberFormatException e) {
            TaskPrinter.printError("Нужно вводить цифры!");
        }
    }
}


