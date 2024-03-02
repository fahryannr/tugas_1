package com.example.tugas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvJumlahBayar;
    private EditText etTiket;
    private RadioGroup rgFilm, rgMember;
    private Button btnSetujui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvJumlahBayar = findViewById(R.id.tvJumlahbayar);
        etTiket = findViewById(R.id.etTiket);
        rgFilm = findViewById(R.id.rgFilm);
        rgMember = findViewById(R.id.rgMember);
        btnSetujui = findViewById(R.id.btnSetujui);

        btnSetujui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hitungJumlahBayar();
                tampilkanPopup();
            }
        });
    }

    private void hitungJumlahBayar() {
        // Mendapatkan pilihan dari RadioGroup Film
        int selectedFilmId = rgFilm.getCheckedRadioButtonId();
        RadioButton selectedFilmRadioButton = findViewById(selectedFilmId);

        // Mendapatkan pilihan dari RadioGroup Member
        int selectedMemberId = rgMember.getCheckedRadioButtonId();
        RadioButton selectedMemberRadioButton = findViewById(selectedMemberId);

        // Mendapatkan jumlah tiket dari EditText
        String jumlahTiketStr = etTiket.getText().toString();

        if (jumlahTiketStr.isEmpty()) {
            // Menampilkan pesan kesalahan jika input jumlah tiket kosong
            etTiket.setError("Masukkan jumlah tiket!");
            return;
        }

        int jumlahTiket = Integer.parseInt(jumlahTiketStr);

        // Menghitung jumlah bayar berdasarkan pilihan film dan member
        int hargaTiket = getHargaTiket(selectedFilmRadioButton.getText().toString());
        int diskonMember = getDiskonMember(selectedMemberRadioButton.getText().toString());

        // Perhitungan total bayar
        int totalBayar = (hargaTiket * jumlahTiket) - diskonMember;

        // Menampilkan hasil perhitungan ke TextView
        tvJumlahBayar.setText("Jumlah Bayar: " + totalBayar);
    }

    private void tampilkanPopup() {
        // Membuat layout untuk pop-up
        LinearLayout layoutPopup = new LinearLayout(this);
        layoutPopup.setOrientation(LinearLayout.VERTICAL);

        // Menetapkan nilai hasil total dan potongan diskon ke TextView di dalam pop-up
        TextView tvTotalPopup = new TextView(this);
        tvTotalPopup.setText("Total Bayar: " + tvJumlahBayar.getText().toString());

        int selectedMemberId = rgMember.getCheckedRadioButtonId();
        RadioButton selectedMemberRadioButton = findViewById(selectedMemberId);
        int diskonMember = getDiskonMember(selectedMemberRadioButton.getText().toString());
        TextView tvDiskonPopup = new TextView(this);
        tvDiskonPopup.setText("Diskon Member: " + diskonMember);

        // Menambahkan TextView ke layout popup
        layoutPopup.addView(tvTotalPopup);
        layoutPopup.addView(tvDiskonPopup);

        // Membuat pop-up window
        PopupWindow popupWindow = new PopupWindow(layoutPopup, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);

        // Menampilkan pop-up di tengah layar
        popupWindow.showAtLocation(tvJumlahBayar, 50, 50, 50);
    }

    private int getHargaTiket(String film) {
        // Implementasi logika mendapatkan harga tiket berdasarkan film
        // ...

        // Contoh implementasi sederhana:
        if (film.equals("Azazil: 25000")) {
            return 25000;
        } else if (film.equals("Cin Kuyusu: 20000")) {
            return 20000;
        } else if (film.equals("Tuyul: 15000")) {
            return 15000;
        }
        return 0;
    }

    private int getDiskonMember(String member) {
        int diskon = 0;

        // Jika member adalah Vvip, berikan diskon sebesar 10%
        if (member.equals("Vvip")) {
            diskon = 10; // Diskon 10%
        }
        // Jika member adalah Baru, berikan diskon sebesar 5%
        else if (member.equals("Baru")) {
            diskon = 15; // Diskon 5%
        }
        // Tidak ada diskon untuk jenis member None
        // Anda bisa menambahkan jenis member lainnya sesuai kebutuhan

        // Mendapatkan harga tiket berdasarkan film yang dipilih
        String film = getSelectedFilm();
        int hargaTiket = getHargaTiket(film);

        // Mengembalikan nilai diskon dalam bentuk nominal (misalnya, 5000 untuk diskon 5% dari 100000)
        return (diskon * hargaTiket) / 100;
    }

    private String getSelectedFilm() {
        // Mendapatkan pilihan film dari RadioGroup Film
        int selectedFilmId = rgFilm.getCheckedRadioButtonId();
        RadioButton selectedFilmRadioButton = findViewById(selectedFilmId);
        return selectedFilmRadioButton.getText().toString();
    }
}

