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
    private external fun VerifyInput(input: Int)
    private var verifyFlag:Int = 0

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
            // clear text field for each press
            binding.logTextView.text =""
            // clear flag
            verifyFlag = 0
            emptyLogs()

        }

        binding.chainButton.setOnClickListener {
            // clear text field for each press
            binding.logTextView.text =""
            // clear flag
            verifyFlag = 0
            emptyLogs()
            if(emptyLogs())
                verifyFlag++
            // if transaction message is empty
            if(binding.msgEditText.text.isEmpty())
                binding.logTextView.append("describe your transaction in words...\n")
            else
                verifyFlag++

            try {
                // if block number is out of range
                if (binding.blocksEditText.text.toString()
                        .toInt() < 2 || binding.blocksEditText.text.toString().toInt() > 888
                )
                    binding.logTextView.append("blocks must be 2 to 888...\n")
                else
                    verifyFlag++
            } catch (e: NumberFormatException) {
                binding.logTextView.append("blocks cannot be empty...\n")
            }
            Log.d(TAG, "verifyFlag: $verifyFlag")
            VerifyInput(verifyFlag)
        }
    }

    /**
     *  If text is empty show error on Logs
     */
    private fun emptyLogs(): Boolean{
        try{
        // If difficulty edit text is out of range
        if(binding.difficultyEditText.text.toString().toInt() < 1 || binding.difficultyEditText.text.toString().toInt() > 10) {
            binding.logTextView.append("difficulty must be 1 to 10...\n")
            return false
        }
        else{return true}
        }catch (e: NumberFormatException){
            //If difficulty edit text is empty
            binding.logTextView.append("difficulty is empty...\n")
            return false
        }
    }
    companion object{

        private val TAG = BTCOActivity::class.java.simpleName
        init {
            System.loadLibrary("btco")
        }
    }
}