package com.white.whiterabbit.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.white.whiterabbit.R
import com.white.whiterabbit.model.db.CompanyEntity

class DetailActivity : AppCompatActivity() {

    lateinit var idStreet: TextView
    lateinit var idSuite: TextView
    lateinit var idCity: TextView
    lateinit var idzipcode: TextView
    lateinit var idphone: TextView
    lateinit var idwebsite: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        idStreet = findViewById(R.id.idstreet)
        idSuite = findViewById(R.id.idsuit)
        idCity = findViewById(R.id.idcity)
        idzipcode= findViewById(R.id.idzipcode)
        idphone = findViewById(R.id.idphone)
        idwebsite = findViewById(R.id.idwebite)

        val companyEntity = intent.getSerializableExtra("EXTRA_PEOPLE") as? CompanyEntity

        idStreet.text =  "Street : "+companyEntity!!.street
        idSuite.text = "Suit : "+companyEntity!!.suite
        idCity.text = "City : "+companyEntity!!.city
        idzipcode.text = "Zipcode : "+companyEntity!!.zipcode
        idphone.text = "Phone : "+companyEntity!!.phone
        idwebsite.text = "Website : "+companyEntity!!.website

    }
}