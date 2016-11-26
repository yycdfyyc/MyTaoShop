package com.yuyunchao.asus.mytaoshop.presenter.main.myself.user.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yuyunchao.asus.mytaoshop.R;
import com.yuyunchao.asus.mytaoshop.model.entity.UserEntity;
import com.yuyunchao.asus.mytaoshop.model.event.Login;
import com.yuyunchao.asus.mytaoshop.presenter.main.myself.user.register.RegisterActivity;
import com.yuyunchao.asus.mytaoshop.utils.CacheUserPreferences;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements TextWatcher,LoginView{
    @Bind(R.id.tb_login)
    Toolbar tb_login;
    @Bind(R.id.et_login_username)
    EditText et_username;
    @Bind(R.id.et_login_pwd)
    EditText et_pwd;
    @Bind(R.id.btn_login)
    Button btn_login;
    @Bind(R.id.tv_register)
    TextView tv_register;

    private String username;
    private String pwd;
    private LoginPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginPresenter();
        presenter.attachView(this);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        setSupportActionBar(tb_login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.login));
        initUI();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

    private void initUI(){
        et_pwd.addTextChangedListener(this);
        et_username.addTextChangedListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        pwd = et_pwd.getText().toString();
        username = et_username.getText().toString();
        if (username.length()>0){
            if (pwd.length() > 0) {
                btn_login.setEnabled(true);
                btn_login.setBackgroundColor(getResources().getColor(R.color.colorblue));
            }else {
                btn_login.setEnabled(false);
                btn_login.setBackgroundColor(getResources().getColor(R.color.gray));
            }
        }else {
            btn_login.setEnabled(false);
            btn_login.setBackgroundColor(getResources().getColor(R.color.gray));
        }

    }

    @OnClick({R.id.btn_login,R.id.tv_register})
    public void click(View view){
        switch (view.getId()) {
            case R.id.btn_login:
//                Toast.makeText(this,"登录",Toast.LENGTH_SHORT).show();
                presenter.getLoginInfo(username,pwd);
                break;
            case R.id.tv_register:
//                Toast.makeText(this,"注册",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }

    @Override
    public void loginSuccess(UserEntity entity) {
        CacheUserPreferences.init(this);
        CacheUserPreferences.setUser(entity);
        Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
        finish();
        EventBus.getDefault().post(new Login(true));
    }

    @Override
    public void loginFail(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        et_username.setText("");
        et_pwd.setText("");
    }

}
