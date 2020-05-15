package com.innowave.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@ToString(of={"id","name","price","itemOrders"})
@Entity
@NoArgsConstructor
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Double price;

	@OneToMany(mappedBy = "id.product")
	private List<ItemOrder> itemOrders = new ArrayList<>();

	//@JsonBackReference - The pair @JsonManagedReference/@JsonBackReference are being ignored for some unknown reason using @jsonIgnore instead
	@JsonBackReference
	@ManyToMany
	@JoinTable(name = "PRODUCT_CATEGORY",
		joinColumns = @JoinColumn(name = "product_id"),
		inverseJoinColumns = @JoinColumn(name = "category_id")
	)
	private List<Category> categories = new ArrayList<>();
	
	public Product(Integer id, String name, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public List<ClientOrder> getClientOrders(){
		List<ClientOrder> list = new ArrayList<>();

		for(ItemOrder x : itemOrders){
			list.add(x.getClientOrder());
		}
		return list;
	}

}
