package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entities.Skier;

import java.util.List;

public interface ISkierRepository extends JpaRepository<Skier, Long> {



}
