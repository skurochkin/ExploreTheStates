package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by slavkurochkin on 10/20/16.
 */
public class StatesModel {
    private final StringProperty state;
    private final StringProperty capitalCity;
    private final StringProperty largestCity;

    public StatesModel(String state, String capitalCity, String largestCity) {
        this.state = new SimpleStringProperty(state);
        this.capitalCity = new SimpleStringProperty(capitalCity);
        this.largestCity = new SimpleStringProperty(largestCity);
    }


    public String getState() {
        return state.get();
    }

    public void setState(String state) {
        this.state.set(state);
    }

    public StringProperty stateProperty() {
        return state;
    }


    public String getCapital() {
        return capitalCity.get();
    }

    public void setCapitalCity(String capitalCity) {
        this.capitalCity.set(capitalCity);
    }

    public StringProperty capitalCityProperty() {
        return capitalCity;
    }

    public String getLargestCity() {
        return largestCity.get();
    }

    public void setLargestCity(String largestCity) {
        this.largestCity.set(largestCity);
    }

    public StringProperty largestCityProperty() {
        return largestCity;
    }
}

