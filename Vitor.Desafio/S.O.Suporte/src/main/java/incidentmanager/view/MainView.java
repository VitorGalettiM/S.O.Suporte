package incidentmanager.view;

import incidentmanager.controller.ChamadoController;
import incidentmanager.model.Chamado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainView {
    private ChamadoController controller = new ChamadoController();
    private TableView<Chamado> table = new TableView<>();

    public void start(Stage stage) {
        stage.setTitle("Sistema de Gestão de Incidentes");

        // Campos de cadastro
        TextField tituloField = new TextField();
        tituloField.setPromptText("Título");

        TextArea descricaoArea = new TextArea();
        descricaoArea.setPromptText("Descrição");

        ComboBox<String> prioridadeBox = new ComboBox<>();
        prioridadeBox.getItems().addAll("Alta", "Média", "Baixa");
        prioridadeBox.setValue("Média");

        Button cadastrarBtn = new Button("Cadastrar Chamado");

        cadastrarBtn.setOnAction(e -> {
            controller.criarChamado(
                    tituloField.getText(),
                    descricaoArea.getText(),
                    prioridadeBox.getValue()
            );
            tituloField.clear();
            descricaoArea.clear();
            carregarChamados("Todos", "Todos");
        });

        // Filtros
        ComboBox<String> filtroPrioridade = new ComboBox<>();
        filtroPrioridade.getItems().addAll("Todos", "Alta", "Média", "Baixa");
        filtroPrioridade.setValue("Todos");

        ComboBox<String> filtroStatus = new ComboBox<>();
        filtroStatus.getItems().addAll("Todos", "Aberto", "Em andamento", "Resolvido", "Fechado");
        filtroStatus.setValue("Todos");

        Button filtrarBtn = new Button("Filtrar");
        filtrarBtn.setOnAction(e -> carregarChamados(filtroPrioridade.getValue(), filtroStatus.getValue()));

        HBox filtros = new HBox(10, filtroPrioridade, filtroStatus, filtrarBtn);
        filtros.setPadding(new Insets(10));

        // Tabela
        TableColumn<Chamado, String> tituloCol = new TableColumn<>("Título");
        tituloCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTitulo()));

        TableColumn<Chamado, String> prioridadeCol = new TableColumn<>("Prioridade");
        prioridadeCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPrioridade()));

        TableColumn<Chamado, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus()));

        table.getColumns().addAll(tituloCol, prioridadeCol, statusCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Botão atualizar status
        ComboBox<String> novoStatusBox = new ComboBox<>();
        novoStatusBox.getItems().addAll("Aberto", "Em andamento", "Resolvido", "Fechado");
        novoStatusBox.setValue("Em andamento");

        Button atualizarStatusBtn = new Button("Atualizar Status");
        atualizarStatusBtn.setOnAction(e -> {
            Chamado selecionado = table.getSelectionModel().getSelectedItem();
            if (selecionado != null) {
                controller.atualizarStatus(selecionado.getId(), novoStatusBox.getValue());
                carregarChamados(filtroPrioridade.getValue(), filtroStatus.getValue());
            }
        });

        VBox layout = new VBox(10,
                new Label("Cadastrar Novo Chamado"),
                tituloField,
                descricaoArea,
                prioridadeBox,
                cadastrarBtn,
                new Separator(),
                filtros,
                table,
                new HBox(10, novoStatusBox, atualizarStatusBtn)
        );
        layout.setPadding(new Insets(15));

        carregarChamados("Todos", "Todos");

        stage.setScene(new Scene(layout, 800, 600));
        stage.show();
    }

    private void carregarChamados(String prioridade, String status) {
        ObservableList<Chamado> chamados = FXCollections.observableArrayList(controller.listarChamados(prioridade, status));
        table.setItems(chamados);
    }
}
