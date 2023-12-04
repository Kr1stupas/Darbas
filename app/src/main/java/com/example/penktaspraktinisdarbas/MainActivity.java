package com.example.penktaspraktinisdarbas;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private EditText filterEditText;
    private ArrayAdapter<String> adapter;
    private DataLoader dataLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        filterEditText = findViewById(R.id.filterEditText);

        List<String> exchangeRates = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, exchangeRates);
        listView.setAdapter(adapter);


        dataLoader = new DataLoader(new DataLoader.OnDataLoadListener() {
            @Override
            public void onDataLoaded(List<String> data) {
                exchangeRates.clear();
                exchangeRates.addAll(data);
                adapter.notifyDataSetChanged();
            }

        });

        filterEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                dataLoader.filterData(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }
}