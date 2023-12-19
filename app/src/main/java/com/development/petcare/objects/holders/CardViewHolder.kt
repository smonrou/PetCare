package com.development.petcare.objects.holders

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.development.petcare.R
import com.development.petcare.objects.basics.Card
import com.development.petcare.objects.details.CardDetailsActivity
import com.development.petcare.objects.providers.CardProvider

class CardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val cv_creditCard: CardView = view.findViewById(R.id.cv_creditCard)
    val txt_cardName: TextView = view.findViewById(R.id.txt_cardName)
    val txt_cardNumber: TextView = view.findViewById(R.id.txt_cardNumber)
    val txt_cardDate: TextView = view.findViewById(R.id.txt_cardDate)
    val ic_more: ImageView = view.findViewById(R.id.ic_more)
    var code = ""
    var position = ""

    fun render(cardModel: Card) {
        txt_cardName.text = cardModel.cardName
        txt_cardNumber.text = cardModel.cardNumber
        txt_cardDate.text = cardModel.date
        code = cardModel.cardCode.toString()
        position = adapterPosition.toString()
        cv_creditCard.setOnClickListener { toTargetDetails() }
        ic_more.setOnClickListener { }
    }

    private fun toTargetDetails() {
        val context = txt_cardDate.context
        val card = CardProvider.CardList[adapterPosition]
        val intent = Intent(context, CardDetailsActivity::class.java)
        intent.putExtra("position", position)
        intent.putExtra("cardName", card.cardName)
        intent.putExtra("cardNumber", card.cardNumber)
        intent.putExtra("code", card.cardCode)
        intent.putExtra("date", card.date)
        intent.putExtra("type", card.type)
        intent.putExtra("proprietary", card.proprietary)
        context.startActivity(intent)
    }
}