package sk.upjs.registracia_konferencia.entities;

import java.time.LocalDate;

public class Workshop {
	private Long id;
	private String name;
	private LocalDate start;
	private LocalDate end;
	private double priceFull;
	private double priceStudent;
	private double priceFullLate;
	private double priceStudentLate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getStart() {
		return start;
	}
	public void setStart(LocalDate start) {
		this.start = start;
	}
	public LocalDate getEnd() {
		return end;
	}
	public void setEnd(LocalDate end) {
		this.end = end;
	}
	public double getPriceFull() {
		return priceFull;
	}
	public void setPriceFull(double priceFull) {
		this.priceFull = priceFull;
	}
	public double getPriceStudent() {
		return priceStudent;
	}
	public void setPriceStudent(double priceStudent) {
		this.priceStudent = priceStudent;
	}
	public double getPriceFullLate() {
		return priceFullLate;
	}
	public void setPriceFullLate(double priceFullLate) {
		this.priceFullLate = priceFullLate;
	}
	public double getPriceStudentLate() {
		return priceStudentLate;
	}
	public void setPriceStudentLate(double priceStudentLate) {
		this.priceStudentLate = priceStudentLate;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
