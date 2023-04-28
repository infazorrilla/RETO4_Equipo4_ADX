package model.pojos;

import java.io.Serializable;
import java.util.Objects;

public class WorkingDaySanitarian implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5334833338493470293L;
	private WorkingDay workingDay;
	private Sanitarian sanitarian;
	
	public WorkingDaySanitarian() {
		
	}

	public WorkingDaySanitarian(WorkingDay workingDay, Sanitarian sanitarian) {
		super();
		this.workingDay = workingDay;
		this.sanitarian = sanitarian;
	}

	public WorkingDay getWorkingDay() {
		return workingDay;
	}

	public void setWorkingDay(WorkingDay workingDay) {
		this.workingDay = workingDay;
	}

	public Sanitarian getSanitarian() {
		return sanitarian;
	}

	public void setSanitarian(Sanitarian sanitarian) {
		this.sanitarian = sanitarian;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(sanitarian, workingDay);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkingDaySanitarian other = (WorkingDaySanitarian) obj;
		return Objects.equals(sanitarian, other.sanitarian) && Objects.equals(workingDay, other.workingDay);
	}

	@Override
	public String toString() {
		return "WorkingDaySanitarian [workingDay=" + workingDay + ", sanitarian=" + sanitarian + "]";
	}

}
