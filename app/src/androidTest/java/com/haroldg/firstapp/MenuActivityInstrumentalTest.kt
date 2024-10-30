package com.haroldg.firstapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.haroldg.firstapp.IMC_Calculator.IMCActivity
import com.haroldg.firstapp.ListaApp.ListaAppActivity
import com.haroldg.firstapp.Menu.MenuActivity
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MenuActivityInstrumentalTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MenuActivity::class.java)

    @Before
    fun setup() {
        Intents.init() // Inicializa el manejo de Intents
    }

    @After
    fun tearDown() {
        Intents.release() // Libera los Intents después de las pruebas
    }

    @Test
    fun testNavigateToSaludoApp() {
        // Simula el clic en el botón "btnSaludar"
        onView(withId(R.id.btnSaludar)).perform(click())
        // Verifica que se inicie la actividad HomeActivity
        Intents.intended(IntentMatchers.hasComponent(HomeActivity::class.java.name))
    }

    @Test
    fun testNavigateToIMCActivity() {
        // Simula el clic en el botón "btnIMC"
        onView(withId(R.id.btnIMC)).perform(click())
        // Verifica que se inicie la actividad IMCActivity
        Intents.intended(IntentMatchers.hasComponent(IMCActivity::class.java.name))
    }

    @Test
    fun testNavigateToListaAppActivity() {
        // Simula el clic en el botón "btnListaAPP"
        onView(withId(R.id.btnListaAPP)).perform(click())
        // Verifica que se inicie la actividad ListaAppActivity
        Intents.intended(IntentMatchers.hasComponent(ListaAppActivity::class.java.name))
    }
}