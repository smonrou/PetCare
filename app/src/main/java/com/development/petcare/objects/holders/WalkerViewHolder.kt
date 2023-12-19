package com.development.petcare.objects.holders

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.development.petcare.R
import com.development.petcare.objects.basics.Walker
import com.development.petcare.objects.details.CarerDetailsActivity
import com.development.petcare.objects.details.WalkerDetailsActivity
import com.development.petcare.objects.providers.CarerProvider
import com.development.petcare.objects.providers.WalkerProvider


class WalkerViewHolder(view: View):RecyclerView.ViewHolder(view){
    val walker_name:TextView = view.findViewById(R.id.walker_name)
    val walker_city:TextView = view.findViewById(R.id.walker_city)
    val walker_species:TextView = view.findViewById(R.id.walker_species)
    val cv_walker:CardView = view.findViewById(R.id.cv_walker)

    var id:Int = 0
    var age:String? = ""
    var stars: String? = ""
    var uri:String? = ""
    var walkerDescription:String? = ""
    var serviceDescription:String? = ""
    var hourPrice:String? =""
    var experience:String? = ""
    var schedule:String? = ""

    fun render(walkerModel : Walker){
        walker_name.text = walkerModel.name
        walker_city.text = walkerModel.city
        walker_species.text = walkerModel.species

        id = walkerModel.id
        age = walkerModel.age
        stars = walkerModel.stars
        uri = walkerModel.uri
        walkerDescription = walkerModel.walkerDescription
        serviceDescription = walkerModel.serviceDescription
        hourPrice = walkerModel.hourPrice
        experience = walkerModel.experience
        schedule = walkerModel.schedule

        cv_walker.setOnClickListener { toWalkerDetails() }
    }
    fun toWalkerDetails(){
        val context = walker_name.context
        val walker = WalkerProvider.WalkerList[adapterPosition]
        val intent = Intent(context, WalkerDetailsActivity::class.java)

        intent.putExtra("name", walker.name)
        intent.putExtra("id", walker.id)
        intent.putExtra("city", walker.city)
        intent.putExtra("age", walker.age)
        intent.putExtra("species", walker.species)
        intent.putExtra("stars", walker.stars)
        intent.putExtra("uri", walker.uri)
        intent.putExtra("walkerDescription", walker.walkerDescription)
        intent.putExtra("serviceDescription", walker.serviceDescription)
        intent.putExtra("hourPrice", walker.hourPrice)
        intent.putExtra("experience", walker.experience)
        intent.putExtra("schedule", walker.schedule)
        context.startActivity(intent)
    }
}