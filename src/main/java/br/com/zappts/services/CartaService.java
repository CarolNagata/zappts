package br.com.zappts.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.zappts.entities.Carta;
import br.com.zappts.entities.Jogador;
import br.com.zappts.jogador.service.exceptions.ResourceNotFoundException;
import br.com.zappts.repositories.CartaRepository;
import br.com.zappts.repositories.JogadorRepository;

@Service
public class CartaService {

	@Autowired
	private CartaRepository repository;

	@Autowired
	private JogadorRepository jogadorRepository;

	public List<Carta> findAll() {
		return repository.findAll();
	}

	public Carta findById(Integer id) {
		Optional<Carta> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id.toString()));
	}

	public Carta insert(Carta obj) {
		return repository.save(obj);
	}

	public List<Jogador> findByNomeCartaContaining(String nomeCarta) {
		List<Carta> cartas = repository.findByNomeCartaContainingIgnoreCase(nomeCarta);
		List<Jogador> jogadores = new ArrayList<>();
		for (Carta x : cartas) {
			@SuppressWarnings("deprecation")
			Jogador buscaJogador = jogadorRepository.getOne(x.getJogador().getIdJogador());
			jogadores.add(buscaJogador);
		}

		return jogadores;
	}

	public List<Jogador> nomeCartaContaining(String nomeCarta) {
		List<Carta> cartas = repository.findByNomeCartaContainingIgnoreCase(nomeCarta);
		List<Jogador> jogadores = new ArrayList<>();
		for (Carta x : cartas) {

			@SuppressWarnings("deprecation")
			Jogador buscaJogador = jogadorRepository.getOne(x.getJogador().getIdJogador());
			jogadores.add(buscaJogador);
		}

		List<Carta> cartoes = repository.findByNomeCartaContainingIgnoreCase(nomeCarta);
		for (Carta x : cartoes) {
			@SuppressWarnings("deprecation")
			Jogador buscaJogador = jogadorRepository.getOne(x.getJogador().getIdJogador());
			if (jogadores.contains(buscaJogador)) {
				jogadores.remove(buscaJogador);
				jogadores.add(0, buscaJogador);
			}
		}

		return jogadores;
	}

	public void delete(Integer id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id.toString());
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException(e.getMessage());
		}
	}

	public Carta update(Integer id, Carta obj) {
		try {
			@SuppressWarnings("deprecation")
			Carta entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id.toString());
		}
	}

	private void updateData(Carta entity, Carta obj) {

		entity.setNomeCarta(obj.getNomeCarta());
		entity.setEdicao(obj.getEdicao());
		entity.setIdioma(obj.getIdioma());
		entity.setTipo(obj.getTipo());
		entity.setValor(obj.getValor());
		entity.setQuantidade(obj.getQuantidade());
	}

}