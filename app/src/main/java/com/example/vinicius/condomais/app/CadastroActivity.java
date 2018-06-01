package com.example.vinicius.condomais.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vinicius.condomais.R;
import com.example.vinicius.condomais.infra.api.APIService;
import com.example.vinicius.condomais.models.TokenAPIModel;
import com.example.vinicius.condomais.models.Usuario;
import com.example.vinicius.condomais.utils.Constants;
import com.example.vinicius.condomais.utils.SecurityPreferences;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_salvar_cadastro) protected Button btnSalvar;
    @BindView(R.id.btn_cancelar_cadastro) protected Button btnCancelar;
    @BindView(R.id.edit_usuario_cadastro) protected EditText editUsuario;
    @BindView(R.id.edit_email_cadastro) protected EditText editEmail;
    @BindView(R.id.edit_senha_cadastro) protected EditText editSenha;
    @BindView(R.id.edit_confirma_senha_cadastro) protected EditText editConfirmaSenha;

    private APIService apiService;
    private SecurityPreferences securityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        ButterKnife.bind(this);
        setupViews();
    }

    private void setupViews() {
        securityPreferences = new SecurityPreferences(this);
        apiService = new APIService("");

        btnSalvar.setOnClickListener(this);
    }

    private void cadastrarUsuario(final Usuario usuario) {
        Call<Usuario> usuarioCall = apiService.usuarioEndPoint.cadastrarUsuario(usuario);

        usuarioCall.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()){
                    confirmaCadastro(usuario);
                    securityPreferences.saveLong(Constants.USUARIO_LOGADO, response.body().getId());
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(CadastroActivity.this, "Falha", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void realizarLogin(final Usuario usuario) {

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
                Toast.makeText(CadastroActivity.this, "Erro" + t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void logarUsuario(TokenAPIModel token) {
        securityPreferences.saveString(Constants.TOKEN, token.getToken());
        startActivity(new Intent(this, CadastroCondominioActivity.class));
        finish();
    }

    private void confirmaCadastro(Usuario usuario) {
        Toast.makeText(this, "Usuário " + usuario.getNomeDeUsuario() + " cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
        realizarLogin(usuario);
    }

    private void criarUsuario() {
        if (validaSenha()){
            String username = editUsuario.getText().toString();
            String email = editEmail.getText().toString();
            String password = editSenha.getText().toString();
            Usuario usuario = new Usuario(username, email, password);

            cadastrarUsuario(usuario);
        }else{
            Toast.makeText(this, "Senhas não conferem!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validaSenha() {
        String senha = editSenha.getText().toString();
        String confirmaSenha = editConfirmaSenha.getText().toString();
        return senha.equals(confirmaSenha) ? true : false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_salvar_cadastro:
                criarUsuario();
                break;

            case R.id.btn_cancelar_cadastro:
                finish();
                break;

            default:
                break;
        }
    }
}
