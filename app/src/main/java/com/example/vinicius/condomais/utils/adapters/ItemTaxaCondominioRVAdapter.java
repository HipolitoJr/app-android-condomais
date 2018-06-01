package com.example.vinicius.condomais.utils.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vinicius.condomais.R;
import com.example.vinicius.condomais.app.ItensTaxaActivity;
import com.example.vinicius.condomais.models.ItemTaxaCondominioAPIModel;

import java.util.List;

public class ItemTaxaCondominioRVAdapter extends RecyclerView.Adapter<ItemTaxaCondominioRVAdapter.ViewHolder> {

    private Activity activity;
    private Context context;
    private List<ItemTaxaCondominioAPIModel> itemTaxaCondominioList;

    public ItemTaxaCondominioRVAdapter(Activity activity, Context context, List<ItemTaxaCondominioAPIModel> itemTaxaCondominioList) {
        this.activity = activity;
        this.context = context;
        this.itemTaxaCondominioList = itemTaxaCondominioList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtDescricaoItemTaxa;
        private TextView txtValorItemTaxa;

        public ViewHolder(View itemView) {
            super(itemView);

            txtDescricaoItemTaxa = itemView.findViewById(R.id.txt_descricao_item_taxa_condominio);
            txtValorItemTaxa = itemView.findViewById(R.id.txt_valor_item_taxa_condominio);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_lista_itens_taxa_condominio, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemTaxaCondominioAPIModel itemTaxaCondominio = itemTaxaCondominioList.get(position);

        holder.txtDescricaoItemTaxa.setText(itemTaxaCondominio.getDescricao());
        holder.txtValorItemTaxa.setText("R$" + String.valueOf(itemTaxaCondominio.getValor()).replace(".", ","));
    }

    @Override
    public int getItemCount() {
        return itemTaxaCondominioList.size();
    }
}
