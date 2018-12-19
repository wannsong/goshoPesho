package scripts.framework.task;

import java.util.LinkedList;
import java.util.List;

import scripts.framework.goal.GoalManager;
import scripts.framework.script.FCScript;

/**
 * This class manages the tasks a script has. It handles
 * the creation of the task list as well as the execution of it.
 * 
 * @author Freddy
 *
 */
public abstract class TaskManager extends GoalManager
{	
	protected List<Task> tasks;
	protected Task currentTask;
	protected boolean running;
	public transient FCScript fcScript;
	
	public TaskManager(FCScript script)
	{
		this.tasks = getTaskList();
		this.fcScript = script;
	}
	
	public TaskManager()
	{}

	public boolean executeTasks()
	{
		synchronized(tasks)
		{
			running = false;
			
			for(Task task : tasks)
			{
				if(task.shouldExecute())
				{
					currentTask = task;
					
					task.execute();
					
					running = true;
				}
			}
			
			return running;
		}
	}
	
	public boolean removeTask(Task task)
	{
		synchronized(tasks)
		{
			return tasks.remove(task);
		}
	}
	
	public boolean addTask(Task task)
	{
		synchronized(tasks)
		{
			return tasks.add(task);
		}
	}
	
	public void setTaskList(List<Task> tasks)
	{
		synchronized(tasks)
		{
			this.tasks = tasks;
		}
	}
	
	public Task getCurrentTask()
	{
		return this.currentTask;
	}
	
	public abstract LinkedList<Task> getTaskList();

}
