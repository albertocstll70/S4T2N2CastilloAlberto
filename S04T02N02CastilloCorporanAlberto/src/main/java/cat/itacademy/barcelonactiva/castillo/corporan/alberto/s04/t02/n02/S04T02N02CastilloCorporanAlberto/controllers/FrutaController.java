package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s04.t02.n02.S04T02N02CastilloCorporanAlberto.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s04.t02.n02.S04T02N02CastilloCorporanAlberto.model.domain.Fruta;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s04.t02.n02.S04T02N02CastilloCorporanAlberto.model.exception.NoEncontradoException;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s04.t02.n02.S04T02N02CastilloCorporanAlberto.model.services.FrutaService;

@Controller
@RequestMapping("/")
public class FrutaController {
	@Autowired
	private FrutaService service;

	@PostMapping("/fruta/add")
	public ResponseEntity<?> addFruta(@RequestBody Fruta fruta) {
		Map<String, Object> respuesta = new HashMap<>();
		try {

			service.add(fruta);
			respuesta.put("Creado con exito", fruta);

		} catch (DataAccessException e) {
			e.getMessage();
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);

	}

	@GetMapping("/fruta/getAll")
	@ResponseBody
	public List<Fruta> getAll() {
		return service.getAll();

	}

	@DeleteMapping("/fruta/delete/{id}")

	public ResponseEntity<?> deleteFruta(@PathVariable Integer id) throws NoEncontradoException {
		Map<String, Object> respuesta = new HashMap<>();

		try {
			if (service.getOne(id) != null) {
				respuesta.put("Eliminado con exito", service.getOne(id));
			}
			service.delete(id);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);

		} catch (NoEncontradoException e) {
			respuesta.put("No encontrado", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@GetMapping("/fruta/getOne/{id}")
	@ResponseBody
	public ResponseEntity<?> getOne(@PathVariable Integer id) {
		Map<String, Object> respuesta = new HashMap<>();
		try {
			Fruta fruta = service.getOne(id);
			respuesta.put("Encontrado ", service.getOne(id));
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		} catch (NoEncontradoException e) {
			respuesta.put(e.getMessage(), null);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/fruta/update/{id}")
	public ResponseEntity<?> update(@RequestBody Fruta fruta, @PathVariable Integer id) throws NoEncontradoException {
		Fruta frutaActual = service.getOne(id);
		Fruta frutaUpdate = null;
		Map<String, Object> respuesta = new HashMap<>();

		if (frutaActual != null) {

			try {
				frutaActual.setNombre(fruta.getNombre());
				frutaActual.setCantidadQuilos(fruta.getCantidadQuilos());
				frutaUpdate = service.update(frutaActual);

			} catch (DataAccessException e) {
				respuesta.put("mensaje", "Error de inserción ");
				respuesta.put("Error", e.getMessage());
				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			respuesta.put("Mensaje", "Update realizado con exito");
			respuesta.put("Fruta", frutaUpdate);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);

		} else {
			respuesta.put("Error", "No existe");
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);

		}
	}

}
