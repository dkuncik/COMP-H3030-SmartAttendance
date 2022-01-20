package com.smartAttendance.lecturer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.smartAttendance.R


class LecturerLogged : Fragment(), View.OnClickListener {
    var navController: NavController? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lecturer_logged, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.StartLecture).setOnClickListener(this)
        view.findViewById<Button>(R.id.SettingsLecturer).setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.StartLecture -> navController!!.navigate(R.id.action_lecturerLogged_to_leturerStart)
            R.id.SettingsLecturer -> navController!!.navigate(R.id.action_lecturerLogged_to_login)

        }

    }
}