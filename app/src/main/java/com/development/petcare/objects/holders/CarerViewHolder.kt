package com.development.petcare.objects.holders

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.development.petcare.R
import com.development.petcare.objects.basics.Carer
import com.development.petcare.objects.providers.CarerProvider
import com.development.petcare.objects.details.CarerDetailsActivity


class CarerViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val carer_name:TextView = view.findViewById(R.id.carer_name)
    val carer_city:TextView = view.findViewById(R.id.carer_city)
    val carer_species:TextView = view.findViewById(R.id.carer_city)
    val carer_profession:TextView = view.findViewById(R.id.carer_profession)
    val cv_carer:CardView = view.findViewById(R.id.cv_carer)

    var id = ""
    var gender:String? = "None"
    var workers = ""
    var uri:String? = "None"


    fun render(carerModel: Carer){
        carer_name.text = carerModel.name
        carer_city.text = carerModel.city
        carer_species.text = carerModel.species
        carer_profession.text = carerModel.profession
        id = carerModel.id.toString()
        gender = carerModel.country
        workers = carerModel.numbWorkers.toString()
        uri = carerModel.uri
        cv_carer.setOnClickListener { toCarerDetails() }
    }
    private fun toCarerDetails(){
        val context = carer_name.context
        val carer = CarerProvider.CarersList[adapterPosition]
        val intent = Intent(context, CarerDetailsActivity::class.java)
        intent.putExtra("id", carer.id)
        intent.putExtra("name", carer.name)
        intent.putExtra("city", carer.city)
        intent.putExtra("gender", carer.country)
        intent.putExtra("age", carer.numbWorkers)
        intent.putExtra("profession", carer.profession)
        intent.putExtra("species", carer.species)
        intent.putExtra("uri", carer.uri)
        context.startActivity(intent)
    }
}