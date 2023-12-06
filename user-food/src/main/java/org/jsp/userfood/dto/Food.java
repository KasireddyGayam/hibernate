package org.jsp.userfood.dto;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Food
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String item_name;
	@Column(nullable = false)
	private String adderss;
	@Column(nullable = false)
	private double cost;
	@CreationTimestamp
	private LocalDateTime order_time;
	@UpdateTimestamp
	private LocalDateTime del_time;
	@ManyToOne
	@JoinColumn(name="u_id")
	private User u;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getAdderss() {
		return adderss;
	}
	public void setAdderss(String adderss) {
		this.adderss = adderss;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public LocalDateTime getOrder_time() {
		return order_time;
	}
	public void setOrder_time(LocalDateTime order_time) {
		this.order_time = order_time;
	}
	public LocalDateTime getDel_time() {
		return del_time;
	}
	public void setDel_time(LocalDateTime del_time) {
		this.del_time = del_time;
	}
	public User getU() {
		return u;
	}
	public void setU(User u) {
		this.u = u;
	}
	
	

}
