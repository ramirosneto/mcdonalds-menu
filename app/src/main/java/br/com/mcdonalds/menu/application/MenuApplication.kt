package br.com.mcdonalds.menu.application

import android.app.Application
import br.com.mcdonalds.menu.di.MenuModule
import org.koin.core.context.GlobalContext.startKoin

class MenuApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(MenuModule.instance)
        }
    }
}
