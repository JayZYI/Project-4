package mahasiswa;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.adminandroid.R;
import com.example.adminandroid.Novel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateNovelActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtTitle;
    private EditText edtChapters;
    private EditText edtNovelCover;
    private EditText edtReadTimes;
    private EditText edtTag;
    private EditText edtViews;
    private Button btnSave;

    private Novel novel;

    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_novel);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        edtTitle = findViewById(R.id.edt_title);
        edtChapters = findViewById(R.id.edt_chapters);
        edtNovelCover = findViewById(R.id.edt_cover);
        edtReadTimes = findViewById(R.id.edt_readtimes);
        edtTag = findViewById(R.id.edt_tag);
        edtViews = findViewById(R.id.edt_views);
        btnSave = findViewById(R.id.btn_save_novel);

        btnSave.setOnClickListener(this);

        novel = new Novel();
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btn_save_novel) {
            saveNovel();
        }

    }

    private void saveNovel() {
        try {
            String title = edtTitle.getText().toString().trim();
            String chapters = edtChapters.getText().toString().trim();
            String novelCover = edtNovelCover.getText().toString().trim();
            String readTimes = edtReadTimes.getText().toString().trim();
            String tag = edtTag.getText().toString().trim();
            String views = edtViews.getText().toString().trim();

//            boolean isEmptyFields = false;
//
//            if (TextUtils.isEmpty(title)) {
//                isEmptyFields = true;
//                edtTitle.setError("Field ini tidak boleh kosong");
//            }
//
//            if (TextUtils.isEmpty(chapters)) {
//                isEmptyFields = true;
//                edtChapters.setError("Field ini tidak boleh kosong");
//            }
//
//            if (TextUtils.isEmpty(novelCover)) {
//                isEmptyFields = true;
//                edtNovelCover.setError("Field ini tidak boleh kosong");
//            }
//
//            if (TextUtils.isEmpty(readTimes)) {
//                isEmptyFields = true;
//                edtReadTimes.setError("Field ini tidak boleh kosong");
//            }
//
//            if (TextUtils.isEmpty(tag)) {
//                isEmptyFields = true;
//                edtTag.setError("Field ini tidak boleh kosong");
//            }
//
//            if (TextUtils.isEmpty(views)) {
//                isEmptyFields = true;
//                edtViews.setError("Field ini tidak boleh kosong");
//            }
//
//            if (!isEmptyFields) {
                Toast.makeText(CreateNovelActivity.this, "Saving Data...", Toast.LENGTH_SHORT).show();

                DatabaseReference dbNovel = mDatabase.child("novel");

                String id = dbNovel.push().getKey();
                novel.setId(id);
                novel.setTitle(title);
                novel.setChapters(chapters);
                novel.setNovelCover(novelCover);
                novel.setReadTimes(readTimes);
                novel.setTag(tag);
                novel.setViews(views);

                // Insert data
                dbNovel.child(id).setValue(novel);

                finish();
//            }
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception stack trace for debugging
        }
    }
}
