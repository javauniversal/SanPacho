package com.poocode.sanpacho.dark;

import android.content.Intent;
import android.os.Bundle;

import com.poocode.sanpacho.Activities.Settings;
import com.poocode.sanpacho.Fragments.FragmentCalendarView;
import com.poocode.sanpacho.Fragments.FragmentEmergencia;
import com.poocode.sanpacho.Fragments.FragmentIndex;
import com.poocode.sanpacho.R;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;
import it.neokree.materialnavigationdrawer.elements.listeners.MaterialAccountListener;


public class Accounts extends MaterialNavigationDrawer implements MaterialAccountListener {

    @Override
    public void init(Bundle savedInstanceState) {
        // add accounts
        MaterialAccount account = new MaterialAccount(this.getResources(), "San Pacho", "Las fiesta del Choco", R.drawable.santo, R.drawable.fondo_menu);
        this.addAccount(account);

        //MaterialAccount account2 = new MaterialAccount(this.getResources(),"Hatsune Miky","hatsune.miku@example.com",R.drawable.photo2,R.drawable.mat2);
        //this.addAccount(account2);

        // set listener
        this.setAccountListener(this);

        // create sections
        this.addSection(newSection("Calendario",R.drawable.ic_event_note_black_48dp, new FragmentCalendarView()));
        this.addSection(newSection("Galeía de imágenes",R.drawable.ic_photo_library_black_48dp, new FragmentIndex()));
        this.addSection(newSection("Lugares", R.drawable.ic_pin_drop_black_48dp, new FragmentIndex()));
        this.addSection(newSection("El bunde en vivo", R.drawable.ic_live_tv_black_48dp, new FragmentIndex()));
        this.addSection(newSection("Guía hotelera", R.drawable.ic_domain_black_48dp, new FragmentIndex()));
        this.addSection(newSection("Consulta ruta de recorrido", R.drawable.ic_map_black_48dp, new FragmentIndex()));
        this.addSection(newSection("Ver donde esta el bunde", R.drawable.ic_surround_sound_black_48dp, new FragmentIndex()));
        this.addSection(newSection("Notícias", R.drawable.ic_forum_black_24dp, new FragmentIndex()));
        this.addSection(newSection("Teléfono de servicios", R.drawable.ic_local_phone_black_24dp, new FragmentEmergencia()));

        // create bottom section
        this.addBottomSection(newSection("Cerrar", R.mipmap.ic_settings_black_24dp, new Intent(this, Settings.class)));
    }

    @Override
    public void onAccountOpening(MaterialAccount materialAccount) {

    }

    @Override
    public void onChangeAccount(MaterialAccount materialAccount) {

    }
}
