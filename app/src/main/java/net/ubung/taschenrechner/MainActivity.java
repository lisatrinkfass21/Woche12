package net.ubung.taschenrechner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.List;

public class MainActivity extends AppCompatActivity{// implements View.OnClickListener {

    private String currentText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text = (TextView)findViewById(R.id.infix);
        text.setText(currentText);

    }

    public void enterValue(View view) {
        TextView text = (TextView)findViewById(R.id.infix);
        PostFixCalculator pfcalc;
        PostFixConverter pfcon;
            Button button = (Button) view;
            if(button.getText().toString().equals("CLEAR")){
                currentText = "";
                text.setText(currentText);
            }else if(button.getText().toString().equals(" = ")){

                pfcon = new PostFixConverter(currentText);
                List<String> expressions = pfcon.getPostfixExpression();
                pfcalc = new PostFixCalculator(expressions);
                BigDecimal bd = pfcalc.getResult();
                currentText = bd.toString();
                text.setText(currentText);
            }
            else{
                currentText = currentText + button.getText().toString();
                text.setText(currentText);
            }

    }



}