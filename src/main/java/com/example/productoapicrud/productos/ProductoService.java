package com.example.productoapicrud.productos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    public final ProductoRepository productoRepository;


    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> getProductos(){
        return productoRepository.findAll();
    }


    public List<String> getNombreProductos() {
        List<Producto> productos = productoRepository.findAll();
        return productos.stream()
                .map(producto -> producto.getNombre())
                .toList();
    }

    public Producto getProductoPorId(Integer productoId) {
        return productoRepository.findById(productoId).orElse(null);
    }

    public List<Producto> getProductoPorNombre(String nombre) {
        return productoRepository.findProductosByNombreContaining(nombre);
    }

    public void deleteById(Integer productoId) {
        productoRepository.deleteById(productoId);
    }

    public void save(Producto producto) {
        if (producto.getProducto_id() != null) {
            Producto productoExistente = productoRepository.findById(producto.getProducto_id()).orElse(null);
            if (productoExistente != null) {
                if (producto.getNombre() != null) productoExistente.setNombre(producto.getNombre());
                if (producto.getPrecio() != null) productoExistente.setPrecio(producto.getPrecio());
                if (producto.getStock() != null) productoExistente.setStock(producto.getStock());
                productoRepository.save(productoExistente);
            } else producto.setProducto_id(null);
        }
        productoRepository.save(producto);
    }






}
