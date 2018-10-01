package com.example.work.jinaryafirebase.Person

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.work.jinaryafirebase.Classes.PersonList
import com.example.work.jinaryafirebase.CompanionObjects
import com.example.work.jinaryafirebase.R
import com.example.work.jinaryafirebase.R.id.home_profile_photo
import com.example.work.jinaryafirebase.SearchPeople.SearchPeopleActivity

import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.nav_header_main.*

class PersonListAdapter(PersonLists:FirestoreRecyclerOptions<PersonList>)
    :FirestoreRecyclerAdapter<PersonList, PersonListAdapter.PersonListHolder>(PersonLists) {


    override fun onCreateViewHolder(parent:ViewGroup, viewType:Int): PersonListHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.person_list_item,
                parent, false)

        return PersonListHolder(v)
    }

    override fun onBindViewHolder( holder:PersonListHolder, position:Int, model:PersonList) {

        holder.personUserId = model.userId

        FirebaseStorage.getInstance().reference
                .child(model.userId).child(CompanionObjects
                .USER_PROFILE_PROFILE_PHOTO_PATH).downloadUrl.addOnSuccessListener {

            val downloadUrl = it.toString()

            Glide.with(holder.personImageView)
                    .load(downloadUrl)
                    .into(holder.personImageView)


        }.addOnFailureListener {

        }

        holder.personName.text = model.userName
        holder.personLocation.text = model.currentCity

    }


    class PersonListHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        var personImageView : ImageView
        var personUserId : String = ""
        var personName:TextView
        var personLocation:TextView

        init {
            personImageView = itemView.findViewById(R.id.person_list_profile_image)
            personName = itemView.findViewById(R.id.person_list_name)
            personLocation = itemView.findViewById(R.id.person_list_location)
        }
    }
}