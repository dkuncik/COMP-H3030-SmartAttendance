package com.example.smartattendance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation


class LeturerStart : Fragment() , View.OnClickListener{

    var navController : NavController? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leturer_start, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.ContinueLecture).setOnClickListener (this)
        view.findViewById<Button>(R.id.EndLecture).setOnClickListener (this)


    }
    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.ContinueLecture -> navController!!.navigate(R.id.action_leturerStart_to_lecturePaused)
            R.id.EndLecture -> navController!!.navigate(R.id.action_leturerStart_to_lecturerLogged2)
        }

    }

}