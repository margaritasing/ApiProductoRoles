package com.example.productoapicrud.productos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping("{productoId}")
    @PreAuthorize("hasAnyRole('ROLE_SELLER', 'ROLE_CLIENTE')")
    public Producto getProductoPorId(@PathVariable Integer productoId){
        return productoService.getProductoPorId(productoId);
    }

    @GetMapping("buscar/{nombre}")
    @PreAuthorize("hasAnyAuthority('producto:write', 'producto:read')")
    public List<Producto> getProductoPorNombre(@PathVariable String nombre){
        return productoService.getProductoPorNombre(nombre);
    }

    @PostMapping("guardar")
    @PreAuthorize("hasAuthority('producto:write')")
    public void crearProducto(@RequestBody Producto producto){
        productoService.save(producto);
    }

    @PutMapping("guardar")
    @PreAuthorize("hasAuthority('producto:write')")
    public void guardarProducto(@RequestBody Producto producto){
        productoService.save(producto);
    }

    @DeleteMapping("{productoId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteProducto(@PathVariable Integer productoId){
        productoService.deleteById(productoId);
    }

    @GetMapping("/mi-rol")
    public String getMiRol(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return securityContext.getAuthentication().getAuthorities().toString();
    }












}
