package com.example.vinicius.condomais.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vinicius.condomais.R;
import com.example.vinicius.condomais.infra.FeaturesUtils;
import com.example.vinicius.condomais.utils.Constants;
import com.example.vinicius.condomais.infra.api.APIService;
import com.example.vinicius.condomais.models.TokenAPIModel;
import com.example.vinicius.condomais.models.Usuario;
import com.example.vinicius.condomais.utils.SecurityPreferences;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_entrar_login) protected Button btnEntrar;
    @BindView(R.id.btn_cadastrar_login) protected Button btnCadastrar;
    @BindView(R.id.edit_usuario_login) protected EditText editUsuario;
    @BindView(R.id.edit_senha_login) protected EditText editSenha;

    private ProgressDialog progressDialog;
    private APIService apiService;
    private SecurityPreferences securityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setupViews();

    }

    private void setupViews() {
        progressDialog = FeaturesUtils.initPgDialog(this, "Aguarde");
        apiService = new APIService("");
        securityPreferences = new SecurityPreferences(this);

        if (estaLogado()) initProxActivity();

        btnEntrar.setOnClickListener(this);
        btnCadastrar.setOnClickListener(this);
    }

    private void realizarLogin(Usuario usuario) {
        progressDialog.show();

        Call<TokenAPIModel> call = apiService.tokenEndPoint.login(usuario);

        call.enqueue(new Callback<TokenAPIModel>() {
            @Override
            public void onResponse(Call<TokenAPIModel> call, Response<TokenAPIModel> response) {
                if (response.isSuccessful()){
                    logarUsuario(response.body());
                }
            }

            @Override
            public void onFailure(Call<TokenAPIModel> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Falha na conexão!", Toast.LENGTH_SHORT).show();
                progressDialog.hide();
            }
        });

    }

    private void logarUsuario(TokenAPIModel token) {
        securityPreferences.saveString(Constants.TOKEN, token.getToken());
        initProxActivity();
    }

    private Usuario criarUsuario() {
        String password = editSenha.getText().toString();
        String username = editUsuario.getText().toString();

        return new Usuario(username, "", password);
    }

    private boolean estaLogado(){
        String token = securityPreferences.getSavedString(Constants.TOKEN);
        return token.equals("") ? false : true;
    }

    private void initProxActivity(){
        Intent intent = new Intent(this, DrawerActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_entrar_login:
                realizarLogin(criarUsuario());
                break;

            case R.id.btn_cadastrar_login:
                Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(intent);
                finish();
                break;

            default:
                break;
        }
    }
}
