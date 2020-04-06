package it.jps.megagame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText txtNumber = null;
    private Button btnCompare = findViewById(R.id.btnCompare);
    private TextView lblResult;
    private ProgressBar progressBarScore;
    private TextView lblHistory;
    private int score;
    private int researchValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNumber=findViewById(R.id.txtNumber);
        btnCompare=findViewById(R.id.btnCompare);
        lblResult=findViewById(R.id.lblResult);
        progressBarScore=findViewById(R.id.progressBarScore);
        lblHistory= findViewById(R.id.lblHistory);

        btnCompare.setOnClickListener(btnCompareListener);

        init();

    }

    private void init(){
        score=0;
        researchValue=1+(int)(Math.random()*100);
        Log.i("DEBUG","Valeur cherchée" + researchValue);

        txtNumber.setText("");
        lblResult.setText("");
        lblHistory.setText("");
        progressBarScore.setProgress(score);

        txtNumber.requestFocus();

    }

    private void congrutalatons(){
        lblResult.setText(R.string.strCongratulations);
        AlertDialog retryAlert = new AlertDialog.Builder(this).create();
        retryAlert.setTitle(R.string.app_name);
        retryAlert.setMessage(getString(R.string.strMessageAlert,score));

        retryAlert.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.strYes), new AlertDialog.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                init();
            }
        });
        retryAlert.setButton(AlertDialog.BUTTON_NEUTRAL, getString(R.string.strNo), new AlertDialog.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        retryAlert.show();
    }

    private View.OnClickListener btnCompareListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i("DEBUG","Bouton cliqué");

            String strNumber= txtNumber.getText().toString();
            if(strNumber.equals(" "))
                return;
            lblHistory.append(strNumber);
            progressBarScore.incrementProgressBy(1);
            score++;

            int enteredValue= Integer.parseInt(strNumber);
            if(enteredValue == researchValue){
                congrutalatons();
            } else
                 if(enteredValue < researchValue){
                    lblResult.setText(R.string.strGreater);
                   } else
                       if(enteredValue > researchValue) {
                           lblResult.setText(R.string.strLower);
                       }

             txtNumber.setText(" ");
             txtNumber.requestFocus();
        }
    };
}
