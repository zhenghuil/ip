package reacher;

import java.util.ArrayList;
import reacher.task.Task;

/**
 * List of Tasks.
 */
public class TaskList {
    ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Creates a list of tasks.
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks(){
        return tasks;
    }

    /**
     * Returns number of tasks in list.
     */
    public int noOfTasks(){
        return tasks.size();
    }

    /**
     * Remove task from list at specified index.
     */
    public void delete(int index){
        tasks.remove(index);
    }

    /**
     * Adds task to the list.
     */
    public void addTask(Task task){
        tasks.add(task);
    }

    /**
     * Returns Task at specified index.
     */
    public Task getTask(int index){
        return tasks.get(index);
    }

    public TaskList findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isMatching(keyword)) {
                matchingTasks.add(task);
            }
        }
        TaskList list = new TaskList(matchingTasks);
        return list;
    }
    @Override
    public String toString() {
        StringBuilder list = new StringBuilder("Tasks:\n");
        int c = 1;
        for (Task task : tasks) {
            list.append(task.toString()).append("\n");
            c++;
        }
        return String.valueOf(list);
    }
}
