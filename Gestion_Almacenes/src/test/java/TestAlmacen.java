import com.empresa.gestion_almacen.models.Almacen;
import com.empresa.gestion_almacen.repositories.AlmacenRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestAlmacen {

    @Autowired
    private AlmacenRepository almacenRepository;

    @Test
    public void testGuardarYConsultarAlmacen() {
        // Crear un nuevo almacén
        Almacen almacen = new Almacen();
        almacen.setNombre("Almacén Central");
        almacenRepository.save(almacen);

        // Recuperar los almacenes
        List<Almacen> almacenes = almacenRepository.findAll();
        assert (!almacenes.isEmpty());
        System.out.println("Datos guardados: " + almacenes);
    }
}