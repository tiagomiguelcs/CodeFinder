/* */
package com.teaching.codefinder;
import java.io.InputStream;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.net.Uri;

public class MainActivity extends AppCompatActivity {

    private static final int ACTIVITY_CHOOSE_FILE1 = 1;
    private Uri CSV;
    private List<Student> students;

    /** Praise the class constructor */
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); // Avoid dark mode
    }

    /** Handle the selection of a file */
    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TextView status     = (TextView) findViewById(R.id.tv_status);
        Button find         = (Button) findViewById(R.id.bt_find);
        Button select       = (Button) findViewById(R.id.bt_select);

        switch (requestCode) {
            case ACTIVITY_CHOOSE_FILE1: {
                if (resultCode == RESULT_OK) {
                    // status.setText(data.getDataString());
                    CSV = data.getData(); // read the URI of the CSV file
                    status.setText("CSV file ready.");
                    find.setEnabled(true);
                    select.setEnabled(false);
                    loadStudents();
                }
            }
        }
    }

    /** Select the CSV file */
    public void selectCSV(View view){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        startActivityForResult(Intent.createChooser(intent, "Open CSV File"), ACTIVITY_CHOOSE_FILE1);
    }

    /** Load the students into a List of students (memory hungry, but that's fine for a list of students that will not be huge) */
    public void loadStudents(){
        TextView error = (TextView)findViewById(R.id.tv_error);
        // Parse and read the selected CSV file.
        InputStream inputStream;
        try{
            /** For tests only (using the local CSV one instead of the one selected by the user):
             inputStream = getResources().openRawResource(R.raw.sample);
             */
            inputStream = getContentResolver().openInputStream(CSV);
        }catch (Exception e){
            error.setText("Error: Sorry, I can't read the selected CSV file.");
            return;
        }
        Reader csv = new Reader(inputStream);
        this.students = csv.read();
    }

    /** Hide the keyboard after some action */
    private void hideKeyboard(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }

    /** Find the student code using the student number */
    public void findStudent(View view){
        TextView error = (TextView)findViewById(R.id.tv_error);
        TextView info   = (TextView)findViewById(R.id.tv_info);
        EditText number = (EditText)findViewById(R.id.et_number);
        TextView results    = (TextView)findViewById(R.id.tv_results);
        LinearLayout code  = (LinearLayout) findViewById(R.id.ll_code);

        // Reset error message after button press.
        error.setText("");
        // Hide keyboard after button press.
        this.hideKeyboard(view);

        // Get the number of the student
        int parsedNumber = -1;
        try{
            parsedNumber = Integer.parseInt((String) number.getText().toString());
        }catch(Exception e){
            error.setText("Error: Please provide a valid student number.");
            return;
        }

        // Let's find the code of the student using the number of the student.
        boolean studentFound=false;
        // TODO: We can make this search faster, but for now, this is fine.
        for(Student student: students ){
            // remove prefix, if available, let's handle exceptions like a champ.
            int tmp = -1;
            try {
                tmp = Integer.parseInt(student.getNumber());
            }catch (Exception e1){
                try{
                    tmp = Integer.parseInt(student.getNumber().substring((1)));
                }catch (Exception e2){
                    // Invalid student (e.g., an invalid line exists on the csv).
                    // Let's go to the next student in the list.
                    continue;
                }
            }
            if (tmp == parsedNumber) {
                studentFound=true;
                ((LinearLayout) findViewById(R.id.ll_code)).setBackgroundResource(R.drawable.textview_border);
                info.setText(student.getName() + " (" + student.getNumber() + ")");
                results.setText(student.getCode());
                break;
            }
        }
        if (!studentFound) error.setText("Error: Student not found.");
    }


}