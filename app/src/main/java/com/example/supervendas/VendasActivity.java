package com.example.supervendas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class VendasActivity extends Activity {
    EditText ed1,ed2,ed3;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_vendas );

        ed1 = findViewById(R.id.produto);
        ed2 = findViewById(R.id.produtodesc);
        ed3 = findViewById(R.id.produtopreco);
        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VendasActivity.this,MainActivity.class);
                startActivity(i);
            }
        });


        b1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
                finish();
            }
        } );
    }

    public void insert(){
        try{
            String produto = ed1.getText().toString();
            String descricao = ed2.getText().toString();
            String produtopreco = ed3.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("vendas", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS vendas(id INTEGER PRIMARY KEY AUTOINCREMENT,produto VARCHAR,prodesc VARCHAR,produtopreco VARCHAR)");

            String sql = "insert into vendas(produto,prodesc,produtopreco)values(?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,produto);
            statement.bindString(2,descricao);
            statement.bindString( 3,produtopreco );
            statement.execute();
            Toast.makeText(this,"Produto adicionado com sucesso!!!",Toast.LENGTH_SHORT).show();
            ed1.setText("");
            ed2.setText("");
            ed1.requestFocus();
        }catch(Exception ex){
            Toast.makeText(this,"Produto deu error!!!",Toast.LENGTH_SHORT).show();
        }
    }
}