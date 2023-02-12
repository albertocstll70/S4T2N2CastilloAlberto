package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s04.t02.n02.S04T02N02CastilloCorporanAlberto.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s04.t02.n02.S04T02N02CastilloCorporanAlberto.model.domain.Fruta;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s04.t02.n02.S04T02N02CastilloCorporanAlberto.model.exception.NoEncontradoException;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s04.t02.n02.S04T02N02CastilloCorporanAlberto.model.repository.FrutaRepository;

@Service("FrutaServiceDB")
public class FrutaServiceImpl implements FrutaService {
	
    @Autowired
	private FrutaRepository repositorio;
	
	@Override
	public Fruta add(Fruta f) {
		
		return repositorio.save(f);
	}

	@Override
	public List<Fruta> getAll() {
		
		return  (List<Fruta>) repositorio.findAll();
	}

	

	@Override
	public Fruta update(Fruta f) {
	
		return repositorio.save(f);
	}

	@Override
	public void delete(Integer id) {
		 repositorio.deleteById(id);
	}

	

	@Override
	public Fruta getOne(Integer id) throws  NoEncontradoException {
		
		if(repositorio.findById(id).get()==null) {
			
			throw new NoEncontradoException();
		}
		
		return repositorio.findById(id).get();
	}

	

}