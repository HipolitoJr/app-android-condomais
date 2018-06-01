package com.example.vinicius.condomais.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.example.vinicius.condomais.R;
import com.example.vinicius.condomais.infra.api.APIService;
import com.example.vinicius.condomais.models.GrupoHabitacionalAPIModel;
import com.example.vinicius.condomais.models.UnidadeHabitacionalAPIModel;
import com.example.vinicius.condomais.utils.Constants;
import com.example.vinicius.condomais.utils.SecurityPreferences;
import com.example.vinicius.condomais.utils.adapters.GrupoHabitacionalRVAdapter;
import com.example.vinicius.condomais.utils.adapters.UnidadeHabitacionalRVAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UnidadesHabitacionaisActivity extends AppCompatActivity {

    @BindView(R.id.rv_unidades_habitacionais) protected RecyclerView rvUnidadesHabitacionais;

    private APIService apiService;
    private SecurityPreferences securityPreferences;
    private long grupoHabitacional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unidades_habitacionais);
        ButterKnife.bind(this);
        setupViews();
        this.setTitle("Unidades Habitacionais");

    }

    private void setupViews() {
        securityPreferences = new SecurityPreferences(this);
        apiService = new APIService(getToken());
        Intent intent = getIntent();
        grupoHabitacional = intent.getLongExtra(Constants.GRUPO_HABITACIONAL_SELECIONADO, 0);

        if (grupoHabitacional != 0)
            getUnidadesHabitacionais(grupoHabitacional);
        else
            Toast.makeText(this, "Grupo não encontrado!", Toast.LENGTH_SHORT).show();
    }

    private void getUnidadesHabitacionais(long grupoHabitacional) {
        Call<GrupoHabitacionalAPIModel> call = apiService.grupoHabitacionalEndPoint.getGrupoHabitacionalUnico(grupoHabitacional);

        call.enqueue(new Callback<GrupoHabitacionalAPIModel>() {
            @Override
            public void onResponse(Call<GrupoHabitacionalAPIModel> call, Response<GrupoHabitacionalAPIModel> response) {
                if (response.isSuccessful()){
                    exibirUnidades(response.body().getUnidades());
                }
            }

            @Override
            public void onFailure(Call<GrupoHabitacionalAPIModel> call, Throwable t) {
                Toast.makeText(UnidadesHabitacionaisActivity.this, "Falha na conexão", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void exibirUnidades(List<UnidadeHabitacionalAPIModel> unidades) {
        UnidadeHabitacionalRVAdapter unidadeHabitacionalRVAdapter = new UnidadeHabitacionalRVAdapter(this, this, unidades);

        rvUnidadesHabitacionais.setAdapter(unidadeHabitacionalRVAdapter);

        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        rvUnidadesHabitacionais.setHasFixedSize(true);
        rvUnidadesHabitacionais.setLayoutManager(gridLayoutManager);
    }

    private String getToken() {
        return securityPreferences.getSavedString(Constants.TOKEN);
    }
}
