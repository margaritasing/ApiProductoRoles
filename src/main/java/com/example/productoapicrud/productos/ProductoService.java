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

   public Producto modificar (Producto producto){
        return productoRepository.save(producto);
   }

    public void salvar(Producto producto) {
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


    public Producto verPorId(Integer id) {
        Producto producto = productoRepository.findById(id).orElse(null);
        return producto;
    }

   public void deleteForId(Integer id){
        productoRepository.deleteById(id);
    }

    public List<Producto> buscarNombreContenido(String nombre){
        return productoRepository.findByNombreContaining(nombre);
    }






}
