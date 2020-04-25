package com.gabutproject.dessertpusher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.gabutproject.dessertpusher.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var revenue = 0
    private var dessertSold = 0

    private lateinit var revenueBinding: String
    private lateinit var amountSoldBinding: String

    // binding layout
    private lateinit var binding: ActivityMainBinding

    /**
     * simple data class contains dessertId, price of the dessert
     * and the startProductionAmount
     */
    data class Dessert(
        val imageId: Int,
        val price: Int,
        val startProductAmount: Int,
        val name: String
    )

    private val allDesserts = listOf(
        Dessert(R.drawable.cupcake, 5, 0, "cupcake"),
        Dessert(R.drawable.donut, 10, 5, "donut"),
        Dessert(R.drawable.eclair, 15, 20, "eclair"),
        Dessert(R.drawable.froyo, 30, 50, "froyo"),
        Dessert(R.drawable.gingerbread, 50, 100, "gingerbread"),
        Dessert(R.drawable.honeycomb, 100, 200, "honeycomb"),
        Dessert(R.drawable.icecreamsandwich, 500, 500, "ice cream sandwich"),
        Dessert(R.drawable.jellybean, 1000, 1000, "jellybean"),
        Dessert(R.drawable.kitkat, 2000, 2000, "kitkat"),
        Dessert(R.drawable.lollipop, 3000, 4000, "lollipop"),
        Dessert(R.drawable.marshmallow, 4000, 8000, "marshmallow"),
        Dessert(R.drawable.nougat, 5000, 16000, "nougat"),
        Dessert(R.drawable.oreo, 6000, 20000, "oreo")
    )

    private var currentDessert = allDesserts[0]


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.dessertButton.setOnClickListener {
            onDessertClick()
        }

        updateBindingData()

        binding.revenue = revenueBinding
        binding.amountSold = amountSoldBinding
        binding.currentDessert = currentDessert.name

        binding.dessertButton.setImageResource(currentDessert.imageId)
    }

    private fun updateBindingData() {
        revenueBinding = getString(R.string.bakery_current_revenue, revenue)
        amountSoldBinding = getString(R.string.bakery_sold_cake, dessertSold)
    }

    private fun onDessertClick() {
        revenue += currentDessert.price
        dessertSold++

        updateBindingData()

        binding.revenue = revenueBinding
        binding.amountSold = amountSoldBinding

        showCurrentDessert()
    }

    private fun showCurrentDessert() {
        var newDessert = allDesserts[0]

        for (dessert in allDesserts) {
            if (dessertSold >= dessert.startProductAmount) {
                // update the current Dessert
                newDessert = dessert
            } else break
        }

        if (newDessert != currentDessert) {
            currentDessert = newDessert

            binding.dessertButton.setImageResource(newDessert.imageId)
            binding.currentDessert = newDessert.name
            Toast.makeText(this, "New menu!", Toast.LENGTH_SHORT).show()
        }
    }
}
