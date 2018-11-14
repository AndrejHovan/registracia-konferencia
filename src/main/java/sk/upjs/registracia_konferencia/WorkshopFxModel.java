package sk.upjs.registracia_konferencia;

import java.time.LocalDate;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sk.upjs.registracia_konferencia.entities.Workshop;

public class WorkshopFxModel {

	private Long id;
	private StringProperty name = new SimpleStringProperty();
	private ObjectProperty<LocalDate> start = new SimpleObjectProperty<>();
	private ObjectProperty<LocalDate> end = new SimpleObjectProperty<>();
	private DoubleProperty priceFull = new SimpleDoubleProperty();
	private DoubleProperty priceStudent = new SimpleDoubleProperty();
	private DoubleProperty priceFullLate = new SimpleDoubleProperty();
	private DoubleProperty priceStudentLate = new SimpleDoubleProperty();
	
	public void setWorkshop(Workshop w) {
		setId(w.getId());
		setName(w.getName());
		setStart(w.getStart());
		setEnd(w.getEnd());
		setPriceFull(w.getPriceFull());
		setPriceStudent(w.getPriceStudent());
		setPriceFullLate(w.getPriceFullLate());
		setPriceStudentLate(w.getPriceStudentLate());
	}
	
	public Workshop getWorkshop() {
		Workshop w = new Workshop();
		w.setId(id);
		w.setName(getName());
		w.setStart(getStart());
		w.setEnd(getEnd());
		w.setPriceFull(getPriceFull());
		w.setPriceStudent(getPriceStudent());
		w.setPriceFullLate(getPriceFullLate());
		w.setPriceStudentLate(getPriceStudentLate());
		return w;
	}
	
	//ID
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	//nameProperty
	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.name.set(name);
	}
	public StringProperty nameProperty() {
		return name;
	}
	
	//start
	public LocalDate getStart() {
		return start.get();
	}
	public void setStart(LocalDate start) {
		this.start.set(start);
	}
	public ObjectProperty<LocalDate> startProperty(){
		return start;
	}
	
	//end
	public LocalDate getEnd() {
		return end.get();
	}
	public void setEnd(LocalDate end) {
		this.end.set(end);
	}
	public ObjectProperty<LocalDate> endProperty(){
		return end;
	}
	
	//price full
	public double getPriceFull() {
		return priceFull.get();
	}
	public void setPriceFull(Double priceFull) {
		this.priceFull.set(priceFull);
	}
	public DoubleProperty priceFullProperty() {
		return priceFull;
	}
	
	//price student
	public double getPriceStudent() {
		return priceStudent.get();
	}
	public void setPriceStudent(Double priceStudent) {
		this.priceStudent.set(priceStudent);
	}
	public DoubleProperty priceStudentProperty() {
		return priceStudent;
	}
	
	//price full late
	public double getPriceFullLate() {
		return priceFullLate.get();
	}
	public void setPriceFullLate(Double priceFullLate) {
		this.priceFullLate.set(priceFullLate);
	}
	public DoubleProperty priceFullLateProperty() {
		return priceFullLate;
	}
	
	//price student late
	public double getPriceStudentLate() {
		return priceStudentLate.get();
	}
	public void setPriceStudentLate(Double priceStudentLate) {
		this.priceStudentLate.set(priceStudentLate);
	}
	public DoubleProperty priceStudentLateProperty() {
		return priceStudentLate;
	}
	
}
