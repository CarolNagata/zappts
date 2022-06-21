package br.com.zappts.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.zappts.entities.Jogador;
import br.com.zappts.jogador.service.exceptions.DatabaseException;
import br.com.zappts.jogador.service.exceptions.ResourceNotFoundException;
import br.com.zappts.repositories.JogadorRepository;

@Service
public class JogadorService {

	@Autowired
	private JogadorRepository repository;

	public List<Jogador> findAll() {
		return repository.findAll();
	}

	public Jogador findById(Integer id) {
		Optional<Jogador> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id.toString()));
	}

	public Jogador insert(Jogador obj) {
		return repository.save(obj);
	}

	public void delete(Integer id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id.toString());
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}

	}

	public Jogador update(Integer id, Jogador obj) {
		try {
			@SuppressWarnings("deprecation")
			Jogador entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id.toString());
		}
	}

	private void updateData(Jogador entity, Jogador obj) {

		entity.setNomeJogador(obj.getNomeJogador());
		entity.setEmail(obj.getEmail());
		entity.setCelular(obj.getCelular());

	}
}