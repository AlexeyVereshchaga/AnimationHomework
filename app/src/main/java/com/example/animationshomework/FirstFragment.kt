package com.example.animationshomework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment


class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_first, container, false)
        val button = rootView.findViewById<Button>(R.id.button_open_fragment)
        button.setOnClickListener {
            openSecondFragment()
        }
        return rootView
    }

    private fun openSecondFragment() {
        val fm = requireActivity().supportFragmentManager
        val transaction = fm.beginTransaction()

        transaction.setCustomAnimations(
            R.anim.slide_in_left,
            R.anim.slide_out_left,
            R.anim.slide_in_left,
            R.anim.slide_out_left
        )

        transaction.replace(R.id.fragment_container, SecondFragment())
            .addToBackStack(null)
            .commit()
    }
}