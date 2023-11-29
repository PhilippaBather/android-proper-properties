package com.philippabather.properproperties.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.philippabather.properproperties.R;

public class MortgageCheckerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mortgage_checker);
    }

    /**
     * Infla el menú de ítems en action_bar.xml
     *
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
     *
     * @param item ítem de menú
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mi_action_mortgage_checker) {
            return true;
        } else if (item.getItemId() == R.id.mi_action_home) {
            goToHomeActivity();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void goToHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}