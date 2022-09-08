package tw.hibernatedemo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class Employee implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="emp_name")
	private String empName;
	
	@Column(name="emp_salary")
	private Integer salary;
	
	@Column(name="emp_vacation")
	private Integer vacation;

	public Employee() {}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [id=");
		builder.append(id);
		builder.append(", empName=");
		builder.append(empName);
		builder.append(", salary=");
		builder.append(salary);
		builder.append(", vacation=");
		builder.append(vacation);
		builder.append("]");
		return builder.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public Integer getVacation() {
		return vacation;
	}

	public void setVacation(Integer vacation) {
		this.vacation = vacation;
	}	
}
