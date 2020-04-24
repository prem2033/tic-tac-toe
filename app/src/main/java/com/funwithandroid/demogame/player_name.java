package com.funwithandroid.demogame;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class player_name extends AppCompatActivity {
    private  Button play;
    private  EditText player1name,player2name;
    private  String name1,name2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle(Html.fromHtml("<font color='#FF7700'>"+ "TIC-TAC-TOE" +"</font>"));
        setContentView(R.layout.activity_player_name);
        play=findViewById(R.id.play);
        player1name=findViewById(R.id.player1name);
        player2name=findViewById(R.id.player2name);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name1=player1name.getText().toString().trim();
                name2=player2name.getText().toString().trim();
                if(name1.isEmpty() || name2.isEmpty()){
                    alertDialog();
                }else{
                    callNextPageIntent();
                }
            }
        });
    }
    public  void alertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.player_name, null);
        builder.setView(view);
        final AlertDialog alertDialog=builder.create();
        alertDialog.setCancelable(false);
        Button yes=view.findViewById(R.id.alertyes);
        Button no=view.findViewById(R.id.alertno);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callNextPageIntent();
                alertDialog.dismiss();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
    public  void callNextPageIntent(){
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        intent.putExtra("PLAYER1_NAME",name1);
        intent.putExtra("PLAYER2_NAME",name2);
        startActivity(intent);
    }
}
