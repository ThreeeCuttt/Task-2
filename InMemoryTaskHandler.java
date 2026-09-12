import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryTaskHandler implements ITaskHandler {

    private final List<Task> tasks;
    private int nextId = 1; //todo Простой генератор ID

    public InMemoryTaskHandler() {
        this.tasks = new ArrayList<>();
    }

    @Override
    public boolean addTask(Task task) {
        if (task == null) return false;
        task.setId(nextId++);
        return tasks.add(task);
    }

    @Override
    public boolean removeTaskById(int taskId) {
        //todo Удаляем через stream.filter().findFirst(), чтобы не ломать цикл for при удалении
        Optional<Task> found = tasks.stream()
                .filter(t -> t.getId() == taskId)
                .findFirst();

        return found.map(tasks::remove).orElse(false);
    }

    @Override
    public boolean completeTask(int taskId) {
        return changeStatus(taskId, Status.DONE);
    }

    private boolean changeStatus(int taskId, Status newStatus) {
        return tasks.stream()
                .filter(t -> t.getId() == taskId)
                .findFirst()
                .map(task -> {
                    task.setStatus(newStatus);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks); //todo Возвращаем копию! Защита от взлома списка снаружи.
    }


}
