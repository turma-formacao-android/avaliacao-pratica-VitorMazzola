package br.com.cast.turmaformacao.agenda;

import android.app.Application;

import br.com.cast.turmaformacao.agenda.util.ApplicationUtil;

public class AgendaManagerApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationUtil.setApplicationContext(getApplicationContext());

    }
}
