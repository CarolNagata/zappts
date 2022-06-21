package br.com.zappts.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zappts.entities.Carta;

public interface CartaRepository extends JpaRepository<Carta, Integer> {

	List<Carta> findByNomeCartaContainingIgnoreCase(String nomeCarta);

}