
import javafx.scene.control.Button;

public class Requerimiento {
    private double longitud;
    private int cantidad;
    private Button boton;

    public Requerimiento(double longitud, int cantidad, Button boton) {
        this.longitud = longitud;
        this.cantidad = cantidad;
        this.boton = boton;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Button getBoton() {
        return boton;
    }

    public void setBoton(Button boton) {
        this.boton = boton;
    }

    @Override
    public String toString() {
        return "Requerimiento{" + "longitud=" + longitud + ", cantidad=" + cantidad + ", boton=" + boton + '}';
    }
    
    
}
