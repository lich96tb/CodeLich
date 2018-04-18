package com.example.lich96tb.mvp.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lich96tb.mvp.R;
import com.example.lich96tb.mvp.presenter.PresenterLogin;
import com.example.lich96tb.mvp.presenter.PresenterLoginResponseToView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements PresenterLoginResponseToView {

    @BindView(R.id.txtName)
    EditText txtName;
    @BindView(R.id.txtPass)
    EditText txtPass;
    @BindView(R.id.btnDangNhap)
    Button btnDangNhap;
    PresenterLogin presenterLogin = new PresenterLogin(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnDangNhap)
    public void onViewClicked() {
        String name, pass;
        name = txtName.getText().toString();
        pass = txtPass.getText().toString();
        presenterLogin.receivedHenle(name, pass);
    }

    @Override
    public void onLoginSuccess() {
        Toast.makeText(this, "thanh cong", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onloginFailed() {
        Toast.makeText(this, "that bai", Toast.LENGTH_SHORT).show();
    }
}
