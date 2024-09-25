package com.gestor.tienda.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestor.tienda.Dto.ProductoDto;
import com.gestor.tienda.Entity.Producto;
import com.gestor.tienda.Entity.TipoPrenda;
import com.gestor.tienda.Service.ProductoService;
import com.gestor.tienda.Service.TipoPrendaService;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin("*")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private TipoPrendaService tipoPrendaService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProductoDto productoDto) {
        if (productoDto.getNombre().isBlank() ||
            productoDto.getPrecio() == null ||
            productoDto.getTalle().isBlank() ||
            productoDto.getColor().isBlank() ||
            productoDto.getMarca().isBlank() ||
            productoDto.getTipoPrendaId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<TipoPrenda> tipoPrendaOpt = tipoPrendaService.getTipoPrendaById(productoDto.getTipoPrendaId());
        if (!tipoPrendaOpt.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        TipoPrenda tipoPrenda = tipoPrendaOpt.get();

        Producto productoNuevo = new Producto(
            productoDto.getNombre(),
            productoDto.getPrecio(),
            productoDto.getTalle(),
            productoDto.getColor(),
            productoDto.getMarca(),
            tipoPrenda
        );

        productoService.saveProducto(productoNuevo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable int id) {
        Optional<Producto> producto = productoService.getProductoById(id);
        return producto.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productos = productoService.getAllProductos();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable int id) {
        if (productoService.getProductoById(id).isPresent()) {
            productoService.deleteProducto(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
