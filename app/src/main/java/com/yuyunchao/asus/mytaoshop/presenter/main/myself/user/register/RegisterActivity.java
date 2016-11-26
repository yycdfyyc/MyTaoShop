package com.yuyunchao.asus.mytaoshop.presenter.main.myself.user.register;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.yuyunchao.asus.mytaoshop.R;
import com.yuyunchao.asus.mytaoshop.model.entity.UserEntity;

import org.hybridsquad.android.library.CropHandler;
import org.hybridsquad.android.library.CropHelper;
import org.hybridsquad.android.library.CropParams;

import java.io.File;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements TextWatcher,RegisterView,View.OnClickListener{
    @Bind(R.id.tb_register)
    Toolbar tb_register;
    @Bind(R.id.et_register_username)
    EditText et_r_username;
    @Bind(R.id.et_register_pwd)
    EditText et_r_pwd;
    @Bind(R.id.et_register_qr_pwd)
    EditText et_r_qr_pwd;
    @Bind(R.id.btn_register)
    Button btn_register;
    @Bind(R.id.im_register_head)
    ImageView im_r_head;
    @Bind(R.id.et_register_nickname)
    EditText et_r_nickname;
    private Button btn_pp_camare,btn_pp_pic,btn_pp_cancel;
    private String r_username;
    private String r_pwd;
    private String r_qr_pwd;
    private String r_nickname;
    private PopupWindow popupWindow;
    private boolean isSet;
    private RegisterPresenter presenter;
    private String imgUri;
    private File imgFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        presenter = new RegisterPresenter();
        presenter.attachView(this);
        initpopuWindow();
    }
    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        setSupportActionBar(tb_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.register));
        et_r_username.addTextChangedListener(this);
        et_r_pwd.addTextChangedListener(this);
        et_r_qr_pwd.addTextChangedListener(this);
    }
    /**
     * 加载popupWindow
     */
    private void initpopuWindow(){
        popupWindow = new PopupWindow();
        View view = getLayoutInflater().inflate(R.layout.layout_popupwin_user_icon, null);
        btn_pp_camare = (Button) view.findViewById(R.id.btn_from_cam);
        btn_pp_pic = (Button) view.findViewById(R.id.btn_from_pic);
        btn_pp_cancel = (Button) view.findViewById(R.id.btn_cancel);
        btn_pp_camare.setOnClickListener(this);
        btn_pp_cancel.setOnClickListener(this);
        btn_pp_pic.setOnClickListener(this);
        popupWindow.setContentView(view);
        popupWindow.setWindowLayoutMode(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //PopupWindow有时需要设置背景，其他的点击等等才会有效果
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setAnimationStyle(R.style.anim_menu_bottombar);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        r_username = et_r_username.getText().toString();
        r_pwd = et_r_pwd.getText().toString();
        r_qr_pwd = et_r_qr_pwd.getText().toString();
        r_nickname = et_r_nickname.getText().toString();
        if (r_nickname.length() > 0) {
            if (r_username.length() > 0) {
                if (r_pwd.length() > 0) {
                    if (r_qr_pwd.length() > 0) {
                        btn_register.setEnabled(true);
                        btn_register.setBackgroundColor(getResources().getColor(R.color.colorblue));
                    } else {
                        btn_register.setEnabled(false);
                        btn_register.setBackgroundColor(getResources().getColor(R.color.gray));
                    }
                } else {
                    btn_register.setEnabled(false);
                    btn_register.setBackgroundColor(getResources().getColor(R.color.gray));
                }
            }else {
                btn_register.setEnabled(false);
                btn_register.setBackgroundColor(getResources().getColor(R.color.gray));
            }
        }else {
            btn_register.setEnabled(false);
            btn_register.setBackgroundColor(getResources().getColor(R.color.gray));
        }

    }

    @OnClick(R.id.btn_register)
    public void click(View v){
        if (!r_pwd.equals(r_qr_pwd)) {
            Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
            et_r_pwd.setText("");
            et_r_qr_pwd.setText("");
        } else {
            pd();
        }

    }

    /**
     * 判断用户名与密码的正则
     */
    private void pd(){
        if (isSet) {
            if (r_username.matches("[a-zA-Z0-9_]{6,24}")) {
                if (r_pwd.matches("[a-zA-Z0-9_]{6,24}")) {
                    presenter.getRegisterInfo(r_username, r_pwd);
                } else {
                    Toast.makeText(this, "密码不正确，密码为6至24位字符串，只能是数字、大小写英文字母或下划线",
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "用户名不正确，用户名为6至24位字符串，只能是数字、大小写英文字母或下划线",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "请上传一张图片作为头像", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void registerSuccess(UserEntity entity) {
        entity.setNickname(r_nickname);
        presenter.getUpdataInfo(entity,imgFile);
    }

    @Override
    public void registerFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        et_r_username.setText("");
        et_r_pwd.setText("");
        et_r_qr_pwd.setText("");
    }

    @Override
    public void upDataSuccess(UserEntity entity) {
        Toast.makeText(this, "注册成功"+entity.getName(), Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void upDataFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.im_register_head)
    public void clickim(){
        if (popupWindow.isShowing()){
            popupWindow.dismiss();
        }else {
            popupWindow.showAtLocation(findViewById(R.id.rl_layout), Gravity.BOTTOM, 0, 0);
        }
    }



    private CropHandler cropHandler = new CropHandler() {
        @Override
        public void onPhotoCropped(Uri uri) {
            if (uri != null) {
                Toast.makeText(RegisterActivity.this, uri.toString(), Toast.LENGTH_LONG).show();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(RegisterActivity.this.getContentResolver(), uri);
                    im_r_head.setImageBitmap(bitmap);
                    imgUri = uri.getPath();
                    imgFile = new File(imgUri);
                    isSet = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        @Override
        public void onCropCancel() {

        }

        @Override
        public void onCropFailed(String message) {

        }

        @Override
        public CropParams getCropParams() {
            CropParams params = new CropParams();
            params.aspectX =400;
            params.aspectY=400;
            return params;
        }

        @Override
        public Activity getContext() {
            return RegisterActivity.this;
        }
    };

    /**
     * popupWindow中按钮点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                popupWindow.dismiss();
                break;
            case R.id.btn_from_pic:
                //相册上传
                CropHelper.clearCachedCropFile(cropHandler.getCropParams().uri);
                Intent intent = CropHelper.buildCropFromGalleryIntent(cropHandler.getCropParams());
                startActivityForResult(intent,CropHelper.REQUEST_CROP);
                break;
            case R.id.btn_from_cam:
                //拍照上传
                CropHelper.clearCachedCropFile(cropHandler.getCropParams().uri);
                Intent intent1 = CropHelper.buildCaptureIntent(cropHandler.getCropParams().uri);
                startActivityForResult(intent1,CropHelper.REQUEST_CAMERA);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CropHelper.handleResult(cropHandler,requestCode,resultCode,data);
    }
}
