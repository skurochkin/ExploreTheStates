package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by slavkurochkin on 10/20/16.
 */
public class StatesModel {
    private final StringProperty abbr;
    private final StringProperty fullStateName;
    private final StringProperty capitalCity;
    private final StringProperty largestCity;

    public StatesModel(String abbr, String fullStateName, String capitalCity, String largestCity) {
        this.abbr = new SimpleStringProperty(abbr);
        this.fullStateName = new SimpleStringProperty(fullStateName);
        this.capitalCity = new SimpleStringProperty(capitalCity);
        this.largestCity = new SimpleStringProperty(largestCity);
    }

    public String getFullStateName() {
        return fullStateName.get();
    }

    public void setFullStateName(String fullStateName) {
        this.fullStateName.set(fullStateName);
    }

    public StringProperty fullStateNameProperty() {
        return fullStateName;
    }

    public String getAbbr() {
        return abbr.get();
    }

    public void setAbbr(String abbr) {
        this.abbr.set(abbr);
    }

    public StringProperty abbrProperty() {
        return abbr;
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

