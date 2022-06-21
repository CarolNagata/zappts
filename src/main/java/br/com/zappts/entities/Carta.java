package br.com.zappts.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.lang.NonNull;

import br.com.zappts.entities.enums.Edicao;
import br.com.zappts.entities.enums.Status;
import br.com.zappts.entities.enums.Tipo;

@Entity
@Table(name = "tbl_cartas")
public class Carta {

	@Id
	@SequenceGenerator(name = "cartas", sequenceName = "sq_tbl_carta", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cartas")
	@Column(name = "id_carta")
	private Integer idCarta;

	@Column(name = "nome_colecao")
	@NotEmpty
	@NonNull
	private String nomeColecao;

	@Column(name = "nome_carta")
	@NotEmpty
	@NonNull
	private String nomeCarta;

	@Column(name = "edicao")
	@Enumerated(EnumType.STRING)
	private Edicao edicao;

	@Column(name = "idioma")
	@NotEmpty
	@NonNull
	private String idioma;

	@Column(name = "tipo")
	@Enumerated(EnumType.STRING)
	private Tipo tipo;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private Status status;

	@Column(name = "valor")
	@NotEmpty
	@NonNull
	private String valor;

	@Column(name = "quantidade")
	@NotEmpty
	@NonNull
	private Integer quantidade;

	@Column(name = "ponto")
	private Integer ponto;

	@ManyToOne
	@JoinColumn(name = "id_jogador")
	private Jogador jogador;

	public Carta() {

	}

	

	public Carta(Integer idCarta, @NotEmpty String nomeColecao, @NotEmpty String nomeCarta, Edicao edicao,
			@NotEmpty String idioma, Tipo tipo, Status status, @NotEmpty String valor, @NotEmpty Integer quantidade,
			Integer ponto, Jogador jogador) {
		super();
		this.idCarta = idCarta;
		this.nomeColecao = nomeColecao;
		this.nomeCarta = nomeCarta;
		this.edicao = edicao;
		this.idioma = idioma;
		this.tipo = tipo;
		this.status = status;
		this.valor = valor;
		this.quantidade = quantidade;
		this.ponto = ponto;
		this.jogador = jogador;
	}



	public Integer getIdCarta() {
		return idCarta;
	}

	public void setIdCarta(Integer idCarta) {
		this.idCarta = idCarta;
	}

	public String getNomeColecao() {
		return nomeColecao;
	}

	public void setNomeColecao(String nomeColecao) {
		this.nomeColecao = nomeColecao;
	}

	public String getNomeCarta() {
		return nomeCarta;
	}

	public void setNomeCarta(String nomeCarta) {
		this.nomeCarta = nomeCarta;
	}

	public Edicao getEdicao() {
		return edicao;
	}

	public void setEdicao(Edicao edicao) {
		this.edicao = edicao;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getPonto() {
		return ponto;
	}

	public void setPonto(Integer ponto) {
		this.ponto = ponto;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCarta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carta other = (Carta) obj;
		return Objects.equals(idCarta, other.idCarta);
	}

}
