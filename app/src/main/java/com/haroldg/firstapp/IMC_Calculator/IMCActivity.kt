package com.haroldg.firstapp.IMC_Calculator

import android.icu.text.DecimalFormat
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.haroldg.firstapp.R

class IMCActivity : AppCompatActivity() {

    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var defaultPeso:Int = 50
    private var defaultEdad:Int = 18

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var valAlt:TextView
    private lateinit var _slider:RangeSlider
    private lateinit var btnMenosPeso: FloatingActionButton
    private lateinit var  btnMasPeso:FloatingActionButton
    private lateinit var txtPeso:TextView
    private lateinit var btnMenosEdad:FloatingActionButton
    private lateinit var btnMasEdad:FloatingActionButton
    private lateinit var txtEdad:TextView

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imcactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponents()
        initListeners()
        initUI()
    }

    private fun initComponents() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        valAlt = findViewById(R.id.valorAlt)
        _slider = findViewById(R.id.slider)
        txtPeso = findViewById(R.id.valPeso)
        btnMenosPeso = findViewById(R.id.btnMenosPeso)
        btnMasPeso = findViewById(R.id.btnMasPeso)
        txtEdad = findViewById(R.id.valEdad)
        btnMenosEdad = findViewById(R.id.btnMenosEdad)
        btnMasEdad = findViewById(R.id.btnMasEdad)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun initListeners() {
        viewMale.setOnClickListener {
            changeGender(true)
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            changeGender(false)
            setGenderColor()
        }
        _slider.addOnChangeListener{_,value,_ ->
            val df = DecimalFormat("#.##")
            val result = df.format(value)
            valAlt.text = String.format("%s cm", result)
        }
        btnMenosPeso.setOnClickListener{
            defaultPeso --
            setPeso()
        }
        btnMasPeso.setOnClickListener{
            defaultPeso++
            setPeso()
        }
        btnMenosEdad.setOnClickListener{
            defaultEdad --
            setEdad()
        }
        btnMasEdad.setOnClickListener{
            defaultEdad ++
            setEdad()
        }
    }


    private fun changeGender(isMale: Boolean) {
        if (isMale) {
            isMaleSelected = true
            isFemaleSelected = false
        } else {
            isMaleSelected = false
            isFemaleSelected = true
        }
    }

    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    private fun getBackgroundColor(isSelectedComponent:Boolean): Int {
        val colorReference = if(isSelectedComponent){
            R.color.background_component_selected
        }else{
            R.color.background_component
        }
        return ContextCompat.getColor(this, colorReference)
    }

    private fun setPeso(){
        txtPeso.text = getString(R.string.peso_text, defaultPeso)
    }

    private fun setEdad() {
        txtEdad.text = getString(R.string.edad_text, defaultEdad)
    }

    private fun initUI() {
        setGenderColor()
        setPeso()
    }

}