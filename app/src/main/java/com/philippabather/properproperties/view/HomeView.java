package com.philippabather.properproperties.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.philippabather.properproperties.R;

/**
 * HomeView - la actividad que maneja la vista de la página principal de la aplicación.
 *
 * @author Philippa Bather
 */
public class HomeView extends AppCompatActivity {

    private CardView cvAdvertiseProperties;
    private CardView cvMortgageChecker;  // Comprobar hipoteca ('mortgage')
    private CardView cvSearchProperties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findViews();
        setOnClickListeners();
    }

    /**
     * Establece las vistas de las propiedades de HomeActivity.
     */
    private void findViews() {
        cvAdvertiseProperties = findViewById(R.id.cv_register_properties);
        cvMortgageChecker = findViewById(R.id.cv_mortgage_checker);
        cvSearchProperties = findViewById(R.id.cv_search_properties);
    }

    /**
     * Establece los 'click listeners'
     */
    private void setOnClickListeners() {
        cvAdvertiseProperties.setOnClickListener(v -> goToPropertyManagement());
        cvMortgageChecker.setOnClickListener(v -> goToMortgageAdviserActivity());
        cvSearchProperties.setOnClickListener(v -> goToSearchPropertiesActivity());
    }


    /**
     * Infla el menú de ítems en action_bar.xml
     * @param menu - el menú en el 'action bar'
     * @return boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Maneja la seleción y la acción correspondiente de un ítem
     * en el action bar
     * @param item ítem de menú
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent();

        if (item.getItemId() == R.id.mi_action_search_map) {
            intent = new Intent(this, PropertyListView.class);
        } else if (item.getItemId() == R.id.mi_action_mortgage_checker) {
            intent = new Intent(this, MortgageCheckerView.class);
        } else {
            return super.onOptionsItemSelected(item);
        }

        startActivity(intent);
        return true;
    }

    /**
     * Naviga a la actividad para buscar inmuebles.
     */
    private void goToSearchPropertiesActivity() {
        Intent intent = new Intent(this, PropertyListView.class);
        startActivity(intent);
    }

    /**
     * Naviga a la actividad de Hipoteca Adviser/Mortgage Checker
     */
    private void goToMortgageAdviserActivity() {
        Intent intent = new Intent(this, MortgageCheckerView.class);
        startActivity(intent);
    }

    private void goToPropertyManagement() {
        Intent intent = new Intent(this, OwnerPropertyView.class);
        startActivity(intent);
    }

}