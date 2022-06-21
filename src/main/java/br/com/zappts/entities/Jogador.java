package br.com.zappts.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table (name = "tbl_jogadores")
public class Jogador {

	@Id
	@SequenceGenerator (name = "jogadores", sequenceName = "sq_tbl_jogador", allocationSize = 1)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "jogadores")
	@Column (name = "id_jogador")
	private Integer idJogador;
	
	@Column (name = "nome_jogador")
	@NotEmpty
	@NonNull
	private String nomeJogador;
	
	@Column (name = "email")
	@NotEmpty
	@NonNull
	private String email;
	
	@Column (name = "celular")
	@NotEmpty
	@NonNull
	private String celular;
	
	@OneToMany (mappedBy = "jogador")
	@JsonIgnore
	@Cascade (CascadeType.ALL)
	private List <Carta> cartas = new ArrayList<>();
	
	
	public Jogador() {
		
	}

	public Jogador(Integer idJogador, String nomeJogador, String email, String celular) {
		super();
		this.idJogador = idJogador;
		this.nomeJogador = nomeJogador;
		this.email = email;
		this.celular = celular;
	}

	public Integer getIdJogador() {
		return idJogador;
	}

	public void setIdJogador(Integer idJogador) {
		this.idJogador = idJogador;
	}

	public String getNomeJogador() {
		return nomeJogador;
	}

	public void setNomeJogador(String nomeJogador) {
		this.nomeJogador = nomeJogador;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public List<Carta> getCartas() {
		return cartas;
	}


	@Override
	public int hashCode() {
		return Objects.hash(idJogador);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jogador other = (Jogador) obj;
		return Objects.equals(idJogador, other.idJogador);
	}
}
