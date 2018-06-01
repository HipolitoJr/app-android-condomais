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
import com.example.vinicius.condomais.models.CondominioAPIModel;
import com.example.vinicius.condomais.utils.Constants;
import com.example.vinicius.condomais.utils.SecurityPreferences;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroCondominioActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_salvar_cadastro_condominio) protected Button btnSalvar;
    @BindView(R.id.btn_cancelar_cadastro_condominio) protected Button btnCancelar;

    @BindView(R.id.edit_nome_cadastro_condominio) protected EditText editNome;
    @BindView(R.id.edit_cnpj_cadastro_condominio) protected EditText editCNPJ;
    @BindView(R.id.edit_endereco_cadastro_condominio) protected EditText editEndereco;

    private APIService apiService;
    private SecurityPreferences securityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_condominio);
        ButterKnife.bind(this);
        setupViews();
    }

    private void setupViews() {
        securityPreferences = new SecurityPreferences(this);
        apiService = new APIService(getToken());
        btnSalvar.setOnClickListener(this);
    }

    private void registrarCondominio(CondominioAPIModel condominio) {
        Call<CondominioAPIModel> call = apiService.condominioEndPoint.registrarCondominio(condominio);

        call.enqueue(new Callback<CondominioAPIModel>() {
            @Override
            public void onResponse(Call<CondominioAPIModel> call, Response<CondominioAPIModel> response) {
                if (response.isSuccessful()){
                    confirmaCadastro(response.body());
                }else{
                    try {
                        Toast.makeText(CadastroCondominioActivity.this, ""+ response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<CondominioAPIModel> call, Throwable t) {
                Toast.makeText(CadastroCondominioActivity.this, "Falha na conexão!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void confirmaCadastro(CondominioAPIModel condominio) {
        Toast.makeText(this, "Cadastro Finalizado!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, DrawerActivity.class));
        finish();
    }

    private CondominioAPIModel criarCondominio() {
        String nome = editNome.getText().toString();
        String cnpj = editCNPJ.getText().toString();
        String endereco = editEndereco.getText().toString();
        CondominioAPIModel condominioAPIModel = new CondominioAPIModel(nome, cnpj, endereco);
        condominioAPIModel.setSindico(securityPreferences.getSavedLong(Constants.USUARIO_LOGADO));

        return condominioAPIModel;
    }

    private String getToken() {
        String token = securityPreferences.getSavedString(Constants.TOKEN);
        Toast.makeText(this, "" + token, Toast.LENGTH_SHORT).show();
        return token;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_salvar_cadastro_condominio:
                registrarCondominio(criarCondominio());
                break;

            default:
                break;
        }
    }
}
