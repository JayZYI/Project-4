package mahasiswa;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.adminandroid.R;
import com.example.adminandroid.Tag;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateTagActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtcat;
    private Button btncatSave;

    private Tag mahasiswa;

    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tag);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        edtcat = findViewById(R.id.edt_cat);
        btncatSave = findViewById(R.id.btncat_save);

        btncatSave.setOnClickListener(this);

        mahasiswa = new Tag();
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btncat_save) {
            saveMahasiswa();
        }

    }

    private void saveMahasiswa()
    {
        try {
            String nim = edtcat.getText().toString().trim();

            boolean isEmptyFields = false;

            if (TextUtils.isEmpty(nim)) {
                isEmptyFields = true;
                edtcat.setError("Field ini tidak boleh kosong");
            }

            if (! isEmptyFields) {

                Toast.makeText(CreateTagActivity.this, "Saving Data...", Toast.LENGTH_SHORT).show();

                DatabaseReference dbMahasiswa = mDatabase.child("Category");

                String id = dbMahasiswa.push().getKey();
                mahasiswa.setId(id);
                mahasiswa.setCatName(nim);

                //insert data
                dbMahasiswa.child(id).setValue(mahasiswa);

                finish();





            }
        } catch (Exception e) {
//            Toast.makeText(CreateTagActivity.this, e.printStackTrace(), Toast.LENGTH_SHORT).show();

            e.printStackTrace(); // Print the exception stack trace for debugging
        }
    }

    private void saveTag()
    {
        try {
            String nim = edtcat.getText().toString().trim();

            boolean isEmptyFields = false;

            if (TextUtils.isEmpty(nim)) {
                isEmptyFields = true;
                edtcat.setError("Field ini tidak boleh kosong");
            }

            if (! isEmptyFields) {

                Toast.makeText(CreateTagActivity.this, "Saving Data...", Toast.LENGTH_SHORT).show();

                DatabaseReference dbMahasiswa = mDatabase.child("Category");

                String id = dbMahasiswa.push().getKey();
                mahasiswa.setId(id);
                mahasiswa.setCatName(nim);

                //insert data
                dbMahasiswa.child(id).setValue(mahasiswa);

                finish();





            }
        } catch (Exception e) {
//            Toast.makeText(CreateTagActivity.this, e.printStackTrace(), Toast.LENGTH_SHORT).show();

            e.printStackTrace(); // Print the exception stack trace for debugging
        }
    }
}