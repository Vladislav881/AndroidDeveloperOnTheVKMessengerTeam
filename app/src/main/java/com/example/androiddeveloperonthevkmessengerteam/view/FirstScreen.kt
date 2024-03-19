package com.example.androiddeveloperonthevkmessengerteam.view

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.androiddeveloperonthevkmessengerteam.R

class FirstScreen: Fragment(R.layout.first_screen) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageButton>(R.id.next_first).setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                .navigate(R.id.action_to_second)
        }
    }
}