package com.example.vinicius.condomais.utils.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vinicius.condomais.R;
import com.example.vinicius.condomais.app.TaxasCondominioActivity;
import com.example.vinicius.condomais.models.UnidadeHabitacionalAPIModel;
import com.example.vinicius.condomais.utils.Constants;

import java.util.List;

public class UnidadeHabitacionalRVAdapter extends RecyclerView.Adapter<UnidadeHabitacionalRVAdapter.ViewHolder> {

    private Activity activity;
    private Context context;
    private List<UnidadeHabitacionalAPIModel> unidadeHabitacionalList;

    public UnidadeHabitacionalRVAdapter(Activity activity, Context context, List<UnidadeHabitacionalAPIModel> unidadeHabitacionalList) {
        this.activity = activity;
        this.context = context;
        this.unidadeHabitacionalList = unidadeHabitacionalList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtDescricaoUnidade;

        public ViewHolder(View itemView) {
            super(itemView);

            txtDescricaoUnidade = itemView.findViewById(R.id.txtDescricaoUnidadeHabitacional);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_lista_unidades_habitacionais, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final UnidadeHabitacionalAPIModel unidadeHabitacional = unidadeHabitacionalList.get(position);

        holder.txtDescricaoUnidade.setText(unidadeHabitacional.getDescricao());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, TaxasCondominioActivity.class);
                intent.putExtra(Constants.UNIDADE_HABITACIONAL_SELECIONADA, unidadeHabitacional.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return unidadeHabitacionalList.size();
    }
}
