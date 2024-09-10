package org.example.taskmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository =  taskRepository;
    }

    // יצירת משימה חדשה
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // קבלת כל המשימות
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // קבלת משימה לפי ID
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    // עדכון משימה קיימת
    public Task updateTask(Long id, Task updatedTask) {
        Optional<Task> existingTaskOptional = taskRepository.findById(id);
        if (existingTaskOptional.isPresent()) {
            Task existingTask = existingTaskOptional.get();
            existingTask.setName(updatedTask.getName());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setStatus(updatedTask.getStatus());
            existingTask.setPriority(updatedTask.getPriority());
            existingTask.setCompleted(updatedTask.isCompleted());
            return taskRepository.save(existingTask);
        } else {
            throw new RuntimeException("Task not found with id: " + id);
        }
    }

    // מחיקת משימה לפי ID
    public void deleteTask(Long id) {
         taskRepository.deleteById(id);
    }
}

