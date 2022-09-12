package br.com.insurance.challenge.insurance.repository;

import br.com.insurance.challenge.insurance.entity.Apolice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApoliceRepository extends CrudRepository<Apolice, Long> {
}
