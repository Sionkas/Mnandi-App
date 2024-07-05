package com.mnandi.mnandiapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BookingActivity extends AppCompatActivity {

    private EditText startDate;
    private EditText endDate;
    private Spinner spinnerVehicle;
    private Switch switchWiFi;
    private Switch switchSnacks;
    private Switch switchChildSeat;
    private Button btnProceedPayment;
    private ImageButton btnBackToService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        spinnerVehicle = findViewById(R.id.spinnerVehicle);
        startDate = findViewById(R.id.etStartDate);
        endDate = findViewById(R.id.etEndDate);
        switchWiFi = findViewById(R.id.switchWiFi);
        switchSnacks = findViewById(R.id.switchSnacks);
        switchChildSeat = findViewById(R.id.switchChildSeat);
        btnProceedPayment = findViewById(R.id.btnProceedPayment);
        btnBackToService = findViewById(R.id.btnBackToService);

        btnBackToService.setOnClickListener(view -> {
            Intent intent = new Intent(BookingActivity.this, ServiceActivity.class);
            startActivity(intent);
        });

        btnProceedPayment.setOnClickListener(view -> {
            Intent intent = new Intent(BookingActivity.this, PaymentActivity.class);
            String selectedVehicle = spinnerVehicle.getSelectedItem().toString();
            intent.putExtra("vehicle", selectedVehicle);
            intent.putExtra("startDate", startDate.getText().toString());
            intent.putExtra("endDate", endDate.getText().toString());
            startActivity(intent);
        });

        startDate.setOnClickListener(view -> showDatePickerDialog(startDate));
        endDate.setOnClickListener(view -> showDatePickerDialog(endDate));
    }

    private void showDatePickerDialog(EditText editText) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(BookingActivity.this,
                (view, year, monthOfYear, dayOfMonth) -> {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, monthOfYear);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                    editText.setText(sdf.format(calendar.getTime()));
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}
