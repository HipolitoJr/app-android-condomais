package com.example.vinicius.condomais.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.vinicius.condomais.R;
import com.example.vinicius.condomais.infra.api.APIService;
import com.example.vinicius.condomais.models.GrupoHabitacionalAPIModel;
import com.example.vinicius.condomais.utils.Constants;
import com.example.vinicius.condomais.utils.SecurityPreferences;
import com.example.vinicius.condomais.utils.adapters.GrupoHabitacionalRVAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.rv_grupos_habitacionais) protected RecyclerView rvGruposHabitacionais;

    private SecurityPreferences securityPreferences;
    private APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        this.setTitle("Condomais");
        ButterKnife.bind(this);

        setupViews();
    }

    public void setupViews(){
        initNavigation();
        securityPreferences = new SecurityPreferences(this);
        apiService = new APIService(getToken());

        getGruposHabitacionais();
    }

    private void getGruposHabitacionais() {
        Call<List<GrupoHabitacionalAPIModel>> call = apiService.grupoHabitacionalEndPoint.getGruposHabitacionais();

        call.enqueue(new Callback<List<GrupoHabitacionalAPIModel>>() {
            @Override
            public void onResponse(Call<List<GrupoHabitacionalAPIModel>> call, Response<List<GrupoHabitacionalAPIModel>> response) {
                if (response.isSuccessful()){
                    exibirLista(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<GrupoHabitacionalAPIModel>> call, Throwable t) {
                Toast.makeText(DrawerActivity.this, "Falha na conexão", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void exibirLista(List<GrupoHabitacionalAPIModel> grupoHabitacionalList) {
        GrupoHabitacionalRVAdapter grupoHabitacionalRVAdapter = new GrupoHabitacionalRVAdapter(this, this, grupoHabitacionalList);

        rvGruposHabitacionais.setAdapter(grupoHabitacionalRVAdapter);

        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        rvGruposHabitacionais.setHasFixedSize(true);
        rvGruposHabitacionais.setLayoutManager(gridLayoutManager);
    }

    private String getToken() {
        return securityPreferences.getSavedString(Constants.TOKEN);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_grupos_habitacionais) {
            startActivity(new Intent(this, DrawerActivity.class));
        } else if (id == R.id.nav_unidades_habitacionais) {

        } else if (id == R.id.nav_despesas) {

        } else if (id == R.id.nav_sobre) {
            startActivity(new Intent(this, SobreActivity.class));

        } else if (id == R.id.nav_sair) {
            securityPreferences.limpar();
            startActivity(new Intent(DrawerActivity.this,LoginActivity.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void initNavigation(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
}
