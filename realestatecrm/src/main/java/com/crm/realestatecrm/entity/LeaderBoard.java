package com.crm.realestatecrm.entity;

public class LeaderBoard {
	
	private SalesExecutive salesExecutive;
	
	private int taskCount;
	
	private int custmerCount;
	
	public LeaderBoard() {
		
	}

	public LeaderBoard(SalesExecutive salesExecutive, int taskCount, int custmerCount) {
		super();
		this.salesExecutive = salesExecutive;
		this.taskCount = taskCount;
		this.custmerCount = custmerCount;
	}

	public SalesExecutive getSalesExecutive() {
		return salesExecutive;
	}

	public void setSalesExecutive(SalesExecutive salesExecutive) {
		this.salesExecutive = salesExecutive;
	}

	public int getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(int taskCount) {
		this.taskCount = taskCount;
	}

	public int getCustmerCount() {
		return custmerCount;
	}

	public void setCustmerCount(int custmerCount) {
		this.custmerCount = custmerCount;
	}

}
