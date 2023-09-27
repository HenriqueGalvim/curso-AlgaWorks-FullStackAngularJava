package com.example.algamoney.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table (name = "categoria")

public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// A linha acima, quer dizer que não vai ser o jpa ou o hibernate que dará o valor a variavel codigo
	private Long codigo;
	
	@NotNull
	@Size(min = 3, max = 20)
	private String nome;
	
	// Clique Control 3 para entrar no negocio de geração de coisas automatizadas do elipse, muito util
	// Get and Setters
	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		return Objects.equals(codigo, other.codigo);
	}
	
	
	
}
