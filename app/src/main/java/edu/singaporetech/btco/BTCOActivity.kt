package edu.singaporetech.btco

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import edu.singaporetech.btco.databinding.ActivityLayoutBinding
import kotlinx.coroutines.*
import java.util.*

class BTCOActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    private lateinit var binding:ActivityLayoutBinding

    /**
     * Init everything needed when created.
     * - set button listeners
     * @param savedInstanceState the usual bundle of joy
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.genesisButton.setOnClickListener {
            emptyLogs()
        }

        binding.chainButton.setOnClickListener {
            emptyLogs()
            // if transaction message is empty
            if(binding.msgEditText.text.isEmpty()){
                binding.logTextView.append("describe your transaction in words...\n")
            }

            // if block number is empty
            if(binding.blocksEditText.text.isEmpty()){
                binding.logTextView.append("blocks cannot be empty...\n")
            }

            // if block number is out of range
            if(binding.blocksEditText.text.toString().toInt() < 2 || binding.blocksEditText.text.toString().toInt() > 888){
                binding.logTextView.append("blocks must be 2 to 888...\n")
            }
        }
    }

    /**
     *  If text is empty show error on Logs
     */
    private fun emptyLogs(){
        // If difficulty edit text is empty
        if(binding.difficultyEditText.text.isEmpty()){
            binding.logTextView.append("difficulty is empty...\n")
        }
        // If difficulty edit text is out of range
        if(binding.difficultyEditText.text.toString().toInt() < 1 || binding.difficultyEditText.text.toString().toInt() > 10){
            binding.logTextView.append("difficulty must be 1 to 10...\n")
        }
    }


}