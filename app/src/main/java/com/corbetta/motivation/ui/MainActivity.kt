package com.corbetta.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.corbetta.motivation.infra.MotivationConstants
import com.corbetta.motivation.R
import com.corbetta.motivation.data.Mock
import com.corbetta.motivation.infra.SecurityPreferences
import com.corbetta.motivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var category = MotivationConstants.Filter.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Esconde barra de navegação
        supportActionBar?.hide()

        handleUserName()
        handleFilter(R.id.image_all)
        handleNextPharse()

        //eventos de click na view do layout
        binding.btnNewPhrase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_new_phrase) {
            handleNextPharse()
        } else if (v.id in listOf(R.id.image_all, R.id.image_happy, R.id.image_sunny)) {
            handleFilter(v.id)
        }
    }

    private fun handleNextPharse() {
        binding.textPhase.text = Mock().getPhrase(category)
    }

    private fun handleFilter(id: Int) {


        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))

        //verifica a variavel
        when (id) {
            R.id.image_all -> {

                binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
                category = MotivationConstants.Filter.ALL
            }
            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                category = MotivationConstants.Filter.SUNNY
            }
            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                category = MotivationConstants.Filter.HAPPY
            }
        }
    }

    private fun handleUserName() {
        // pega o valor salvo na useractivity e atribui para o textname na activity main
        val name = SecurityPreferences(this).getString(MotivationConstants.Key.USER_NAME)
        binding.textUserName.text = "Olá, $name !"
    }


}