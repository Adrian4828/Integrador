package com.example.web.Controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.domain.Detalle_pedido;
import com.example.domain.Pedido_proveedor;
import com.example.domain.Producto;
import com.example.web.Dao.PedidoDAO;
import com.example.web.Services.DetallePedidoService;
import com.example.web.Services.PedidoService;
import com.example.web.Services.ProductoService;
import com.example.web.Services.ProveedoresService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@SessionAttributes({ "nombre", "id", "rol" })
public class PedidosController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ProductoService productosService;

    @Autowired
    private DetallePedidoService detallePedidoService;

    @Autowired
    private ProveedoresService proveedoresService;

    @Autowired
    private PedidoDAO pedidoDAO;

    List<Detalle_pedido> detalles = new ArrayList<Detalle_pedido>();

    List<Detalle_pedido> lista_temporal = new ArrayList<Detalle_pedido>();


    Pedido_proveedor venta = new Pedido_proveedor();

    int id_pedido;
    String prov;

    @GetMapping("/pedidos")
    public String pedido(Model model) {

        detalles.clear();
        id_pedido = 0;

        model.addAttribute("pedidos", pedidoService.listarPedido());
        model.addAttribute("proveedores", pedidoService.listarProveedores());
        model.addAttribute("usuarios", pedidoService.listarUsuarios());


        return "pedidos";
    }

    @GetMapping("/agregarPedido")
    public String agregar(Model model) {
        model.addAttribute("productos", pedidoService.listarProductos());
        model.addAttribute("proveedores", pedidoService.listarProveedores());
        return "agregarPedido";
    }

    @PostMapping("/añadirPedido")
    public String agregarPedido(
            @RequestParam("proveedorInput") String proveedor,
            @RequestParam("productInput") String nombre_prod,
            @RequestParam("canti") int cantidad,
            Model model) {

        prov = proveedor;
        Detalle_pedido detalle_pedido = new Detalle_pedido();
        Producto producto = new Producto();
        int contador = 0;
        Producto optional = productosService.obtenerProductoPorNombre(nombre_prod);

        producto = optional;

        detalle_pedido.setIdProducto(producto.getIdProducto());
        detalle_pedido.setCantidad(cantidad);

        for (Detalle_pedido detalle_p : detalles) {
            if (detalle_p.getIdProducto() == detalle_pedido.getIdProducto()) {
                detalle_p.setCantidad(detalle_pedido.getCantidad() + cantidad);
                contador = 1;
            }
        }

        if (contador == 0) {
            detalles.add(detalle_pedido);
        }

        model.addAttribute("productos", pedidoService.listarProductos());
        model.addAttribute("proveedores", pedidoService.listarProveedores());
        model.addAttribute("prov", prov);
        model.addAttribute("detalles", detalles);

        return "agregarPedido";
    }

    @GetMapping("/eliminar_pedido/{id}")
    public String eliminarProd(
            @PathVariable Integer id,
            Model model) {

        List<Detalle_pedido> detalle_ventas = new ArrayList<Detalle_pedido>();
        for (Detalle_pedido detalle_pedido : detalles) {
            if (detalle_pedido.getIdProducto() != id) {
                detalle_ventas.add(detalle_pedido);
            }
        }

        detalles = detalle_ventas;

        model.addAttribute("productos", pedidoService.listarProductos());
        model.addAttribute("proveedores", pedidoService.listarProveedores());
        model.addAttribute("prov", prov);
        model.addAttribute("detalles", detalles);

        return "agregarPedido";

    }

    @PostMapping("/registroPedido")
    public String registrarPedido(
            @RequestParam("inputProveedor") String proveedor,
            @RequestParam("inputDescuento") int descuento,
            @RequestParam("inputImpuesto") int impuesto,
            @RequestParam("total_reg") double total,
            Model model, HttpSession session, SessionStatus status) {

        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());

        int id = (int) session.getAttribute("id");
        Pedido_proveedor re = new Pedido_proveedor();
        re.setIdUsuario(id);
        re.setIdProveedor(proveedoresService.obtenerProveedorPorNombre(proveedor).getIdProveedor());
        re.setFecha(date);
        re.setTotal(total);
        re.setDescuento(descuento);
        re.setImpuesto(impuesto);

        Pedido_proveedor nueva = pedidoDAO.save(re);

        for (Detalle_pedido detalle_pedido : detalles) {
            detalle_pedido.setIdPedido(nueva.getIdPedido());
            detallePedidoService.agregarDetalle(detalle_pedido);
        }

        id_pedido = nueva.getIdPedido();

        model.addAttribute("detalles", detalles);
        model.addAttribute("productos", pedidoService.listarProductos());
        model.addAttribute("id", id);
        model.addAttribute("pedido", nueva);
        return "RegistroPedido";
    }

    @GetMapping("/editarPedido")
    public String editarPedido(Model model, HttpSession session, SessionStatus status) {


        model.addAttribute("detalles", detalles);
        model.addAttribute("productos", pedidoService.listarProductos());
        model.addAttribute("proveedores", pedidoService.listarProveedores());
        model.addAttribute("id", id_pedido);
        return "editarPedido";
    }

    @PostMapping("/añadirP")
    public String agregarP(
            @RequestParam("proveedorInput") String proveedor,
            @RequestParam("productInput") String nombre_prod,
            @RequestParam("canti") int cantidad,
            Model model) {

        prov = proveedor;
        Detalle_pedido detalle_pedido = new Detalle_pedido();
        Producto producto = new Producto();
        int contador = 0;
        Producto optional = productosService.obtenerProductoPorNombre(nombre_prod);

        producto = optional;

        detalle_pedido.setIdProducto(producto.getIdProducto());
        detalle_pedido.setCantidad(cantidad);

        for (Detalle_pedido detalle_p : detalles) {
            if (detalle_p.getIdProducto() == detalle_pedido.getIdProducto()) {
                detalle_p.setCantidad(detalle_p.getCantidad() + cantidad);
                contador = 1;
                producto.setStock(producto.getStock() + cantidad);
            }
        }

        if (contador == 0) {
            detalles.add(detalle_pedido);
        }

        productosService.guardarProducto(producto);


        model.addAttribute("productos", pedidoService.listarProductos());
        model.addAttribute("proveedores", pedidoService.listarProveedores());
        model.addAttribute("prov", prov);
        model.addAttribute("detalles", detalles);
        return "editarPedido";
    }

    @GetMapping("/eliminarP/{id}")
    public String eliminar(
            @PathVariable Integer id,
            HttpSession session, SessionStatus status,
            Model model) {

        List<Detalle_pedido> detalle_ventas = new ArrayList<Detalle_pedido>();
        for (Detalle_pedido detalle_pedido : detalles) {
            if (detalle_pedido.getIdProducto() != id) {
                detalle_ventas.add(detalle_pedido);
            } else{
                Optional<Producto> optional = productosService.obtenerProductoPorId(id);
                if (optional.isPresent()) {
                    Producto producto = optional.get();
                    producto.setStock(producto.getStock() - detalle_pedido.getCantidad());
                    productosService.guardarProducto(producto);
                }
            } 
        }

        detalles = detalle_ventas;

        model.addAttribute("productos", pedidoService.listarProductos());
        model.addAttribute("proveedores", pedidoService.listarProveedores());
        model.addAttribute("prov", prov);
        model.addAttribute("detalles", detalles);

        return "/editarPedido";

    }

    @PostMapping("/actualizarP")
    public String actualizar(
            @RequestParam("inputProveedor") String proveedor,
            @RequestParam("inputDescuento") int descuento,
            @RequestParam("inputImpuesto") int impuesto,
            @RequestParam("total_reg") double total,
            Model model, HttpSession session, SessionStatus status) {

        int idP = id_pedido;
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());

        int id = (int) session.getAttribute("id");
        Optional<Pedido_proveedor> pedido = pedidoDAO.findById(Long.valueOf(idP));
        Pedido_proveedor re = pedido.get();
        re.setIdProveedor(proveedoresService.obtenerProveedorPorNombre(proveedor).getIdProveedor());
        re.setFecha(date);
        re.setTotal(total);
        re.setDescuento(descuento);
        re.setImpuesto(impuesto);

        pedidoDAO.save(re);

        detallePedidoService.eliminarDetalle(idP);

        for (Detalle_pedido detalle_pedido : detalles) {
            detalle_pedido.setIdPedido(re.getIdPedido());
            Optional<Producto> optional = productosService.obtenerProductoPorId(detalle_pedido.getIdProducto());
            Producto producto = optional.get();
            producto.setStock(producto.getStock() - detalle_pedido.getCantidad());
            productosService.guardarProducto(producto);
            detallePedidoService.agregarDetalle(detalle_pedido);
        }

        id_pedido = re.getIdPedido();

        model.addAttribute("detalles", detalles);
        model.addAttribute("productos", pedidoService.listarProductos());
        model.addAttribute("id", id);
        model.addAttribute("pedido", re);
        return "RegistroPedido";
    }

    @PostMapping("/eliminarPedido")
    public String eliminarPedido(
            @RequestParam("id_eliminar") int id_pedido,
            Model model) {


        List<Detalle_pedido> eliminar_detalles = detallePedidoService.obtenerPedidoPorIdPedido(id_pedido);

        for (Detalle_pedido detalle_pedido : eliminar_detalles) {
            Optional<Producto> optional = productosService.obtenerProductoPorId(detalle_pedido.getIdProducto());
            if (optional.isPresent()) {
                Producto producto = optional.get();
                producto.setStock(producto.getStock() - detalle_pedido.getCantidad());
                productosService.guardarProducto(producto);
            }
        }

        pedidoService.eliminarPedido(id_pedido);

        model.addAttribute("pedidos", pedidoService.listarPedido());
        model.addAttribute("proveedores", pedidoService.listarProveedores());
        model.addAttribute("usuarios", pedidoService.listarUsuarios());


        return "pedidos";

    }

    @PostMapping("/verPedido")
    public String ver(
            @RequestParam("id_detalle") int id_p,
            Model model) {

        id_pedido = id_p;
        Optional<Pedido_proveedor> v = pedidoService.obtenerPedidoPorId(id_pedido);

        Pedido_proveedor venta = v.get();
        detalles = detallePedidoService.obtenerPedidoPorIdPedido(venta.getIdPedido()); 
        for (Detalle_pedido detalle_pedido : detalles) {
            Detalle_pedido copia = new Detalle_pedido();
            copia.setCantidad(detalle_pedido.getCantidad());
            copia.setIdDetalle(detalle_pedido.getIdDetalle());
            copia.setIdPedido(detalle_pedido.getIdPedido());
            copia.setIdProducto(detalle_pedido.getIdProducto());
            lista_temporal.add(copia);
        }

        model.addAttribute("detalles", detalles);
        model.addAttribute("productos", pedidoService.listarProductos());
        model.addAttribute("id", venta.getIdUsuario());
        model.addAttribute("pedido", venta);
        return "RegistroPedido";
    }


    @GetMapping("/cancelarEdit")
    public String cancelar(Model model) {

        for (Detalle_pedido detallePedidoActual : detalles) {
            Optional<Producto> optional = productosService.obtenerProductoPorId(detallePedidoActual.getIdProducto());
    
            if (optional.isPresent()) {
                Producto producto = optional.get();
                
                // Buscar si el producto está en lista_temporal
                Detalle_pedido detalleOriginal = lista_temporal.stream()
                        .filter(detalle -> detalle.getIdProducto() == detallePedidoActual.getIdProducto())
                        .findFirst()
                        .orElse(null);
    
                if (detalleOriginal != null) {
                    // Ajustar stock según la diferencia entre cantidades
                    int diferencia = detallePedidoActual.getCantidad() - detalleOriginal.getCantidad();
                    producto.setStock(producto.getStock() - diferencia);
                } else {
                    // Producto no está en lista_temporal, revertir completamente su stock
                    producto.setStock(producto.getStock() + detallePedidoActual.getCantidad());
                }
    
                // Guardar cambios en el stock del producto
                productosService.guardarProducto(producto);
            }
        }

        lista_temporal.clear();
        detalles.clear();
        id_pedido = 0;

        model.addAttribute("pedidos", pedidoService.listarPedido());
        model.addAttribute("proveedores", pedidoService.listarProveedores());
        model.addAttribute("usuarios", pedidoService.listarUsuarios());


        return "pedidos";
    }
}
