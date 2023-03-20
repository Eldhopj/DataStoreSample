package com.example.datastoresample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.datastoresample.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject lateinit var dao: DataStoreDao
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setName()
        observeName()
    }

    private fun setName() {
        binding.personNameEt.doAfterTextChanged {
            lifecycleScope.launch {
                dao.setName(it.toString())
            }
        }
    }

    private fun observeName() {
        lifecycleScope.launch {
            dao.name.flowWithLifecycle(lifecycle).collect {
                binding.personNameTv.text = it
            }
        }
    }
}
