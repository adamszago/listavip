package com.zago.lista.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.zago.lista.entities.Convidado;

public interface ConvidadoRepository extends CrudRepository<Convidado, Long>{

	List<Convidado> findByNome(String nome);
}
