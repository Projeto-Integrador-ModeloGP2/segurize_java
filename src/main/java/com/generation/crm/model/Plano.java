package com.generation.crm.model;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity	
@Table(name = "tb_planos")
public class Plano {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O Atributo Nome é Obrigatório!")
	private String nome;
	
	@NotNull(message = "O Atributo Descrição é Obrigatório!")
	private String descricao;
	
	@NotBlank(message = "O Atributo Valor é Obrigatório!")
	@Size(min = 4, max = 10, message = "A Valor deve ter no mínimo 4 caracteres e no máximo 10")
	private String valor;
	
	@NotNull(message = "O Atributo Vigencia de Tempo é Obrigatório!")
	private String vigencia;
	
	@NotNull(message = "O Atributo da Franquia é Obrigatorio!")
	private String franquia;
	
	private boolean status;
	
	@ManyToOne
	@JsonIgnoreProperties("plano")
	private Usuario usuario;
	
	@ManyToOne
	@JsonIgnoreProperties("plano")
	private Seguradora seguradora;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getVigencia() {
		return vigencia;
	}

	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}

	public String getFranquia() {
		return franquia;
	}

	public void setFranquia(String franquia) {
		this.franquia = franquia;
	}

	public boolean isStatus() {
		return status;
	}
	
	public void ativarStatus() {
        this.status = true;
    }

    public void desativarStatus() {
        this.status = false;
    }

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Seguradora getSeguradora() {
		return seguradora;
	}

	public void setSeguradora(Seguradora seguradora) {
		this.seguradora = seguradora;
	}
	
}
