import java.util.List;

public interface ITaskHandler {

    boolean addTask(Task task);
    boolean removeTaskById(int taskId);
    List<Task> getAllTasks();
    boolean completeTask(int taskId);
}


