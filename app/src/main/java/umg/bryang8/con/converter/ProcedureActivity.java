package umg.bryang8.con.converter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class ProcedureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procedure);

        Intent myIntent = getIntent(); // gets the previously created intent
        String procedimiento = myIntent.getStringExtra("procedimiento");

        TextView txtProcedimiento = (TextView) findViewById(R.id.txt_procedure);
        txtProcedimiento.setText(procedimiento);
        txtProcedimiento.setMovementMethod(new ScrollingMovementMethod());
    }
}
