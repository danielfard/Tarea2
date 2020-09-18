package co.edu.unmagdalena.apmoviles.tarea2;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Random;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    EditText x1,x2,y1,y2;
    Button p_medio,pendiente,cuadrante;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        x1=findViewById(R.id.editx1);
        x2=findViewById(R.id.edx2);
        y1=findViewById(R.id.edy1);
        y2=findViewById(R.id.edy2);

        p_medio=findViewById(R.id.p_medio);
        pendiente=findViewById(R.id.pendiente);
        cuadrante=findViewById(R.id.cuadrante);

        p_medio.setOnClickListener(this);
        pendiente.setOnClickListener(this);
        cuadrante.setOnClickListener(this);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.Aleatorio:
                DecimalFormat formato1 = new DecimalFormat("#.00");
                Random random = new Random();
                x1.setText(formato1.format(-50 + (50 + 50) *random.nextDouble()));
                x2.setText(formato1.format(-50 + (50 + 50) *random.nextDouble()));
                y1.setText(formato1.format(-50 + (50 + 50) *random.nextDouble()));
                y2.setText(formato1.format(-50 + (50 + 50) *random.nextDouble()));
                break;
            case R.id.DEDP:

                validar_campos();

                if(!TextUtils.isEmpty(x1.getText().toString()) && !TextUtils.isEmpty(x2.getText().toString()) && !TextUtils.isEmpty(y1.getText().toString()) && !TextUtils.isEmpty(y2.getText().toString())){
                    double x_1 = Double.parseDouble(x1.getText().toString());
                    double x_2 = Double.parseDouble(x2.getText().toString());
                    double y_1 = Double.parseDouble(y1.getText().toString());
                    double y_2 = Double.parseDouble(y2.getText().toString());
                    double distancia=Math.sqrt(Math.pow((x_2-x_1),2)+Math.pow((y_2-y_1),2));
                    Toast.makeText(this, Double.toString(distancia), Toast.LENGTH_SHORT).show();
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    private String hallar_cuadrante(double x, double y){
            if(x > 0 && y > 0)
                return "Primer Cuadrante";
            else
                if(x < 0 && y > 0)
                    return "Segundo Cuadrante";
                else
                    if(x < 0 && y < 0)
                       return "Tercer Cuadrante";
                    else
                        if(x > 0 && y < 0)
                           return "Cuarto Cuadrante";
                        else
                            if(x==0)
                                if(y>0)
                                    return "Sobre el eje X y Y positivo";
                                else
                                    if(y<0)
                                        return "Sobre el eje X y Y positivo";
                            else
                                if(y==0)
                                    if(x>0)
                                        return "Sobre el eje Y y X positivo";
                                    else
                                    if(x<0)
                                        return "Sobre el eje Y y X positivo";
            return "Punto en el origen";

    }
    private void validar_campos(){
        if(TextUtils.isEmpty(x1.getText().toString()))
            x1.setError("campo requerido");

        if(TextUtils.isEmpty(x2.getText().toString()))
            x2.setError("campo requerido");

        if( TextUtils.isEmpty(y1.getText().toString()))
            y1.setError("campo requerido");

        if(TextUtils.isEmpty(y2.getText().toString()))
            y2.setError("campo requerido");
    }
    public void onClick(View v){

        validar_campos();

         if(!TextUtils.isEmpty(x1.getText().toString()) && !TextUtils.isEmpty(x2.getText().toString()) && !TextUtils.isEmpty(y1.getText().toString()) && !TextUtils.isEmpty(y2.getText().toString())){
            double x_1 = Double.parseDouble(x1.getText().toString());
            double x_2 = Double.parseDouble(x2.getText().toString());
            double y_1 = Double.parseDouble(y1.getText().toString());
            double y_2 = Double.parseDouble(y2.getText().toString());
            switch (v.getId()){
                case R.id.p_medio:
                    double x_punto_medio=(x_1+x_2)/2;
                    double y_punto_medio=(y_1+y_2)/2;
                    Toast.makeText(this,"Punto Medio: ( "+x_punto_medio+" , "+y_punto_medio+" )",Toast.LENGTH_LONG).show();
                    break;
                case R.id.pendiente:
                    if(x_2==x_1){
                        Toast.makeText(this,"Pendiente: = 0",Toast.LENGTH_LONG).show();
                    }else{
                        double m=(y_2-y_1)/(x_2-x_1);

                        Toast.makeText(this,"Pendiente: = "+m,Toast.LENGTH_LONG).show();
                    }

                    break;

                case R.id.cuadrante:
                    String p1=hallar_cuadrante(x_1,y_1);
                    String p2=hallar_cuadrante(x_2,y_2);

                    Toast.makeText(this,"punto 1 en: "+p1,Toast.LENGTH_LONG).show();
                    Toast.makeText(this,"punto 2 en:"+p2,Toast.LENGTH_LONG).show();
                    break;


            }
         }
    }
}
