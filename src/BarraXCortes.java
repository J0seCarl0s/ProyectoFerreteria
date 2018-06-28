/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;

/**
 *
 * @author Josecarlos
 */
public class BarraXCortes {
    private double longitud;
    private List<Double> cortes;
    private double sobrante;

    public BarraXCortes(double longitud, List<Double> cortes, double sobrante) {
        this.longitud = longitud;
        this.cortes = cortes;
        this.sobrante = sobrante;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public List<Double> getCortes() {
        return cortes;
    }

    public void setCortes(List<Double> cortes) {
        this.cortes = cortes;
    }

    public double getSobrante() {
        return sobrante;
    }

    public void setSobrante(double sobrante) {
        this.sobrante = sobrante;
    }

    
}
