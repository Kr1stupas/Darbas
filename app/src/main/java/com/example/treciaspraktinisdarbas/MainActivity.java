package com.example.treciaspraktinisdarbas;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.radiobutton.MaterialRadioButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result,solution;
    MaterialButton buttonC, buttonAC;
    MaterialButton buttonDivide, buttonMultiply, buttonPlus, buttonMinus, buttonEquals;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonDot, buttonRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.result);
        solution = findViewById(R.id.solution);

        assignId(button0, R.id.button_zero);
        assignId(buttonC, R.id.button_c);
        assignId(buttonAC, R.id.button_ac);
        assignId(buttonDivide, R.id.button_devide);
        assignId(buttonMultiply, R.id.button_multiply);
        assignId(buttonPlus, R.id.button_plus);
        assignId(buttonMinus, R.id.button_minus);
        assignId(buttonEquals, R.id.button_equals);
        assignId(button1, R.id.button_one);
        assignId(button2, R.id.button_two);
        assignId(button3, R.id.button_three);
        assignId(button4, R.id.button_four);
        assignId(button5, R.id.button_five);
        assignId(button6, R.id.button_six);
        assignId(button7, R.id.button_seven);
        assignId(button8, R.id.button_eight);
        assignId(button9, R.id.button_nine);
        assignId(buttonDot, R.id.button_dot);
        assignId(buttonRoot, R.id.button_root);




    }

    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataCalculate = solution.getText().toString();

        if(buttonText.equals("AC")){
            solution.setText("");
            result.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solution.setText(result.getText());
            return;
        }
        if (buttonText.equals("√")){
            dataCalculate = solution.getText().toString();
            if (!dataCalculate.isEmpty()) {
                double number = Double.parseDouble(dataCalculate);
                if (number >= 0) {
                    double sqrtResult = Math.sqrt(number);
                    solution.setText("√(" + dataCalculate + ")");
                    result.setText(String.valueOf(sqrtResult));
                } else {
                    solution.setText("Invalid Input");
                    result.setText("Error");
                }
            }
        }
        if(buttonText.equals("C")){
            dataCalculate = dataCalculate.substring(0, dataCalculate.length()-1);
        }else{
            dataCalculate = dataCalculate+buttonText;
        }

        solution.setText(dataCalculate);

        String finalResult = getResult(dataCalculate);
        if (!finalResult.equals("Error")){
            result.setText(finalResult);
        }
    }
    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data, "Javascript", 1, null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        }catch (Exception e){
            return "Error";
        }
    }
}