package com.haroldg.firstapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.haroldg.firstapp.ListaApp.ListaAppActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListaAppActivityInstrumentalTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(ListaAppActivity::class.java)

    @Test
    fun testShowDialogOnFabClick(){
        onView(withId(R.id.fabButton)).check(matches(isDisplayed())).perform(click())
        onView(withId(R.id.edTextTask)).check(matches(isDisplayed()))
    }

    @Test
    fun testAddTask(){
        onView(withId(R.id.fabButton)).perform(click())
        onView(withId(R.id.edTextTask)).perform(typeText("Tarea de prueba"), closeSoftKeyboard())
        onView(withId(R.id.rgCategories)).perform(click())
        onView(withId(R.id.btnAgregarTask)).perform(click())
        onView(withText("Tarea de prueba")).check(matches(isDisplayed()))
    }
}