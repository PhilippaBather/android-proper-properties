package com.philippabather.properproperties.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.philippabather.properproperties.api.PropertyApi;
import com.philippabather.properproperties.api.PropertyApiInterface;
import com.philippabather.properproperties.contract.PropertyListContract;
import com.philippabather.properproperties.domain.Property;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PropertyListModel implements PropertyListContract.Model {

    private final PropertyApiInterface api;

    public PropertyListModel() {
        this.api = PropertyApi.buildInstance();
    }

//    @Override
//    public void loadAllProperties(OnLoadPropertiesListener listener) {
//        Call<Set<Property>> callProperties = api.getProperties();
//        callProperties.enqueue(new Callback<List<Property>>() {
//            @Override
//            public void onResponse(@NonNull Call<Set<Property>> call, @NonNull Response<List<Property>> response) {
//                List<Property> properties = response.body();
//                for (Property property:
//                     properties) {
//                    Log.e("property", property.toString());
//                }
//                listener.onLoadPropertiesSuccess(properties);
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<Set<Property>> call, @NonNull Throwable t) {
//                Log.e("getProperties", Objects.requireNonNull(t.getMessage()));
//                listener.onLoadPropertiesError("Se ha producido un error al connectar con el servidor.");
//            }
//        });


    @Override
    public void loadAllProperties(OnLoadPropertiesListener listener) {
        Call<Set<Property>> callProperties = api.getProperties();

        callProperties.enqueue(new Callback<Set<Property>>() {
            @Override
            public void onResponse(@NonNull Call<Set<Property>> call, Response<Set<Property>> response) {
                Set<Property> properties = response.body();
                List<Property> propertyList = new ArrayList<>(properties);
                for (Property property:
                        propertyList) {
                    Log.e("property", property.toString());
                }
                listener.onLoadPropertiesSuccess(propertyList);
            }

            @Override
            public void onFailure(Call<Set<Property>> call, Throwable t) {
                Log.e("getProperties", Objects.requireNonNull(t.getMessage()));
                listener.onLoadPropertiesError("Se ha producido un error al connectar con el servidor.");
            }
        });

//        Call<List<Task>> callTasks = api.getTasks();
//        callTasks.enqueue(new Callback<List<Task>>() {
//            @Override
//            public void onResponse(@NonNull Call<List<Task>> call, @NonNull Response<List<Task>> response) {
//                List<Task> tasks = response.body();
//                for (Task task:
//                     tasks) {
//                    Log.e("getTasks", task.toString());
//                }
////                listener.onLoadTasksSuccess(tasks);
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<List<Task>> call, @NonNull Throwable t) {
//                Log.e("getTasks", Objects.requireNonNull(t.getMessage()));
//                listener.onLoadPropertiesError("Se ha producido un error al conectar con el servidor.");
//                t.printStackTrace();
//            }
//        });
    }
}
