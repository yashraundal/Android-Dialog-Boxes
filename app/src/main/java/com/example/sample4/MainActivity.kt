package com.example.sample4

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sample4.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize binding
        binding = ActivityMainBinding.inflate(layoutInflater)

        dialog = Dialog(this)
        dialog.setContentView(R.layout.custom_dialogbox)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ok = dialog.findViewById<Button>(R.id.ok)

        binding.btn1.setOnClickListener {
            val options = arrayOf("Delhi", "Mumbai", "Kolkata", "Chennai")
            AlertDialog.Builder(this)
                .setTitle("What is the capital of India?")
                .setIcon(R.drawable.outline_add_circle_24)
                .setSingleChoiceItems(options, 0) { _, which ->
                    Toast.makeText(this, "You selected ${options[which]}", Toast.LENGTH_SHORT)
                        .show()
                }
                .setPositiveButton("Submit") { _, _ -> }
                .show()
        }

        binding.btn2.setOnClickListener {
            val options = arrayOf("Kotlin", "Java", "Python", "HTML")
            val selected = booleanArrayOf(true, false, false, false)
            AlertDialog.Builder(this)
                .setTitle("Select Programming Languages")
                .setMultiChoiceItems(options, selected) { _, which, isChecked ->
                    selected[which] = isChecked
                    Toast.makeText(
                        this,
                        "${options[which]} is ${if (isChecked) "checked" else "unchecked"}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .setPositiveButton("OK") { _, _ ->
                    val result = options.filterIndexed { index, _ -> selected[index] }
                    Toast.makeText(this, "Final selection: $result", Toast.LENGTH_LONG).show()
                }
                .show()
        }

        binding.btn3.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Feedback")
                .setMessage("Do you like Android Development?")
                .setPositiveButton("Yes") { _, _ ->
                    Toast.makeText(this, "Great! Glad to hear.", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("No") { _, _ ->
                    Toast.makeText(this, "Oh, sorry to hear that.", Toast.LENGTH_SHORT).show()
                }
                .setNeutralButton("Maybe") { _, _ ->
                    Toast.makeText(this, "Keep learning!", Toast.LENGTH_SHORT).show()
                }
                .show()
        }

        binding.btn4.setOnClickListener {
            val departments = arrayOf("IT", "Computer Science", "Mechanical", "Civil")
            AlertDialog.Builder(this)
                .setTitle("Choose Department")
                .setItems(departments) { _, which ->
                    Toast.makeText(this, "You chose: ${departments[which]}", Toast.LENGTH_SHORT)
                        .show()
                }
                .show()
        }
        binding.submit.setOnClickListener {
            dialog.show()
        }
        ok.setOnClickListener {
            dialog.dismiss()
        }
    }
}