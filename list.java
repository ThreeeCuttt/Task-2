import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class list {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<String> tasks = new ArrayList<>();

        while (true) {

            System.out.println("\n=== Menu ===");
            System.out.println("У вас есть " + tasks.size() + " задач(а/и).");
            System.out.println("1. Добавить новую задачу");
            System.out.println("2. Отметить задачу как выполненную");
            System.out.println("3. Удалить задачу(по номерам)");
            System.out.println("4. Показать все задачи");
            System.out.println("0. Выйти из программы");
            System.out.print("Выберите пункт меню: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Введите текст новой задачи");
                        String newTask = scanner.nextLine();
                        tasks.add(newTask);
                        System.out.println("Задача успешно добавлена!");

                        break;
                    case 2:
                        System.out.println("\nВы выбрали: Отметить задачу как выполненную");

                        if (tasks.isEmpty()) {
                            // Если задач нет, говорим об этом и выходим из case
                            System.out.println("[Список пуст] У вас пока нет задач, которые можно отметить.");
                        } else {
                            for (int i = 0; i < tasks.size(); i++) {
                                System.out.println((i + 1) + ". \t" + tasks.get(i));
                            }
                            System.out.print("Введите номер задачи, которую хотите отметить: ");
                            if (scanner.hasNextInt()) {
                                int taskNumber = scanner.nextInt() - 1;

                                if (taskNumber >= 0 && taskNumber < tasks.size()) {

                                    String oldTask = tasks.get(taskNumber);

                                    String newCompletedTask = "[V] " + oldTask;

                                    tasks.set(taskNumber, newCompletedTask);

                                    System.out.println("Задача успешно отмечена!");
                                } else {
                                    System.out.println("Ошибка: Нет задачи с таким номером.");
                                }
                            } else {
                                System.out.println("Ошибка: Нужно вводить цифры.");
                                scanner.next();
                            }
                        }
                        break;

                    case 3:
                        System.out.println("Вы выбрали: Удалить задачу(по номерам)");

                        if (tasks.isEmpty()) {
                            System.out.println("[Список пуст] Нечего удалять.");
                        } else {

                            System.out.println("Ваши задачи:");
                            for (int i = 0; i < tasks.size(); i++) {
                                System.out.println((i + 1) + ". " + tasks.get(i));
                            }

                            System.out.print("Введите номер задачи для удаления: ");

                            if (scanner.hasNextInt()) {
                                int taskNumber = scanner.nextInt();
                                int indexToDelete = taskNumber - 1; // для списка не с 0 а с 1

                                if (indexToDelete >= 0 && indexToDelete < tasks.size()) {

                                    String removedTask = tasks.remove(indexToDelete);
                                    System.out.println("Задача успешно удалена: " + removedTask);
                                } else {
                                    System.out.println("Ошибка: Нет задачи с таким номером.");
                                }
                            } else {
                                System.out.println("Ошибка: Нужно вводить цифры.");
                                scanner.next();
                            }
                        }
                        break;

                    case 4:
                        System.out.println("\n=== Список ваших задач ===");

                        if (tasks.size() == 0) {
                            System.out.println("[Список пуст] Задач нет. Самое время добавить первую!");
                        } else {

                            for (int i = 0; i < tasks.size(); i++) {

                                String текущаяЗадача = tasks.get(i);

                                System.out.println(i + ". \t" + текущаяЗадача);
                            }
                        }
                        System.out.println("\nНажмите Enter, чтобы вернуться в меню...");
                        scanner.nextLine();
                        break;

                    case 0:
                        System.out.print("Вы выбрали: Выйти из программы");
                        scanner.close();
                        return;

                    default:

                        System.out.println("Ошибка/выберите/ от 0 до 4.");

                }
            } else {

                System.out.println("Ошибка: Нужно вводить цифры.");
                scanner.next();
            } // остался последнее задание это удалять не начинал его

        }
    }
}

