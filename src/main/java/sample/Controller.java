package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.awt.*;

public class Controller {
    @FXML
    private javafx.scene.control.TextField filterField;
    @FXML
    private TableView<StatesModel> stateTable;

    @FXML
    private TableColumn<StatesModel, String> capitalCityColumn;
    @FXML
    private TableColumn<StatesModel, String> largestCityColumn;

    private ObservableList<StatesModel> masterData = FXCollections.observableArrayList();

    public Controller() {
        masterData.add(new StatesModel("AL","Montgomery", "Birmingham"));
        masterData.add(new StatesModel("AK","Juneau", "Anchorage"));
        masterData.add(new StatesModel("AZ","Phoenix", "Phoenix"));
        masterData.add(new StatesModel("AR","Little Rock", "Little Rock"));
        masterData.add(new StatesModel("CA","Sacramento", "Los Angeles"));
        masterData.add(new StatesModel("CO","Denver", "Denver"));
        masterData.add(new StatesModel("CT","Hartford", "Bridgeport"));
        masterData.add(new StatesModel("DE","Dover", "Wilmington"));
        masterData.add(new StatesModel("FL","Pensacola", "Jacksonville"));
    }@FXML
     private void initialize() {
    // 0. Initialize the columns.
    capitalCityColumn.setCellValueFactory(cellData -> cellData.getValue().capitalCityProperty());
    largestCityColumn.setCellValueFactory(cellData -> cellData.getValue().largestCityProperty());

    // 1. Wrap the ObservableList in a FilteredList (initially display all data).
    FilteredList<StatesModel> filteredData = new FilteredList<>(masterData, p -> true);

    // 2. Set the filter Predicate whenever the filter changes.
    filterField.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(statesModel -> {
            // If filter text is empty, display all persons
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            // Compare capital and largest of every state with filter text.
            String lowerCaseFilter = newValue.toLowerCase();

            if (statesModel.getState().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true; // Filter matches first name.
            } else if (statesModel.getLargestCity().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true; // Filter matches last name.
            }
            return false; // Does not match.
        });
    });

    // 3. Wrap the FilteredList in a SortedList.
    SortedList<StatesModel> sortedData = new SortedList<>(filteredData);

    // 4. Bind the SortedList comparator to the TableView comparator.
    // 	  Otherwise, sorting the TableView would have no effect.
    sortedData.comparatorProperty().bind(stateTable.comparatorProperty());

    // 5. Add sorted (and filtered) data to the table.
    stateTable.setItems(sortedData);
}
}