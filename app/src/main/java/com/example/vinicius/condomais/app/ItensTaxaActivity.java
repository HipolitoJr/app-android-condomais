package com.example.vinicius.condomais.app;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vinicius.condomais.R;
import com.example.vinicius.condomais.infra.FeaturesUtils;
import com.example.vinicius.condomais.infra.api.APIService;
import com.example.vinicius.condomais.infra.api.endpoints.TaxaCondominioEndPoint;
import com.example.vinicius.condomais.models.ItemTaxaCondominioAPIModel;
import com.example.vinicius.condomais.models.TaxaCondominioAPIModel;
import com.example.vinicius.condomais.utils.Constants;
import com.example.vinicius.condomais.utils.SecurityPreferences;
import com.example.vinicius.condomais.utils.adapters.ItemTaxaCondominioRVAdapter;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItensTaxaActivity extends AppCompatActivity {

    @BindView(R.id.rv_itens_taxa_condominio) protected RecyclerView rvItensTaxaCondominio;
    @BindView(R.id.txt_referencia_itens_taxa_condominio) protected TextView txtReferencia;
    @BindView(R.id.txt_valor_total_itens_taxa_condominio) protected TextView txtValorTotal;

    private APIService apiService;
    private long taxaCondominioSelecionada;
    private long unidadeHabitacionalSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itens_taxa);
        ButterKnife.bind(this);
        this.setTitle("Itens da Taxa de Condominio");
        setupViews();
    }

    private void setupViews() {
        apiService = new APIService(FeaturesUtils.getToken(this));
        taxaCondominioSelecionada = getIntent().getLongExtra(Constants.TAXA_CONDOMINIO_SELECIONADA, 0);
        unidadeHabitacionalSelecionada = getIntent().getLongExtra(Constants.UNIDADE_HABITACIONAL_SELECIONADA, 0);

        if (taxaCondominioSelecionada != 0)
            getItensTaxaCondominio(taxaCondominioSelecionada);
        else
            Toast.makeText(this, "Taxa não encontrada", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public Intent getParentActivityIntent() {
        Intent intent = super.getParentActivityIntent();
        intent.putExtra(Constants.UNIDADE_HABITACIONAL_SELECIONADA, unidadeHabitacionalSelecionada);
        return intent;
    }

    private void getItensTaxaCondominio(long taxaCondominioSelecionada) {
        Call<TaxaCondominioAPIModel> call = apiService.taxaCondominioEndPoint.getTaxaCondominioUnica(taxaCondominioSelecionada);

        call.enqueue(new Callback<TaxaCondominioAPIModel>() {
            @Override
            public void onResponse(Call<TaxaCondominioAPIModel> call, Response<TaxaCondominioAPIModel> response) {
                if (response.isSuccessful()){
                    exibirItensTaxa(response.body());
                }
            }

            @Override
            public void onFailure(Call<TaxaCondominioAPIModel> call, Throwable t) {
                Toast.makeText(ItensTaxaActivity.this, "Falha na conexão", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void exibirItensTaxa(TaxaCondominioAPIModel taxaCondominio) {
        txtReferencia.setText("Ref: " + taxaCondominio.getMesAno().replace("-", "/"));
        txtValorTotal.setText("Valor Total: R$" + String.valueOf(taxaCondominio.getValorAPagar()).replace(".", ","));

        ItemTaxaCondominioRVAdapter adapter = new ItemTaxaCondominioRVAdapter(this, this, taxaCondominio.getItens());

        rvItensTaxaCondominio.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        rvItensTaxaCondominio.setHasFixedSize(true);
        rvItensTaxaCondominio.setLayoutManager(linearLayoutManager);
    }
}
