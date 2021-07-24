package com.bolsadeideas.springboot.di.app;

import com.bolsadeideas.springboot.di.app.models.domain.ItemFactura;
import com.bolsadeideas.springboot.di.app.models.domain.Producto;
import com.bolsadeideas.springboot.di.app.models.service.IServicio;
import com.bolsadeideas.springboot.di.app.models.service.MiServicio;
import com.bolsadeideas.springboot.di.app.models.service.MiServicioComplejo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Arrays;
import java.util.List;

/**
 * Trabajando con un AppConfig ya no será
 * necesario usar @Component y/o @Primary
 *
 */
@Configuration
public class AppConfig {

    @Bean("miServicioSimple") // Parecido al Component
    public IServicio registrarMiServicio() {
        return new MiServicio();
    }

    @Bean("miServicioComplejo")
    @Primary // Acá si es obligatorio tener un Primary (Depende)
    public IServicio registrarMiServicioComplejo() {
        return new MiServicioComplejo();
    }

    @Bean("itemsFactura")
    public List<ItemFactura> registrarItems() {
        Producto producto1 = new Producto("Camara Sony", 100);
        Producto producto2 = new Producto("Bicicleta Bianchi aro 26", 200);

        ItemFactura linea1 = new ItemFactura(producto1, 2);
        ItemFactura linea2 = new ItemFactura(producto2, 4);

        return Arrays.asList(linea1, linea2);
    }

    // Tenemos dos retornos iguales por lo que se necesita trabajar con el @Qualifier
    // al usarlo en alguna parte. Se recomienda usar @Qualifier siempre
    @Bean("default")
    public List<ItemFactura> registrarItemsOficina() {
        Producto producto1 = new Producto("Monitor LG LCD 24'", 250);
        Producto producto2 = new Producto("Notebook Asus", 500);
        Producto producto3 = new Producto("Impresora HP Multifuncional", 80);
        Producto producto4 = new Producto("Escritorio Oficina", 300);

        ItemFactura linea1 = new ItemFactura(producto1, 2);
        ItemFactura linea2 = new ItemFactura(producto2, 4);
        ItemFactura linea3 = new ItemFactura(producto3, 1);
        ItemFactura linea4 = new ItemFactura(producto4, 5);

        return Arrays.asList(linea1, linea2, linea3, linea4);

    }

}
