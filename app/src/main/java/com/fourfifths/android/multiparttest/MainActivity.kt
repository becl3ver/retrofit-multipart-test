package com.fourfifths.android.multiparttest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import com.fourfifths.android.multiparttest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = this.javaClass.simpleName
    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel

    private val getImage =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.data.let { uri ->
                    viewModel.setUri(uri)
                }
            } else {
                Log.d(TAG, "failed to get image")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        binding.btnGetImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            intent.putExtra("crop", true)
            intent.action = Intent.ACTION_GET_CONTENT
            getImage.launch(intent)
        }

        binding.btnSignUp.setOnClickListener {
            viewModel.signUp(contentResolver)
        }

        binding.btnEmailComplete.setOnClickListener {
            viewModel.completeEmailCheck()
        }

        binding.btnSignIn.setOnClickListener {
            viewModel.signIn()
        }

        binding.btnCreateStudy.setOnClickListener {
            viewModel.createStudy()
        }

        binding.btnReissue.setOnClickListener {
            viewModel.getTemporaryPassword()
        }

        viewModel.emailCheckResult.observe(this, Observer {
            if (it) {
                Log.e(TAG, "????????? ?????? ??????")
                Toast.makeText(this, "????????? ?????? ??????", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.loginResult.observe(this, Observer {
            if(it != null) {
                Log.d(TAG, it.toString())
                Toast.makeText(this, "????????? ??????", Toast.LENGTH_SHORT).show()

                // TODO : ????????? ?????? ??????????????????, ?????? ???????????? ?????? ???????????? ??????
            }
        })
    }
}