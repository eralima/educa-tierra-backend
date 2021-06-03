package com.g4.Ecommerce.model;

//import java.util.List;

//import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;




@Entity
@Table(name = "tb_produto")
public class Produto {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	private long id;
	
	@NotNull
	private String nome;
	
	@NotNull
	private String descricao;
	
	@NotNull
	private String link;
	
	@NotNull
	private boolean statusTermo;
	
	
	/*@OneToMany(mappedBy = "descricao", cascade = CascadeType.ALL )  
	@JsonIgnoreProperties("descricao")
	private List<Categoria> categoria;*/
	
	
	

	
	
	

}
