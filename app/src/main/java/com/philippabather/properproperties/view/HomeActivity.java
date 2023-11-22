package com.philippabather.properproperties.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.philippabather.properproperties.R;

public class HomeActivity extends AppCompatActivity {

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
        cvAdvertiseProperties.setOnClickListener(v -> testCardViewClickListener());
        cvMortgageChecker.setOnClickListener(v -> goToMortgageAdviserActivity());
        cvSearchProperties.setOnClickListener(v -> testCardViewClickListener());
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
        if (item.getItemId() == R.id.mi_action_mortgage_checker) {
            goToMortgageAdviserActivity();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Naviga a la actividad de Hipoteca Adviser
     */
    private void goToMortgageAdviserActivity() {
        Intent intent = new Intent(this, MortgageCheckerActivity.class);
        startActivity(intent);
    }

    // TODO: test - remove
    private void testCardViewClickListener() {
        // TODO: remove test
        Toast.makeText(this, "Clicked!", Toast.LENGTH_LONG).show();
    }


}