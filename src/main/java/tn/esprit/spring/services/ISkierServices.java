package tn.esprit.spring.services;

import tn.esprit.spring.entities.Skier;

import java.util.List;

public interface ISkierServices {

	List<Skier> retrieveAllSkiers();

	Skier  addSkier(Skier  skier);


	void removeSkier (Long numSkier);

	Skier retrieveSkier (Long numSkier);




}
