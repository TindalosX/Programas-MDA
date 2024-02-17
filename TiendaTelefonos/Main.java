import java.util.ArrayList;
import java.util.List;

// Interfaz Subject
interface Subject {
    void agregarObserver(Observer observer);

    void quitarObserver(Observer observer);

    void notificarObservers(String mensaje);
}
// Interfaz Observer
interface Observer {
    void actualizar(String mensaje);
}

// Clase Cliente que implementa Observer
class Cliente implements Observer {
    private String nombre;

    public Cliente(String nombre) {
        this.nombre = nombre;
    }

    //@Override
    public void actualizar(String mensaje) {
        System.out.println("Cliente " + nombre + ": " + mensaje);
    }
}

// Clase Tienda que implementa Subject
class Tienda implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private boolean productoDisponible = false;

    public void setProductoDisponible(boolean productoDisponible) {
        this.productoDisponible = productoDisponible;
        if (productoDisponible) {
            notificarObservers("¡El producto de tu interes ya está disponible! 0_0/");
        }
    }

    public void agregarObserver(Observer observer) {
        observers.add(observer);
    }

    public void quitarObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notificarObservers(String mensaje) {
        for (Observer observer : observers) {
            observer.actualizar(mensaje);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Tienda tienda = new Tienda();

        Cliente cliente1 = new Cliente("Cristina");
        Cliente cliente2 = new Cliente("Víctor");

        tienda.agregarObserver(cliente1);
        tienda.agregarObserver(cliente2);

        // El producto aún no está disponible
        tienda.setProductoDisponible(false);

        //se notificará a los clientes
        tienda.setProductoDisponible(true);

        // Cliente1 deja de estar interesado
        tienda.quitarObserver(cliente1);

        // notifica a Cliente2
        //~ tienda.setProductoDisponible(true);
    }
}
