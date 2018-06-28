/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Josecarlos
 */
public class barraID {
    
    private double longitud;
    private int cantidad;

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

    public barraID(double longitud, int cantidad) {
        this.longitud = longitud;
        this.cantidad = cantidad;
    }

    
}
