import com.empresa.gestion_almacen.App;
import com.empresa.gestion_almacen.models.Almacen;
import com.empresa.gestion_almacen.models.Producto;
import com.empresa.gestion_almacen.models.Registro;
import com.empresa.gestion_almacen.repositories.AlmacenRepository;
import com.empresa.gestion_almacen.repositories.ProductoRepository;
import com.empresa.gestion_almacen.repositories.RegistroRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = App.class)
public class GestionAlmacenesTest {

    @Autowired
    private AlmacenRepository almacenRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private RegistroRepository registroRepository;

    // Tests para Almacén
    @Test
    public void testGuardarYConsultarAlmacen() {
        Almacen almacen = new Almacen();
        almacen.setId("2D"); // ID de almacén
        almacen.setNombre("Almacén Central");
        almacen.setUbicacion("Calle Ficticia, 123");
        almacenRepository.save(almacen);

        List<Almacen> almacenes = almacenRepository.findAll();
        assertFalse(almacenes.isEmpty());
        assertTrue(almacenes.stream().anyMatch(a -> a.getNombre().equals("Almacén Central")));
        System.out.println("Datos de almacén guardados: " + almacenes);
    }

    // Tests para Producto
    @Test
    public void testGuardarYConsultarProducto() {
        Almacen almacen = almacenRepository.findByNombre("Almacén Central");

        Producto producto = new Producto();
        producto.setId("1C"); // ID de producto
        producto.setNombre("Producto Test");
        producto.setDescripcion("Descripción de prueba");
        producto.setStock(10);
        producto.setStockMinimo(2);
        producto.setAlmacenId(almacen.getId());  // Asociar el producto al almacén
        productoRepository.save(producto);

        List<Producto> productos = productoRepository.findAll();
        assertFalse(productos.isEmpty());
        assertTrue(productos.stream().anyMatch(p -> p.getNombre().equals("Producto Test")));
        System.out.println("Productos guardados: " + productos);
    }

    @Test
    public void testActualizarProducto() {
        Almacen almacen = almacenRepository.findByNombre("Almacén Central");

        Producto producto = productoRepository.findById("1C").orElseThrow();

        producto.setNombre("Producto Actualizado");
        producto.setDescripcion("Descripción actualizada");
        producto.setStock(30);
        productoRepository.save(producto);

        Producto actualizado = productoRepository.findById(producto.getId()).orElseThrow();
        assertEquals("Producto Actualizado", actualizado.getNombre());
        assertEquals(30, actualizado.getStock());
    }

    // Tests para Registro
    @Test
    public void testCrearYConsultarRegistro() {
        Almacen almacen = almacenRepository.findByNombre("Almacén Central");

        Producto producto = productoRepository.findById("1C").orElseThrow();

        Registro registro = new Registro();
        registro.setId("3C");
        registro.setIdProducto(producto.getId());
        registro.setTipoMovimiento("ENTRADA");
        registro.setCantidad(10);
        registro.setFechaMovimiento(LocalDateTime.now());
        registroRepository.save(registro);

        List<Registro> registros = registroRepository.findAll();
        assertFalse(registros.isEmpty());
        assertTrue(registros.stream().anyMatch(r -> r.getIdProducto().equals(producto.getId())));
        System.out.println("Registros guardados: " + registros);
    }

    @Test
    public void testEliminarRegistro() {
        Registro registro = new Registro();
        registro.setIdProducto("1L"); // Asume que un producto con ID 1 existe
        registro.setTipoMovimiento("SALIDA");
        registro.setCantidad(5);
        registro.setFechaMovimiento(LocalDateTime.now());
        registro = registroRepository.save(registro);

        registroRepository.delete(registro);

        assertFalse(registroRepository.findById(registro.getId()).isPresent());
    }
}
