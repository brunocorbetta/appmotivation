package com.corbetta.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.corbetta.motivation.infra.MotivationConstants
import com.corbetta.motivation.R
import com.corbetta.motivation.infra.SecurityPreferences
import com.corbetta.motivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener(this)

        supportActionBar?.hide()

        verifyUserName()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_save) {
            handleSalve()
        }
    }

    private fun verifyUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.Key.USER_NAME)
        if (name != "") {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun handleSalve() {


        val name = binding.editName.text.toString()

        if (name != "") {

            //Salva o nome do usuario
            SecurityPreferences(this).storeString(MotivationConstants.Key.USER_NAME, name)

            //isso aqui que carrega outra activity
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            Toast.makeText(this, R.string.validation_mandatory_name, Toast.LENGTH_SHORT).show()
        }
    }
}