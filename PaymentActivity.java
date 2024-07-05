package com.mnandi.mnandiapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class PaymentActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPhone, etAddress, etCardName, etCardNumber, etExpiryDate, etSecurityCode;
    private TextView tvPaymentAmount;
    private Button btnPay;
    private ImageButton btnBackToBooking;
    private ImageView visa, master, paypal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);
        etCardName = findViewById(R.id.etCardName);
        etCardNumber = findViewById(R.id.etCardNumber);
        etExpiryDate = findViewById(R.id.etExpiryDate);
        etSecurityCode = findViewById(R.id.etSecurityCode);
        tvPaymentAmount = findViewById(R.id.tvPaymentAmount);
        btnPay = findViewById(R.id.btnPay);
        btnBackToBooking = findViewById(R.id.btnBackToBooking);
        visa = findViewById(R.id.visa);
        master = findViewById(R.id.master);
        paypal = findViewById(R.id.paypal);

        btnBackToBooking.setOnClickListener(view -> {
            Intent intent = new Intent(PaymentActivity.this, BookingActivity.class);
            startActivity(intent);
        });

        Intent intent = getIntent();
        String startDate = intent.getStringExtra("startDate");
        String endDate = intent.getStringExtra("endDate");

        long daysBetween = calculateDaysBetween(startDate, endDate);
        long amount = daysBetween * 750;

        tvPaymentAmount.setText("Payment amount: R" + amount);
        btnPay.setText("Pay R" + amount);
    }

    private long calculateDaysBetween(String startDate, String endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        try {
            Date start = sdf.parse(startDate);
            Date end = sdf.parse(endDate);
            long diffInMillis = end.getTime() - start.getTime();
            return TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
