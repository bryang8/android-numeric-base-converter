package umg.bryang8.con.converter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private NumericBaseConverter convert = new NumericBaseConverter();
    private EditText inputOriginal, inputBaseOriginal, inputBaseDeseada;
    private TextInputLayout inputLayoutOriginal, inputLayoutBaseOriginal, inputLayoutBaseDeseada;
    private CoordinatorLayout coordinatorLayout;
    private Button btnConvert;
    //private Spinner spnBaseOriginal, spnBaseDeseada;
    private Integer selectedBaseOriginal, selectedBaseDeseada;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);

        /*
        spnBaseOriginal= (Spinner) findViewById(R.id.spn_base_original);
        spnBaseOriginal.setOnItemSelectedListener(new ItemSelectedListener(0));

        spnBaseDeseada = (Spinner) findViewById(R.id.spn_base_deseada);
        spnBaseDeseada.setOnItemSelectedListener(new ItemSelectedListener(1));

        */
        inputLayoutOriginal = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutBaseOriginal = (TextInputLayout) findViewById(R.id.input_layout_base_original);
        inputLayoutBaseDeseada = (TextInputLayout) findViewById(R.id.input_layout_base_deseada);

        inputOriginal= (EditText) findViewById(R.id.input_original);
        inputBaseOriginal= (EditText) findViewById(R.id.input_base_o);
        inputBaseDeseada= (EditText) findViewById(R.id.input_base_d);

        inputOriginal.setFilters(new InputFilter[] {new InputFilter.AllCaps()});

        btnConvert= (Button) findViewById(R.id.btn_convert);

        resultText = (TextView) findViewById(R.id.txt_result);

        inputOriginal.addTextChangedListener(new MyTextWatcher(inputOriginal));

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convert();
            }
        });

        /*Button btnMostrarDialog = (Button)findViewById(R.id.btnShowDialog);
        btnMostrarDialog.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                new Information().show(getSupportFragmentManager(), "Creadores");
            }

        });*/

        Button btnMostarProc = (Button)findViewById(R.id.btnShowProcedure);
        btnMostarProc.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
               openProcedure();
            }

        });
    }

    private void openProcedure() {
        Intent myIntent = new Intent(this, ProcedureActivity.class);
        myIntent.putExtra("procedimiento", convert.getProcedimiento());
        startActivity(myIntent);
    }


    private void convert() {
        if (!validateName() || !validateBaseO() || !validateBaseD()) {
            return;
        }

        String
                baseO = inputBaseOriginal.getText().toString().trim(),
                baseD =inputBaseDeseada.getText().toString().trim();
        if (baseO == baseD){
            Snackbar.make(coordinatorLayout,"Debe seleccionar dos bases numéricas diferentes" , Snackbar.LENGTH_SHORT).show();
            return;
        }

        String result = convert.convert(String.valueOf(inputOriginal.getText()),
                Integer.parseInt(baseO),
                Integer.parseInt(baseD));

        if (result == null || result.equals("null")){
            Snackbar.make(coordinatorLayout,"Formato de número inválido" , Snackbar.LENGTH_SHORT).show();
            return;
        }

        resultText.setText("Resultado: " + result);
        System.out.println(convert.getProcedimiento());
    }

    private boolean validateName() {
        if (inputOriginal.getText().toString().trim().isEmpty()) {
            inputLayoutOriginal.setError(getString(R.string.err_msg_name));
            requestFocus(inputOriginal);
            inputLayoutOriginal.setErrorEnabled(true);
            return false;
        } else {
            inputLayoutOriginal.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateBaseO() {
        String string = inputBaseOriginal.getText().toString().trim();
        if (string.isEmpty()) {
            inputLayoutBaseOriginal.setError("Ingrese la base original");
            requestFocus(inputBaseOriginal);
            inputLayoutBaseOriginal.setErrorEnabled(true);
            return false;
        }
        else if (!string.equals("2") && !string.equals("8") && !string.equals("10") && !string.equals("16")){
            inputLayoutBaseOriginal.setError("Solo se aceptan 2,8,10,16");
            requestFocus(inputBaseOriginal);
            inputLayoutBaseOriginal.setErrorEnabled(true);
            return false;
        }
        else {
            inputLayoutBaseOriginal.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateBaseD() {
        String string = inputBaseDeseada.getText().toString().trim();
        if (string.isEmpty()) {
            inputLayoutBaseDeseada.setError("Ingrese la base deseada");
            requestFocus(inputBaseDeseada);
            inputLayoutBaseDeseada.setErrorEnabled(true);
            return false;
        }
        else if (!string.equals("2") && !string.equals("8") && !string.equals("10") && !string.equals("16")){
            inputLayoutBaseDeseada.setError("Solo se aceptan 2,8,10,16");
            requestFocus(inputBaseDeseada);
            inputLayoutBaseDeseada.setErrorEnabled(true);
            return false;
        }
        else {
            inputLayoutBaseDeseada.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_original:
                    validateName();
                    break;
                /*case R.id.input_email:
                    validateEmail();
                    break;*/
            }
        }
    }

    /*public class ItemSelectedListener implements AdapterView.OnItemSelectedListener {

        //get strings of first item
        private String firstItem;
        private Spinner spinner;
        private Integer type;

        public ItemSelectedListener(Integer _type){
            type = _type;
            spinner = (type == 0) ? spnBaseOriginal
                    : spnBaseDeseada;

            firstItem = String.valueOf(spinner.getSelectedItem());
        }

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if (firstItem.equals(String.valueOf(spinner.getSelectedItem()))) {
                // ToDo when first item is selected
            } else {
                resultText.setText("");
                if (type == 0){
                    selectedBaseOriginal = pos;
                }
                else {
                    selectedBaseDeseada = pos;
                }
                // Todo when item is selected by the user
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg) {

        }

    } */

}