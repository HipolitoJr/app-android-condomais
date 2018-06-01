package com.example.vinicius.condomais.utils.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vinicius.condomais.R;
import com.example.vinicius.condomais.app.ItensTaxaActivity;
import com.example.vinicius.condomais.models.TaxaCondominioAPIModel;
import com.example.vinicius.condomais.utils.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TaxaCondominioRVAdapter extends RecyclerView.Adapter<TaxaCondominioRVAdapter.ViewHolder> {

    private Activity activity;
    private Context context;
    private List<TaxaCondominioAPIModel> taxaCondominioList;

    public TaxaCondominioRVAdapter(Activity activity, Context context, List<TaxaCondominioAPIModel> taxaCondominioList) {
        this.activity = activity;
        this.context = context;
        this.taxaCondominioList = taxaCondominioList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtValorAPagar;
        public TextView txtDataVencimento;
        public TextView txtMesAno;
        public LinearLayout linearLayoutBgTaxa;

        public ViewHolder(View itemView) {
            super(itemView);

            txtValorAPagar = itemView.findViewById(R.id.txtValorAPagarTaxaCondominio);
            txtDataVencimento = itemView.findViewById(R.id.txtDataVencimentoTaxaCondominio);
            txtMesAno = itemView.findViewById(R.id.txtMesAnoTaxaCondominio);
            linearLayoutBgTaxa = itemView.findViewById(R.id.ll_bg_taxa_condominio);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_lista_taxas_condominio, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final TaxaCondominioAPIModel taxaCondominio = taxaCondominioList.get(position);

        holder.txtValorAPagar.setText(String.valueOf("R$"+ taxaCondominio.getValorAPagar()).replace('.', ','));
        holder.txtDataVencimento.setText("Venc: " + taxaCondominio.getDataVencimento());
        holder.txtMesAno.setText("Ref: " + taxaCondominio.getMesAno().replace("-", "/"));

        if (taxaCondominio.isPago())
            holder.linearLayoutBgTaxa.setBackgroundResource(R.color.colorBgTaxaPaga);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ItensTaxaActivity.class);
                intent.putExtra(Constants.TAXA_CONDOMINIO_SELECIONADA, taxaCondominio.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return taxaCondominioList.size();
    }
}
