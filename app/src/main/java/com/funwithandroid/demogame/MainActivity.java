package com.funwithandroid.demogame;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    private  ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9;
    private boolean player1=false,player2=false;
    int[][] winPositions;
    boolean[] touched;
    int[] touchstroe;
    int p1sign,p2sign;
    private  String p1name,p2name;
    private TextView playerturn,player1Score,player2Score,leadingtext;
    private  int count=0,player1win=0,player2win=0;
    boolean winflag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle(Html.fromHtml("<font color='#FF7700'>"+ "TIC-TAC-TOE" +"</font>"));
        p1sign=1;
        p2sign=2;
        winPositions= new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9},
                {1, 4, 7}, {2, 5, 8}, {3, 6, 9},
                {1, 5, 9}, {3, 5, 7}};
        touchstroe=new int[]{0,0,0,0,0,0,0,0,0};
        touched=new boolean[]{true,true,true,true,true,true,true,true,true};
        setContentView(R.layout.activity_main);
        imageView1=findViewById(R.id.imageView1);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);
        playerturn=findViewById(R.id.playerturn);
        player1Score=findViewById(R.id.player1Score);
        player2Score=findViewById(R.id.player2Score);
        leadingtext=findViewById(R.id.leadingtext);
        playerturn.setSelected(true);
        player1Score.setSelected(true);
        player2Score.setSelected(true);
        leadingtext.setSelected(true);
        player1=true;
        getIntentValue();
        playerturn.setText(p1name+" Turn");
        player1Score.setText(p1name+"=0");
        player2Score.setText(p2name+"=0");
        leadingtext.setText("yet to do...");
    }
    public  void showicon(View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(player1 && touched[tappedImage-1]){
        switch (tappedImage){
            case 1:imageView1.setImageResource(R.drawable.close2);break;
            case 2:imageView2.setImageResource(R.drawable.close2);break;
            case 3:imageView3.setImageResource(R.drawable.close2);break;
            case 4:imageView4.setImageResource(R.drawable.close2);break;
            case 5:imageView5.setImageResource(R.drawable.close2);break;
            case 6:imageView6.setImageResource(R.drawable.close2);break;
            case 7:imageView7.setImageResource(R.drawable.close2);break;
            case 8:imageView8.setImageResource(R.drawable.close2);break;
            case 9:imageView9.setImageResource(R.drawable.close2);break;
        }
        touchstroe[tappedImage-1]=p1sign;
        player1=false; player2=true;
            count++;
         checkwin("player1");
            playerturn.setText(p2name+" Turn");
        }else if(player2 && touched[tappedImage-1]){
        switch (tappedImage){
            case 1:imageView1.setImageResource(R.drawable.circle);break;
            case 2:imageView2.setImageResource(R.drawable.circle);break;
            case 3:imageView3.setImageResource(R.drawable.circle);break;
            case 4:imageView4.setImageResource(R.drawable.circle);break;
            case 5:imageView5.setImageResource(R.drawable.circle);break;
            case 6:imageView6.setImageResource(R.drawable.circle);break;
            case 7:imageView7.setImageResource(R.drawable.circle);break;
            case 8:imageView8.setImageResource(R.drawable.circle);break;
            case 9:imageView9.setImageResource(R.drawable.circle);break;
        }
        touchstroe[tappedImage-1]=p2sign;
        player1=true;player2=false;
            count++;
        checkwin("player2");
            playerturn.setText(p1name+" Turn");
        }
        touched[tappedImage-1]=false;
        if(count==9 && winflag==false)
            checkwin("draw");
    }
    public  void checkwin(String player){
        if(player.equals("player1")) {
            for (int[] winposition : winPositions) {
                if(touchstroe[winposition[0]-1]==1 && touchstroe[winposition[1]-1]==1 && touchstroe[winposition[2]-1]==1){
                    winflag=true;
                    alterdialog(p1name);
                    player1win++;
                    player1Score.setText(p1name+"="+player1win);
                    player2=false;
                    player1=false;
                }
            }
        }else if(player.equals("player2")){
            for (int[] winposition : winPositions) {
                if(touchstroe[winposition[0]-1]==2 && touchstroe[winposition[1]-1]==2 && touchstroe[winposition[2]-1]==2){
                    winflag=true;
                    alterdialog(p2name);
                    player2win++;
                    player2Score.setText(p2name+"="+player2win);
                    player2=false;
                    player1=false;
                }
            }
        }else{
            alertForDraw();
        }
    }
    public void refresh(){
        imageView1.setImageResource(0);
        imageView2.setImageResource(0);
        imageView3.setImageResource(0);
        imageView4.setImageResource(0);
        imageView5.setImageResource(0);
        imageView6.setImageResource(0);
        imageView7.setImageResource(0);
        imageView8.setImageResource(0);
        imageView9.setImageResource(0);
        touched=new boolean[]{true,true,true,true,true,true,true,true,true};
        touchstroe=new int[]{0,0,0,0,0,0,0,0,0};
        player1=true;
        player2=false;
        playerturn.setText(p1name+" Turn");
        count=0;
        winflag=false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_item, menu);
            return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.refresh:
                refresh();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void alterdialog(String player){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.win_dialog, null);
        builder.setView(view);
        final AlertDialog alertDialog=builder.create();
        alertDialog.setCancelable(false);
        Button retry=view.findViewById(R.id.retry);
        Button exit=view.findViewById(R.id.exit);
        TextView name=view.findViewById(R.id.dialogname);
        name.setText(player+" Won");
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
                alertDialog.dismiss();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                //System.exit(1);
                finish();
            }
        });
        alertDialog.show();
    }
    //getting player name form previous Intent
   public void  getIntentValue(){
       Intent intent=getIntent();
       p1name=intent.getStringExtra("PLAYER1_NAME");
       p2name=intent.getStringExtra("PLAYER2_NAME");
       if(p1name.isEmpty()) {
           p1name = "Player1";
       }
       if(p2name.isEmpty()) {
           p2name = "Player2";
       }
    }
    public  void alertForDraw(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.win_dialog, null);
        builder.setView(view);
        final AlertDialog alertDialog=builder.create();
        alertDialog.setCancelable(false);
        Button retry=view.findViewById(R.id.retry);
        Button exit=view.findViewById(R.id.exit);
        retry.setText("YES");exit.setText("NO");
        TextView name=view.findViewById(R.id.dialogname);
        TextView congrats=view.findViewById(R.id.textViewcongrats);
        congrats.setText(" DRAW! ");
        name.setText("Do you want to continue");
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
                alertDialog.dismiss();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                //System.exit(1);
                finish();
            }
        });
        alertDialog.show();
    }
}
