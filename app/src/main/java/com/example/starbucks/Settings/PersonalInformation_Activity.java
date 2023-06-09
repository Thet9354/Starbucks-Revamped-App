package com.example.starbucks.Settings;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.starbucks.R;

public class PersonalInformation_Activity extends AppCompatActivity {

    private ImageView btn_back;
    private TextView txtView_save;
    private EditText editTxt_salutation, editTxt_firstName, editTxt_lastName, editTxt_month, editTxt_year, editTxt_email, editTxt_countryCode, editTxt_phoneNumber;
    private androidx.appcompat.widget.AppCompatCheckBox cb_fnb, cb_merchandise, cb_promotions, cb_cno, cb_events, cb_brandValues, cb_email, cb_sms;

    //Variable to store values
    private String mSalutation, firstName, lastName, phoneNumber;
    private Boolean fnb, merchandise, promotions, cno, events, brandValues, email, sms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);

        initWidget();

        initUI();

        pageDirectories();
    }

    private void pageDirectories() {

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        txtView_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Update the user's personal information
                mSalutation = editTxt_salutation.getText().toString();
                firstName = editTxt_firstName.getText().toString();
                lastName = editTxt_lastName.getText().toString();
                phoneNumber = editTxt_phoneNumber.getText().toString();

                //Do not need to validate salutation since it can never be empty
                validateFirstName();
                validateLastName();
                validatePhoneNumber();
                validateInput();



            }
        });

        editTxt_salutation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View salutationDialog = LayoutInflater.from(PersonalInformation_Activity.this).inflate(R.layout.custom_dialog, null);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(PersonalInformation_Activity.this);

                alertDialog.setView(salutationDialog);
                ImageView btn_close = (ImageView) salutationDialog.findViewById(R.id.btn_close);
                RadioGroup rg_salutations = (RadioGroup) salutationDialog.findViewById(R.id.rg_salutations);
                RadioButton rb_mdm = (RadioButton) salutationDialog.findViewById(R.id.rb_mdm);
                RadioButton rb_mr = (RadioButton) salutationDialog.findViewById(R.id.rb_mr);
                RadioButton rb_mrs = (RadioButton) salutationDialog.findViewById(R.id.rb_mrs);
                RadioButton rb_ms = (RadioButton) salutationDialog.findViewById(R.id.rb_ms);

                final AlertDialog dialog = alertDialog.create();

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                rg_salutations.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId)
                        {
                            case R.id.rb_mdm:
                                editTxt_salutation.setText(rb_mdm.getText().toString());
                                break;
                            case R.id.rb_mr:
                                editTxt_salutation.setText(rb_mr.getText().toString());
                                break;
                            case R.id.rb_mrs:
                                editTxt_salutation.setText(rb_mrs.getText().toString());
                                break;
                            case R.id.rb_ms:
                                editTxt_salutation.setText(rb_ms.getText().toString());
                                break;
                        }
                        dialog.cancel();
                    }
                });


                btn_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

            }
        });
    }

    private void validateInput() {
        if (!validateFirstName() | !validateLastName() | !validatePhoneNumber())
            return;
        else
            updateUserInfo();
    }

    private void updateUserInfo() {
        //Update the user's info in firebase
    }

    private boolean validatePhoneNumber() {
        if (phoneNumber.isEmpty())
        {
            editTxt_phoneNumber.setError("Field cannot be empty to proceed");
            return false;
        }
        else
            return true;
    }

    private boolean validateLastName() {
        if (lastName.isEmpty())
        {
            editTxt_lastName.setError("Field cannot be empty to proceed");
            return false;
        }
        else
            return true;
    }

    private boolean validateFirstName() {
        if (firstName.isEmpty())
        {
            editTxt_firstName.setError("Field cannot be empty to proceed");
            return false;
        }
        else
            return true;
    }

    private void initUI() {
        //Set the user's information
        checkboxDefaultValues();
    }

    private void checkboxDefaultValues() {
        //Setting a default boolean value for all the boolean variables for checkboxes
        fnb = false;
        merchandise = false;
        promotions = false;
        cno = false;
        events = false;
        brandValues = false;
        email = true;
        sms = true;
    }

    private void initWidget() {

        //ImageView
        btn_back = findViewById(R.id.btn_back);

        //TextView
        txtView_save = findViewById(R.id.txtView_save);

        //EditText
        editTxt_salutation = findViewById(R.id.editTxt_salutation);
        editTxt_firstName = findViewById(R.id.editTxt_firstName);
        editTxt_lastName = findViewById(R.id.editTxt_lastName);
        editTxt_month = findViewById(R.id.editTxt_month);
        editTxt_year = findViewById(R.id.editTxt_year);
        editTxt_email = findViewById(R.id.editTxt_email);
        editTxt_countryCode = findViewById(R.id.editTxt_countryCode);
        editTxt_phoneNumber = findViewById(R.id.editTxt_phoneNumber);

        //androidx.appcompat.widget.AppCompatCheckBox
        cb_fnb = findViewById(R.id.cb_fnb);
        cb_merchandise = findViewById(R.id.cb_merchandise);
        cb_promotions = findViewById(R.id.cb_promotions);
        cb_cno = findViewById(R.id.cb_cno);
        cb_events = findViewById(R.id.cb_events);
        cb_brandValues = findViewById(R.id.cb_brandValues);
        cb_email = findViewById(R.id.cb_email);
        cb_sms = findViewById(R.id.cb_sms);

    }
}