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
import com.example.vinicius.condomais.app.UnidadesHabitacionaisActivity;
import com.example.vinicius.condomais.models.GrupoHabitacionalAPIModel;
import com.example.vinicius.condomais.utils.Constants;

import java.util.List;
import java.util.zip.Inflater;

public class GrupoHabitacionalRVAdapter extends RecyclerView.Adapter<GrupoHabitacionalRVAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private List<GrupoHabitacionalAPIModel> grupoHabitacionalList;

    public GrupoHabitacionalRVAdapter(Context context, Activity activity, List<GrupoHabitacionalAPIModel> grupoHabitacionalList) {
        this.context = context;
        this.activity = activity;
        this.grupoHabitacionalList = grupoHabitacionalList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtDescricaoGrupoHabitacional;

        public ViewHolder(View itemView) {
            super(itemView);

            txtDescricaoGrupoHabitacional = itemView.findViewById(R.id.txtDescricaoGrupoHabitacional);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_lista_grupos_habitacionais, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final GrupoHabitacionalAPIModel grupoHabitacional = grupoHabitacionalList.get(position);

        holder.txtDescricaoGrupoHabitacional.setText(grupoHabitacional.getDescricao());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, UnidadesHabitacionaisActivity.class);
                intent.putExtra(Constants.GRUPO_HABITACIONAL_SELECIONADO, grupoHabitacional.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return grupoHabitacionalList.size();
    }
}
