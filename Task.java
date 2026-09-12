import java.time.LocalDate;

enum Priority {
    LOW, MEDIUM, HIGH
}

enum Status {
    PENDING, IN_PROGRESS, DONE
}

public class Task {

    private int id;
    private String name;
    private String description;
    private LocalDate deadline;
    private Priority priority;
    private Status status;

    // ID пользователя, которому принадлежит задача.
    // Пока будет использоваться только при хранении в базе данных.
    private int userId;

    public Task(int id, String name, String description,
                LocalDate deadline, Priority priority) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.status = Status.PENDING;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        String descText = (description != null) ? description : "нет";
        String dateText = (deadline != null) ? deadline.toString() : "не задан";

        return "Задача #" + id + " [" + priority + "]" +
                "\n  Название: " + name +
                "\n  Описание: " + descText +
                "\n  Дедлайн: " + dateText +
                "\n  Статус: " + status;
    }
}



