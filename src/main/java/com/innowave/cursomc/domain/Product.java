package com.innowave.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString(of={"id","name","price"})
@Entity
@NoArgsConstructor
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Double price;

	@JsonIgnore
	@OneToMany(mappedBy = "id.product")
	private List<ItemOrder> itemOrders = new ArrayList<>();

	@JsonIgnore
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

	@JsonIgnore
	public List<ClientOrder> getClientOrders(){
		List<ClientOrder> list = new ArrayList<>();

		for(ItemOrder x : itemOrders){
			list.add(x.getClientOrder());
		}
		return list;
	}

}
