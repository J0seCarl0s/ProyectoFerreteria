/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Josecarlos
 */
public class ResultadosController implements Initializable {
    
    @FXML
    private Pane panelDibujo;
    @FXML
    private Group root;
    @FXML
    private TableView<BarraXCortes> tablaResultado;
    @FXML
    private TableColumn<BarraXCortes, Double> columnaBarra;
    @FXML
    private TableColumn<BarraXCortes, List<Double>> columnaCortes;
    @FXML
    private TableColumn<BarraXCortes, Double> columnaSobrante;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
//        List<Double> corte1 = new ArrayList<>();
//        corte1.add(20.0);
//        corte1.add(10.0);
//        corte1.add(15.0);
//        corte1.add(35.0);
//        corte1.add(3.0);
//        
//        List<Double> corte2 = new ArrayList<>();
//        corte2.add(5.0);
//        corte2.add(20.0);
//        corte2.add(45.0);
//        corte2.add(5.0);
//        corte2.add(5.0);
//        
//        List<Double> corte3 = new ArrayList<>();
//        List<Double> corte4 = new ArrayList<>();
//        List<Double> corte5 = new ArrayList<>();
//        
//        cortes.add(corte1);
//        cortes.add(corte2);
//        cortes.add(corte3);
//        cortes.add(corte4);
//        cortes.add(corte5);
        
        List<List<Double>> cortes = new ArrayList<>();
        List<Double> barras = new ArrayList<>();
        for (int i = 0; i < Util.barrasStock.size(); i++) {
            for(int j=0; j < Util.barrasStock.get(i).getCantidad();j++){
                cortes.add(new ArrayList<>());
                barras.add(Util.barrasStock.get(i).getLongitud());
            }
        }
        
        
//        for(int i=0;i<Util.barrasStock.size();i++){
//            for(int j=0;j<Util.barrasStock.get(i).getCantidad();j++){
//                barras.add(Util.barrasStock.get(i).getLongitud());
//            }
//        }
        
        List<Double> requerimientos = new ArrayList<>();
        for(int i=0;i<Util.barrasRequerimiento.size();i++){
            for(int j=0;j<Util.barrasRequerimiento.get(i).getCantidad();j++){
                System.out.println("agregando");
                requerimientos.add(Util.barrasRequerimiento.get(i).getLongitud());
            }
        }
        
        OptimizacionCortes.firstFit(barras, requerimientos, cortes);
        
        llenarCuadroResultado(barras, cortes);
        dibujarRectancgulos(barras,cortes);
    }    
    
    public void dibujarRectancgulos(List<Double> barras, List<List<Double>> cortes){
        
        double max = 0;
        double porcentajeMargenIzqDer = 0.1;
        double porcentajeMargenArrAba = 0.1;
        double anchoBarra = 15;
        double espacioEntreBarras = 55;
                
        //Calculando la barra de maximo tamaño
        max = barras.stream().max((d1,d2)->d1.compareTo(d2)).get();
        
        //espacio entre panel y dibujos la quinta parte de la anchura del panel por cada lado
        double escala = (panelDibujo.getPrefWidth()*(1-2*porcentajeMargenIzqDer))/max;
        
        //Ubicacion en el eje x
        double xInicial = panelDibujo.getPrefWidth()*porcentajeMargenIzqDer;
        //Ubicacion en el eje y
        double yInicial = panelDibujo.getPrefHeight()*porcentajeMargenArrAba;
        
        //Asociare cada tamaño de corte a un color
        Map<Double,Color> mapaColores = new HashMap<>();
        
        //Lista de colores para pintar las barras
        List<Color> listaColores = new ArrayList<>();
        listaColores.add(Color.rgb( 230,220,229));
        listaColores.add(Color.rgb( 235,217,217));
        listaColores.add(Color.rgb( 232,209,211));
        listaColores.add(Color.rgb( 212,225,234));
        listaColores.add(Color.rgb( 255,226 ,164));
        listaColores.add(Color.rgb( 215 ,185  ,161));
        int posColor = 0;
        
        for(int i=0;i<barras.size();i++){
            double x = xInicial;
            double y = yInicial;
            
            if(!cortes.get(i).isEmpty()){
                //Dibujando las partes cortadas
                
                for(int j=0;j<cortes.get(i).size();j++){
                    Rectangle rectangulo = new Rectangle(x,y,cortes.get(i).get(j)*escala,anchoBarra);

                    Color color = mapaColores.get(cortes.get(i).get(j));
                    if(color==null){
                        color = listaColores.get(posColor);
                        mapaColores.put(cortes.get(i).get(j),listaColores.get(posColor));
                        posColor = (posColor+1)%listaColores.size();
                    }

                    rectangulo.setFill(color);
                    //Bordes negros
                    rectangulo.setStroke(Color.BLACK);

                    rectangulo.setStyle(
                            "-fx-background-color: palegreen; " +
                            "-fx-background-insets: 10; " +
                            "-fx-background-radius: 10; " +
                            "-fx-effect: dropshadow(three-pass-box, black, 10, 0, 0, 0);"
                    );

                    panelDibujo.getChildren().add(rectangulo);

                    x += cortes.get(i).get(j)*escala;
                }

                //Dibujo el resto de la barra, la parte que no ha sido cortada
                Rectangle rectangulo = new Rectangle(x, y, barras.get(i)*escala-(x-xInicial),anchoBarra);
                rectangulo.setFill(Color.WHITE);
                rectangulo.setStroke(Color.BLACK);
                panelDibujo.getChildren().add(rectangulo);

                Line linea = new Line(xInicial, yInicial+25,xInicial+barras.get(i)*escala, yInicial+25);
                linea.setStroke(Color.RED);
                panelDibujo.getChildren().add(linea);

                Label labelLong = new Label(barras.get(i)+" m");
                labelLong.setVisible(true);
                labelLong.setLayoutX(xInicial+(barras.get(i)*escala)*0.5-20.0);
                labelLong.setLayoutY(yInicial+25);
                panelDibujo.getChildren().add(labelLong);

                yInicial += espacioEntreBarras;
            }
        }
    }
    
    public void llenarCuadroResultado(List<Double> longitudBarras, List<List<Double>> cortes){
        ObservableList<BarraXCortes> obsListBarrasXCorte = FXCollections.observableArrayList();
        
        System.out.println(longitudBarras.size());
        for(int i=0;i<longitudBarras.size();i++){
            if(!cortes.get(i).isEmpty()){
                double sobrante = longitudBarras.get(i);
                for(int j=0;j<cortes.get(i).size();j++){
                    sobrante -= cortes.get(i).get(j);
                }
                obsListBarrasXCorte.add(new BarraXCortes(
                        longitudBarras.get(i),cortes.get(i),sobrante));
            }
        }
        
        columnaBarra.setCellValueFactory(new PropertyValueFactory<>("longitud"));
        columnaCortes.setCellValueFactory(new PropertyValueFactory<>("cortes"));
        columnaSobrante.setCellValueFactory(new PropertyValueFactory<>("sobrante"));
        
        tablaResultado.setItems(obsListBarrasXCorte);
    }
}