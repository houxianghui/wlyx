package com.blue.task;

public class Task {
	private String taskType;
	private String taskReward;
	private String taskId;
	private int timeConsume;
	
	public Task(String taskId,String taskType,String timeConsume) {
		this.taskId = taskId;
		this.taskType = taskType;
		this.timeConsume = Integer.parseInt(timeConsume);
	}
	
	public int getTimeConsume(){
		return timeConsume;
	}
	
	public String getTaskType() {
		return taskType;
	}
	
	public String getTaskReward() {
		return taskReward;
	}
	public void setTaskReward(String taskReward) {
		this.taskReward = taskReward;
	}
	public String getTaskId() {
		return taskId;
	}
}
