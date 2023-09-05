package cr.ac.una.unaplanilla;

import cr.ac.una.unaplanilla.model.Gasto;
import cr.ac.una.unaplanilla.model.Salonero;
import cr.ac.una.unaplanilla.util.FlowController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FlowController.getInstance().InitializeFlow(stage, null);
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/cr/ac/una/unaplanilla/resources/LogoUNArojo.png")));
        stage.setTitle("UNA PLANILLA");
        FlowController.getInstance().goViewInWindow("LogInView");

        double iva = 0.13; // Porcentaje del IVA
        List<Gasto> gastos = new ArrayList<>();
        gastos.add(new Gasto("Gasto 1", 50.0));
        gastos.add(new Gasto("Gasto 2", 120.0));
        gastos.add(new Gasto("Gasto 3", 80.0));
        gastos.add(new Gasto("Gasto 4", 150.0));

        gastos = gastos.stream()
                .map(g -> {
                    g.setMonto(g.getMonto() * (1 + iva));
                    return g;
                }).collect(Collectors.toList());

        gastos = gastos.stream().filter(x -> x.getMonto() <= 100).collect(Collectors.toList());
        double total = gastos.stream().mapToDouble(Gasto::getMonto).sum();
        
        
        List<Salonero> saloneros = new ArrayList<>();
        saloneros.add(new Salonero(25, false, 1000.0, 200.0));
        saloneros.add(new Salonero(30, true, 1200.0, 150.0));
        saloneros.add(new Salonero(22, false, 900.0, 180.0));
        saloneros.add(new Salonero(28, false, 1100.0, 220.0));
        saloneros.add(new Salonero(35, true, 1300.0, 250.0));
        
        int anyos = 25; 
        
        double minSal = saloneros.stream().filter(edad(anyos).and(temporal)).mapToDouble(Salonero::getPropina).min().getAsDouble();
        
        
        /*saloneros = saloneros.stream().filter(edad(anyos).and(temporal).and(minPro(minSal))).forEach(s-> {
            s.setSalarioBase(s.getSalarioBase()*1.1);
        });
        */
        
        
    }

    public static void main(String[] args) {
        launch();
    }

    Predicate<Salonero> edad(int edad){
        return x->x.getEdad()>edad;
    }
    
    Predicate<Salonero> temporal= (t) -> !t.getTemporal() ;
    
    Predicate<Salonero> minPro (double min){
        return x->x.getPropina() <= min;
    }
    
    
    
}
