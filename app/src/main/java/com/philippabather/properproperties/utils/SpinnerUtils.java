package com.philippabather.properproperties.utils;

import static com.philippabather.properproperties.constants.Constants.PROPERTY_TYPE_COMMERICAL_EN;
import static com.philippabather.properproperties.constants.Constants.PROPERTY_TYPE_COMMERICAL_ES;
import static com.philippabather.properproperties.constants.Constants.PROPERTY_TYPE_FLAT_EN;
import static com.philippabather.properproperties.constants.Constants.PROPERTY_TYPE_FLAT_ES;
import static com.philippabather.properproperties.constants.Constants.PROPERTY_TYPE_HOUSE_EN;
import static com.philippabather.properproperties.constants.Constants.PROPERTY_TYPE_HOUSE_ES;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.philippabather.properproperties.domain.PropertyType;

/**
 * SpinnerUtils - métodos auxiliares para establecer y manejar el 'drop down' menú.
 *
 * @author Philippa Bather
 */
public class SpinnerUtils {
    public static void setUpSpinner(ArrayAdapter<CharSequence> adapter, Spinner spPropertyType,
                                    AdapterView.OnItemSelectedListener listener) {
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPropertyType.setAdapter(adapter);
        spPropertyType.setOnItemSelectedListener(listener);
    }

    public static PropertyType setPropertyType(String type) {
        return switch(type.toUpperCase()) {
            case PROPERTY_TYPE_COMMERICAL_EN, PROPERTY_TYPE_COMMERICAL_ES -> PropertyType.COMMERCIAL;
            case PROPERTY_TYPE_FLAT_EN, PROPERTY_TYPE_FLAT_ES -> PropertyType.FLAT;
            case PROPERTY_TYPE_HOUSE_EN, PROPERTY_TYPE_HOUSE_ES -> PropertyType.HOUSE;
            default -> null;
        };
    }

    public static int setSelection(PropertyType type) {
        return switch(type) {
            case COMMERCIAL -> 2;
            case FLAT-> 1;
            case HOUSE -> 0;
        };
    }

}
