package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static sample.JsonReader.readJsonFromUrl;

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

    public Controller() throws IOException, JSONException {

        JSONObject json = readJsonFromUrl("http://services.groupkt.com/state/get/USA/all");
        String js = json.getJSONObject("RestResponse").getString("result");
        JSONArray jArray = new JSONArray(js);
        JSONObject jsObj = null;
        String name = null;
        String stateAbbr = null;
        String largestCity = null ;
        String capital= null;

        for(int i=0; i < jArray.length(); i++)
        {
            try {
                jsObj = jArray.getJSONObject(i);
                name = jsObj.getString("name");
                stateAbbr = jsObj.getString("abbr");
                capital = jsObj.getString("capital");
                largestCity = jsObj.getString("largest_city");
                masterData.add(new StatesModel(stateAbbr,name,capital, largestCity));
            }catch (org.json.JSONException ex){
                masterData.add(new StatesModel(stateAbbr,name,capital, capital));
            }
        }
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

            if (statesModel.getAbbr().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true; // Filter matches state abbr.
            } else if (statesModel.getFullStateName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true; // Filter matches full state name.
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