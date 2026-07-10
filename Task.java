import java.time.LocalDate;
enum Priority {
    LOW, MEDIUM, HIGH
}
enum Status {
    PENDING, IN_PROGRESS, DONE
}
public class Task {

    // 1. Поля (данные)
    private int id; // Уникальный номер
    private String name;
    private String description;
    private LocalDate deadline; // Дедлайн (год-месяц-день)
    private Priority priority;
    private Status status;

    //todo: конструктор
    public Task(int id, String name) {
        this.id = id;
        this.name = name;
        this.status = Status.PENDING; // По умолчанию новая задача "ожидает"
    }
//todo set an get
    public int getId() {return id;}

    public void setId(int id) {this.id = id;}


    public String getName() {return name;}

    public void setName(String name) {this.name = name;}


    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}


    public LocalDate getDeadline() {return deadline;}

    public void setDeadline(LocalDate deadline) {this.deadline = deadline;}


    public Priority getPriority() {return priority;}

    public void setPriority(Priority priority) {this.priority = priority;}


    public Status getStatus() {return status;}

    public void setStatus(Status status) {this.status = status;}

    @Override
    public String toString() {
        return "Задача #" + id + ": " + name + " | Статус: " + status;
    }
}
