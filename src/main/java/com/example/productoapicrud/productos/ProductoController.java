package com.example.productoapicrud.productos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
####- listar todos los productos        GET/api/productos
####- listar un producto por ID         GET/api/productos/{producto_id}
####- listar todos los productos que contengan un string en el nombre  GET /api/productos/{nombre}


###Además, los administradores pueden:
####- modificar un producto existente   POST /api/productos/guardar
####- crear nuevos productos            PUT /api/productos/guardar
####- eliminar un producto por ID       DELETE /api/productos/{producto_id}

* */

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    public final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping()
    public List<Producto> listarProductos(){
        return productoService.getProductos();
    }

    @GetMapping("/{id}")
    public Producto listarPorId(@PathVariable Integer id){
        return productoService.verPorId(id);
    }

    @GetMapping("{nombre}")
    public List<Producto> listarPorNombre( @PathVariable String nombre){
        return productoService.buscarNombreContenido(nombre);
    }

    @PutMapping("actualizar")
    public Producto actualizarProducto(@RequestBody Producto producto){
        productoService.modificar(producto);
        return  producto;
    }


    @PostMapping(path="salvar",consumes = "application/json")
    public Producto salvarProducto(@RequestBody Producto producto){
        productoService.salvar(producto);
        return producto;
    }

    @DeleteMapping("/{id}")
    public void borrar(@PathVariable Integer id){
        productoService.deleteForId(id);
    }












}
