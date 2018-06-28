
import java.util.ArrayList;
import java.util.List;

public class OptimizacionCortes {
    public static void firstFit(List<Double> longitudStock, List<Double> longitudReq, List<List<Double>> cortesXBarra) {
 
        // Creamos una copia del arreglo de las barras en Stock
        List<Double> longitudStockAux = new ArrayList<>();
        
        for (int i = 0; i < longitudStock.size(); i++) {
            longitudStockAux.add(longitudStock.get(i));
        }
 
        // Recorremos cada barra de requisitos del Cliente
        for (int i = 0; i < longitudReq.size(); i++) {
 
            // Recorremos cada barra auxiliar de Stock
            for (int j = 0; j < longitudStock.size(); j++) {
 
                // Verificamos que la barra a cortar sea mayor al de requisito del cliente
                if (longitudStockAux.get(j) - longitudReq.get(i) >= 0) {
                    longitudStockAux.set(j , longitudStockAux.get(j)-longitudReq.get(i));
                    System.out.println(longitudStockAux.get(j)+ " - " + longitudReq.get(i));
                    cortesXBarra.get(j).add(longitudReq.get(i));
                    break;
                }
            }
 
        }
 
    }
}
