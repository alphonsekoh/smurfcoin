package edu.singaporetech.btco

import android.content.Context
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import edu.singaporetech.btco.databinding.ActivityLayoutBinding
import kotlinx.coroutines.*
import java.util.*

class BTCOActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    private lateinit var binding:ActivityLayoutBinding
    private external fun VerifyInput(diff:String, message: String )


    /**
     * Init everything needed when created.
     * - set button listeners
     * @param savedInstanceState the usual bundle of joy
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var transaction_message:String = ""
        binding.genesisButton.setOnClickListener {
            // clear text field for each press
            binding.logTextView.text =""
            // clear flag
            emptyLogs()

        }

        binding.chainButton.setOnClickListener {
            // clear text field for each press
            binding.logTextView.text =""

            emptyLogs()

            // if transaction message is empty
            if(binding.msgEditText.text.isEmpty())
                binding.logTextView.append("describe your transaction in words...\n")
            else
                transaction_message = binding.msgEditText.text.toString()

            try {
                // if block number is out of range
                if (binding.blocksEditText.text.toString()
                        .toInt() < 2 || binding.blocksEditText.text.toString().toInt() > 888
                )
                    binding.logTextView.append("blocks must be 2 to 888...\n")

            } catch (e: NumberFormatException) {
                binding.logTextView.append("blocks cannot be empty...\n")
            }

            VerifyInput(emptyLogs(), transaction_message)
        }
    }

    /**
     *  If text is empty show error on Logs
     */
    private fun emptyLogs(): String{
        try{
        // If difficulty edit text is out of range
        if(binding.difficultyEditText.text.toString().toInt() < 1 || binding.difficultyEditText.text.toString().toInt() > 10) {
            binding.logTextView.append("difficulty must be 1 to 10...\n")
            return ""
        }
        else{return binding.difficultyEditText.text.toString()}
        }catch (e: NumberFormatException){
            //If difficulty edit text is empty
            binding.logTextView.append("difficulty is empty...\n")
            return ""
        }
    }
    companion object{

        private val TAG = BTCOActivity::class.java.simpleName
        init {
            System.loadLibrary("btco")
        }
    }
}