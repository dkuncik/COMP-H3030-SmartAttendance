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


class ClassLeaved : Fragment(), View.OnClickListener {
    var navController: NavController? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.student_class_left, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.EnterAgain).setOnClickListener(this)
        view.findViewById<Button>(R.id.leaveClass).setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.EnterAgain -> navController!!.navigate(R.id.action_classLeaved_to_studentStart)
            R.id.leaveClass -> navController!!.navigate(R.id.action_classLeaved_to_studentLogged)
        }

    }

}