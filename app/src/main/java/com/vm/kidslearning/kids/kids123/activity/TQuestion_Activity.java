package com.vm.kidslearning.kids.kids123.activity;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vm.kidslearning.kids.kids123.R;
import com.vm.kidslearning.kids.kids123.dbhandler.DatabaseHandler;

public class TQuestion_Activity extends AppCompatActivity {
    int count = 1, ca = 0, ia = 0, temp[];
    Bitmap bmp;
    String id, tnum, ans;
    TextView tvIncorrAns, tvCorrAns, tvQueNum;
    TextView tvTitle, tvMsg, tvDiaCa, tvDiaIca;
    RadioButton rdOp1, rdOp2, rdOp3, rdOp4;
    RadioGroup radioGroup;
    DatabaseHandler databaseHandler;
    ImageView imgPr, imgNxt, imgQuestion;
    ImageView ivDia;
    String [] op;
    String [] selec = new String[5];
    Dialog dialog;
    Button btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tquestion);

        TTestNumber_Activity.tp++;

        tvIncorrAns = findViewById(R.id.tvIncorrAns);
        tvCorrAns = findViewById(R.id.tvCorrAns);
        tvQueNum = findViewById(R.id.tvQueNum);
        imgQuestion = findViewById(R.id.imgQuestion);

        rdOp1 = findViewById(R.id.rdOp1);
        rdOp2 = findViewById(R.id.rdOp2);
        rdOp3 = findViewById(R.id.rdOp3);
        rdOp4 = findViewById(R.id.rdOp4);
        radioGroup = findViewById(R.id.radioGroup);

        imgPr = findViewById(R.id.imgPr);
        imgNxt = findViewById(R.id.imgNxt);

        id = getIntent().getStringExtra("id");
        tnum = getIntent().getStringExtra("tnum");

        setTitle(getIntent().getStringExtra("cat")+" - Test "+ getIntent().getStringExtra("tnum"));
        databaseHandler = new DatabaseHandler(getApplicationContext());
        valid();
        temp = new int[5];

        initial();

        imgNxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(valid2()){

                    checkAns(count);
                    radioGroup.clearCheck();
                    initial();
                    count++;
                    initial();
                    selectRadio(count-1);
                    valid();
                }
                else{
                    Toast. makeText(getApplicationContext(),"Please choose one option",Toast. LENGTH_SHORT).show();
                }
            }
        });

        imgPr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count--;
                if (temp[count-1] == 1){
                    ca--;
                }
                else{
                    ia--;
                }
                initial();
                selectRadio(count-1);
                valid();
            }
        });


        dialog = new Dialog(TQuestion_Activity.this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        ivDia = dialog.findViewById(R.id.ivDia);
        tvTitle = dialog.findViewById(R.id.tvTitle);
        tvDiaCa = dialog.findViewById(R.id.tvDiaCa);
        tvDiaIca = dialog.findViewById(R.id.tvDiaIca);
        tvMsg = dialog.findViewById(R.id.tvMsg);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


//                rdOp1.setBackground(getDrawable(R.drawable.homebtn));
//                rdOp2.setBackground(getDrawable(R.drawable.homebtn));
//                rdOp3.setBackground(getDrawable(R.drawable.homebtn));
//                rdOp4.setBackground(getDrawable(R.drawable.homebtn));
//
//                RadioButton rButton = findViewById(checkedId);
//                rButton.setBackground(getDrawable(R.drawable.checked_option));

                if(rdOp1.isChecked()){
                    rdOp1.setBackground(getDrawable(R.drawable.checked_option));
                }
                else{
                    rdOp1.setBackground(getDrawable(R.drawable.homebtn));
                }

                if(rdOp2.isChecked()){
                    rdOp2.setBackground(getDrawable(R.drawable.checked_option));
                }

                else{
                    rdOp2.setBackground(getDrawable(R.drawable.homebtn));
                }

                if(rdOp3.isChecked()){
                    rdOp3.setBackground(getDrawable(R.drawable.checked_option));
                }
                else{
                    rdOp3.setBackground(getDrawable(R.drawable.homebtn));
                }

                if(rdOp4.isChecked()){
                    rdOp4.setBackground(getDrawable(R.drawable.checked_option));
                }
                else{
                    rdOp4.setBackground(getDrawable(R.drawable.homebtn));
                }

                if(count == 5){
                    checkAns(count);
                    setDialog();
                }
            }
        });
    }

    void initial(){

        tvQueNum.setText(String.valueOf(count));
        tvCorrAns.setText(String.valueOf(ca));
        tvIncorrAns.setText(String.valueOf(ia));

        op = databaseHandler.getOption(String.valueOf(count), id, tnum);

        rdOp1.setText(op[0].toString());
        rdOp2.setText(op[1].toString());
        rdOp3.setText(op[2].toString());
        rdOp4.setText(op[3].toString());
        ans = op[4].toString();
        byte b [] = databaseHandler.getOpimage(String.valueOf(count), id, tnum);

        bmp = BitmapFactory.decodeByteArray(b, 0, b.length);
        imgQuestion.setImageBitmap(bmp);
    }

    void valid(){
        if(count == 5){
            imgNxt.setVisibility(View.INVISIBLE);
        }
        else {
            imgNxt.setVisibility(View.VISIBLE);
        }
        if (count == 1){
            imgPr.setVisibility(View.INVISIBLE);
        }
        else {
            imgPr.setVisibility(View.VISIBLE);
        }
    }
    boolean valid2(){
        if (rdOp1.isChecked() || rdOp2.isChecked() || rdOp3.isChecked() || rdOp4.isChecked()){
            return true;
        }
        else{
            return false;
        }
    }
    void checkAns(int c){
        int x = radioGroup.getCheckedRadioButtonId();
        RadioButton rb = findViewById(x);
        if(ans.equals(rb.getText().toString())){
            ca++;
            temp[c-1] = 1;
        }
        else {
            ia++;
            temp[c-1] = 0;
        }
        selec[c-1] = rb.getText().toString();
        if(ia == 3){
            setDialog();
        }
    }
    void selectRadio(int c){
        if(rdOp1.getText().toString().equals(selec[c])){
            rdOp1.setChecked(true);
        }
        else if(rdOp2.getText().toString().equals(selec[c])){
            rdOp2.setChecked(true);
        }
        else if(rdOp3.getText().toString().equals(selec[c])){
            rdOp3.setChecked(true);
        }
        else if(rdOp4.getText().toString().equals(selec[c])){
            rdOp4.setChecked(true);
        }
    }
    void setDialog(){
        if(ia > 2){
            tvTitle.setText("Sorry");
            tvTitle.setTextColor(Color.RED);
            tvMsg.setText("You failed exam");
            ivDia.setImageDrawable(getDrawable(R.drawable.delete));
        }
        tvDiaCa.setText(String.valueOf(ca));
        tvDiaIca.setText(String.valueOf(ia));

        btnFinish = dialog.findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent in = new Intent(TQuestion_Activity.this, TCategory_Activity.class);
//                startActivity(in);
                finish();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}