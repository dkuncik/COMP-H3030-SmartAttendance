package com.example.smartattendance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation


class StudentStart : Fragment(), View.OnClickListener {

    var navController : NavController? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_start, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.EndClass).setOnClickListener (this)
        view.findViewById<Button>(R.id.LeaveLecture).setOnClickListener (this)


    }
    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.EndClass -> navController!!.navigate(R.id.action_studentStart_to_studentLogged)
            R.id.LeaveLecture -> navController!!.navigate(R.id.action_studentStart_to_classLeaved)
        }

    }

}