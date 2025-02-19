package reacher.command;

import reacher.Storage;
import reacher.TaskList;

public class HelpCommand extends Command{
    @Override
    public String execute(String input, TaskList tasks, Storage storage) {
        return "Command structure:\n" +
                "Add, {name of task}, {task type}, {start}, {end}\n" +
                "edit, {task no}, {done, undone or delete}\n" +
                "list\n" +
                "find, {keyword}\n" +
                "bye";
    }
}
