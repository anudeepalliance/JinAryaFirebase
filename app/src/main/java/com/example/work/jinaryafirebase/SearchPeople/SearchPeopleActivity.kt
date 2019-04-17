package com.example.work.jinaryafirebase.SearchPeople

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import com.example.work.jinaryafirebase.Classes.PersonList
import com.example.work.jinaryafirebase.CompanionObjects.Companion.USER_ID_NAME_KEY
import com.example.work.jinaryafirebase.CompanionObjects.Companion.usersCollection
import com.example.work.jinaryafirebase.Person.PersonListAdapter
import com.example.work.jinaryafirebase.R
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.search_people_bar_main.*
import kotlinx.android.synthetic.main.search_people_content.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import android.view.inputmethod.EditorInfo
import com.example.work.jinaryafirebase.Person.PersonActivity


class SearchPeopleActivity : AppCompatActivity() {

    private val usersCollectionRef = FirebaseFirestore.getInstance().collection(usersCollection)

    private var adapter: PersonListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_people_drawer)
        setTitle(R.string.search_people)

        setSupportActionBar(search_people_toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        configureClearButton()

        configureSearchButton()

//        setUpDefaultRecyclerView()
    }

    private fun configureClearButton() {

        person_name_search_clear.setOnClickListener {

            person_name_search_clear.visibility = INVISIBLE
            type_person_name_edit_text.setText("")
        }

    }


    private fun configureSearchButton() {

        type_person_name_edit_text.setOnClickListener {

            person_name_search_clear.visibility = VISIBLE
        }

        type_person_name_edit_text.setOnEditorActionListener { v, actionId, event ->

            if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                val searchQuery = type_person_name_edit_text.text.toString().trim()
//                setUpRecyclerViewWithSearch(searchQuery)
                setUpDefaultRecyclerView()
                adapter!!.startListening()
                baseContext.toast("search button clicked $searchQuery")

            }
            false
        }

    }


    private fun setUpDefaultRecyclerView() {

        val query = usersCollectionRef
                .limit(20)

        val options = FirestoreRecyclerOptions.Builder<PersonList>()
                .setQuery(query, PersonList::class.java)
                .build()

        adapter = PersonListAdapter(options)

        adapter!!.setOnItemClickListener(object : PersonListAdapter.OnItemClickListener {

            override fun onItemClick(documentSnapshot: DocumentSnapshot, position: Int) {

                val id = documentSnapshot.id
                baseContext.toast("Position: $position ID: $id")
                startActivity<PersonActivity>(USER_ID_NAME_KEY to id)

            }
        })

        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter

    }

    override fun onStart() {
        super.onStart()
//        adapter!!.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter!!.stopListening()
    }

}
