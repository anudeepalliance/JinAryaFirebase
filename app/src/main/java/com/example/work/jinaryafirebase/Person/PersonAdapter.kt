import android.app.Person
import android.support.annotation.NonNull
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.work.jinaryafirebase.Classes.PersonList
import com.example.work.jinaryafirebase.R
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class PersonAdapter
            (@NonNull options:FirestoreRecyclerOptions<PersonList>)
            :FirestoreRecyclerAdapter<PersonList,
            PersonAdapter.PersonListHolder>(options) {

    override fun onBindViewHolder
            (@NonNull holder:PersonListHolder,
             position:Int,
             @NonNull model:PersonList) {

        holder.personName.text = model.personListName
        holder.personLocation.text = model.personListCity

    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent:ViewGroup, viewType:Int)
            : PersonListHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.person_list_item, parent, false)
        return PersonListHolder(v)
    }

    inner class PersonListHolder(itemView:View)
        :RecyclerView.ViewHolder(itemView) {

        var personName:TextView
        var personLocation:TextView

        init {
            personName = itemView.findViewById(R.id.person_list_name)
            personLocation = itemView.findViewById(R.id.person_list_location)
        }
    }
}