
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class VentanaInicioController {

    @FXML
    private TabPane tabPane;

    @FXML
    private TableView<barraID> tablaInventario;
    @FXML
    private TableColumn<barraID, Integer> columnaNum;
    @FXML
    private TableColumn<barraID, Double> columnaLong;
    
    @FXML
    private TableView<Requerimiento> tablaRequerimiento;
    @FXML
    private TableColumn<Requerimiento, Double> colReqLongitud;
    @FXML
    private TableColumn<Requerimiento, Integer> colReqCantidad;
    @FXML
    private TableColumn<Requerimiento, Button> colReqBoton;
    
    
    @FXML
    private TextField txtIngreso;
    @FXML
    private TextField txtCantidad;

    @FXML
    private TextField txtLongitudReq;
    @FXML
    private TextField txtCantidadReq;
    
    @FXML
    void eliminarAction(ActionEvent event) {

        Util.barrasStock.clear();

        actualizarTablaInventario();

        tablaInventario.setPlaceholder(new Label("Stock vacio"));

    }

    @FXML
    void insertarReqAction(ActionEvent event) {

        System.out.println("Ventana Cortar Seleccionada");

        //nuevaVentana("VentanaInicio");
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();

        selectionModel.select(2);
        
        colReqCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colReqLongitud.setCellValueFactory(new PropertyValueFactory<>("longitud"));
        colReqBoton.setCellValueFactory(new PropertyValueFactory<>("boton"));
        
        colReqCantidad.setCellFactory(TextFieldTableCell.<Requerimiento,Integer>forTableColumn(new IntegerStringConverter()));
        colReqLongitud.setCellFactory(TextFieldTableCell.<Requerimiento,Double>forTableColumn(new DoubleStringConverter()));
        
        tablaRequerimiento.setItems(Util.observableListReq);
        // Cerrar Ventana actual
        //((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void insertarStockAction(ActionEvent event) {

        System.out.println("Ventana Inventario Seleccionada");

        //nuevaVentana("VentanaInicio");
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();

        selectionModel.select(1);

        tablaInventario.setPlaceholder(new Label("Stock vacio"));

        // Cerrar Ventana actual
        //((Node) (event.getSource())).getScene().getWindow().hide();
        actualizarTablaInventario();
    }

    @FXML
    void mostrarDescAction(ActionEvent event) {

        System.out.println("Ventana Descripcion Seleccionada");

        //nuevaVentana("VentanaInicio");
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();

        selectionModel.select(0);

        // Cerrar Ventana actual
        //((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void agregarInventarioAction(ActionEvent event) {

        // Agregamos una nueva barra (longitud, cantidad)
        barraID barraNueva = new barraID(Double.parseDouble(txtIngreso.getText()),
                Integer.parseInt(txtCantidad.getText()));
        Util.barrasStock.add(barraNueva);

        txtIngreso.setText("");
        txtCantidad.setText("");
        
        actualizarTablaInventario();
    }

    @FXML
    public void agregarRequerimientoAction(ActionEvent event) {
        // Agregamos una nueva barra (longitud, cantidad)
        barraID nuevoRequerimiento = new barraID(Double.parseDouble(txtLongitudReq.getText()),
                Integer.parseInt(txtCantidadReq.getText()));
        
        Util.barrasRequerimiento.add(nuevoRequerimiento);
        Util.observableListReq.add(new Requerimiento(
                nuevoRequerimiento.getLongitud(),nuevoRequerimiento.getCantidad(),
                obtenerBotonEliminarRequerimiento()));
        
        txtIngreso.setText("");
        txtCantidad.setText("");
        
//        actualizarTablaRequerimiento();
    }
    
    @FXML
    public void iniciarCorteAction(ActionEvent event) {

        nuevaVentana("Resultados");
    }
    
    private void actualizarTablaInventario() {
        ObservableList<barraID> listaBarrasID = FXCollections.observableArrayList();
        int i = 0;

        for (barraID barra : Util.barrasStock) {
            System.out.println("Barra Insertada, longitud: " + barra.getLongitud() + " cantidad: " + barra.getCantidad());
            listaBarrasID.add(barra);
            i++;
        }

        columnaNum.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        columnaLong.setCellValueFactory(new PropertyValueFactory<>("longitud"));

        tablaInventario.setItems(listaBarrasID);
    }

//    private void actualizarTablaRequerimiento() {
//        ObservableList<Requerimiento> listaRequerimientos = FXCollections.observableArrayList();
//        
//        for (barraID barra : Util.barrasRequerimiento) {
//            System.out.println("Barra Requerida, longitud: " + barra.getLongitud() + " cantidad: " + barra.getCantidad());
//            listaRequerimientos.add(new Requerimiento(barra.getLongitud(), barra.getCantidad(),obtenerBotonEliminarRequerimiento()));
//        }
//
//        colReqCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
//        colReqLongitud.setCellValueFactory(new PropertyValueFactory<>("longitud"));
//        colReqBoton.setCellValueFactory(new PropertyValueFactory<>("boton"));
//        
//        tablaRequerimiento.setItems(listaRequerimientos);
//    }
    
    
//    private Button obtenerBotonEliminarRequerimiento(){
//        
//    }
    
private Button obtenerBotonEliminarRequerimiento(){
        Button boton = new Button("-");
//        ImageView image = new ImageView("\\images\\menos.png");
//        if(image!=null){
//            boton.setGraphic(new ImageView("\\images\\menos.png"));
//        }
        
//        boton.setOnMouseReleased((evt)->Util.observableListReq.removeIf((t)->t.getBoton() == (Button) evt.getButton()));

//        
//        boton.setOnMouseReleased((e)->{
//                    //Cambio el boton del elemento anterior en la tabla
//                    Button botonDelAnterior = Util.observableListReq.get(Util.observableListReq.size()-1).getBoton();
//                    botonDelAnterior.setText("-");
//                    //Cambio el evento del elemento anterior
//                    botonDelAnterior.setOnMouseReleased((h)->{
//                        Util.observableListReq.removeIf((t)-> t.getBoton() == botonDelAnterior);
//                        Util.observableListReq.get(Util.observableListReq.size()-1).setBoton(obtenerBotonAgregarRequerimiento());
//
//                        tablaRequerimiento.setItems(Util.observableListReq);
//                    });
//                    
//                    //Agrega una nueva fila a la tabla
//                    Util.observableListReq.add(obtenerRequerimientoVacio());
//                    tablaRequerimiento.setItems(Util.observableListReq);});
        
        return boton;
    }
    
    
    private void nuevaVentana(String nombreVentana) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource(nombreVentana + ".fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.show();

            stage.setScene(scene);

        } catch (Exception e) {
            System.out.println("excepcion: Nombre de archivo incorrecto: " + nombreVentana);

        }
    }

}
