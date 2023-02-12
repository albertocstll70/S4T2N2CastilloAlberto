package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s04.t02.n02.S04T02N02CastilloCorporanAlberto.model.services;

import java.util.List;

import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s04.t02.n02.S04T02N02CastilloCorporanAlberto.model.domain.Fruta;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s04.t02.n02.S04T02N02CastilloCorporanAlberto.model.exception.NoEncontradoException;

public interface FrutaService {
	Fruta add(Fruta f);
	List<Fruta> getAll();
	Fruta getOne(Integer id) throws NoEncontradoException;
	void delete(Integer id);
	Fruta update(Fruta f);

}
