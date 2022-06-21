package br.com.zappts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zappts.entities.Jogador;

public interface JogadorRepository extends JpaRepository<Jogador, Integer> {

}
