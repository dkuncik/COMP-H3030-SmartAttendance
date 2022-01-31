package com.smartAttendance.student

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.smartAttendance.R


class StudentStart : Fragment(), View.OnClickListener {

    var navController: NavController? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.student_class_attending, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.studentClass_Attending_ClassInfo).setOnClickListener(this)
        view.findViewById<Button>(R.id.studentClass_Attending_GeoFenceActive).setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.studentClass_Attending_ClassInfo -> navController!!.navigate(R.id.action_studentStart_to_studentLogged)
            R.id.studentClass_Attending_GeoFenceActive -> navController!!.navigate(R.id.action_studentStart_to_classLeaved)
        }

    }

}