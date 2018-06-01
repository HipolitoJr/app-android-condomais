package com.example.vinicius.condomais.app;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.example.vinicius.condomais.R;
import com.example.vinicius.condomais.infra.api.APIService;
import com.example.vinicius.condomais.models.TaxaCondominioAPIModel;
import com.example.vinicius.condomais.models.UnidadeHabitacionalAPIModel;
import com.example.vinicius.condomais.utils.Constants;
import com.example.vinicius.condomais.utils.SecurityPreferences;
import com.example.vinicius.condomais.utils.adapters.TaxaCondominioRVAdapter;
import com.example.vinicius.condomais.utils.adapters.UnidadeHabitacionalRVAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaxasCondominioActivity extends AppCompatActivity {

    @BindView(R.id.rv_taxas_condominio) protected RecyclerView rvTaxasCondominio;

    private APIService apiService;
    private SecurityPreferences securityPreferences;
    private long unidadeHabitacional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxas_condominio);
        ButterKnife.bind(this);
        this.setTitle("Taxas de Condomínio");
        setupViews();
    }

    private void setupViews() {
        securityPreferences = new SecurityPreferences(this);
        apiService = new APIService(getToken());

        unidadeHabitacional = getIntent().getLongExtra(Constants.UNIDADE_HABITACIONAL_SELECIONADA, 0);

        if (unidadeHabitacional != 0)
            getTaxasCondominio(unidadeHabitacional);
        else
            Toast.makeText(this, "Unidade nao encontrada!", Toast.LENGTH_SHORT).show();
    }

    private void getTaxasCondominio(long unidadeHabitacional) {
        Call<UnidadeHabitacionalAPIModel> call = apiService.unidadeHabitacionalEndPoint.getUnidadeHabitacionalUnica(unidadeHabitacional);

        call.enqueue(new Callback<UnidadeHabitacionalAPIModel>() {
            @Override
            public void onResponse(Call<UnidadeHabitacionalAPIModel> call, Response<UnidadeHabitacionalAPIModel> response) {
                if (response.isSuccessful()){
                    exibirTaxas(response.body().getTaxas());
                }
            }

            @Override
            public void onFailure(Call<UnidadeHabitacionalAPIModel> call, Throwable t) {
                Toast.makeText(TaxasCondominioActivity.this, "Falha na conexão", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void exibirTaxas(List<TaxaCondominioAPIModel> taxas) {
        TaxaCondominioRVAdapter taxaCondominioRVAdapter = new TaxaCondominioRVAdapter(this, this, taxas);

        rvTaxasCondominio.setAdapter(taxaCondominioRVAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        rvTaxasCondominio.setHasFixedSize(true);
        rvTaxasCondominio.setLayoutManager(linearLayoutManager);
    }

    public String getToken() {
        return securityPreferences.getSavedString(Constants.TOKEN);
    }
}
