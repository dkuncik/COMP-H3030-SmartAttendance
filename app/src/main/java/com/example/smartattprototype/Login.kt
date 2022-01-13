package com.example.smartattprototype

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation

class Login : Fragment() , View.OnClickListener {

    var navController : NavController? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.LecturerBTN).setOnClickListener (this)
        view.findViewById<Button>(R.id.studentLogged).setOnClickListener (this)


    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.LecturerBTN -> navController!!.navigate(R.id.action_login_to_lecturerLogged)
            R.id.studentLogged -> navController!!.navigate(R.id.action_login_to_studentLogged)
        }

    }

}